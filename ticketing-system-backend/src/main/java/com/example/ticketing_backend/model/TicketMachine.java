package com.example.ticketing_backend.model;

import java.util.ArrayList;
import java.util.List;

public class TicketMachine {
    private int totalTickets;         // Current ticket count
    private final int maxTicketCapacity;  // Max ticket capacity
    private int totalRetrievedTickets; //Retrived ticket amount by customers
    private final int ticketReleaseRate;  // Rate at which vendors add tickets
    private final int customerRetrievalRate; // Rate at which customers consume tickets
    private final List<String> transactionLog = new ArrayList<>(); // Log for transactions

    private volatile boolean isRunning = true; // To control thread lifecycle

    // Constructor (Removed maxTicketsToProcess)
    public TicketMachine(int initialTickets,int maxTicketCapacity, int ticketReleaseRate, int customerRetrievalRate) {
        this.totalTickets = initialTickets;
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalRetrievedTickets = 0;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    // Producer: Vendor adds tickets
    public synchronized void addTickets() {
        while (isRunning && totalTickets + ticketReleaseRate > maxTicketCapacity) {
            try {
                String message = "Ticket pool full! Vendor is waiting to add tickets...";
                transactionLog.add(message);
                wait(); // Wait until there's space
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        if (!isRunning) return; // Exit if system is stopped

        totalTickets += ticketReleaseRate;

        // Log the transaction
        String logEntry = Thread.currentThread().getName() + " added " + ticketReleaseRate + " tickets. CURRENT TICKETS: " + totalTickets;
        System.out.println(logEntry);
        transactionLog.add(logEntry); // Add to the transaction log
        notifyAll(); // Notify consumers
    }

    // Consumer: Customer retrieves tickets
    public synchronized void retrieveTickets() {
        while (isRunning && totalTickets < customerRetrievalRate) {
            try {
                String message = "Not enough tickets available to retrive in the store! Customer is waiting...";
                transactionLog.add(message);
                wait(); // Wait until tickets are available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Exit gracefully if interrupted
            }
        }
        if (!isRunning) return; // Exit if system is stopped
        totalTickets -= customerRetrievalRate;
        totalRetrievedTickets += customerRetrievalRate;
        // Log the transaction
        String logEntry = Thread.currentThread().getName() + " retrieved " + customerRetrievalRate + " tickets. TICKETS LEFT: " + totalTickets;
        System.out.println(logEntry);
        transactionLog.add(logEntry);
        notifyAll(); // Signal producers
    }

    // Stop the system
    public synchronized void stop() {
        isRunning = false;
        notifyAll(); // Notify all threads to exit
        String totalTicketMsg = "\n TOTAL RETRIVED TICKETS BY THE CUSTOMERS: " + totalRetrievedTickets;
        transactionLog.add(totalTicketMsg);
    }

    // Log all transactions (prints to console)
    public synchronized void printTransactionLog() {
        System.out.println("Transaction Log:");
        transactionLog.forEach(System.out::println);
    }

    // Get all transactions as a string
    public synchronized String getTransactionLog() {
        StringBuilder log = new StringBuilder();
        for (String entry : transactionLog) {
            log.append(entry).append("\n");
        }
        return log.toString();
    }

    // Getter for total tickets
    public synchronized int getTotalTickets() {
        return totalTickets;
    }

    // Getter for isRunning flag
    public synchronized boolean isRunning() {
        return isRunning;
    }
}
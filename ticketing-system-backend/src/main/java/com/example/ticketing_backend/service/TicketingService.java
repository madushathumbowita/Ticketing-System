package com.example.ticketing_backend.service;

import com.example.ticketing_backend.model.TicketMachine;
import com.example.ticketing_backend.model.Vendor;
import com.example.ticketing_backend.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class TicketingService {

    private TicketMachine ticketMachine;      // Class-level TicketMachine
    private Thread vendorThread;              // Class-level vendor thread
    private Thread customerThread;            // Class-level customer thread

    // Start the Ticketing System
    public String startTicketingSystem(int initialTickets, int maxTicketCapacity, int ticketReleaseRate,
                                       int customerRetrievalRate) {

        System.out.println("Welcome to the Ticket System!");

        // Initialize the TicketMachine
        ticketMachine = new TicketMachine(initialTickets, maxTicketCapacity, ticketReleaseRate, customerRetrievalRate);
        // Create Vendor and Customer objects
        Vendor vendor = new Vendor(ticketMachine);
        Customer customer = new Customer(ticketMachine);
        // Initialize threads
        vendorThread = new Thread(vendor, "Vendor");
        customerThread = new Thread(customer, "Customer");
        // Start the threads
        System.out.println("Starting ticket operations...");

        vendorThread.start();
        customerThread.start();

        return "Ticketing system started successfully";
    }

    // Stop the Ticketing System
    public String stopTicketingSystem() {
        if (ticketMachine != null && ticketMachine.isRunning()) {
            System.out.println("Stopping the ticketing system...");
            ticketMachine.stop(); // Stop the system
            // Wait for threads to terminate
            try {
                if (vendorThread != null) vendorThread.join();
                if (customerThread != null) customerThread.join();
                System.out.println("Ticketing system stopped successfully.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error while stopping the system: " + e.getMessage());
                return "Error while stopping the ticketing system.";
            }
            // Get and return the final transaction log
            return "SYSTEM STOPPED.\n" + ticketMachine.getTransactionLog();
        } else {
            return "Ticketing system is not running.";
        }
    }
    public String resetTicketingSystem() {
        if (ticketMachine != null && ticketMachine.isRunning()) {
            ticketMachine.stop();
            try {
                if (vendorThread != null) vendorThread.join();
                if (customerThread != null) customerThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "Error during reset: " + e.getMessage();
            }
        }
        ticketMachine = null;
        vendorThread = null;
        customerThread = null;
        return "Ticketing system reset successfully.";
    }
    public String getTransactionLog() {
        if (ticketMachine != null) {
            return ticketMachine.getTransactionLog();
        } else {
            return "Ticketing system is not running.";
        }
    }
}

package com.example.ticketing_backend.model;

public class Customer implements Runnable {
    private final TicketMachine ticketMachine;

    public Customer(TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
    }

    @Override
    public void run() {
        while (ticketMachine.isRunning()) {
            ticketMachine.retrieveTickets();
            try {
                Thread.sleep(1000); // Simulate the customer retrieval rate
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

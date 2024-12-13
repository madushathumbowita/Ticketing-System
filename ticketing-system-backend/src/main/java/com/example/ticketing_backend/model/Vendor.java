package com.example.ticketing_backend.model;

public class Vendor implements Runnable {
    private final TicketMachine ticketMachine;

    public Vendor(TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
    }

    @Override
    public void run() {
        while (ticketMachine.isRunning()) {
            ticketMachine.addTickets();
            try {
                Thread.sleep(1000); // Simulate the vendor adding tickets rate
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

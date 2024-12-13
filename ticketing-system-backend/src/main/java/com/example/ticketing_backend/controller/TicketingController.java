package com.example.ticketing_backend.controller;
import com.example.ticketing_backend.service.TicketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticketing")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from the frontend
public class TicketingController {

    @Autowired
    private TicketingService ticketingService;
 
    // POST method to start ticketing system
    @PostMapping("/start")
    public String startTicketing(@RequestParam int initialTickets,
                                 @RequestParam int maxTicketCapacity,
                                 @RequestParam int ticketReleaseRate,
                                 @RequestParam int customerRetrievalRate) {
        return ticketingService.startTicketingSystem(initialTickets, maxTicketCapacity, ticketReleaseRate,
                customerRetrievalRate);
    }

    @PostMapping("/stop")
    public String stopTicketing(){
        return ticketingService.stopTicketingSystem();
    }
    @PostMapping("/reset")
    public String resetTicketing(){
        return ticketingService.resetTicketingSystem();
    }
    @GetMapping("/log")
    public String getTransactionLog() {
        if (ticketingService != null) {
            return ticketingService.getTransactionLog();
        }
        return "No transaction log available. Start the system first.";
    }
}
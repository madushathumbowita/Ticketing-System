package com.example.ticketing_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class})
public class TicketingBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketingBackendApplication.class, args);
    }
}
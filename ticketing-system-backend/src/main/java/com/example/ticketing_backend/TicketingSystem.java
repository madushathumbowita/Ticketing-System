// import java.util.Scanner;

// public class TicketingSystem {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.println("Welcome to the Ticket System CLI!");

//         // Get user input for system configuration
//         System.out.print("Enter the initial number of tickets: ");
//         int initialTickets = scanner.nextInt();

//         System.out.print("Enter the maximum ticket capacity: ");
//         int maxTicketCapacity = scanner.nextInt();

//         System.out.print("Enter the ticket release rate (tickets added per second): ");
//         int ticketReleaseRate = scanner.nextInt();

//         System.out.print("Enter the customer retrieval rate (tickets retrieved per second): ");
//         int customerRetrievalRate = scanner.nextInt();

//         System.out.print("Enter the maximum tickets to process before stopping: ");
//         int maxTicketsToProcess = scanner.nextInt();

//         // Create the TicketMachine
//         TicketMachine ticketMachine = new TicketMachine(initialTickets, maxTicketCapacity, ticketReleaseRate, customerRetrievalRate, maxTicketsToProcess);

//         // Create Vendor and Customer objects (which are Runnable)
//         Vendor vendor = new Vendor(ticketMachine);
//         Customer customer = new Customer(ticketMachine);

//         // Threads for vendors and customers
//         Thread vendorThread = new Thread(vendor, "Vendor");
//         Thread customerThread = new Thread(customer, "Customer");

//         // Start Command
//         while (true) {
//             System.out.println("Type a command: [start]");
//             String command = scanner.next().toLowerCase();

//             if ("start".equals(command)) {
//                 System.out.println("Starting ticket operations...");
//                 vendorThread.start();
//                 customerThread.start();
//                 break; // Exit CLI loop after starting
//             } else {
//                 System.out.println("Invalid command. Type 'start' to begin.");
//             }
//         }

//         // Wait for threads to finish (stop when max tickets processed)
//         try {
//             vendorThread.join(); // Wait for vendor thread to finish
//             customerThread.join(); // Wait for customer thread to finish
//             System.out.println("Ticket operations completed.");
//         } catch (InterruptedException e) {
//             Thread.currentThread().interrupt(); // Handle interruption
//         }

//         System.out.println("Total tickets sold: "+ ticketMachine.maxTicketsToProcess);
//         // Print transaction log after completion
//         ticketMachine.printTransactionLog();

//         // Close scanner to prevent resource leak
//         scanner.close();
//     }
// }

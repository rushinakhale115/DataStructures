import java.util.Scanner;
public class Assignment5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RequestQueue requestQueue = new RequestQueue();
// Simple menu to interact with the system
        while (true) {
            System.out.println("\nCall Center System:");
            System.out.println("1. Add new request");
            System.out.println("2. Process next request");
            System.out.println("3. Show pending requests");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
// Add new request
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter service requirement: ");
                    String serviceRequirement = scanner.nextLine();
                    System.out.print("Enter contact number: ");
                    int contact = scanner.nextInt();
                    requestQueue.addRequest(new CustomerRequest(name, contact,
                            serviceRequirement));
                    break;
                case 2:
// Process next request
                    requestQueue.processNextRequest();
                    break;
                case 3:
// Show pending requests
                    requestQueue.showPendingRequests();
                    break;
                case 4:
// Exit
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
// Class to store customer service request details
class CustomerRequest {
    private String name;
    private int contactInfo;
    private String serviceRequirement;
    private String status;
    public CustomerRequest(String name, int contactInfo, String serviceRequirement) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.serviceRequirement = serviceRequirement;
        this.status = "Pending";
    }
    public void processRequest() {
        status = "Processed";
    }
    @Override
    public String toString() {
        return "Customer: " + name + "\nContact: " + contactInfo + "\nService Requirement: " +
                serviceRequirement + "\nStatus: " + status;
    }
}
// Node class representing a node in the linked list for the queue
class RequestNode {
    CustomerRequest request;
    RequestNode next;
    public RequestNode(CustomerRequest request) {
        this.request = request;
        this.next = null;
    }
}
// Queue class using linked list to store customer service requests
class RequestQueue {
    private RequestNode front;
    private RequestNode rear;
    private int size;
    public RequestQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    // Add a new request to the queue
    public void addRequest(CustomerRequest request) {
        RequestNode newNode = new RequestNode(request);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("New service request added for " + request.toString() + "\n");
    }
    // Process the request at the front of the queue
    public void processNextRequest() {
        if (front == null) {
            System.out.println("No pending requests to process.\n");
        } else {
            CustomerRequest currentRequest = front.request;
            front = front.next;
            if (front == null) {
                rear = null; // Queue is now empty
            }
            size--;
            currentRequest.processRequest();
            System.out.println("Processing request:\n" + currentRequest.toString() + "\n");
        }
    }
    // Display pending requests
    public void showPendingRequests() {
        if (front == null) {
            System.out.println("No pending requests.\n");
        } else {
            System.out.println("Pending requests:");
            RequestNode current = front;
            while (current != null) {
                System.out.println(current.request.toString() + "\n");
                current = current.next;
            }
        }
    }
}

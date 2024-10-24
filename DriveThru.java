
import java.util.Scanner;
// Class representing a customer order
class Order {
    int orderID;
    String items;
    String customerName;
    String status; // "Pending" or "Processed"
    public Order(int orderID, String items, String customerName) {
        this.orderID = orderID;
        this.items = items;
        this.customerName = customerName;
        this.status = "Pending"; // Orders are initially pending
    }
    // Mark the order as processed
    public void processOrder() {
        status = "Processed";
    }
    @Override
    public String toString() {
        return "Order ID: " + orderID + "\nItems: " + items + "\nCustomer Name: " + customerName
                + "\nStatus: " + status;
    }
}
// Node class for the linked list representing an order
class Node {
    Order order;
    Node next;
    public Node(Order order) {
        this.order = order;
        this.next = null;
    }
}
// Circular Queue using Linked List
class CircularQueue {
    private Node front;
    private Node rear;
    private boolean isEmpty;
    public CircularQueue() {
        this.front = this.rear = null;
        this.isEmpty = true;
    }
    // Add a new order to the circular queue
    public void addOrder(Order order) {
        Node newNode = new Node(order);
        if (isEmpty) {
            front = rear = newNode;
            rear.next = front; // Circular link
            isEmpty = false;
        } else {
            rear.next = newNode;
            rear = newNode;
            rear.next = front; // Maintain circular property
        }
        System.out.println("New order added:\n" + order.toString() + "\n");
    }
    // Process the order at the front of the queue
    public void processNextOrder() {
        if (isEmpty) {
            System.out.println("No pending orders to process.\n");
        } else {
            Order currentOrder = front.order;
            front = front.next;
            rear.next = front; // Maintain circular property
            currentOrder.processOrder();
            System.out.println("Processing order:\n" + currentOrder.toString() + "\n");
            if (front == rear) { // If there was only one order
                isEmpty = true;
            }
        }
    }
    // Show all pending orders
    public void showPendingOrders() {
        if (isEmpty) {
            System.out.println("No pending orders.\n");
        } else {
            Node temp = front;
            System.out.println("Pending orders:");
            do {
                System.out.println(temp.order.toString() + "\n");
                temp = temp.next;
            } while (temp != front); // Circular traversal
        }
    }
}


    public class DriveThru {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
// Initialize the circular queue (with no fixed capacity)
            CircularQueue orderQueue = new CircularQueue();
// Add initial customer orders to the queue
            orderQueue.addOrder(new Order(101, "Burger, Fries, Drink", "Rahul Sharma"));
            orderQueue.addOrder(new Order(102, "Chicken Sandwich, Salad, Drink", "Priya Patel"));
            orderQueue.addOrder(new Order(103, "Pizza, Wings, Drink", "Aarav Gupta"));
// Simple menu to interact with the system
            while (true) {
                System.out.println("\nDrive-Thru System:");
                System.out.println("1. Add new order");
                System.out.println("2. Process next order (Round-Robin)");
                System.out.println("3. Show pending orders");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
// Add new order
                        System.out.print("Enter order ID: ");
                        int orderID = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter items (comma-separated): ");
                        String items = scanner.nextLine();
                        System.out.print("Enter customer name: ");
                        String customerName = scanner.nextLine();
                        orderQueue.addOrder(new Order(orderID, items, customerName));
                        break;
                    case 2:
// Process next order
                        orderQueue.processNextOrder();
                        break;
                    case 3:
// Show pending orders
                        orderQueue.showPendingOrders();
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


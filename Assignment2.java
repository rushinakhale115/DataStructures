import java.util.Scanner;
class Assignment2 {
    public static void main(String[] args) {
        LL list = new LL();
        Scanner s1 = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display");
            System.out.println("5. Update");
            System.out.println("6. To know total value of inventory");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = s1.nextInt();
            switch (choice) {
                case 1:
                    String name = list.getName();
                    int quantity = list.getQuantity();
                    int price = list.getPrice();
                    String category = list.getCategory();
                    list.addLast(name, quantity, price, category);
                    break;
                case 2:
                    String name1 = list.getName();
                    list.search(name1);
                    break;
                case 3:
                    String name2 = list.getName();
                    list.delete(name2);
                    break;
                case 4:
                    list.display();
                    break;
                case 5:
                    String name3 = list.getName();
                    list.update(name3);
                    break;
                case 6:
                    list.inventoryValue();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    s1.close();
                    return;
                default:
                    System.out.println("Please enter a valid option.");
            }
        }
    }
}
class LL {
    Node head;
    int productId = 100;
    int totalValue = 0;
    Scanner sc = new Scanner(System.in);
    class Node{
        String name;
        int quantity;
        int price;
        Node next;
        int productID;
        String category;
        Node(String name, int quantity, int price, String category){
            this.name = name;
            this.quantity = quantity;
            this.price = price;
            this.category = category;
        }
    }
    public void addLast(String name, int quantity, int price, String category) {
        Node newNode = new Node(name, quantity, price, category);
        Node currNode = head;
        if(head==null){
            head = newNode;
            productId++;
            newNode.productID = productId;
            totalValue = totalValue + newNode.price*newNode.quantity;
            return;
        }
        else{
            while (currNode.next!=null) {
                currNode = currNode.next;
            }
            currNode.next = newNode;
            productId++;
            newNode.productID = productId;
            totalValue = totalValue + newNode.price*newNode.quantity;
        }
    }
    public void display(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Category: ");
        String userCategory = sc.next();
        displayByCategory(userCategory);
    }
    public void displayByCategory(String userCategory) {
        Node currNode = head;
        boolean found = false;
        System.out.println("Category: " + userCategory);
        while (currNode != null) {
            if (currNode.category.equals(userCategory)) {
                System.out.println("ProductId: " + currNode.productID);
                System.out.println("[ Name: " + currNode.name + " Quantity: " + currNode.quantity + " price: " + currNode.price + "]" );
                found = true;
            }
            currNode = currNode.next;
        }
        if (!found) {
            System.out.println("No items found in the category.");
        }
    }
    public void search(String name){
        Node currNode = head;
        while (currNode != null) {
            if(currNode.name.equals(name)){
                System.out.println("ProductId: " + currNode.productID);
                System.out.print("[ Name: " + currNode.name + " " + "Quantity: " + currNode.quantity);
                System.out.print(" " + "Price: " + currNode.price + " ]");
                System.out.println(" ");
                return;
            }
            currNode = currNode.next;
        }
        if(currNode == null)
            System.out.println("No item found ");
        return;
    }
    public void delete(String name) {
        Node currNode = head;
        Node prevNode = null;
// If head needs to be deleted
        if (currNode != null && currNode.name.equals(name)) {
            head = currNode.next; // Change head to the next node
            totalValue -= currNode.quantity * currNode.price; // Adjust total value
            System.out.println("Product " + currNode.name + " with ID: " + currNode.productID + " successfully deleted.");
            return;
        }
// Traverse the list to find the product to delete
        while (currNode != null && !currNode.name.equals(name)) {
            prevNode = currNode;
            currNode = currNode.next;
        }
// If product was not found
        if (currNode == null) {
            System.out.println("Product " + name + " not found.");
            return;
        }
// Unlink the product from the list
        prevNode.next = currNode.next;
        totalValue -= currNode.quantity * currNode.price; // Adjust total value
        System.out.println("Product " + currNode.name + " with ID: " + currNode.productID + " successfully deleted.");
    }
    public void update(String name) {
        Node currNode = head;
        while (currNode != null) {
            if (currNode.name.equals(name)) {
                System.out.println("ProductId: " + currNode.productID);
                System.out.print("[ Name: " + currNode.name + " " + "Quantity: " + currNode.quantity);
                System.out.print(" " + "Price: " + currNode.price + " ]");
                System.out.println(" ");
// Update the quantity and price
                System.out.println("Enter new quantity: ");
                int oldTotalValue = currNode.quantity * currNode.price;
                int newQuantity = sc.nextInt();
                System.out.println("Enter new price: ");
                int newPrice = sc.nextInt();
                currNode.quantity = newQuantity;
                currNode.price = newPrice;
// Update the total inventory value
                totalValue = totalValue - oldTotalValue + (newQuantity * newPrice);
                System.out.println("Product updated successfully");
                System.out.print("[ Name: " + currNode.name + " " + "Quantity: " + currNode.quantity);
                System.out.print(" " + "Price: " + currNode.price + " ]");
                System.out.println(" ");
                return;
            }
            currNode = currNode.next;
        }
        System.out.println("No item found with the name " + name);
    }
    public void inventoryValue(){
        System.out.println("Total Value of Inventory is: " + totalValue);
    }
    public String getName(){
        System.out.println("Enter product name: ");
        String name = sc.next();
        return name;
    }
    public int getQuantity(){
        System.out.println("Enter quantity: ");
        int quantity = sc.nextInt();
        return quantity;
    }
    public int getPrice(){
        System.out.println("Enter price: ");
        int price = sc.nextInt();
        return price;
    }
    public String getCategory() {
        System.out.println("Enter category: ");
        return sc.next();
    }
}
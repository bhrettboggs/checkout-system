package GroupProject;

import java.util.LinkedList;

public class Main { // Main class for the project
    public static void main(String[] args) { // Main method
        // Example usage of Inventory and Product classes
        LinkedList<Product> products = HardCode.initialize();

        System.out.println("Inventory List:"); 
        for (Product p : products) { // Loop through products
            System.out.println(p); // Print product
        }

        // Example usage of ReadLinkedList
        ReadLinkedList linkedList = new ReadLinkedList(); // Create ReadLinkedList object
        linkedList.addToList("Item 1");  
        linkedList.addToList("Item 2");
        linkedList.readLinkedList(); 
    }
}

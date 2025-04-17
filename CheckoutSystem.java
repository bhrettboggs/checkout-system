package GroupProject;

import java.io.*;
import java.util.*;


public class CheckoutSystem {

    static HashMap<String, Integer> rewardsMap = new HashMap<>();
    static ArrayList<Product> cart = new ArrayList<>();
    static LinkedList<Product> inventory = new LinkedList<>();

    //static LinkedList<Product> inventory = HardCode.initialize(); // Load products into inventory
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("Program started");

        loadRewards();
        loadCart();
        displayCart();

        manageCart();
        redeemRewards();
        checkout();
        printReceipt();

        
    }

    // Loads Rewards.txt into rewardsMap
    static void loadRewards() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("CSCI230/GroupProject/Rewards.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    rewardsMap.put(parts[0].trim(), Integer.valueOf(parts[1].trim()));
                }
            }
        }catch (IOException e) {
            System.out.println("Error loading rewards: " + e.getMessage());
        }
        System.out.println("Rewards loaded successfully.");
    }

    // Loads Items.txt and searches products by name (or id if your barcode scanner saves ids)
    static void loadCart() throws IOException {
        inventory = HardCode.initialize();

        System.out.println("Loading cart...");
        try (BufferedReader br = new BufferedReader(new FileReader("CSCI230/GroupProject/Items.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
    
                try {
                    int scannedId = Integer.parseInt(line); // Read ID as int
    
                    // Search inventory using product ID
                    Product product = searchInventory(scannedId);
    
                    if (product != null) {
                        cart.add(product);
                    } else {
                        System.out.println("Scanned item not found in inventory: " + scannedId);
                    }
    
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format in Items.txt: " + line);
                }
            }
        }catch (IOException e) {
            System.out.println("Error loading cart: " + e.getMessage());
        }
        System.out.println("Cart loaded successfully.");
    
        if (cart.isEmpty()) {
            System.out.println("No valid items in cart. Exiting.");
            //System.exit(1);
        }
    }
    
    // Search inventory by product ID
    static Product searchInventory(int scannedId) {
        for (Product p : inventory) {
            if (p.getId() == scannedId) {
                return p;
            }
        }
        return null;
    }
    
    static void displayCart() {
        System.out.println("\n--- Cart Contents ---");
        int index = 1;
        for (Product item : cart) {
            System.out.printf("%d. %s - $%.2f \n",
                index, item.getName(), item.getPrice());
            index++;
        }
    }
    
    
    static void manageCart() {
        System.out.println("\nWould you like to remove an item? (yes/no)");
        String response = scanner.nextLine().trim().toLowerCase();

        while (response.equals("yes")) {
            System.out.println("Enter item number to remove:");
            int itemNumber = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            if (itemNumber > 0 && itemNumber <= cart.size()) {
                Product removedItem = cart.remove(itemNumber - 1);
                System.out.println("Removed: " + removedItem.getName());
                displayCart();
            } else {
                System.out.println("Invalid item number.");
            }

            System.out.println("Remove another item? (yes/no)");
            response = scanner.nextLine().trim().toLowerCase();
        }
    }

    static void redeemRewards() {
        System.out.println("\nEnter your MemberID to redeem points (or 'skip'):");
        String memberId = scanner.nextLine().trim();

        if (memberId.equalsIgnoreCase("skip")) {
            return;
        }

        if (!rewardsMap.containsKey(memberId)) {
            System.out.println("Member ID not found. No rewards applied.");
            return;
        }

        int points = rewardsMap.get(memberId);
        double discount = points * 0.01; // 1 point = $0.01

        double total = getTotal();
        System.out.printf("You have %d points worth $%.2f.\n", points, discount);
        System.out.println("Would you like to apply them? (yes/no)");
        String apply = scanner.nextLine().trim().toLowerCase();

        if (apply.equals("yes")) {
            total -= discount;
            if (total < 0) {
                int remain = (int) Math.abs(total);
                total = 0;
                rewardsMap.put(memberId, remain); // Update points
            }
            rewardsMap.put(memberId, 0); // Reset points

            System.out.printf("New total after rewards: $%.2f\n", total);

        }    
        else{ // Award new points (5% of total)
            int newPoints = (int)(total * 0.05);
            rewardsMap.put(memberId, newPoints);
            System.out.println("You now have " + newPoints + " new points!");
        }
    }

    static double getTotal() {
        double total = 0;
        for (Product item : cart) {
            total += item.getPrice();
        }
        return total;
    }

    public static void checkout(){
        System.out.println("\n--- Checkout ---");
        double total = getTotal();
        System.out.printf("Total: $%.2f\n", total);
        System.out.println("Please enter your payment method (cash/credit):");
        String paymentMethod = scanner.nextLine().trim().toLowerCase();

        if (paymentMethod.equals("cash")) {
            System.out.println("Please enter the amount you are paying with:");
            double cashAmount = scanner.nextDouble();
            total = getTotal();
            if (cashAmount < total) {
                System.out.println("Insufficient cash. You owe: $" + (total - cashAmount));
                paymentMethod = "credit"; // Switch to credit payment
                checkout();
            } else if (cashAmount == total) {
                System.out.println("Exact amount received. Thank you!");
            } else {
                double change = cashAmount - total;
                System.out.printf("Change: $%.2f\n", change);
            }
        } else if (paymentMethod.equals("credit")) {
            System.out.println("Processing credit card payment...");
        } else {
            System.out.println("Invalid payment method. Try again.");
            checkout();
        }
    }

    static void printReceipt() {
        System.out.println("\n------ Receipt ------");
        displayCart();
        double total = getTotal();
        System.out.printf("Total: $%.2f\n", total);
        System.out.println("Payment method: " + (total == 0 ? "Cash" : "Credit"));
        System.out.println("Rewards redeemed: " + (rewardsMap.isEmpty() ? "No" : "Yes"));
        System.out.println("---------------------");
        System.out.println("Thank you for shopping!");
    }
}

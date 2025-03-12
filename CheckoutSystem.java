package GroupProject;

import java.io.*;
import java.util.*;

public class CheckoutSystem {

    static HashMap<String, Integer> rewardsMap = new HashMap<>();
    static ArrayList<Product> cart = new ArrayList<>();
    static LinkedList<Product> inventory = HardCode.initialize(); // Load products into inventory
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        loadRewards();
        loadCart();
        displayCart();

        manageCart();
        redeemRewards();
        printReceipt();

        updateRewards();
    }

    // Loads Rewards.txt into rewardsMap
    static void loadRewards() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("GroupProject/Rewards.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 2) {
                rewardsMap.put(parts[0], Integer.parseInt(parts[1]));
            }
        }
        br.close();
    }

    // Loads Items.txt and searches products by name (or id if your barcode scanner saves ids)
    static void loadCart() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Items.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String scannedItem = line.trim();

            // Search inventory for the scanned item
            Product product = searchInventory(scannedItem);

            if (product != null) {
                cart.add(product);
            } else {
                System.out.println("Scanned item not found in inventory: " + scannedItem);
            }
        }
        br.close();

        if (cart.isEmpty()) {
            System.out.println("No valid items in cart. Exiting.");
            System.exit(1);
        }
    }

    // Search inventory by name (or by ID if preferred)
    static Product searchInventory(String scannedItem) {
        for (Product p : inventory) {
            if (p.getName().equalsIgnoreCase(scannedItem)) {
                return p;
            }
            // If your scanner writes IDs instead of names:
            // if (String.valueOf(p.getId()).equals(scannedItem)) return p;
        }
        return null;
    }

    static void displayCart() {
        System.out.println("\n--- Cart Contents ---");
        int index = 1;
        for (Product item : cart) {
            System.out.printf("%d. %s - $%.2f\n", index, item.getName(), item.getPrice());
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
        System.out.println("\nEnter your Member ID to redeem points (or 'skip'):");
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
            if (total < 0) total = 0;

            rewardsMap.put(memberId, 0); // Reset points

            System.out.printf("New total after rewards: $%.2f\n", total);

            // Award new points (5% of total)
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

    static void printReceipt() {
        System.out.println("\n------ Receipt ------");
        displayCart();
        double total = getTotal();
        System.out.printf("Total: $%.2f\n", total);
        System.out.println("---------------------");
        System.out.println("Thank you for shopping!");
    }

    static void updateRewards() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("GroupProject/Rewards.txt"));

        for (Map.Entry<String, Integer> entry : rewardsMap.entrySet()) {
            bw.write(entry.getKey() + "," + entry.getValue() + "\n");
        }

        bw.close();
    }
}

import java.util.HashMap;
import java.util.Map;

public class ProductInventory {
    public static void main(String[] args) {
        HashMap<Integer, Integer> inventory = new HashMap<>();

        // Add products
        inventory.put(1, 100);
        inventory.put(2, 150);
        inventory.put(3, 200);

        // Update quantity of product 2
        inventory.put(2, 180);

        // Remove product 1
        inventory.remove(1);

        // Display inventory
        for (Map.Entry<Integer, Integer> entry : inventory.entrySet()) {
            System.out.println("Product ID: " + entry.getKey() + ", Quantity: " + entry.getValue());
        }
    }
}

package BookMyStay;

import java.io.*;
import java.util.*;
class PersistenceService {
    private static final String FILE_NAME = "inventory.dat";
    public Map<String, Integer> loadInventory() {
        Map<String, Integer> inventory = null;
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("No valid inventory data found. Starting fresh.");
                return createDefaultInventory();
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            inventory = (Map<String, Integer>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("No valid inventory data found. Starting fresh.");
            inventory = createDefaultInventory();
        }
        return inventory;
    }
    public void saveInventory(Map<String, Integer> inventory) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(inventory);
            oos.close();
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }
    private Map<String, Integer> createDefaultInventory() {
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
        return inventory;
    }
}
public class UseCaseBookMyStay {
    public static void main(String[] args) {
        System.out.println("System Recovery");
        PersistenceService service = new PersistenceService();
        Map<String, Integer> inventory = service.loadInventory();
        System.out.println();
        System.out.println("Current Inventory:");
        System.out.println("Single: " + inventory.get("Single"));
        System.out.println("Double: " + inventory.get("Double"));
        System.out.println("Suite: " + inventory.get("Suite"));
        service.saveInventory(inventory);
    }
}




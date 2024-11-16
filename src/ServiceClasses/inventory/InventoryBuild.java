package ServiceClasses.inventory;

import input.IntInput;
import input.Scan;
import models.User;
import models.enums.Status;

import java.util.ArrayList;

/**
 * Class responsible for building prescriptions, either interactively from user input
 * or from provided data in the form of arrays.
 */
public class InventoryBuild {

    /**
     * Builds a prescription based on user input.
     * Prompts the user to enter the name, ID, stock level, and low stock alert level.
     *
     * @param user the user who is creating the prescription
     * @return a newly created Prescription object
     */
    public Prescription build(User user) {
        System.out.printf("Prescription name: ");
        String name = Scan.scan.nextLine();
        System.out.printf("Prescription ID: ");
        String itemID = Scan.scan.nextLine();
        int stockLevel = IntInput.integer("Stock Level");
        int lowStockAlertLevel = IntInput.integer("Low Stock Alert Level");
        return new Prescription(name, itemID, stockLevel, lowStockAlertLevel);
    }

    /**
     * Builds a prescription from an array of data. The array should contain at least
     * the name, item ID, stock level, and low stock alert level.
     * If there are 6 elements, additional stock status and restock amount will be parsed.
     *
     * @param data an array of Strings containing prescription data
     * @return a newly created Prescription object, or null if data is incomplete or invalid
     */
    public Prescription build(String[] data) {
        if (data.length < 4) return null;

        if (data.length == 4) {
            String name = data[0];
            String itemID = data[1];
            int stockLevel = Integer.parseInt(data[2]);
            int lowStockAlertLevel = Integer.parseInt(data[3]);
            return new Prescription(name, itemID, stockLevel, lowStockAlertLevel);
        } else if (data.length == 6) {
            String name = data[0];
            String itemID = data[1];
            int stockLevel = Integer.parseInt(data[2]);
            int lowStockAlertLevel = Integer.parseInt(data[3]);
            Status stockStatus = Status.fromString(data[4]);
            int restockAmount = Integer.parseInt(data[5]);
            return new Prescription(name, itemID, stockLevel, lowStockAlertLevel, stockStatus, restockAmount);
        } else {
            return null;
        }
    }

    /**
     * Builds multiple prescriptions from a list of data arrays.
     * Each array should represent a prescription's data, which is passed to the build() method.
     *
     * @param data a list of string arrays, where each array contains prescription data
     * @return a list of Prescription objects
     */
    public ArrayList<Prescription> buildMany(ArrayList<String[]> data) {
        ArrayList<Prescription> Inventory = new ArrayList<>();

        for (String[] item : data) {
            Inventory.add(build(item));
        }

        return Inventory;
    }
}

package ServiceClasses.inventory;

import java.util.ArrayList;

import input.IntInput;

public class InventorySort {
    public void sortMenu() {
        System.out.println("Select Sort Method:");
        System.out.println("1 - Prescription ID");
        System.out.println("2 - Stock Level");
        System.out.println("3 - Alphabetical Order (Default)");
    }

    /** The order of sorting available to the user */
    public void orderMenu() {
        System.out.println("Select Order:");
        System.out.println("1 - Ascending (Default)");
        System.out.println("2 - Descending");
    }

    public ArrayList<Prescription> sortByID(ArrayList<Prescription> Inventory, int orderOption) {
        switch (orderOption) {
            case 1:
                Inventory.sort((item1, item2) -> item1.getItemID().compareTo(item2.getItemID()));
                break;
            default:
                Inventory.sort((item1, item2) -> -item1.getItemID().compareTo(item2.getItemID()));
                break;
        }
        return Inventory;
    }

    public ArrayList<Prescription> sortByStockLevel(ArrayList<Prescription> Inventory, int orderOption) {
        switch (orderOption) {
            case 1:
                Inventory.sort((item1, item2) -> Integer.compare(item1.getStockLevel(), item2.getStockLevel()));
                break;
            default:
                Inventory.sort((item1, item2) -> Integer.compare(item2.getStockLevel(), item1.getStockLevel()));
                break;
        }
        return Inventory;
    }

    public ArrayList<Prescription> sortByAlphabetical(ArrayList<Prescription> Inventory, int orderOption) {
        switch (orderOption) {
            case 1:
                Inventory.sort((item1,
                        item2) -> -item1.getItemName().toLowerCase().compareTo(item2.getItemName().toLowerCase()));
                break;
            default:
                Inventory.sort((item1, item2) -> item1.getItemName().toLowerCase()
                        .compareTo(item2.getItemName().toLowerCase()));
                break;
        }
        return Inventory;
    }

    public ArrayList<Prescription> inventorySort(ArrayList<Prescription> Inventory) {
        sortMenu();
        int sortOption = IntInput.integer("Option");
        orderMenu();
        int orderOption = IntInput.integer("Option");
        switch (sortOption) {
            case 1:
                return sortByID(Inventory, orderOption);
            case 2:
                return sortByStockLevel(Inventory, orderOption);
            default:
                return sortByAlphabetical(Inventory, orderOption);
        }
    }

}

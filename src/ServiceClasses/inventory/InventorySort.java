package ServiceClasses.inventory;

import java.util.ArrayList;
import input.IntInput;

/**
 * The InventorySort class provides sorting functionalities for a list of prescriptions.
 * It allows users to sort prescriptions by ID, stock level, or name (alphabetically) in both ascending and descending order.
 * The class also provides methods to display sorting menus for the user to choose their preferred sorting method and order.
 */
public class InventorySort {

    /**
     * Displays the menu for sorting options available to the user.
     * The user can choose to sort by Prescription ID, Stock Level, or Alphabetical Order.
     */
    public void sortMenu() {
        System.out.println("Select Sort Method:");
        System.out.println("1 - Prescription ID");
        System.out.println("2 - Stock Level");
        System.out.println("3 - Alphabetical Order (Default)");
    }

    /**
     * Displays the menu for order options available to the user.
     * The user can choose to sort in ascending or descending order.
     */
    public void orderMenu() {
        System.out.println("Select Order:");
        System.out.println("1 - Ascending (Default)");
        System.out.println("2 - Descending");
    }

    /**
     * Sorts the prescriptions by their Prescription ID.
     *
     * @param Inventory the list of prescriptions to be sorted
     * @param orderOption the order in which to sort (1 for ascending, 2 for descending)
     * @return the sorted list of prescriptions
     */
    public ArrayList<Prescription> sortByID(ArrayList<Prescription> Inventory, int orderOption) {
        switch (orderOption) {
            case 1:
                // Ascending order
                Inventory.sort((item1, item2) -> item1.getItemID().compareTo(item2.getItemID()));
                break;
            default:
                // Descending order
                Inventory.sort((item1, item2) -> -item1.getItemID().compareTo(item2.getItemID()));
                break;
        }
        return Inventory;
    }

    /**
     * Sorts the prescriptions by their stock level.
     *
     * @param Inventory the list of prescriptions to be sorted
     * @param orderOption the order in which to sort (1 for ascending, 2 for descending)
     * @return the sorted list of prescriptions
     */
    public ArrayList<Prescription> sortByStockLevel(ArrayList<Prescription> Inventory, int orderOption) {
        switch (orderOption) {
            case 1:
                // Ascending order
                Inventory.sort((item1, item2) -> Integer.compare(item1.getStockLevel(), item2.getStockLevel()));
                break;
            default:
                // Descending order
                Inventory.sort((item1, item2) -> Integer.compare(item2.getStockLevel(), item1.getStockLevel()));
                break;
        }
        return Inventory;
    }

    /**
     * Sorts the prescriptions alphabetically by their name.
     *
     * @param Inventory the list of prescriptions to be sorted
     * @param orderOption the order in which to sort (1 for ascending, 2 for descending)
     * @return the sorted list of prescriptions
     */
    public ArrayList<Prescription> sortByAlphabetical(ArrayList<Prescription> Inventory, int orderOption) {
        switch (orderOption) {
            case 1:
                // Ascending order
                Inventory.sort((item1, item2) -> -item1.getItemName().toLowerCase().compareTo(item2.getItemName().toLowerCase()));
                break;
            default:
                // Descending order
                Inventory.sort((item1, item2) -> item1.getItemName().toLowerCase()
                        .compareTo(item2.getItemName().toLowerCase()));
                break;
        }
        return Inventory;
    }

    /**
     * Prompts the user to select a sorting method and order, then returns the sorted list of prescriptions.
     *
     * @param Inventory the list of prescriptions to be sorted
     * @return the sorted list of prescriptions based on user input
     */
    public ArrayList<Prescription> inventorySort(ArrayList<Prescription> Inventory) {
        sortMenu(); // Display sorting options
        int sortOption = IntInput.integer("Option"); // Get the user's choice for sorting method
        orderMenu(); // Display order options (ascending/descending)
        int orderOption = IntInput.integer("Option"); // Get the user's choice for sorting order
        switch (sortOption) {
            case 1:
                return sortByID(Inventory, orderOption); // Sort by Prescription ID
            case 2:
                return sortByStockLevel(Inventory, orderOption); // Sort by Stock Level
            default:
                return sortByAlphabetical(Inventory, orderOption); // Sort by Alphabetical Order
        }
    }

}

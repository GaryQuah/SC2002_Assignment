package ServiceClasses.inventory;

import java.util.ArrayList;

import models.User;

/**
 * Interface that defines methods for managing and interacting with the inventory.
 * It includes methods to add, edit, delete, create inventory items, retrieve prescriptions,
 * and handle low-stock and restock inventory.
 */
public interface IInventory {

    /**
     * An instance of InventoryBuild used to build the inventory.
     */
    public InventoryBuild inventoryBuild = new InventoryBuild();

    // public InventoryParse inventoryParse = new InventoryParse();

    /**
     * Adds a user to the inventory system.
     *
     * @param user the user to be added
     */
    public void add(User user);

    /**
     * Edits an existing inventory item based on user input.
     *
     * @param user the user whose inventory item is being edited
     */
    public void edit(User user);

    /**
     * Deletes an inventory item.
     *
     * @param user the user whose inventory item is being deleted
     */
    public void delete(User user);

    /**
     * Creates a new inventory item.
     *
     * @param user the user creating the new inventory item
     */
    public void create(User user);

    /**
     * Retrieves a prescription by its ID.
     *
     * @param prescriptionID the ID of the prescription
     * @return the prescription corresponding to the given ID
     */
    public Prescription getPrescriptionByID(String prescriptionID);

    /**
     * Retrieves a list of prescriptions with low stock levels.
     *
     * @return an ArrayList of prescriptions with low stock
     */
    public ArrayList<Prescription> getLowStockInventory();

    /**
     * Retrieves a list of prescriptions that need to be restocked.
     *
     * @return an ArrayList of prescriptions that require restocking
     */
    public ArrayList<Prescription> getRestockInventory();
}

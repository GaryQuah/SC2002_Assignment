package ServiceClasses.inventory;

import java.util.HashMap;

import models.User;
import models.enums.Status;

/**
 * Interface that defines methods for controlling inventory actions related to medication.
 * This includes displaying inventory, sorting, selecting medication, dispensing, and handling replenishment requests.
 */
public interface IControl {

    /**
     * An instance of InventoryDisplay used to display inventory.
     */
    public InventoryDisplay inventoryDisplay = new InventoryDisplay();

    /**
     * An instance of InventorySort used for sorting inventory.
     */
    public InventorySort inventorySort = new InventorySort();

    /**
     * Selects medication based on the user's request.
     *
     * @param user the user requesting the medication
     * @return a map of selected medications with their quantities
     */
    public HashMap<String, Integer> selectMedication(User user);

    /**
     * Dispenses the selected medication and updates its status.
     *
     * @param medicationMap a map of medications and their quantities to be dispensed
     * @return the status of the dispensing operation
     */
    public Status dispenseMedicine(HashMap<String, Integer> medicationMap);

    /**
     * Requests replenishment for inventory based on the user's actions.
     *
     * @param user the user requesting replenishment
     */
    public void replenishmentRequest(User user);

    /**
     * Approves the replenishment request made by a user.
     *
     * @param user the user whose replenishment request is being approved
     */
    public void approveReplenishmentRequest(User user);

}

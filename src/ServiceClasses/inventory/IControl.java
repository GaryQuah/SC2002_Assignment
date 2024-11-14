package ServiceClasses.inventory;

import java.util.HashMap;

import models.User;
import models.enums.Status;

public interface IControl {

    public InventoryDisplay inventoryDisplay = new InventoryDisplay();

    public InventorySort inventorySort = new InventorySort();

    public HashMap<String, Integer> selectMedication(User user);

    public Status dispenseMedicine(HashMap<String, Integer> medicationMap);

    public void replenishmentRequest(User user);

    public void approveReplenishmentRequest(User user);

}

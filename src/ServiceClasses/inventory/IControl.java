package ServiceClasses.inventory;

import java.util.HashMap;

import models.User;

public interface IControl {

    public InventoryDisplay inventoryDisplay = new InventoryDisplay();

    public InventorySort inventorySort = new InventorySort();

    public HashMap<String, Integer> selectMedication(User user);

    public boolean dispenseMedicine(HashMap<String, Integer> medicationMap);

    public void replenishmentRequest(User user);

    public void approveReplenishmentRequest(User user);

}

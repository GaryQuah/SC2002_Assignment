package ServiceClasses.inventory;

import java.util.ArrayList;

import models.User;

public interface IInventory {

    public InventoryBuild inventoryBuild = new InventoryBuild();

    // public InventoryParse inventoryParse = new InventoryParse();

    public void add(User user);

    public void edit(User user);

    public void delete(User user);

    public void create(User user);

    public Prescription getPrescriptionByID(String prescriptionID);
    
    public ArrayList<Prescription> getLowStockInventory();

    public ArrayList<Prescription> getRestockInventory();
}

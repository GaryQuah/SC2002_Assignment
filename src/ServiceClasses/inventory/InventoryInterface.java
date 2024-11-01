package ServiceClasses.inventory;

public interface InventoryInterface {
    public InventoryBuild inventoryBuild = new InventoryBuild();
    
    public InventoryManager inventoryManager = new InventoryManager();

    public InventoryParse inventoryParse = new InventoryParse();

    public InventoryDisplay inventoryDisplay = new InventoryDisplay();
}

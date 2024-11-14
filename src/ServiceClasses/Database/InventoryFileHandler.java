package ServiceClasses.Database;

import java.util.ArrayList;

import ServiceClasses.inventory.InventoryBuild;
import ServiceClasses.inventory.InventoryControl;
import ServiceClasses.inventory.Prescription;
import input.CSVParse;

public class InventoryFileHandler extends FileHandler<Prescription> {

    // ArrayList <Prescription> inventory = InventoryControl.getInstance().getInventory();
    
    
    public InventoryFileHandler() {
        super("src\\data\\Medicine_List.csv");
    }


    @Override
    public ArrayList<Prescription> retrieveData() {
        // inventory.clear();
        ArrayList <Prescription> results = new ArrayList <Prescription> ();
        try {
            ArrayList <String[]> inventoryData = CSVParse.read(getFilePath(), false);
            results = (new InventoryBuild()).buildMany(inventoryData);
            InventoryControl.getInstance().setInventory(results);
            
        } catch (Exception e) {}
        return results;
    }

    @Override
    public void saveData() {
        
        ArrayList <String> data = new ArrayList <String> ();
        
        for (Prescription prescription : InventoryControl.getInstance().getInventory()) {
            data.add(
                prescription.getItemName() + "," +
                prescription.getItemID()+ "," +
                prescription.getStockLevel() + "," +
                prescription.getLowStockAlertLevel() + "," +
                prescription.getStockStatus().toString() + "," + 
                prescription.getRestockAmount()
            );
        }
        try {
            CSVParse.write(getFilePath(), data);
        } catch (Exception e) {}
    }

}

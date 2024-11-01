package ServiceClasses.inventory;

import input.CSVParse;
import java.util.ArrayList;

public class InventoryParse {

    public ArrayList <Prescription> parse(String file) {
        ArrayList <Prescription> results = new ArrayList <Prescription> ();
        try {
            ArrayList <String[]> inventoryData = CSVParse.read(file);
            results = (new InventoryBuild()).buildMany(inventoryData);
        } catch (Exception e) {}
        return results;
    }

    public void write(String file, ArrayList <Prescription> inventory) {
        ArrayList <String> data = new ArrayList <String> ();
        for (Prescription prescription : inventory) {
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
            CSVParse.write(file, data);
        } catch (Exception e) {}
    }

}
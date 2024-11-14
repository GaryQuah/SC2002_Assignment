package ServiceClasses.inventory;

import input.IntInput;
import input.Scan;
import models.User;
import models.enums.Status;

import java.util.ArrayList;

public class InventoryBuild {


    public Prescription build(User user) {
        System.out.printf("Prescription name: "); String name = Scan.scan.nextLine();
        System.out.printf("Prescription ID: "); String itemID = Scan.scan.nextLine();
        int stockLevel = IntInput.integer("Stock Level");
        int lowStockAlertLevel = IntInput.integer("Low Stock Alert Level");
        return new Prescription(name, itemID, stockLevel, lowStockAlertLevel);
    }

    public Prescription build(String[] data) {
        if(data.length < 4) return null;

        if (data.length == 4){
            String name = data[0];
            String itemID = data[1];
            int stockLevel = Integer.parseInt(data[2]);
            int lowStockAlertLevel = Integer.parseInt(data[3]);
            return new Prescription(name, itemID, stockLevel, lowStockAlertLevel);
        }

        else if(data.length == 6){
            String name = data[0];
            String itemID = data[1];
            int stockLevel = Integer.parseInt(data[2]);
            int lowStockAlertLevel = Integer.parseInt(data[3]);
            Status stockStatus = Status.fromString(data[4]);
            int restockAmount = Integer.parseInt(data[5]);
            return new Prescription(name, itemID, stockLevel, lowStockAlertLevel, stockStatus, restockAmount);
        }
        else return null;
    }

    public ArrayList<Prescription> buildMany(ArrayList<String[]> data) {
        ArrayList<Prescription> Inventory = new ArrayList<>();

        for (String[] item : data){
            Inventory.add(build(item));
        }

        return Inventory;
    }

}

package ServiceClasses.inventory;

import models.enums.Status;

public class Prescription {
    private String itemName;
    private String itemID;
    private int stockLevel; // Current stock level for this medication
    private int lowStockAlertLevel; // Threshold for low stock alert
    private Status stockStatus;
    private int restockAmount;

    public Prescription(String itemName, String itemID, int stockLevel, int lowStockAlertLevel) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.stockLevel = stockLevel;
        this.lowStockAlertLevel = lowStockAlertLevel;
        checkStatus();
        this.restockAmount = 0;
    }
    public Prescription(String itemName, String itemID, int stockLevel, int lowStockAlertLevel, Status stockStatus, int restockAmount) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.stockLevel = stockLevel;
        this.lowStockAlertLevel = lowStockAlertLevel;
        this.stockStatus = stockStatus;
        this.restockAmount = restockAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getLowStockAlertLevel() {
        return lowStockAlertLevel;
    }

    public void setLowStockAlertLevel(int lowStockAlertLevel) {
        this.lowStockAlertLevel = lowStockAlertLevel;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public boolean checkStatus(){
        if(stockLevel < lowStockAlertLevel){
            setStockStatus(Status.LOWSTOCK);
            return true;}
        else{setStockStatus(Status.OK);
            return false;
        }
    }

    public Status getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Status stockStatus) {
        this.stockStatus = stockStatus;
    }

    public int getRestockAmount() {
        return restockAmount;
    }
    public void setRestockAmount(int restockAmount) {
        this.restockAmount = restockAmount;
    }
}
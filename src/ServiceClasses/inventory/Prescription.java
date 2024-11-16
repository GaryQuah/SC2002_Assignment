package ServiceClasses.inventory;

import models.enums.Status;

/**
 * The Prescription class represents a medication prescription in the inventory system.
 * Each prescription has a name, ID, stock level, low stock alert level, and current stock status.
 * The class includes methods to manage the prescription's details, update stock levels,
 * and check if the prescription is low on stock.
 */
public class Prescription {

    private String itemName;
    private String itemID;
    private int stockLevel; // Current stock level for this medication
    private int lowStockAlertLevel; // Threshold for low stock alert
    private Status stockStatus; // Current stock status (e.g., OK or LOWSTOCK)
    private int restockAmount; // The amount requested for restocking

    /**
     * Constructs a new Prescription with the specified details.
     *
     * @param itemName the name of the prescription
     * @param itemID the unique ID of the prescription
     * @param stockLevel the current stock level of the prescription
     * @param lowStockAlertLevel the low stock threshold for this prescription
     */
    public Prescription(String itemName, String itemID, int stockLevel, int lowStockAlertLevel) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.stockLevel = stockLevel;
        this.lowStockAlertLevel = lowStockAlertLevel;
        checkStatus(); // Update the stock status based on the stock level
        this.restockAmount = 0; // Default to no restock requested
    }

    /**
     * Constructs a new Prescription with all attributes, including stock status and restock amount.
     *
     * @param itemName the name of the prescription
     * @param itemID the unique ID of the prescription
     * @param stockLevel the current stock level of the prescription
     * @param lowStockAlertLevel the low stock threshold for this prescription
     * @param stockStatus the current stock status (e.g., OK or LOWSTOCK)
     * @param restockAmount the amount to restock if required
     */
    public Prescription(String itemName, String itemID, int stockLevel, int lowStockAlertLevel, Status stockStatus, int restockAmount) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.stockLevel = stockLevel;
        this.lowStockAlertLevel = lowStockAlertLevel;
        this.stockStatus = stockStatus;
        this.restockAmount = restockAmount;
    }

    // Getters and Setters for Prescription properties

    /**
     * Gets the name of the prescription.
     *
     * @return the name of the prescription
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the name of the prescription.
     *
     * @param itemName the new name of the prescription
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the stock level of the prescription.
     *
     * @return the current stock level
     */
    public int getStockLevel() {
        return stockLevel;
    }

    /**
     * Sets the stock level of the prescription.
     *
     * @param stockLevel the new stock level
     */
    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    /**
     * Gets the low stock alert level for the prescription.
     *
     * @return the low stock alert level
     */
    public int getLowStockAlertLevel() {
        return lowStockAlertLevel;
    }

    /**
     * Sets the low stock alert level for the prescription.
     *
     * @param lowStockAlertLevel the new low stock alert level
     */
    public void setLowStockAlertLevel(int lowStockAlertLevel) {
        this.lowStockAlertLevel = lowStockAlertLevel;
    }

    /**
     * Gets the unique ID of the prescription.
     *
     * @return the prescription ID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * Sets the unique ID of the prescription.
     *
     * @param itemID the new prescription ID
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * Checks the current stock status of the prescription and updates it.
     * The stock status is set to `LOWSTOCK` if the current stock level is below the low stock alert level,
     * or `OK` if the stock level is sufficient.
     *
     * @return true if the prescription is low on stock, false otherwise
     */
    public boolean checkStatus() {
        if (stockLevel < lowStockAlertLevel) {
            setStockStatus(Status.LOWSTOCK); // Set status to low stock
            return true;
        } else {
            setStockStatus(Status.OK); // Set status to OK
            return false;
        }
    }

    /**
     * Gets the current stock status of the prescription.
     *
     * @return the stock status (e.g., OK or LOWSTOCK)
     */
    public Status getStockStatus() {
        return stockStatus;
    }

    /**
     * Sets the stock status of the prescription.
     *
     * @param stockStatus the new stock status (e.g., OK or LOWSTOCK)
     */
    public void setStockStatus(Status stockStatus) {
        this.stockStatus = stockStatus;
    }

    /**
     * Gets the restock amount for the prescription.
     *
     * @return the amount to restock if necessary
     */
    public int getRestockAmount() {
        return restockAmount;
    }

    /**
     * Sets the restock amount for the prescription.
     *
     * @param restockAmount the amount to restock
     */
    public void setRestockAmount(int restockAmount) {
        this.restockAmount = restockAmount;
    }
}

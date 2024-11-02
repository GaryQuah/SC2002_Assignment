package ServiceClasses.inventory;

import java.util.ArrayList;
import java.util.HashMap;

import models.User;
import models.enums.Role;
import ServiceClasses.AppointmentManager;


public class InventoryControl implements InventoryInterface {
    private static ArrayList<Prescription> inventory = new ArrayList<>();

    public static InventoryControl instance = new InventoryControl();

    public static void start() {
        inventory.addAll(inventoryParse.parse("src\\data\\Medicine_List.csv"));
    }

    public static void close() {
        inventoryParse.write("src\\data\\Medicine_List.csv", inventory);
    }

    private Boolean isDoctor(User user){
        if(user.getRole() == Role.Doctor) return true;
        System.out.println("Only Doctor are permitted to conduct this operation. ");
        return false;
    }

    private Boolean isPharmacist(User user){
        if(user.getRole() == Role.Pharmacist) return true;
        System.out.println("Only Pharmacist are permitted to conduct this operation. ");
        return false;
    }

    private Boolean isAdmin(User user){
        if(user.getRole() == Role.Administrator) return true;
        System.out.println("Only Admin are permitted to conduct this operation. ");
        return false;
    }

    // private Boolean isStaff(User user){
    //     if(user.getRole() != Role.Patient) return true;
    //     System.out.println("Only Staff are permitted to conduct this operation. ");
    //     return false;
    // }    

    public void addNew(Prescription prescription, User user){
        if (!isAdmin(user)) return;
        for (Prescription item : inventory) {
            if (item.getItemID().equalsIgnoreCase(prescription.getItemID())) {
                System.out.println("Item already exists in the inventory.");
                return;
            }
        }
        inventory.add(prescription);
        inventoryManager.sortByAlphabetical(inventory, 0);
        System.out.println("New item added: " + prescription.getItemName());
    }

    public void addNew(String[] data, User user){
        if (!isAdmin(user)) return;
        Prescription prescription = inventoryBuild.build(data);
        for (Prescription item : inventory) {
            if (item.getItemID().equalsIgnoreCase(prescription.getItemID())) {
                System.out.println("Item already exists in the inventory.");
                return;
            }
        }
        inventory.add(prescription);
        inventoryManager.sortByAlphabetical(inventory, 0);
        System.out.println("New item added: " + prescription.getItemName());
    }
    
    public void edit(User user){
        if (!isAdmin(user)) return;
        Prescription prescription = inventoryManager.select(inventory);
        inventoryManager.edit(prescription);
    }

    public void delete(User user){
        if(!isAdmin(user)) return;
        System.out.println("Please select Prescription that you want to remove.");
        Prescription prescription = inventoryManager.select(inventory);
        inventory.remove(prescription);
    }

    public void addPrescription(User user){
        if(!isAdmin(user)) return;
        inventoryManager.addMedicine(inventory);  
    }

    public HashMap<String, Integer> selectMedication(){
        return inventoryManager.selectMedication(inventory);

        // apt.


        
        
        // something here = apt.
    }

    public String dispenseMedicine(HashMap<String, Integer> medicationMap){
        for (String prescriptionID : medicationMap.keySet()) {
            Prescription prescription = getPrescriptionByID(prescriptionID);
            int amount = medicationMap.get(prescriptionID); // Get the quantity to dispense for this prescription
            String itemName = prescription.getItemName(); // Get the name for messaging purposes
            
            // Check if the prescription exists in the inventory and if stock is sufficient
            if (prescription != null && (prescription.getStockLevel() > amount)) {
                // Update the stock level
                prescription.setStockLevel(prescription.getStockLevel() - amount);
                System.out.println(amount + " units of " + itemName + " dispensed.");
                prescription.checkStatus(); 
                return "DISPENSED";
                } else {
                    System.out.println("Insufficient stock for " + itemName + ".");
                    prescription.setStockStatus(StockStatus.LOWSTOCK);
                    return "PENDING RESTOCK";
                }      
        }
        return null;
    }

    public void showInventory(){
        inventoryDisplay.printPrescriptions(inventory);
    }

    public void showInventoryDoctor(User user){
        if(!isDoctor(user)) return;
        inventoryDisplay.printPrescriptionsDoctor(inventory);
    }

    public void showInventoryLowStock(){
        inventoryDisplay.printPrescriptions(getLowStockInventory(inventory));
    }
    
    public void showInventoryReStock(User user){
        if(!isAdmin(user)) return;
        inventoryDisplay.printPrescriptions(getRestockInventory(inventory));
    }

    // private Prescription getPrescriptionByName(String itemName) {
    //     for (Prescription prescription : inventory) {
    //         if (prescription.getItemName().equalsIgnoreCase(itemName)) {
    //             return prescription;
    //         }
    //     }
    //     return null;
    // }

    private Prescription getPrescriptionByID(String prescriptionID) {
        for (Prescription prescription : inventory) {
            if (prescription.getItemID().equalsIgnoreCase(prescriptionID)) {
                return prescription;
            }
        }
        return null;
    }

    public void replenishmentRequest(User user){
        if (!isPharmacist(user)) return;
        inventoryManager.replenishmentRequest(getLowStockInventory(inventory));
    }    
    
    public void approveReplenishmentRequest(User user){
        if (!isAdmin(user)) return;
        inventoryManager.approveReplenishmentRequest(getRestockInventory(inventory));
    }

    private ArrayList<Prescription> getRestockInventory(ArrayList<Prescription> inventory){
        ArrayList<Prescription> result = new ArrayList<>();
        for (Prescription prescription : inventory) {
            if (prescription.getStockStatus() == StockStatus.RESTOCK) {
                result.add(prescription);
            }
        }
        if (result.size() == 0){return new ArrayList<>();}
        else {return result;}
    }

    private ArrayList<Prescription> getLowStockInventory(ArrayList<Prescription> inventory){
        ArrayList<Prescription> result = new ArrayList<>();
        for (Prescription prescription : inventory) {
            if (prescription.getStockStatus() == StockStatus.LOWSTOCK) {
                result.add(prescription);
            }
        }
    return result;
    }

}

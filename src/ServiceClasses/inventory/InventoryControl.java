package ServiceClasses.inventory;

import java.util.ArrayList;
import java.util.HashMap;

import input.IntInput;
import input.Scan;
import models.User;
import models.enums.Role;
import models.enums.StockStatus;

public class InventoryControl implements IControl, IInventory {
    private ArrayList<Prescription> inventory = new ArrayList<>();

    public void setInventory(ArrayList<Prescription> inventory) {
        this.inventory = inventory;
    }

    public ArrayList<Prescription> getInventory() {
        return inventory;
    }

    private static InventoryControl instance;

    public static InventoryControl getInstance() {

        if (instance == null) {
            instance = new InventoryControl();
        }
        return instance;
    }

    // public void start() {
    //     inventory.clear();
    //     inventory.addAll(inventoryParse.parse("src\\data\\Medicine_List.csv"));
    // }

    // public void close() {
    //     inventoryParse.write("src\\data\\Medicine_List.csv", inventory);
    // }

    private Boolean isDoctor(User user) {
        if (user.getRole() == Role.Doctor)
            return true;
        System.out.println("Only Doctor are permitted to conduct this operation. ");
        return false;
    }

    private Boolean isPharmacist(User user) {
        if (user.getRole() == Role.Pharmacist)
            return true;
        System.out.println("Only Pharmacist are permitted to conduct this operation. ");
        return false;
    }

    private Boolean isAdmin(User user) {
        if (user.getRole() == Role.Administrator)
            return true;
        System.out.println("Only Admin are permitted to conduct this operation. ");
        return false;
    }

    @Override
    public void create(User user) {
        if (!isAdmin(user))
            return;
        Prescription newPrescription = inventoryBuild.build(user);
        for (Prescription item : inventory) {
            if (item.getItemID().equalsIgnoreCase(newPrescription.getItemID())) {
                System.out.println("Item already exists in the inventory.");
                return;
            }
        }
        inventory.add(newPrescription);
        inventorySort.sortByAlphabetical(inventory, 0);
        System.out.println("New item added: " + newPrescription.getItemName());

    }

    @Override
    public void add(User user) {
        if (!isAdmin(user))
            return;
        InventoryDisplay display = new InventoryDisplay();

        while (true) {
            display.printPrescriptions(inventory);
            System.out.println("Enter the index number of the medicine to restock, or -1 to cancel:");
            int index = IntInput.integer("Prescription Index");

            if (index == -1) {
                break;
            }

            if (index < 1 || index > inventory.size()) {
                System.out.println("Invalid selection. Please enter a valid index.");
                continue;
            }

            Prescription selectedPrescription = inventory.get(index - 1);
            int amount = IntInput.integer("Enter quantity to add for " + selectedPrescription.getItemName());
            selectedPrescription.setStockLevel(selectedPrescription.getStockLevel() + amount);
            selectedPrescription.checkStatus();
            System.out.println(amount + " units added to " + selectedPrescription.getItemName() + ". New stock level: "
                    + selectedPrescription.getStockLevel());
        }
    }

    @Override
    public void edit(User user) {
        if (!isAdmin(user))
            return;
        Prescription prescription = select();

        if (prescription == null)
            return;

        System.out.println("Select Attribute to Edit:");
        System.out.println("1 - Prescription Name");
        System.out.println("2 - Prescription ID");
        System.out.println("3 - Stock Level");
        System.out.println("4 - Low Stock Alert Level");

        int option = IntInput.integer("Option");
        switch (option) {
            case 1:
                System.out.printf("Enter New Name: ");
                String newName = Scan.scan.nextLine();
                prescription.setItemName(newName);
                break;
            case 2:
                System.out.printf("Enter New ID: ");
                String newID = Scan.scan.nextLine();
                prescription.setItemID(newID);
                break;
            case 3:
                prescription.setStockLevel(IntInput.integer("Enter New Stock Level"));
                prescription.checkStatus();
                break;

            case 4:
                prescription.setLowStockAlertLevel(IntInput.integer("Enter Low Stock Alert Level"));
                prescription.checkStatus();
                break;

            default:
                break;

        }
    }

    @Override
    public void delete(User user) {
        if (!isAdmin(user))
            return;
        System.out.println("Please select Prescription that you want to remove.");
        Prescription prescription = select();
        inventory.remove(prescription);
    }

    public Prescription select() {
        (new InventoryDisplay()).printPrescriptions(inventory);
        if (inventory.size() == 0)
            return null;
        int index = IntInput.integer("Select Prescription");

        if (1 <= index && index <= inventory.size()) {
            return inventory.get(index - 1);
        }

        System.out.println("Invalid selection.");
        return null;
    }

    public boolean dispenseMedicine(HashMap<String, Integer> medicationMap) {
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
                return true;
            } else {
                System.out.println("Insufficient stock for " + itemName + ".");
                prescription.setStockStatus(StockStatus.LOWSTOCK);
                return false;
            }
        }
        return true;
    }

    public Prescription getPrescriptionByID(String prescriptionID) {
        for (Prescription prescription : inventory) {
            if (prescription.getItemID().equalsIgnoreCase(prescriptionID)) {
                return prescription;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Prescription> getLowStockInventory() {
        ArrayList<Prescription> result = new ArrayList<>();
        for (Prescription prescription : inventory) {
            if (prescription.getStockStatus() == StockStatus.LOWSTOCK) {
                result.add(prescription);
            }
        }
        if (result.size() == 0) {
            return new ArrayList<>();
        } else {
            return result;
        }
    }

    @Override
    public ArrayList<Prescription> getRestockInventory() {
        ArrayList<Prescription> result = new ArrayList<>();
        for (Prescription prescription : inventory) {
            if (prescription.getStockStatus() == StockStatus.RESTOCK) {
                result.add(prescription);
            }
        }
        if (result.size() == 0) {
            return new ArrayList<>();
        } else {
            return result;
        }
    }

    @Override
    public HashMap<String, Integer> selectMedication(User user) {
        if(!isDoctor(user)) return null;
        HashMap<String, Integer> medicationMap = new HashMap<>();
        InventoryDisplay display = new InventoryDisplay();

        while (true) {
            display.printPrescriptionsDoctor(inventory);
            System.out.println("Enter the index number to add to the list, or -1 to stop:");
            int index = IntInput.integer("Prescription No.");

            if (index == -1) {
                break;
            }

            if (index < 1 || index > inventory.size()) {
                System.out.println("Invalid selection. Please enter a valid index.");
                continue;
            }

            String selectedPrescriptionID = inventory.get(index - 1).getItemID();
            int quantity = IntInput.integer("Enter quantity for " + inventory.get(index - 1).getItemName());
            medicationMap.put(selectedPrescriptionID, quantity);
        }

        if (medicationMap.size() == 0) {
            System.out.println("No medication selected.");
            return new HashMap<>();
        }
        System.out.println("Final Medication List:");

        for (String prescriptionID : medicationMap.keySet()) {
            System.out.println("- " + prescriptionID + ": " + medicationMap.get(prescriptionID) + " units");
        }

        return medicationMap;
    }

    @Override
    public void replenishmentRequest(User user) {
        if (!isPharmacist(user)) return;
        ArrayList<Prescription> lowStockInventory = getLowStockInventory();
        InventoryDisplay display = new InventoryDisplay();
        if (lowStockInventory.size() == 0) {
            System.out.println("No low stock medications.");
            return;
        }

        while (true) {
            display.printPrescriptions(lowStockInventory);
            System.out.println("Enter the index number of the medicine to restock, or -1 to cancel:");
            int index = IntInput.integer("Prescription Index");

            if (index == -1) {
                break;
            }

            if (index < 1 || index > lowStockInventory.size()) {
                System.out.println("Invalid selection. Please enter a valid index.");
                continue;
            }

            Prescription selectedPrescription = lowStockInventory.get(index - 1);
            String prescriptionID = selectedPrescription.getItemID();
            int amount = IntInput.integer("Enter quantity to add for " + selectedPrescription.getItemName() + ": ");
            selectedPrescription.setStockStatus(StockStatus.RESTOCK);
            selectedPrescription.setRestockAmount(amount);
            System.out.println(
                    "Added " + prescriptionID + " (" + selectedPrescription.getItemName() + ") to restock request.");
        }
    }

    @Override
    public void approveReplenishmentRequest(User user) {
        if (!isAdmin(user)) return;
        ArrayList<Prescription> reStockInventory = getRestockInventory();

        InventoryDisplay display = new InventoryDisplay();
        if (reStockInventory.size() == 0) {
            System.out.println("No Replenishment Request. ");
            return;
        }

        while (true) {
            display.printRestockPrescriptions(reStockInventory);

            // Ensure the option is a valid integer (0 or 1)
            int option = IntInput.integer("Enter 1 for Approve, 2 for Reject");
            switch (option) {
                case 2:
                    System.out.println("Replenishment Request Rejected.");
                    return;

                case 1:
                    for (Prescription prescription : reStockInventory) {
                        int restockAmount = prescription.getRestockAmount();

                        if (restockAmount > 0) { // Only restock if the amount is positive
                            prescription.setStockLevel(prescription.getStockLevel() + restockAmount);
                            prescription.checkStatus();
                            prescription.setRestockAmount(0); // Reset restock amount after replenishing
                        }
                    }
                    System.out.println("Replenishment Request Approved.");
                    return;
                default:
                    System.out.println("Invalid input. Please enter again.");
                    break;
            }
        }
    }
    
    public void showInventory(){
        inventoryDisplay.printPrescriptions(inventory);
    }

    public void showInventoryDoctor(User user){
        if(!isDoctor(user)) return;
        inventoryDisplay.printPrescriptionsDoctor(inventory);
    }

    public void showInventoryLowStock(){
        inventoryDisplay.printPrescriptions(getLowStockInventory());
    }
    
    public void showInventoryReStock(User user){
        if(!isAdmin(user)) return;
        inventoryDisplay.printPrescriptions(getRestockInventory());
    }
}

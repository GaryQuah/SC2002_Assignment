package ServiceClasses.inventory;

import input.IntInput;
import input.Scan;
import java.util.ArrayList;
import java.util.HashMap;


public class InventoryManager {
    public void editMenu(){
        System.out.println("Select Attribute to Edit:");
        System.out.println("1 - Prescription Name");
        System.out.println("2 - Prescription ID");
        System.out.println("3 - Stock Level");
        System.out.println("4 - Low Stock Alert Level");
    }

    public void sortMenu() {
        System.out.println("Select Sort Method:");
        System.out.println("1 - Prescription ID");
        System.out.println("2 - Stock Level");
        System.out.println("3 - Alphabetical Order (Default)");
    }
    /** The order of sorting available to the user */
    public void orderMenu() {
        System.out.println("Select Order:");
        System.out.println("1 - Ascending (Default)");
        System.out.println("2 - Descending");
    }

    public void edit(Prescription prescription){
        if (prescription == null) return;

        editMenu();

        int option = IntInput.integer("Option");
        switch (option) {
            case 1:
                System.out.printf("Enter New Name: "); String newName = Scan.scan.nextLine();
                prescription.setItemName(newName); break;
            case 2:
                System.out.printf("Enter New ID: "); String newID = Scan.scan.nextLine();
                prescription.setItemID(newID); break;
            case 3:
                prescription.setStockLevel(IntInput.integer("Enter New Stock Level")); prescription.checkStatus(); break;
                
            case 4:
                prescription.setLowStockAlertLevel(IntInput.integer("Enter Low Stock Alert Level")); prescription.checkStatus(); break;

            default: break;            

        }
    }

    public ArrayList <Prescription> sortByID(ArrayList <Prescription> Inventory, int orderOption) {
        switch (orderOption) {
            case 1: Inventory.sort((item1, item2) -> item1.getItemID().compareTo(item2.getItemID())); break;
            default: Inventory.sort((item1, item2) -> - item1.getItemID().compareTo(item2.getItemID())); break;
        }
        return Inventory;
    }

    public ArrayList <Prescription> sortByStockLevel(ArrayList <Prescription> Inventory, int orderOption) {
        switch (orderOption) {
            case 1: Inventory.sort((item1, item2) -> Integer.compare(item1.getStockLevel(), item2.getStockLevel())); break;
            default: Inventory.sort((item1, item2) -> Integer.compare(item2.getStockLevel(), item1.getStockLevel())); break;
        }
        return Inventory;
    }

    public ArrayList <Prescription> sortByAlphabetical(ArrayList <Prescription> Inventory, int orderOption) {
        switch (orderOption) {
            case 1: Inventory.sort((item1, item2) -> - item1.getItemName().toLowerCase().compareTo(item2.getItemName().toLowerCase())); break;
            default: Inventory.sort((item1, item2) ->  item1.getItemName().toLowerCase().compareTo(item2.getItemName().toLowerCase())); break;
        }
        return Inventory;
    }

    public ArrayList<Prescription> inventorySort (ArrayList <Prescription> Inventory){
        sortMenu();
        int sortOption = IntInput.integer("Option");
        orderMenu();
        int orderOption = IntInput.integer("Option");
        switch (sortOption) {
            case 1: return sortByID(Inventory, orderOption);
            case 2: return sortByStockLevel(Inventory, orderOption);
            default: return sortByAlphabetical(Inventory, orderOption);
        }
    }

    public Prescription select(ArrayList<Prescription> inventory){
        (new InventoryDisplay()).printPrescriptions(inventory);
        if (inventory.size() == 0) return null;
        int index = IntInput.integer("Select Prescription");

        if (1 <= index && index <= inventory.size()) {
            return inventory.get(index - 1); 
        }


        System.out.println("Invalid selection.");
        return null;
    }

    public HashMap<String, Integer> selectMedication(ArrayList<Prescription> inventory){
        HashMap<String, Integer> medicationMap = new HashMap<>();
        InventoryDisplay display = new InventoryDisplay();

        while (true){
            display.printPrescriptionsDoctor(inventory);        
            System.out.println("Enter the index number to add to the list, or -1 to stop:");
            int index = IntInput.integer("Prescription No.");

            if(index == -1){break;}

            if (index < 1 || index > inventory.size()) {
                System.out.println("Invalid selection. Please enter a valid index.");
                continue;
            }

            String selectedPrescriptionID = inventory.get(index - 1).getItemID(); 
            int quantity = IntInput.integer("Enter quantity for " + inventory.get(index - 1).getItemName());
            medicationMap.put(selectedPrescriptionID, quantity);
        }

        if(medicationMap.size() == 0) {System.out.println("No medication selected."); return new HashMap<>();}
        System.out.println("Final Medication List:");

        for (String prescriptionID : medicationMap.keySet()) {
            System.out.println("- " + prescriptionID+ ": " + medicationMap.get(prescriptionID) + " units");
        }
        
        return medicationMap;  // Return the map with prescriptions and quantities


    }

    public void addMedicine(ArrayList<Prescription> inventory){
 
        InventoryDisplay display = new InventoryDisplay();

        while (true){
            display.printPrescriptions(inventory);
            System.out.println("Enter the index number of the medicine to restock, or -1 to cancel:");
            int index = IntInput.integer("Prescription Index");

            if(index == -1){
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
            System.out.println(amount + " units added to " + selectedPrescription.getItemName() + ". New stock level: " + selectedPrescription.getStockLevel());
        }

    }

    public void replenishmentRequest(ArrayList<Prescription> inventory){
        InventoryDisplay display = new InventoryDisplay();

        while (true){
            display.printPrescriptions(inventory);
            System.out.println("Enter the index number of the medicine to restock, or -1 to cancel:");
            int index = IntInput.integer("Prescription Index");

            if(index == -1){break;}

            if (index < 1 || index > inventory.size()) {
                System.out.println("Invalid selection. Please enter a valid index.");
                continue;
            }

            Prescription selectedPrescription = inventory.get(index - 1);
            String prescriptionID = selectedPrescription.getItemID();
            int amount = getPositiveInt("Enter quantity to add for " + selectedPrescription.getItemName() + ": ");
            selectedPrescription.setStockStatus(StockStatus.RESTOCK);
            selectedPrescription.setRestockAmount(amount);
            System.out.println("Added " + prescriptionID + " (" + selectedPrescription.getItemName() + ") to restock request.");
        }
    }

    public void approveReplenishmentRequest(ArrayList<Prescription> inventory) {
        InventoryDisplay display = new InventoryDisplay();

        while (true) {
            display.printRestockPrescriptions(inventory);
            
            // Ensure the option is a valid integer (0 or 1)
            int option = IntInput.integer("Enter 1 for Approve, 2 for Reject");
            switch (option) {
                case 2:
                    System.out.println("Replenishment Request Rejected.");
                    return;

                case 1:
                    for (Prescription prescription : inventory) {
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

    private int getPositiveInt(String prompt) {
        int number;
    
        while (true) {
            number = IntInput.integer(prompt);

            if (number >= 0) {
                return number;
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
    }
        

}

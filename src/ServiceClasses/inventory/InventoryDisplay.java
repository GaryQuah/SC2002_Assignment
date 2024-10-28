package ServiceClasses.inventory;

import java.util.ArrayList;

public class InventoryDisplay {

    public void header() {
        System.out.printf("PRESCRIPTIONS "); 
        for (int i = 0; i < 83; i++) System.out.printf("-");
        System.out.println();
    
        System.out.printf("%-5s |", "No.");             // New index column
        System.out.printf("%-15s |", "Prescription ID");
        System.out.printf(" %-20s |", "Prescription Name");
        System.out.printf(" %-12s |", "Stock Number");
        System.out.printf(" %-20s |", "Low Stock Alert Level");
        System.out.printf(" %-10s\n", "Status");
    }

    public void printPrescription(Prescription prescription, int index) {
        if (prescription == null) return;
    
        System.out.printf("%-5d |", index);  // Print the index number
        System.out.printf("%-15s |", prescription.getItemID());
        System.out.printf(" %-20s |", prescription.getItemName());
        System.out.printf(" %-12d |", prescription.getStockLevel());
        System.out.printf(" %-21d |", prescription.getLowStockAlertLevel());
        System.out.printf(" %-10s\n", prescription.getStockStatus());
    }

    public void printPrescriptions(ArrayList<Prescription> prescriptions) {
        
        if (prescriptions.size() == 0) {
            System.out.println("No Prescriptions Found");
            return;
        }
    
        header(); // Print the header once
        for (int i = 0; i < prescriptions.size(); i++) {
            // System.out.println(prescriptions.size());
            printPrescription(prescriptions.get(i), i + 1); // Pass the index + 1 to display 1-based numbering
        }
    }

    public void headerDoctor(){
        System.out.printf("PRESCRIPTIONS "); 
        for (int i = 0; i < 40; i++) System.out.printf("-");
        System.out.println();
    
        System.out.printf("%-5s |", "No.");             // New index column
        System.out.printf("%-15s |", "Prescription ID");
        System.out.printf(" %-20s\n", "Prescription Name");
    }

    public void printPrescriptionDoctor(Prescription prescription, int index) {
        if (prescription == null) return;
    
        System.out.printf("%-5d |", index);  // Print the index number
        System.out.printf("%-15s |", prescription.getItemID());
        System.out.printf(" %-20s\n", prescription.getItemName());
    }

    public void printPrescriptionsDoctor(ArrayList<Prescription> prescriptions) {
        if (prescriptions.size() == 0) {
            System.out.println("No Prescriptions Found");
            return;
        }
    
        headerDoctor(); // Print the header once
        for (int i = 0; i < prescriptions.size(); i++) {
            printPrescriptionDoctor(prescriptions.get(i), i + 1); // Pass the index + 1 to display 1-based numbering
        }
    }
    public void restockHeader() {
        System.out.printf("RESTOCK PRESCRIPTIONS "); 
        for (int i = 0; i < 68; i++) System.out.printf("-");
        System.out.println();
    
        System.out.printf("%-5s |", "No.");             // New index column
        System.out.printf("%-15s |", "Prescription ID");
        System.out.printf(" %-20s |", "Prescription Name");
        System.out.printf(" %-12s |", "Stock Number");
        System.out.printf(" %-15s\n", "Restock Amount");
    }

    public void printRestockPrescription(Prescription prescription, int index) {
        if (prescription == null) return;
    
        System.out.printf("%-5d |", index);  // Print the index number
        System.out.printf("%-15s |", prescription.getItemID());
        System.out.printf(" %-20s |", prescription.getItemName());
        System.out.printf(" %-12s |", prescription.getStockLevel());
        System.out.printf(" %-15s\n", prescription.getRestockAmount());

    }

    public void printRestockPrescriptions(ArrayList<Prescription> prescriptions) {
        if (prescriptions.size() == 0) {
            System.out.println("No Prescriptions Found");
            return;
        }
    
        restockHeader(); // Print the header once
        for (int i = 0; i < prescriptions.size(); i++) {
            printRestockPrescription(prescriptions.get(i), i + 1); // Pass the index + 1 to display 1-based numbering
        }
    }
}

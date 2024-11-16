package ServiceClasses.inventory;

import java.util.ArrayList;

/**
 * The InventoryDisplay class is responsible for formatting and displaying prescription data.
 * It provides various methods to display prescription information in a tabular format, depending on the user's role.
 * It includes methods to display general prescriptions, those visible to doctors, and prescriptions that require restocking.
 */
public class InventoryDisplay {

    /**
     * Prints the header for the general prescription table.
     */
    public void header() {
        System.out.printf("PRESCRIPTIONS ");
        for (int i = 0; i < 83; i++)
            System.out.printf("-");
        System.out.println();

        System.out.printf("%-5s |", "No."); // New index column
        System.out.printf("%-15s |", "Prescription ID");
        System.out.printf(" %-20s |", "Prescription Name");
        System.out.printf(" %-12s |", "Stock Number");
        System.out.printf(" %-20s |", "Low Stock Alert Level");
        System.out.printf(" %-10s\n", "Status");
    }

    /**
     * Prints the information of a single prescription.
     *
     * @param prescription the prescription to display
     * @param index the index of the prescription in the list
     */
    public void printPrescription(Prescription prescription, int index) {
        if (prescription == null)
            return;

        System.out.printf("%-5d |", index); // Print the index number
        System.out.printf("%-15s |", prescription.getItemID());
        System.out.printf(" %-20s |", prescription.getItemName());
        System.out.printf(" %-12d |", prescription.getStockLevel());
        System.out.printf(" %-21d |", prescription.getLowStockAlertLevel());
        System.out.printf(" %-10s\n", prescription.getStockStatus());
    }

    /**
     * Prints a list of prescriptions in a tabular format.
     *
     * @param prescriptions the list of prescriptions to display
     */
    public void printPrescriptions(ArrayList<Prescription> prescriptions) {
        if (prescriptions.size() == 0) {
            System.out.println("No Prescriptions Found");
            return;
        }

        header(); // Print the header once
        for (int i = 0; i < prescriptions.size(); i++) {
            printPrescription(prescriptions.get(i), i + 1); // Pass the index + 1 to display 1-based numbering
        }
    }

    /**
     * Prints the header for the prescription table for doctors.
     */
    public void headerDoctor() {
        System.out.printf("PRESCRIPTIONS ");
        for (int i = 0; i < 40; i++)
            System.out.printf("-");
        System.out.println();

        System.out.printf("%-5s |", "No."); // New index column
        System.out.printf("%-15s |", "Prescription ID");
        System.out.printf(" %-20s\n", "Prescription Name");
    }

    /**
     * Prints the information of a single prescription for a doctor.
     *
     * @param prescription the prescription to display
     * @param index the index of the prescription in the list
     */
    public void printPrescriptionDoctor(Prescription prescription, int index) {
        if (prescription == null)
            return;

        System.out.printf("%-5d |", index); // Print the index number
        System.out.printf("%-15s |", prescription.getItemID());
        System.out.printf(" %-20s\n", prescription.getItemName());
    }

    /**
     * Prints a list of prescriptions in a simplified format for doctors.
     *
     * @param prescriptions the list of prescriptions to display
     */
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

    /**
     * Prints the header for the restock prescription table.
     */
    public void restockHeader() {
        System.out.printf("RESTOCK PRESCRIPTIONS ");
        for (int i = 0; i < 80; i++)
            System.out.printf("-");
        System.out.println();

        System.out.printf("%-5s |", "No."); // New index column
        System.out.printf("%-15s |", "Prescription ID");
        System.out.printf(" %-20s |", "Prescription Name");
        System.out.printf(" %-12s |", "Stock Number");
        System.out.printf(" %-20s |", "Low Stock Alert Level");
        System.out.printf(" %-15s\n", "Restock Amount");
    }

    /**
     * Prints the information of a single prescription in the restock table.
     *
     * @param prescription the prescription to display
     * @param index the index of the prescription in the list
     */
    public void printRestockPrescription(Prescription prescription, int index) {
        if (prescription == null)
            return;

        System.out.printf("%-5d |", index); // Print the index number
        System.out.printf("%-15s |", prescription.getItemID());
        System.out.printf(" %-20s |", prescription.getItemName());
        System.out.printf(" %-12s |", prescription.getStockLevel());
        System.out.printf(" %-21d |", prescription.getLowStockAlertLevel());
        System.out.printf(" %-15s\n", prescription.getRestockAmount());
    }

    /**
     * Prints a list of prescriptions in the restock table.
     *
     * @param prescriptions the list of prescriptions to display
     */
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

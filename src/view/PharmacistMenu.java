package view;

import ServiceClasses.AppointmentOutcome.AppoinmentOutcomeControl;
import ServiceClasses.Database.DataBaseManager;
import ServiceClasses.inventory.InventoryControl;
import input.IntInput;
import models.User;

public class PharmacistMenu implements Menu {
    private static InventoryControl inventoryControl = InventoryControl.getInstance();

    public void header() {
        System.out.println();
        System.out.println("---------- Pharmacist Menu ----------");
        System.out.println("1. View Appoinment Outcome");
        System.out.println("2. Update Prescription Status");
        System.out.println("3. View Medication Inventory");
        System.out.println("4. Submit Replenishment Request");
        System.out.println("5. Logout");
    }

    public void displayMenu(User loggedInUser) {

        int choice;
        DataBaseManager.getInstance().getInventoryFileHandler().retrieveData();
        DataBaseManager.getInstance().getOutcomeFileHandler().retrieveData();

        do {
            header();
            choice = IntInput.integer("Option");
            switch (choice) {
                case 1:
                    AppoinmentOutcomeControl.getInstance().viewMedicalRecordsByPharmacist(loggedInUser);
                    break;
                case 2:
                    AppoinmentOutcomeControl.getInstance().updatePrescriptionStatus(loggedInUser);
                    break;

                case 3:
                    inventoryControl.showInventory();
                    break;

                case 4:
                    inventoryControl.replenishmentRequest(loggedInUser);
                    break;

                case 5:
                    System.out.println("Logout...");
                    DataBaseManager.getInstance().getInventoryFileHandler().saveData();
                    DataBaseManager.getInstance().getOutcomeFileHandler().saveData();
                    return;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }

        } while (choice != 5);

    }
}

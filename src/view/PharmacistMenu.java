package view;

import ServiceClasses.AppointmentOutcome.AppoinmentOutcomeControl;
import ServiceClasses.Database.DataBaseManager;
import ServiceClasses.inventory.InventoryControl;
import input.IntInput;
import models.User;

/**
 * The {@code PharmacistMenu} class provides a menu interface for pharmacists.
 * <p>
 * It allows pharmacists to:
 * <ul>
 * <li>View appointment outcomes</li>
 * <li>Update prescription statuses</li>
 * <li>View and manage medication inventory</li>
 * <li>Submit replenishment requests</li>
 * </ul>
 * This class implements the {@code Menu} interface.
 */
public class PharmacistMenu implements Menu {

    /**
     * A static reference to the {@code InventoryControl} instance
     * for managing inventory-related operations.
     */
    private static InventoryControl inventoryControl = InventoryControl.getInstance();

    /**
     * Displays the header for the pharmacist menu.
     */
    public static void header() {
        System.out.println();
        System.out.println("=======================================");
        System.out.println("|           Pharmacist Menu           |");
        System.out.println("=======================================");
        System.out.println("1. View Appointment Outcome");
        System.out.println("2. Update Prescription Status");
        System.out.println("3. View Medication Inventory");
        System.out.println("4. Submit Replenishment Request");
        System.out.println("5. Logout");
    }

    /**
     * Displays the pharmacist menu and handles user input for menu operations.
     * <p>
     * Allows pharmacists to interact with the system for various tasks,
     * such as viewing appointment outcomes, updating prescriptions,
     * and managing inventory.
     * </p>
     *
     * @param loggedInUser The currently logged-in user.
     */
    public void displayMenu(User loggedInUser) {

        int choice;

        do {
            header();
            choice = IntInput.integer("Option");
            DataBaseManager.getInstance().getOutcomeFileHandler().retrieveData();
            DataBaseManager.getInstance().getInventoryFileHandler().retrieveData();
            switch (choice) {
                case 1:
                    System.out.println("1. View All Appointment Outcome");
                    System.out.println("2. View Pending Appointment Outcome");
                    choice = IntInput.integer("Option");
                    switch (choice) {
                        case 1:
                            AppoinmentOutcomeControl.getInstance().viewAppoinmentOutcomes(loggedInUser);
                            break;

                        case 2:
                            AppoinmentOutcomeControl.getInstance().viewMedicalRecordsByPharmacist(loggedInUser);
                            break;

                        default:
                            System.out.println("Invalid Option.");
                            break;
                    }
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
                    break;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            DataBaseManager.getInstance().getOutcomeFileHandler().saveData();
            DataBaseManager.getInstance().getInventoryFileHandler().saveData();

        } while (choice != 5);

    }
}

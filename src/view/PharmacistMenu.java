package view;

import ServiceClasses.Database.DataBaseManager;
import ServiceClasses.inventory.InventoryControl;
import input.IntInput;
import models.User;

public class PharmacistMenu implements Menu {
    private static InventoryControl inventoryControl = InventoryControl.getInstance();

    public void header(){
        System.out.println("---------- Pharmacist Menu ----------");
        System.out.println("1. View Appoinment Outcome");
        System.out.println("2. Update Prescription Status");
        System.out.println("3. View Medication Inventory");
        System.out.println("4. Submit Replenishment Request");
        System.out.println("5. Logout");    
    }

    public void displayMenu(User loggedInUser){

        int choice;

        do{
            
            header();
            choice = IntInput.integer("Option");
            DataBaseManager.getInstance().getInventoryFileHandler().retrieveData();
            switch (choice) {
                case 1:
                    System.out.println("View Appoinment Outcome");
                    /*
                     * Show a list of appoinment outcomes
                     */

                    break;
                
                case 2:
                    System.out.println("Update Prescription Status");
                    /*
                     * 1. Show a list of appoinment outcomes
                     * (Show only completed appointment + show dispense status = false)
                     * 2. Select one appoinment outcome
                     * 3. Fulfill the request and update the prescrition status
                     */


                    break;
            
                case 3:
                    inventoryControl.showInventory();
                    break;
                
                case 4:
                    inventoryControl.replenishmentRequest(loggedInUser);
                    break;

                case 5:
                    System.out.println("Logout...");
                    return;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            DataBaseManager.getInstance().getInventoryFileHandler().saveData();

        }while(choice!= 5);
        
        


    }
}

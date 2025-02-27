package view.HMS;

import ServiceClasses.Database.DataBaseManager;
import ServiceClasses.Database.PatientFileHandler;
import ServiceClasses.Database.StaffFileHandler;
import ServiceClasses.SignUp.PTSignup;
import models.Staff;

import java.util.InputMismatchException;
import models.User;
import view.*;

import java.util.Scanner;

public class HMSApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataBaseManager dbManager = DataBaseManager.getInstance();
        dbManager.getappointmentFileHandler().retrieveData();
        dbManager.getPatientFileHandler().retrieveData();
        dbManager.getStaffFileHandler().retrieveData();

        String currentUserID;
        String currentUserPassword;
        User loggedInUser = null;
        Menu menu = null;

        int choice;

        // Display login/register menu with error handling
        do {
            System.out.println("Welcome To The HMS System");
            System.out.println("1. LOGIN");
            System.out.println("2. REGISTER");
            System.out.println("3. EXIT");
            
            try {
                choice = sc.nextInt();
                sc.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                sc.nextLine();
                choice = 0; 
            }

            if (choice == 2) {
                PTSignup signUp = new PTSignup();
                signUp.signUp();
                continue;
            }
            // LOGIN
            else if (choice == 1) {
                System.out.println("Enter your User ID followed by Password to login.");
    
                System.out.print("User ID: ");
                currentUserID = sc.nextLine();
                System.out.print("Password: ");
                currentUserPassword = sc.nextLine();
    
                // Check if user is a Patient or Staff
                if (currentUserID.startsWith("PT")) {
                    // Attempt to log in as Patient
                    PatientFileHandler patientFileHandler = dbManager.getPatientFileHandler();
                    if (patientFileHandler.checkLogin(currentUserID, currentUserPassword)) {
                        loggedInUser = patientFileHandler.getUserById(currentUserID); // Store as Patient object
                        System.out.println("Login successful as Patient.");
                    }
                } else {
                    // Attempt to log in as Staff
                    StaffFileHandler staffFileHandler = dbManager.getStaffFileHandler();
                    if (staffFileHandler.checkLogin(currentUserID, currentUserPassword)) {
                        loggedInUser = staffFileHandler.getUserById(currentUserID); // Store as Staff object
                        System.out.println("Login successful as Staff. Role: " + ((Staff) loggedInUser).getRole());
                    }
                }
            }

            if (loggedInUser != null) {
                menu = MenuFactory.createMenu(loggedInUser);
                if (menu != null) {
                    menu.displayMenu(loggedInUser);
                    // Clear menu & user details after logout
                    loggedInUser = null;
                    menu = null;
                }
            }
            else{ // failed login loggedInUser is null
                System.out.println("User login failed.");
            }

        } while (choice != 3);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Code to be executed when the JVM shuts down
            System.out.println("Program is exiting. Performing cleanup...");
            // Add any cleanup or resource release logic here
            dbManager.getappointmentFileHandler().saveData();
        }));
    }
}

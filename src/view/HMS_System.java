package view;
import java.util.Vector;

import ServiceClasses.Appointment;
import models.User;

import java.util.Scanner;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class HMS_System {   

    private static final String FILE_PATH = "user_data.csv";

    // Method to store user ID and password in CSV format
    public static void storeUserData(String userId, String password) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            // Write userId and password, separated by a comma
            writer.append(userId)
                  .append(",")
                  .append(password)
                  .append("\n");
        }
        System.out.println("User data stored successfully.");
    }

    public class CSVUserDataRetrieval {

        private static final String FILE_PATH = "user_data.csv";
    
        // Method to retrieve and print user ID and password from the CSV file
        public static void retrieveUserData() throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Split each line by comma to get userId and password
                    String[] userData = line.split(",");
                    String userId = userData[0];
                    String password = userData[1];
                    System.out.println("User ID: " + userId + ", Password: " + password);
                }
            }
        }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vector<Appointment> AppointmentManager;
        Vector<User> ListOfUsers = new Vector<>();;

        String currentUserID;
        String currentUserPassword;

        //Create code here to open up file to retrieve locally stored users

        int choice = -1;
        while(choice != 1 || choice != 2)
        {
            System.out.println("Welcome To The HMS System - Key In Your User ID Followed By Your User Password");
            currentUserID = sc.nextLine();
            currentUserPassword = sc.nextLine();

            boolean succesfulLogin = false;

            while (succesfulLogin == false) {
                System.out.println("Key In A User ID Followed By Your User Password");
                currentUserID = sc.nextLine();
                currentUserPassword = sc.nextLine();

                for (int i = 0; i < ListOfUsers.size(); i++) {
                    if (ListOfUsers.get(i).getUserID().equals(currentUserID)) {
                        if(ListOfUsers.get(i).getUserID().equals(currentUserPassword)) //Check if password is succesfully entered. 
                        {
                            succesfulLogin = true;
                            break; //Break out - Succesfully login 
                        }
                        System.out.println("Invalid Password Entered");
                        break; //Break out - Invalid Password 
                    }
                }
            }
        }
        //Get role from users in the system
        
        
        String role = "";

        do{
            switch(role){
                case "Patient":
                    break;
                case "Doctor":
                    break;
                case "Pharmacist":
                    break;
                case "Administrator":
                    break;
                case "Exit":
                    System.out.println("Thank You For Using The System. See You Again Soon.");
                    break;
                default:
                    System.out.println("Inavlid Role Entered. Please key in a valid role");
                    break;
            }
        }while(role != "Exit");

        //At the end of the main code, store the user data into excel
        try {
            // Example to store user data
            storeUserData("user123", "password123");
            storeUserData("user456", "password456");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PatientMenu()
    {

    }

    public void DoctorMenu()
    {

    }

    public void PharmacistMenu()
    {

    }

    public void AdministratorMenu()
    {

    }
}
}

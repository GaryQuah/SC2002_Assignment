package view.HMS;
import java.util.Vector;


import ServiceClasses.MedicalRecordService;
import ServiceClasses.Appointment.Appointment;
import ServiceClasses.Database.DataBaseManager;
import ServiceClasses.Database.PatientFileHandler;
import ServiceClasses.Database.StaffFileHandler;
import ServiceClasses.SignUp.PTSignup;
import models.MedicalRecord;
import models.Patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.User;
import view.AdministratorMenu;

import java.util.Scanner;


public class HMSApp {
    public static void main(String[] args) {
        // Initialize system and load data
        // List<String[]> m_UserList = StaffFileHandler.getStaffData();
        // Handle user login and delegate to the appropriate menu

        ////////////////////// corn's part, ignore this
        // Scanner scanner = new Scanner(System.in);

        // while (true) {
        //     System.out.println("1. Add Medical Record");
        //     System.out.println("2. View Medical Records");
        //     System.out.println("3. Update Medical Record");
        //     System.out.println("4. Delete Medical Record");
        //     System.out.println("5. Exit");
        //     int choice = scanner.nextInt();
        //     scanner.nextLine(); // Consume newline

        //     try {
        //         switch (choice) {
        //             case 1:
        //                 System.out.println("Enter Patient ID:");
        //                 String patientId = scanner.nextLine();
        //                 System.out.println("Enter Doctor ID:");
        //                 String doctorId = scanner.nextLine();
        //                 System.out.println("Enter Diagnosis:");
        //                 String diagnosis = scanner.nextLine();
        //                 System.out.println("Enter Treatment:");
        //                 String treatment = scanner.nextLine();
        //                 System.out.println("Enter Date:");
        //                 String date = scanner.nextLine();
        //                 MedicalRecord record = new MedicalRecord(patientId, doctorId, diagnosis, treatment, date);
        //                 MedicalRecordService.addMedicalRecord(record);
        //                 break;
        //             case 2:
        //                 List<MedicalRecord> records = MedicalRecordService.getAllMedicalRecords();
        //                 for (MedicalRecord rec : records) {
        //                     System.out.println(rec.getPatientId() + " " + rec.getDoctorId() + " " + rec.getDiagnosis() + " " + rec.getTreatment() + " " + rec.getDate());
        //                 }
        //                 break;
        //             case 3:
        //                 System.out.println("Enter Patient ID to update:");
        //                 String updatePatientId = scanner.nextLine();
        //                 System.out.println("Enter new Doctor ID:");
        //                 String updateDoctorId = scanner.nextLine();
        //                 System.out.println("Enter new Diagnosis:");
        //                 String updateDiagnosis = scanner.nextLine();
        //                 System.out.println("Enter new Treatment:");
        //                 String updateTreatment = scanner.nextLine();
        //                 System.out.println("Enter new Date:");
        //                 String updateDate = scanner.nextLine();
        //                 MedicalRecord updatedRecord = new MedicalRecord(updatePatientId, updateDoctorId, updateDiagnosis, updateTreatment, updateDate);
        //                 MedicalRecordService.updateMedicalRecord(updatedRecord);
        //                 break;
        //             case 4:
        //                 System.out.println("Enter Patient ID to delete:");
        //                 String deletePatientId = scanner.nextLine();
        //                 MedicalRecordService.deleteMedicalRecord(deletePatientId);
        //                 break;
        //             case 5:
        //                 System.exit(0);
        //         }
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // }
        ////////////////////// corn's part, ignore this
        // private static final String FILE_PATH = "user_data.csv";

        Scanner sc = new Scanner(System.in);
        Vector<Appointment> AppointmentManager;
        Vector<User> ListOfUsers = new Vector<>();
        List<String[]> staffData = new ArrayList<>();
        List<String[]> patientData = new ArrayList<>();
        String role = "";

        String currentUserID;
        String currentUserPassword;

        //Create code here to open up file to retrieve locally stored users

        int choice = 1;
        while(choice == 1 || choice == 2)
        {
            boolean succesfulLogin = false;

            System.out.println("Welcome To The HMS System");
            System.out.println("1. LOGIN");
            System.out.println("2. REGISTER");
            choice = sc.nextInt();
            sc.nextLine();
            

            // REGISTER
            if (choice == 2)
            {
                System.out.println(choice);
                PTSignup.main(null);
            }
            // LOGIN
            else if (choice == 1)
            {
                System.out.println("Welcome To The HMS System - Key In Your User ID Followed By Your User Password");
                currentUserID = sc.nextLine();
                currentUserPassword = sc.nextLine();

                // get the first 2 alphabet of currentUserID to check if user is a patient or staff
                String firstLetter = currentUserID.substring(0, 2);
                if (firstLetter.equals("PT")) 
                {
                    if (PatientFileHandler.checkLogin(currentUserID, currentUserPassword))
                    {
                        succesfulLogin = true;
                        role = "Patient";
                    }
                    else
                    {
                        succesfulLogin = false;
                    }
                }
                else{
                    if (DataBaseManager.getInstance().getStaffFileHandler().checkLogin(currentUserID, currentUserPassword))
                    {
                        succesfulLogin = true;
                        role = DataBaseManager.getInstance().getStaffFileHandler().getRoleById(currentUserID);
                        System.out.println(role);
                    }
                    else
                    {
                        succesfulLogin = false;
                    }
                }

                do{
                    switch(role){
                        case "Patient":
                            System.out.println("Patient Menu");
                            break;
                        case "Doctor":
                            System.out.println("Doctor Menu");
                            break;
                        case "Pharmacist":
                            System.out.println("Pharmacist Menu");
                            break;
                        case "Administrator":
                            AdministratorMenu.main(args);
                            break;
                        case "Exit":
                            System.out.println("Thank You For Using The System. See You Again Soon.");
                            break;
                        default:
                            role = "Exit";
                            break;
                    }
                }while(role != "Exit");
            }
            else {
                System.out.println("Invalid Choice");
            }
        }
    }
    



    public class HMS_System {   

        

        // public void PatientMenu()
        // {

        // }

        // public void DoctorMenu()
        // {

        // }

        // public void PharmacistMenu()
        // {

        // }

        // public void AdministratorMenu()
        // {

        // }
    }
}


package view.HMS;

import javax.xml.crypto.Data;

import ServiceClasses.Appointment.AppointmentManager;
import ServiceClasses.Appointment.AppointmentStatus;
import ServiceClasses.AppointmentOutcome.AppoinmentOutcomeControl;
import ServiceClasses.MedicalRecordService;
import ServiceClasses.Appointment.Appointment;
import ServiceClasses.Database.DataBaseManager;
import ServiceClasses.Database.PatientFileHandler;
import ServiceClasses.Database.StaffFileHandler;
import ServiceClasses.SignUp.PTSignup;
import ServiceClasses.inventory.InventoryControl;
import models.Doctor;
import models.MedicalRecord;
import models.Patient;
import models.Pharmacist;
import models.Staff;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import models.User;
import models.enums.BloodType;
import models.enums.Gender;
import models.enums.Role;
import view.*;

import java.util.Scanner;

public class HMSApp {
    public static void main(String[] args) {
        DataBaseManager dataBaseManager = DataBaseManager.getInstance();

        dataBaseManager.getappointmentFileHandler().retrieveData();
        dataBaseManager.getOutcomeFileHandler().retrieveData();
        dataBaseManager.getInventoryFileHandler().retrieveData();

        AppoinmentOutcomeControl appoinmentOutcomeControl = AppoinmentOutcomeControl.getInstance();
        AppointmentManager appointmentManager = AppointmentManager.getInstance();
        // User loggedInUser = null;

        // /*
        //  * User input ==> logged in user, check wether is doctor
        //  * selected appoinment and update it to complete and fill up the outcome
        //  */
        // appoinmentOutcomeControl.create(null, null);

        // /*
        //  * Sample usage for creating a new outcome by Doctor.
        //  * Please a create a function to Complete Appointment.
        //  */
        // Appointment appointment = appointmentManager.getAppointmentScheduler().getAppointmentByID(2);
        // if (appointment == null) {
        //     System.out.println("Invalid Appoinment ID");
        // } else {
        //     appoinmentOutcomeControl.create(loggedInUser, appointment);
        // }
        // /*
        //  * View All AppointmentOutcome. 
        //  * For Admin or Everyone.
        //  */
        // appoinmentOutcomeControl.viewAppoinmentOutcomes(null);
        // /*
        //  * View all the Medical Records under this particular Doctor supervision.
        //  */
        // appoinmentOutcomeControl.viewMedicalRecordsByDoctor(null);
        // /*
        //  * View all the Medical Records of this particular Patient.
        //  */
        // appoinmentOutcomeControl.viewMedicalRecordsByPatient(null);
        // /*
        //  * View all the Pending Medical Records.
        //  * For Dispense Purpose.
        //  */
        // appoinmentOutcomeControl.viewMedicalRecordsByPharmacist(null);

        // // delete appointment, for admin only. not necessary but leave it here first
        // appoinmentOutcomeControl.delete(loggedInUser);

        // // edit appointment, can be used by doctor.
        // appoinmentOutcomeControl.edit(loggedInUser);

        // // for pharmacist to update the dispesnse status from pending to completed 
        // appoinmentOutcomeControl.updatePrescriptionStatus(loggedInUser);



        // dataBaseManager.getOutcomeFileHandler().saveData();
        // dataBaseManager.getInventoryFileHandler().saveData();
        // dataBaseManager.getappointmentFileHandler().saveData();
        // dataBaseManager.getStaffFileHandler().retrieveData();

        // AppoinmentOutcomeControl appoinmentOutcomeControl =
        // AppoinmentOutcomeControl.getInstance();

        User loggedInUser = new Doctor("123", "John", Role.Doctor, Gender.Female, 10, "1234", "1234");

        appoinmentOutcomeControl.edit(loggedInUser);

        // appointmentManager.getAppointmentViewer().ViewAllAppointments();
        // appointmentManager.getAppointmentScheduler().updateAppointmentRequestStatus("John", 1,
        //         AppointmentStatus.ACCEPTED);
        // appointmentManager.getAppointmentViewer().ViewAllAppointments();
        // appointmentManager.getAppointmentScheduler().updateAppointmentRequestStatus("John", 2,
        //         AppointmentStatus.COMPLETED);

        // Appointment appointment = appointmentManager.getAppointmentScheduler().getAppointmentByID(2);
        // if (appointment == null) {
        //     System.out.println("Invalid Appoinment ID");
        // } else {
        //     appoinmentOutcomeControl.create(loggedInUser, appointment);
        // }

        // // appoinmentOutcomeControl.viewAppoinmentOutcomes(loggedInUser);
        // appoinmentOutcomeControl.getAppointmentOutcomes();
        // appoinmentOutcomeControl.viewMedicalRecordsByDoctor(loggedInUser);

        // // Staff pharma = dataBaseManager.getStaffFileHandler().getUserById("P0001");

        // // User pharma = new Pharmacist("hi", "hi", Role.Pharmacist, Gender.Female, 30,
        // // "abc", "password");

        // // Staff pharma2 = new Pharmacist("name", "name", Role.Pharmacist, Gender.Male,
        // // 18, "null", "password");
        // String[] row = { "P0002", "Bernard", "Pharmacist", "Female", "32", "P0002", "password" };
        // // User staff = new Pharmacist(row[0], row[1], Role.valueOf(row[2]),
        // // Gender.valueOf(row[3]),
        // // Integer.parseInt(row[4]), row[5], row[6]);
        // Staff pharma = new Pharmacist("P123", "sohai", Role.Pharmacist, Gender.Female, 30, "null", "pw");
        // // InventoryControl.getInstance().showInventory(ph);
        // // appoinmentOutcomeControl.viewAppoinmentOutcomes(staff);

        // InventoryControl.getInstance().showInventory();
        // appoinmentOutcomeControl.updatePrescriptionStatus(pharma);

        // InventoryControl.getInstance().showInventory();

        // appoinmentOutcomeControl.viewMedicalRecordsByPharmacist(pharma);

        // User patient = new Patient("123", "123", "12-09-2001", Gender.Female, BloodType.AB_NEGATIVE, "lanjiao", "ok",
        //         "null");
        // appoinmentOutcomeControl.viewMedicalRecordsByPatient(patient);

        // appoinmentOutcomeControl.

        // appoinmentOutcomeControl.viewMedicalRecordsByPatient(patient);

        dataBaseManager.getOutcomeFileHandler().saveData();
        dataBaseManager.getInventoryFileHandler().saveData();
        dataBaseManager.getappointmentFileHandler().saveData();

        // Scanner sc = new Scanner(System.in);
        // DataBaseManager dbManager = DataBaseManager.getInstance();
        // dbManager.getappointmentFileHandler().retrieveData();
        // // For debugging
        // AppointmentManager aptManager = AppointmentManager.getInstance();
        // System.out.println("AppointmentManager has " +
        // aptManager.getAppointmentList().size() + " appointments");
        // //
        // // ArrayList<Patient> patientsData =
        // dbManager.getPatientFileHandler().retrieveData();
        // // ArrayList<Staff> staffData =
        // dbManager.getStaffFileHandler().retrieveData();

        // String currentUserID;
        // String currentUserPassword;
        // User loggedInUser = null;
        // Menu menu = null;

        // int choice;

        // // Display login/register menu with error handling
        // do {
        // System.out.println("Welcome To The HMS System");
        // System.out.println("1. LOGIN");
        // System.out.println("2. REGISTER");
        // System.out.println("3. EXIT");

        // try {
        // choice = sc.nextInt();
        // sc.nextLine();
        // } catch (InputMismatchException e) {
        // System.out.println("Invalid input. Please enter a number (1 or 2).");
        // sc.nextLine();
        // choice = 0;
        // }

        // if (choice == 2) {
        // PTSignup signUp = new PTSignup();
        // signUp.signUp();
        // continue;
        // }
        // // LOGIN
        // else if (choice == 1) {
        // System.out.println("Enter your User ID followed by Password to login.");

        // System.out.print("User ID: ");
        // currentUserID = sc.nextLine();
        // System.out.print("Password: ");
        // currentUserPassword = sc.nextLine();

        // // Check if user is a Patient or Staff
        // if (currentUserID.startsWith("PT")) {
        // // Attempt to log in as Patient
        // PatientFileHandler patientFileHandler = dbManager.getPatientFileHandler();
        // if (patientFileHandler.checkLogin(currentUserID, currentUserPassword)) {
        // loggedInUser = patientFileHandler.getUserById(currentUserID); // Store as
        // Patient object
        // System.out.println("Login successful as Patient.");
        // }
        // } else {
        // // Attempt to log in as Staff
        // StaffFileHandler staffFileHandler = dbManager.getStaffFileHandler();
        // if (staffFileHandler.checkLogin(currentUserID, currentUserPassword)) {
        // loggedInUser = staffFileHandler.getUserById(currentUserID); // Store as Staff
        // object
        // System.out.println("Login successful as Staff. Role: " + ((Staff)
        // loggedInUser).getRole());
        // }
        // }
        // }

        // if (loggedInUser != null) {
        // if (loggedInUser instanceof Patient) {
        // System.out.println("Accessing Patient Menu...");
        // Patient patient = (Patient) loggedInUser;
        // menu = new PatientMenu(patient);
        // } else if (loggedInUser instanceof Staff) {
        // Staff staffUser = (Staff) loggedInUser;
        // switch (staffUser.getRole()) {
        // case Doctor:
        // System.out.println("Doctor Menu");
        // // menu=new DoctorMenu();
        // break;
        // case Pharmacist:
        // System.out.println("Pharmacist Menu");
        // menu = new PharmacistMenu();
        // break;
        // case Administrator:
        // System.out.println("Administrator Menu");
        // menu = new AdministratorMenu();
        // break;
        // default:
        // System.out.println("Exit");
        // break;
        // }
        // }
        // menu = MenuFactory.createMenu(loggedInUser);
        // if (menu != null) {
        // menu.displayMenu(loggedInUser);
        // // Clear menu & user details after logout
        // loggedInUser = null;
        // menu = null;
        // }
        // }
        // else{ // failed login loggedInUser is null
        // System.out.println("User login failed.");
        // }

        // } while (choice != 3);

        // Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        // // Code to be executed when the JVM shuts down
        // System.out.println("Program is exiting. Performing cleanup...");
        // // Add any cleanup or resource release logic here
        // dbManager.getappointmentFileHandler().saveData();
        // }));
    }
}

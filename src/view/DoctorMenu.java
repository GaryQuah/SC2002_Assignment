package view;

import ServiceClasses.Appointment.Appointment;
import ServiceClasses.Appointment.AppointmentManager;
import ServiceClasses.AppointmentOutcome.AppoinmentOutcomeControl;
import ServiceClasses.AppointmentOutcome.AppointmentOutcome;
import ServiceClasses.Database.DataBaseManager;
import ServiceClasses.MedicalRecordService;
import input.IntInput;
import models.Doctor;
import models.Patient;
import models.MedicalRecord;
import models.User;
import models.enums.BloodType;
import models.enums.Gender;

import java.io.IOException;
import java.io.Serial;
import java.util.HashMap;
import java.util.Scanner;

public class DoctorMenu implements Menu {

    public void header() {
        System.out.println();
        System.out.println("---------- Doctor Menu ----------");
        System.out.println("1. View Patient Medical Records");
        System.out.println("2. Update Patient Medical Records");
        System.out.println("3. View Personal Schedule");
        System.out.println("4. Set Availability for Appointments");
        System.out.println("5. Accept or Decline Appointment Requests");
        System.out.println("6. View Upcoming Appointments");
        System.out.println("7. Record Appointment Outcome");
        System.out.println("8. Logout");
    }

    @Override
    public void displayMenu(User loggedInUser) { // Ensure this matches the interface
        if (!(loggedInUser instanceof Doctor)) {
            System.out.println("Access Denied: Only Doctors can access this menu.");
            return;
        }

        Doctor doctor = (Doctor) loggedInUser; // Safely cast the logged-in user to Doctor
        int choice;

        do {
            DataBaseManager.getInstance().getInventoryFileHandler().retrieveData();
            DataBaseManager.getInstance().getOutcomeFileHandler().retrieveData();
            DataBaseManager.getInstance().getappointmentFileHandler().retrieveData();

            header();
            choice = IntInput.integer("Option");

            switch (choice) {
                case 1 -> viewPatientMedicalRecords(doctor);
                case 2 -> updatePatientMedicalRecords(doctor);
                case 3 -> viewPersonalSchedule(doctor);
                case 4 -> setAvailability(doctor);
                case 5 -> handleAppointmentRequests(doctor);
                case 6 -> viewUpcomingAppointments(doctor);
                case 7 -> recordAppointmentOutcome(doctor);
                case 8 -> System.out.println("Logout...");
                default -> System.out.println("Invalid Option. Please try again.");
            }
            DataBaseManager.getInstance().getInventoryFileHandler().saveData();
            DataBaseManager.getInstance().getOutcomeFileHandler().saveData();
            DataBaseManager.getInstance().getappointmentFileHandler().saveData();
        } while (choice != 8);
    }

    private void viewPatientMedicalRecords(Doctor doctor) {
        AppoinmentOutcomeControl.getInstance().viewMedicalRecordsByDoctor(doctor);
    }

    private void updatePatientMedicalRecords(Doctor doctor) {
        AppoinmentOutcomeControl.getInstance().edit(doctor);
    }

    private void viewPersonalSchedule(Doctor doctor) {
        System.out.println("Fetching your personal schedule...");
        AppointmentManager.getInstance().getAppointmentViewer().ViewDoctorAppointments(doctor.getName());
    }

    private void setAvailability(Doctor doctor) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your available start time (HH:mm): ");
        String startTime = sc.nextLine();
        System.out.print("Enter your available end time (HH:mm): ");
        String endTime = sc.nextLine();

        boolean success = AppointmentManager.getInstance().getAppointmentScheduler()
                .SetDoctorAvailability(doctor.getName(), startTime, endTime);

        if (success) {
            System.out.println("Availability updated successfully.");
        } else {
            System.out.println("Failed to update availability.");
        }
    }

    private void handleAppointmentRequests(Doctor doctor) {
        System.out.println("Managing appointment requests...");
        AppointmentManager.getInstance().getAppointmentScheduler().ManageDoctorAppointments(doctor.getName());
    }

    private void viewUpcomingAppointments(Doctor doctor) {
        System.out.println("Your upcoming appointments:");
        AppointmentManager.getInstance().getAppointmentViewer().ViewUpcomingAppointments(doctor.getName());
    }

    private void recordAppointmentOutcome(Doctor doctor) {
        int appointmentId = IntInput.integer("Enter Appointment ID");
        Appointment appointment = AppointmentManager.getInstance().getAppointmentScheduler().getAppointmentByID(appointmentId);
        if (appointment == null) {
            System.out.println("Invalid Appoinment ID.");
        } else {
            System.out.println("Found." + appointment.getDoctorName());
            AppoinmentOutcomeControl.getInstance().create(doctor, appointment);
        }

    }
}

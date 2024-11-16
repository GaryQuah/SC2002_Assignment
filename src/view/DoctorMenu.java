package view;

import ServiceClasses.Appointment.AppointmentManager;
import ServiceClasses.AppointmentOutcome.AppointmentOutcome;
import ServiceClasses.MedicalRecordService;
import input.IntInput;
import models.Doctor;
import models.Patient;
import models.User;

import java.io.IOException;
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

    public void displayMenu(User loggedInUser) {
        if (!(loggedInUser instanceof Doctor)) {
            System.out.println("Access Denied: Only Doctors can access this menu.");
            return;
        }

        Doctor doctor = (Doctor) loggedInUser; // Casting User to Doctor
        int choice;

        do {
            header();
            choice = IntInput.integer("Option");

            switch (choice) {
                case 1:
                    viewPatientMedicalRecords();
                    break;
                case 2:
                    updatePatientMedicalRecords(doctor);
                    break;
                case 3:
                    viewPersonalSchedule(doctor);
                    break;
                case 4:
                    setAvailability(doctor);
                    break;
                case 5:
                    handleAppointmentRequests(doctor);
                    break;
                case 6:
                    viewUpcomingAppointments(doctor);
                    break;
                case 7:
                    recordAppointmentOutcome(doctor);
                    break;
                case 8:
                    System.out.println("Logout...");
                    return;

                default:
                    System.out.println("Invalid Option. Please try again.");
                    break;
            }
        } while (choice != 8);
    }

    private void viewPatientMedicalRecords() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter patient ID to view records: ");
        String patientId = sc.nextLine();

        // try {
        //     MedicalRecordService.viewMedicalRecord(new Patient(patientId));
        // } catch (IOException e) {
        //     System.out.println("Error fetching medical records: " + e.getMessage());
        // }
    }

    private void updatePatientMedicalRecords(Doctor doctor) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter patient ID to update records: ");
        String patientId = sc.nextLine();

        System.out.print("Enter new diagnosis: ");
        String diagnosis = sc.nextLine();

        System.out.print("Enter new treatment: ");
        String treatment = sc.nextLine();

        System.out.print("Enter the date (dd-mm-yyyy): ");
        String date = sc.nextLine();

        try {
            MedicalRecordService.updateMedicalRecord(
                    new models.MedicalRecord(patientId, doctor.getUserID(), diagnosis, treatment, date));
            System.out.println("Medical record updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating medical records: " + e.getMessage());
        }
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
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter appointment ID: ");
        int appointmentId = IntInput.integer("Appointment ID");

        System.out.print("Enter type of service provided: ");
        String serviceType = sc.nextLine();

        System.out.print("Enter consultation notes: ");
        String consultationNotes = sc.nextLine();

        HashMap<String, Integer> medications = new HashMap<>();
        System.out.print("Enter medication (format: name=quantity, separate multiple with commas): ");
        String medicationInput = sc.nextLine();
        if (!medicationInput.trim().isEmpty()) {
            String[] meds = medicationInput.split(",");
            for (String med : meds) {
                String[] parts = med.split("=");
                if (parts.length == 2) {
                    medications.put(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                }
            }
        }

        // boolean success = AppointmentManager.getInstance()
        //         .getAppointmentOutcomeControl()
        //         .create(
        //                 doctor,
        //                 new AppointmentOutcome(
        //                         appointmentId,
        //                         null,
        //                         doctor.getName(),
        //                         serviceType,
        //                         null,
        //                         medications,
        //                         consultationNotes));

        // if (success) {
        //     System.out.println("Appointment outcome recorded successfully.");
        // } else {
        //     System.out.println("Failed to record appointment outcome.");
        // }
    }
}

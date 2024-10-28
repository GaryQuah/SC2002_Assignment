package models;

import java.util.List;

import ServiceClasses.Appointment;
import ServiceClasses.AppointmentManager;
import ServiceClasses.MedicalRecordService;
import models.enums.Gender;
import models.enums.Role;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Patient extends User {
    // ------------------ variables -----------------------------
    private String emailAddress;
    private String phoneNumber;
    private MedicalRecord medicalRecord;
    private AppointmentManager appointmentManager = new AppointmentManager();

    // ------------------- Functions ---------------------------

    // Creates a "Patient" after passing the user's username. default password is
    // "password", default role is "Patient"
    public Patient(String userName, MedicalRecord medicalRecord) {
        super(userName, Role.Patient);
        this.medicalRecord = medicalRecord;
        this.emailAddress = medicalRecord.getEmailAddress(); // initialize with medical record email
        this.phoneNumber = medicalRecord.getPhoneNumber(); // initialize with medical record phone number
    }

    // ------------------ setters ---------------------

    public void updateEmailAddress(String newEmail) {
        this.emailAddress = newEmail;
        medicalRecord.setEmailAddress(newEmail); // sync with medical record
    }

    public void updatePhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
        medicalRecord.setPhoneNumber(newPhoneNumber); // sync with medical record
    }
    // ------------------ getters ---------------------

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    // ------------------ Appointment Management ------------------

    public void scheduleAppointment(String doctorName, String appointmentDate,
            String timeSlot, String appointmentType, int appointmentID) {

        String patientName = medicalRecord.getName();

        boolean isSlotAvailable = appointmentManager.ScheduleAppointment(doctorName, patientName, appointmentDate,
                timeSlot,
                appointmentType);
        if (isSlotAvailable) {
            System.out.println("Appointment scheduled successfully.");
        } else {
            System.out.println("Failed to schedule appointment.");
        }

    }

    // cancel patient appointment
    public void cancelPatientAppointment(String doctorName, String appointmentDate,
            String timeSlot) {

        String patientName = medicalRecord.getName();

        boolean isCancelled = appointmentManager.CancelAppointment(doctorName, patientName, appointmentDate, timeSlot);
        if (isCancelled) {
            System.out.println("Appointment cancelled.");
        } else {
            System.out.println("Failed to cancel appointment.");
        }
    }

    // reschedule patient appointment
    public void reschedulePatientAppointment(String doctorName, String newAppointmentDate,
            String newTimeSlot, String appointmentType, String oldAppointmentDate, String oldTimeSlot) {
        String patientName = medicalRecord.getName();

        appointmentManager.ReScheduleAppointment(doctorName, patientName, newAppointmentDate, newTimeSlot,
                appointmentType,
                oldAppointmentDate, oldTimeSlot);
    }

    // get patient past appointments
    public void getPastPatientAppointments() {
        String patientName = medicalRecord.getName();

        Vector<Appointment> pastAppointments = appointmentManager.getPastAppointments(patientName);

        if (pastAppointments.isEmpty()) {
            System.out.println("No past apppointments found.");
        } else {
            for (Appointment appointment : pastAppointments) {
                System.out.println(appointment);
            }
        }
    }

    // view scheduled appointments
    public void viewPatientScheduledAppointments() {
        String patientName = medicalRecord.getName();

        appointmentManager.ViewPatientAppointments(patientName);
    }

    public void viewAvailableAppointments(String doctorName) {
        appointmentManager.ViewAvailableDates(doctorName);
    }

    // view medical records
    public void ViewMedicalRecord() {
        try {
            List<MedicalRecord> allMedicalRecords = MedicalRecordService.getAllMedicalRecords();
            System.out.println("------ Medical Records ------");
            boolean recordFound = false;

            for (MedicalRecord record : allMedicalRecords) {
                if (record.getPatientId().equals(medicalRecord.getPatientId())) {
                    printRecord(record);
                    recordFound = true;
                }
            }
            if (!recordFound) {
                System.out.println("No medical records found for this patient.");
            }

        } catch (IOException e) {
            System.out.println("Error reading medical records: " + e.getMessage());

        }

    }

    public void printRecord(MedicalRecord medicalRecord) {
        System.out.println("Name: " + medicalRecord.getName());
        System.out.println("Patient ID: " + medicalRecord.getPatientId());
        System.out.println("Date of Birth: " + medicalRecord.getDateOfBirth());
        System.out.println("Gender: " + medicalRecord.getGender());
        System.out.println("Contact Number: " + medicalRecord.getPhoneNumber());
        System.out.println("Email: " + medicalRecord.getEmailAddress());
        System.out.println("Blood Type: " + medicalRecord.getBloodType());
        System.out.println("------ Medical History ------");
    }

}
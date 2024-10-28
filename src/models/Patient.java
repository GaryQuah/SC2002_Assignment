package models;

import java.util.List;

import ServiceClasses.Appointment;
import ServiceClasses.AppointmentManager;
import models.enums.Gender;
import models.enums.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Patient extends User {
    // ------------------ Variables -----------------------------
    private String emailAddress;
    private int phoneNumber;
    private MedicalRecord medicalRecord; // Reference to MedicalRecord
    private AppointmentManager appointmentManager = new AppointmentManager();

    // ------------------- Functions ---------------------------

    // Creates a "Patient" after passing the user's username. default password is
    // "password", default role is "Patient"
    public Patient(String userName, MedicalRecord medicalRecord) {
        super(userName, Role.Patient);
        this.medicalRecord = medicalRecord;
        this.emailAddress = medicalRecord.getEmailAddress(); // initialize with medical record email
        this.phoneNumber = Integer.parseInt(medicalRecord.getPhoneNumber()); // initialize phone number
    }

    // ------------------ setters ---------------------

    public void updateEmailAddress(String newEmail) {
        this.emailAddress = newEmail;
        medicalRecord.setEmailAddress(newEmail); // sync with medical record
    }

    public void updatePhoneNumber(int newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
        medicalRecord.setPhoneNumber(String.valueOf(newPhoneNumber)); // sync with medical record
    }
    // ------------------ getters ---------------------

    public int getPhoneNumber() {
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

    /*
     * // To view medical records
     * // shift this to view
     * public void ViewMedicalRecord() {
     * System.out.println("Name: " + m_Name);
     * System.out.println("Patient ID: " + m_PatientID);
     * System.out.println("Date of Birth: " + m_DateOfBirth);
     * System.out.println("Gender: " + m_Gender);
     * System.out.println("Contact Number: " + m_PhoneNumber);
     * System.out.println("Email: " + m_EmailAddress);
     * System.out.println("Blood Type: " + m_BloodType);
     * System.out.println("------ Medical History ------");
     * 
     * for (MedicalRecord record : m_MedicalRecords) {
     * // print records
     * }
     * 
     * }
     */

    // Patients are not allowed to modify the past diagnoses, prescribed
    // medications, treatments or blood type..
}

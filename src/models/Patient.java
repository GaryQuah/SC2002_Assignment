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
    private String m_PatientID;
    private String m_Name;
    private String m_DateOfBirth;
    private Gender m_Gender;
    private String m_EmailAddress;
    private int m_PhoneNumber;
    // private String m_BloodType;
    // private List<MedicalRecord> m_MedicalRecords; // update this

    // ------------------- Functions ---------------------------

    AppointmentManager appointmentManager = new AppointmentManager();

    // Creates a "Patient" after passing the user's username. default password is
    // "password", default role is "Patient"
    public Patient(String m_UserName) {
        super(m_UserName, Role.Patient); // Calls the constructor of the User class

    }

    // Patients can update non-medical personal information such as email address
    // and contact number.
    public void updateEmail(String m_EmailString) {
        this.m_EmailAddress = m_EmailString;
    }

    public void updatePhoneNumber(int m_PhoneNumber) {
        this.m_PhoneNumber = m_PhoneNumber;
    }

    // check appointment manager methods...
    // Schedule appointment
    // choose doctor, date, available time slot
    public void scheduleAppointment(String doctorName, String appointmentDate,
            String timeSlot, String appointmentType, int appointmentID) {

        boolean isSlotAvailable = appointmentManager.ScheduleAppointment(doctorName, m_Name, appointmentDate, timeSlot,
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
        boolean isCancelled = appointmentManager.CancelAppointment(doctorName, m_Name, appointmentDate, timeSlot);
        if (isCancelled) {
            System.out.println("Appointment cancelled.");
        } else {
            System.out.println("Failed to cancel appointment.");
        }
    }

    // reschedule patient appointment
    public void reschedulePatientAppointment(String doctorName, String newAppointmentDate,
            String newTimeSlot, String appointmentType, String oldAppointmentDate, String oldTimeSlot) {
        appointmentManager.ReScheduleAppointment(doctorName, m_Name, newAppointmentDate, newTimeSlot, appointmentType,
                oldAppointmentDate, oldTimeSlot);
    }

    public void getPastPatientAppointments() {
        Vector<Appointment> pastAppointments = appointmentManager.getPastAppointments(m_Name);

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
        appointmentManager.ViewPatientAppointments(m_Name);
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

package models;

import java.util.List;

import ServiceClasses.Appointment.Appointment;
import ServiceClasses.Appointment.AppointmentManager;
import ServiceClasses.MedicalRecordService;

import models.enums.Gender;
import models.enums.Role;
import models.enums.BloodType;

import java.io.IOException;

import java.util.Vector;

public class Patient extends User {
    // ------------------ variables -----------------------------
    private String ContactInformation;
    private MedicalRecord medicalRecord;
    private int patientID;
    private String patientName;
    private String dateOfBirth;
    private BloodType bloodType;
    private Gender gender;
    // ------------------- Functions ---------------------------

    // Creates a "Patient" after passing the user's username. default password is
    // "password", default role is "Patient"
    public Patient(int patientID, String patientName, String DOB,
            Gender gender, BloodType bloodType, String ContactInformation, String userName) {
        super(userName, Role.Patient, gender);
        this.ContactInformation = ContactInformation;
        this.patientID = patientID;
        this.gender = gender;
        this.patientName = patientName;
        this.dateOfBirth = DOB;
        this.bloodType = bloodType;
    }

    // ------------------ setters ---------------------

    public void updateContactInfo(String newContactInfo) {
        this.ContactInformation = newContactInfo;
    }

    // ------------------ getters ---------------------

    public String getContactInfo() {
        return this.ContactInformation;
    }

    // ------------------ Appointment Management ------------------

    public void scheduleAppointment(String doctorName, String appointmentDate,
            String timeSlot, String appointmentType, int appointmentID) {

        boolean isSlotAvailable = AppointmentManager.getInstance().ScheduleAppointment(doctorName, patientName,
                appointmentDate,
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

        boolean isCancelled = AppointmentManager.getInstance().CancelAppointment(doctorName, patientName,
                appointmentDate, timeSlot);
        if (isCancelled) {
            System.out.println("Appointment cancelled.");
        } else {
            System.out.println("Failed to cancel appointment.");
        }
    }

    // reschedule patient appointment
    public void reschedulePatientAppointment(String doctorName, String newAppointmentDate,
            String newTimeSlot, String appointmentType, String oldAppointmentDate, String oldTimeSlot) {
        AppointmentManager.getInstance().ReScheduleAppointment(doctorName, patientName, newAppointmentDate, newTimeSlot,
                appointmentType,
                oldAppointmentDate, oldTimeSlot);
    }

    // get patient past appointments
    public void getPastPatientAppointments() {

        Vector<Appointment> pastAppointments = AppointmentManager.getInstance().getPastAppointments(patientName);

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

        AppointmentManager.getInstance().ViewPatientAppointments(patientName);
    }

    public void viewAvailableAppointments(String doctorName) {
        AppointmentManager.getInstance().ViewAvailableDates(doctorName);
    }

    // view medical records
    public void ViewMedicalRecord() {
        try {
            List<MedicalRecord> allMedicalRecords = MedicalRecordService.getAllMedicalRecords();
            System.out.println("------ Medical Records ------");
            boolean recordFound = false;

            // print out patient details once
            System.out.println("Name: " + patientName);
            System.out.println("Patient ID: " + patientID);
            System.out.println("Date of Birth: " + dateOfBirth);
            System.out.println("Gender: " + gender);
            System.out.println("Contact Information: " + ContactInformation);
            System.out.println("Blood Type: " + bloodType);

            // print out all records
            for (MedicalRecord record : allMedicalRecords) {
                if (record.getPatientId().equals(medicalRecord.getPatientId())) {
                    printRecord(record);
                    recordFound = true;
                }
            }
            if (!recordFound) {
                System.out.println("No medical records found for this patient.");
            }

            System.out.println("------ Medical History ------");

        } catch (IOException e) {
            System.out.println("Error reading medical records: " + e.getMessage());

        }

    }

    public void printRecord(MedicalRecord medicalRecord) {
        System.out.println("Blood Type: " + medicalRecord.getDiagnosis());
        System.out.println("Blood Type: " + medicalRecord.getTreatment());
    }

}
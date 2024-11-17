package ServiceClasses.AppointmentOutcome;

import java.util.HashMap;

import models.enums.Status;

public class AppointmentOutcome {

    private int appointmentID;
    private String patientName;
    private String doctorName;


    private String serviceType;
    private String dateTime;
    private HashMap<String, Integer> prescribedMedications;
    private Status status; 

    private String consultationNotes;

    /**
     * Constructs a new AppointmentOutcome with the specified details.
     * Sets the appointment medication dispsense status to PENDING by default.
     *
     * @param appointmentID the unique identifier of the appointment
     * @param patientName the name of the patient
     * @param doctorName the name of the doctor
     * @param serviceType the type of service provided in the appointment
     * @param dateTime the date and time of the appointment
     * @param prescribedMedications a map of medications prescribed, with quantity
     * @param consultationNotes notes from the doctor's consultation
     */
    public AppointmentOutcome(int appointmentID, String patientName, String doctorName, String serviceType,
            String dateTime, HashMap<String, Integer> prescribedMedications, String consultationNotes) {
        this.appointmentID = appointmentID;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.serviceType = serviceType;
        this.dateTime = dateTime;
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
        this.status = Status.PENDING;
    }

    /**
     * Constructs a new AppointmentOutcome with the specified details and status.
     *
     * @param appointmentID the unique identifier of the appointment
     * @param patientName the name of the patient
     * @param doctorName the name of the doctor
     * @param serviceType the type of service provided in the appointment
     * @param dateTime the date and time of the appointment
     * @param prescribedMedications a map of medications prescribed, with quantity
     * @param consultationNotes notes from the doctor's consultation
     * @param status the current medication dispense status of the appointment (DISPENSED or PENDING)
     */
    public AppointmentOutcome(int appointmentID, String patientName, String doctorName, String serviceType,
            String dateTime, HashMap<String, Integer> prescribedMedications, String consultationNotes, Status status) {
        this.appointmentID = appointmentID;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.serviceType = serviceType;
        this.dateTime = dateTime;
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
        this.status = status;
    }

    /**
     * Gets the unique identifier for the appointment.
     *
     * @return the appointmentID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Sets a new unique identifier for the appointment.
     *
     * @param appointmentID the new appointment ID
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Gets the name of the patient associated with the appointment.
     *
     * @return the patient's name
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Sets a new name for the patient associated with the appointment.
     *
     * @param patientName the new patient's name
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * Gets the type of service provided in the appointment.
     *
     * @return the service type
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets a new type of service for the appointment.
     *
     * @param serviceType the new service type
     */   
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Gets the date and time of the appointment.
     *
     * @return the appointment date and time
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets a new date and time for the appointment.
     *
     * @param dateTime the new appointment date and time
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets the map of prescribed medications and their quantities.
     *
     * @return a map of medication names and quantities
     */
    public HashMap<String, Integer> getPrescribedMedications() {
        return prescribedMedications;
    }

    /**
     * Sets a new list of prescribed medications with quantities.
     *
     * @param prescribedMedications a map of medication names and quantities
     */
    public void setPrescribedMedications(HashMap<String, Integer> prescribedMedications) {
        this.prescribedMedications = prescribedMedications;
    }

    /**
     * Gets the consultation notes from the doctor.
     *
     * @return the consultation notes
     */
    public String getConsultationNotes() {
        return consultationNotes;
    }

    /**
     * Sets new consultation notes for the appointment.
     *
     * @param consultationNotes the new consultation notes
     */
    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    /**
     * Gets the current medication dispsense status of the appointment (DISPENSED or PENDING).
     *
     * @return the appointment status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the current status of the appointment.
     *
     * @param status the new status (COMPLETED or PENDING)
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets the name of the doctor associated with the appointment.
     *
     * @return the doctor's name
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * Sets a new name for the doctor associated with the appointment.
     *
     * @param doctorName the new doctor's name
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}

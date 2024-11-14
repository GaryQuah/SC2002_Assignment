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
    private Status status; // True for Completed and False for Pending

    private String consultationNotes;

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

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public HashMap<String, Integer> getPrescribedMedications() {
        return prescribedMedications;
    }

    public void setPrescribedMedications(HashMap<String, Integer> prescribedMedications) {
        this.prescribedMedications = prescribedMedications;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}

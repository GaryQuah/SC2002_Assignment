package ServiceClasses.Appointment;

import ServiceClasses.Database.DataBaseManager;

import java.util.HashMap;

/**
 * Represents an appointment in a hospital management system.
 */
public class Appointment {
    // Variable Declaration
    private String m_DoctorName;
    private String m_PatientName;
    private String m_AppointmentDate; // Format: DDMMYY
    private String m_TimeSlot;
    private AppointmentStatus m_AppointmentStatus; // 0 = unaccepted, 1 = accepted, -1 = declined, 2 = completed.
    private String m_AppointmentType;
    private int m_AppointmentID;

    // For completed appointments
    private HashMap<String, Integer> medicationMap;
    private String dispenseStatus;
    private String consultationNotes;

    // End of variable declaration

    // Constructors

    /**
     * Creates a new appointment with default status as unaccepted.
     *
     * @param m_DoctorName       the doctor's name
     * @param m_PatientName      the patient's name
     * @param m_AppointmentDate  the date of the appointment in DDMMYY format
     * @param m_TimeSlot         the time slot for the appointment
     * @param m_AppointmentType  the type of the appointment (e.g., consultation, follow-up)
     */
    public Appointment(String m_DoctorName, String m_PatientName, String m_AppointmentDate, String m_TimeSlot,
                       String m_AppointmentType) {
        this.m_AppointmentDate = m_AppointmentDate;
        this.m_DoctorName = m_DoctorName;
        this.m_TimeSlot = m_TimeSlot;
        this.m_PatientName = m_PatientName;
        this.m_AppointmentType = m_AppointmentType;
        m_AppointmentStatus = AppointmentStatus.UNACCEPTED;

        // Gets a new ID on creation
        this.m_AppointmentID = AppointmentManager.getInstance().getNewID();
    }

    /**
     * Creates an appointment with an existing status, used for initializing from CSV data.
     *
     * @param m_AppointmentID     the ID of the appointment
     * @param m_DoctorName        the doctor's name
     * @param m_PatientName       the patient's name
     * @param m_AppointmentDate   the date of the appointment in DDMMYY format
     * @param m_TimeSlot          the time slot for the appointment
     * @param m_AppointmentType   the type of the appointment
     * @param m_AppointmentStatus the current status of the appointment
     */
    public Appointment(int m_AppointmentID, String m_DoctorName, String m_PatientName, String m_AppointmentDate,
                       String m_TimeSlot, String m_AppointmentType, AppointmentStatus m_AppointmentStatus) {
        this.m_AppointmentDate = m_AppointmentDate;
        this.m_DoctorName = m_DoctorName;
        this.m_TimeSlot = m_TimeSlot;
        this.m_PatientName = m_PatientName;
        this.m_AppointmentType = m_AppointmentType;
        this.m_AppointmentStatus = m_AppointmentStatus;
        this.m_AppointmentID = m_AppointmentID;

        // Updates the max ID upon retrieving data from CSV
        AppointmentManager.getInstance().updateMaxID(m_AppointmentID);
    }
    // End of constructors

    // Getters

    /**
     * @return the dispense status of the appointment
     */
    public String getDispenseStatus() {
        return dispenseStatus;
    }

    /**
     * @return the consultation notes for the appointment
     */
    public String getConsultationNotes() {
        return consultationNotes;
    }

    /**
     * @return a map of medications and their quantities prescribed for the appointment
     */
    public HashMap<String, Integer> getMedicationMap() {
        return medicationMap;
    }

    /**
     * @return the doctor's name
     */
    public String getDoctorName() {
        return m_DoctorName;
    }

    /**
     * @return the patient's name
     */
    public String getPatientName() {
        return m_PatientName;
    }

    /**
     * @return the appointment date in DDMMYYYY format
     */
    public String getAppointmentDate() {
        return m_AppointmentDate;
    }

    /**
     * @return the time slot of the appointment in HH:MM format
     */
    public String getTimeSlot() {
        return m_TimeSlot;
    }

    /**
     * @return the current status of the appointment
     */
    public AppointmentStatus getAppointmentStatus() {
        return m_AppointmentStatus;
    }

    /**
     * @return the ID of the appointment
     */
    public int getAppointmentID() {
        return m_AppointmentID;
    }

    /**
     * @return the type of the appointment
     */
    public String getAppointmentType() {
        return m_AppointmentType;
    }

    // End of getters

    // Setters

    /**
     * Sets the dispense status of the appointment.
     *
     * @param dispenseStatus the status to set
     */
    public void setDispenseStatus(String dispenseStatus) {
        this.dispenseStatus = dispenseStatus;
    }

    /**
     * Sets the consultation notes for the appointment.
     *
     * @param consultationNotes the notes to set
     */
    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    /**
     * Sets the medication map for the appointment.
     *
     * @param medicationMap a map of medications and their quantities
     */
    public void setMeidcation(HashMap<String, Integer> medicationMap) {
        this.medicationMap = medicationMap;
    }

    /**
     * Sets the status of the appointment.
     *
     * @param m_Status the status to set
     * @return false if the operation is successful (default)
     */
    public boolean setAppointmentStatus(AppointmentStatus m_Status) {
        m_AppointmentStatus = m_Status;
        DataBaseManager.getInstance().getappointmentFileHandler().saveData();
        return false;
    }

    // End of setters

    /**
     * Returns a string representation of the appointment.
     *
     * @return a string containing the appointment details
     */
    @Override
    public String toString() {
        return ("Appointment ID : " + m_AppointmentID + ", Doctor : " + m_DoctorName + ", Patient : " + m_PatientName
                + ", Date : " + m_AppointmentDate +
                ", Time Slot : " + m_TimeSlot + ", Appointment Type : " + m_AppointmentType + ", Accepted : "
                + m_AppointmentStatus);
    }
}

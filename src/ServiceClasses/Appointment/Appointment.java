package ServiceClasses.Appointment;

import java.util.HashMap;

public class Appointment {
    // Variable Declaration
    private String m_DoctorName;
    private String m_PatientName;
    private String m_AppointmentDate; // String keep it as DDMMYY
    private String m_TimeSlot;
    private AppointmentStatus m_AppointmentStatus; // 0 = unaccepted , 1 = accepted , -1 = declined, 2 = completed.
    private String m_AppointmentType;
    private int m_AppointmentID;

    // For Completed Appointment
    private HashMap<String, Integer> medicationMap;
    private String dispenseStatus;
    private String consultationNotes;

    // End of variable declaration

    //Constructors
    //When creating a new appointment, get the AppointmentManager to provide the new MaxID. - status by default is unaccepted
    //For creating new appointments through the CLI
    public Appointment(/*int m_AppointmentID,*/ String m_DoctorName, String m_PatientName, String m_AppointmentDate, String m_TimeSlot,
                                                String m_AppointmentType) {
        this.m_AppointmentDate = m_AppointmentDate;
        this.m_DoctorName = m_DoctorName;
        this.m_TimeSlot = m_TimeSlot;
        this.m_PatientName = m_PatientName;
        this.m_AppointmentType = m_AppointmentType;
        m_AppointmentStatus = AppointmentStatus.UNACCEPTED;

        // Gets new ID on creation
        this.m_AppointmentID = AppointmentManager.getInstance().getNewID();
    }

    //For CSV creation - need to init with status
    public Appointment(int m_AppointmentID, String m_DoctorName, String m_PatientName, String m_AppointmentDate, String m_TimeSlot,
                       String m_AppointmentType, AppointmentStatus m_AppointmentStatus) {
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
    //End of constructors

    //Getters
    public String getDispenseStatus() {
        return dispenseStatus;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public HashMap<String, Integer> getMedicationMap() {
        return medicationMap;
    }

    // Methods

    //End of getters

    //Setters
    public void setDispenseStatus(String dispenseStatus) {
        this.dispenseStatus = dispenseStatus;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public void setMeidcation(HashMap<String, Integer> medicationMap)
    {
        this.medicationMap = medicationMap;
    }

    public boolean setAppointmentStatus(AppointmentStatus m_Status)
    {
        m_AppointmentStatus = m_Status;
        return false;
    }
    //End of Setters

    //Getters
    public String getDoctorName() {
        return m_DoctorName;
    }

    public String getPatientName() {
        return m_PatientName;
    }

    public String getAppointmentDate() {
        return m_AppointmentDate;
    }

    public String getTimeSlot() {
        return m_TimeSlot;
    }

    public AppointmentStatus getAppointmentStatus() {
        return m_AppointmentStatus;
    }

    public int getAppointmentID() {
        return m_AppointmentID;
    }

    public String getAppointmentType() {
        return m_AppointmentType;
    }
    //End of Getters

    // Override print
    @Override
    public String toString() {
        return ("Appointment ID : " + m_AppointmentID + ", Doctor : " + m_DoctorName + ", Patient : " + m_PatientName
                + ", Date : " + m_AppointmentDate +
                ", Time Slot : " + m_TimeSlot + ", Appointment Type : " + m_AppointmentType + ", Accepted : "
                + m_AppointmentStatus);
    }
}

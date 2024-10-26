package ServiceClasses;

public class Appointment {
    private String m_DoctorName;
    private String m_PatientName;
    private String m_AppointmentDate; // String keep it as DDMMYY
    private String m_TimeSlot;
    private int m_Accepted; // 0 = unaccepted , 1 = accepted , -1 = declined.
    private String m_AppointmentType;
    private int m_AppointmentID;

    // Constructor
    public Appointment(String m_DoctorName, String m_PatientName, String m_AppointmentDate, String m_TimeSlot,
            String m_AppointmentType, int m_AppointmentID) {
        this.m_AppointmentDate = m_AppointmentDate;
        this.m_DoctorName = m_DoctorName;
        this.m_TimeSlot = m_TimeSlot;
        this.m_AppointmentType = m_AppointmentType;
        m_Accepted = 0;
        // this.m_AppointmentID = UUID.randomUUID().toString(); - If we want a unique
        // 128 bit ID we can use this, for simplicity we shall use int values.
        this.m_AppointmentID = m_AppointmentID;
    }

    // Methods
    public boolean UpdateAppointmentStatus(String m_DoctorName, int m_Status) // Add a checker here with doctor name in
                                                                              // case wrong doctor tries to access the
                                                                              // appointment
    {
        if (this.m_DoctorName.equals(m_DoctorName)) {
            m_Accepted = m_Status;
            System.out.println(
                    "Successfully updated the appointment of ID " + this.m_AppointmentID + " to " + m_Accepted);
            return true;
        }
        System.out.println("Failed to update the appointment of ID " + this.m_AppointmentID + " to " + m_Accepted
                + " -- Wrong doctor name for this appointment.");
        return false;
    }

    // Getters
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

    public int appointmentStatus() {
        return m_Accepted;
    }

    public int getAppointmentID() {
        return m_AppointmentID;
    }

    // Setters here

    // Override print
    @Override
    public String toString() {
        return ("Appointment ID : " + m_AppointmentID + " Doctor : " + m_DoctorName + " Patient : " + m_PatientName
                + " Date : " + m_AppointmentDate +
                " Time Slot : " + m_TimeSlot + " Appointment Type : " + m_AppointmentType + " Accepted : "
                + m_Accepted);
    }
}

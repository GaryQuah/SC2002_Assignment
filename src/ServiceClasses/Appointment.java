package ServiceClasses;

public class Appointment {
    private String m_DoctorName;
    private String m_PatientName;
    private String m_AppointmentDate; //String keep it as DDMMYY
    private String m_TimeSlot;
    private boolean m_Accepted;
    private String m_AppointmentType;

    //Constructor
    public Appointment(String m_DoctorName, String m_PatientName , String m_AppointmentDate, String m_TimeSlot, String m_AppointmentType)
    {
        this.m_AppointmentDate = m_AppointmentDate;
        this.m_DoctorName = m_DoctorName;
        this.m_TimeSlot = m_TimeSlot;
        this.m_AppointmentType = m_AppointmentType;
        m_Accepted = false;
    }

    //Methods
    public void AcceptAppointment(String m_DoctorName) //Add a checker here with doctor name in case wrong doctor tries to access the appointment
    {
        if (this.m_DoctorName == m_DoctorName)
            m_Accepted = true;
    }

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
    
    public boolean isAccepted() {
        return m_Accepted;
    }

    //Setters
}

package models;
public class Appointment {
    private String m_DoctorName;
    private String m_AppointmentDate;
    private String m_TimeSlot;
    private boolean m_Accepted;

    public Appointment(String m_DoctorName, String m_AppointmentDate, String m_TimeSlot)
    {
        this.m_AppointmentDate = m_AppointmentDate;
        this.m_DoctorName = m_DoctorName;
        this.m_TimeSlot = m_TimeSlot;
        m_Accepted = false;
    }
}

package models;

import models.enums.Role;
import models.User;
import java.util.Vector;
import ServiceClasses.Appointment;
import ServiceClasses.AppointmentManager;
import ServiceClasses.StaffManager;

public class Administrator extends User
{
    AppointmentManager appointmentManager = AppointmentManager.getInstance();
    StaffManager staffManager = StaffManager.getInstance();

    public Administrator(String m_UserName) 
    {
        super(m_UserName, Role.Administrator); // Calls the constructor of the User class
        // m_StaffMembers = new Vector<Staffs>();
        m_Appointments = new Vector<Appointment>();
    }

    public void addStaff(String m_StaffID, String m_StaffName, String m_StaffRole, String m_StaffGender, int m_StaffAge)
    {
        StaffManager.getInstance().addStaff(m_StaffID, m_StaffName, m_StaffRole, m_StaffGender, m_StaffAge);
    }

    public void removeStaffById(String m_StaffID)
    {
        staffManager.removeStaffById(m_StaffID);
    }

    public void viewScheduledAppointments()
    {
        appointmentManager.ViewAllAppointmentsByStatus(1);
    }
}

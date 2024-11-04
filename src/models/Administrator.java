package models;

import models.enums.Gender;
import models.enums.Role;
import models.User;
import java.util.Vector;
import ServiceClasses.Appointment;
import ServiceClasses.AppointmentManager;
import ServiceClasses.CSVManager.StaffManager;

public class Administrator extends Staff
{
    // AppointmentManager appointmentManager = AppointmentManager.getInstance(); 
    StaffManager staffManager = StaffManager.getInstance();

    public Administrator(String username, String password, String staffId, String name, Role role, Gender gender, int age) {
        super(username, password, staffId, name, role, gender, age);
    }

    // public void addStaff(String m_StaffID, String m_StaffName, String m_StaffRole, String m_StaffGender, int m_StaffAge)
    // {
    //     StaffManager.getInstance().addStaff(m_StaffID, m_StaffName, m_StaffRole, m_StaffGender, m_StaffAge);
    // }

    // public void removeStaffById(String m_StaffID)
    // {
    //     staffManager.removeStaffById(m_StaffID);
    // }

    // public void viewScheduledAppointments()
    // {
    //     appointmentManager.ViewAllAppointmentsByStatus(1);
    // }
}

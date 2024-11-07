package models;

import models.enums.Gender;
import models.enums.Role;
//import java.util.Vector;
//import ServiceClasses.Appointment;
//import ServiceClasses.AppointmentManager;

public class Administrator extends Staff
{

    public Administrator(int staffId, String name, Role role, Gender gender, int age, String username, String password) {
        super(staffId, name, role, gender, age, username, password);
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

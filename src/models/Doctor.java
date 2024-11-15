package models;
import java.util.Vector;

import models.enums.Gender;
import models.enums.Role;

public class Doctor extends Staff implements DoctorAction {
    //------------------ Variables -----------------------------
    private Vector<String> m_PatientNames;

    //------------------- Functions ---------------------------

    //Creates a "Doctor" after passing the user's username. default password is "password", default role is "Doctor"
    // ID, name, role, gender, age, username, password
    public Doctor(String staffId, String name, Role role, Gender gender, int age, String password) {
        super(staffId, name, role, gender, age, password);
    }

    public void addPatient(String m_PatientName)
    {
        m_PatientNames.add(m_PatientName);
    }

    public void removePatient(String m_PatientName)
    {
        m_PatientNames.remove(m_PatientName);
    }
    
}

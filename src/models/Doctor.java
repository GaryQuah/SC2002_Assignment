package models;
import java.util.Vector;

import models.enums.Role;

public class Doctor extends User {
    //------------------ Variables -----------------------------
    private Vector<String> m_PatientNames;




    //------------------- Functions ---------------------------

    //Creates a "Doctor" after passing the user's username. default password is "password", default role is "Doctor"
    public Doctor(String m_UserName) {
        super(m_UserName, Role.Doctor); // Calls the constructor of the User class
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

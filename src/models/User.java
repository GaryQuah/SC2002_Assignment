package models;

import java.util.Vector;

import ServiceClasses.Appointment.Appointment;

import models.enums.Role;
import models.enums.Gender;

public abstract class User {
    // Users must log in to the system using their unique hospital ID and a default
    // password ("password").
    private String m_UserName;
    private String m_PassWord;
    private Gender m_Gender;

    // Users will have roles such as Patient, Doctor, Pharmacist or Administrator.
    private Role m_Role;

    // Stores appointment data
    private Vector<Appointment> m_Appointments;

    // Users can change their password after their initial login.
    public User(String m_UserName, Role m_Role, Gender m_Gender) {
        this.m_UserName = m_UserName;
        this.m_Role = m_Role;
        this.m_PassWord = "password";
        this.m_Gender = m_Gender;
    }

    public boolean ValidateUser(String m_UserName, String m_PassWord) {
        if (this.m_UserName.equals(m_UserName) && this.m_PassWord.equals(m_PassWord)) {
            return true;
        } else
            return false;
    }

    public String getUserID() {
        return this.m_UserName;
    }

    public String getPassword() {
        return this.m_PassWord;
    }

    public Role getRole() {
        return this.m_Role;
    }

    public void updatePassword(String newPassword) {
        this.m_PassWord = newPassword;
    }
}
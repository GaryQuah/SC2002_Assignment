package models;

import java.util.Vector;

import ServiceClasses.Appointment.Appointment;

import models.enums.Role;
import models.enums.Gender;

public abstract class User {
    // Users must log in to the system using their unique hospital ID and a default
    // password ("password").
    private int m_UserID;
    private String m_Name;
    private Gender m_Gender;
    private String m_UserName;
    private String m_PassWord;
    private Role m_Role;

    private static int maxID = 0;
    // Users will have roles such as Patient, Doctor, Pharmacist or Administrator.

    // Stores appointment data
    private Vector<Appointment> m_Appointments;

    public User(int userID, String name, Gender gender, String userName, String passWord, Role role) {
        m_UserID = userID;
        m_Name = name;
        m_Gender = gender;
        m_UserName = userName;
        m_PassWord = passWord;
        m_Role = role;

        if (userID > maxID) { maxID = userID; } //goes up to infinity
    }

    public boolean ValidateUser(String m_UserName, String m_PassWord) {
        if (this.m_UserName.equals(m_UserName) && this.m_PassWord.equals(m_PassWord)) {
            return true;
        } else
            return false;
    }

    public String getName()
    {
        return this.m_Name;
    }

    public int getUserID() {
        return this.m_UserID;
    }

    public String getPassword() {
        return this.m_PassWord;
    }

    public Role getRole() {
        return this.m_Role;
    }

    public Gender getGender() {
        return this.m_Gender;
    }

    public void updatePassword(String newPassword) {
        this.m_PassWord = newPassword;
    }

    public int getMaxID()
    {
        return maxID;
    }

    public abstract String toString();
}
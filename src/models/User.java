package models;

import java.util.Vector;

import ServiceClasses.Appointment.Appointment;

import models.enums.Role;
import models.enums.Gender;
import models.UserIDManager;

public abstract class User {
    // Users must log in to the system using their unique hospital ID and a default
    // password ("password").
    private String m_UserID;
    private String m_Name;
    private Gender m_Gender;
    private String m_UserName;
    private String m_PassWord;
    private Role m_Role;

    //private static int maxID = 0;
    // Users will have roles such as Patient, Doctor, Pharmacist or Administrator.

    // Stores appointment data
    private Vector<Appointment> m_Appointments;
    //When creating a user, the ID field would be UserIDManager.getInstance().getNewID(m_Role);
    public User(String userID, String name, Gender gender, String userName, String passWord, Role role) { 
        m_UserID = userID;
        m_Name = name;
        m_Gender = gender;
        m_UserName = userName;
        m_PassWord = passWord;
        m_Role = role;

        //Extracts the userID from
        String numericString = userID.replaceAll("[^0-9]", "");
        int numericValue = Integer.parseInt(numericString);

        UserIDManager.getInstance().updateID(m_Role, numericValue);
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

    public String getUserID() {
        return this.m_UserID;
    }

    public String getPassword() {
        return this.m_PassWord;
    }

    public String getUsername() {
        return this.m_UserName;
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

    public void setName(String newName) {
        this.m_Name = newName;
    }

    public void setRole(Role newRole) {
        this.m_Role = newRole;
    }

    public void setGender(Gender newGender) {
        this.m_Gender = newGender;
    }

    public abstract String toString();
}
package models;
import models.enums.Role;
import models.enums.Gender;


public class Staff extends User{

    private int m_age;

    public Staff(int  staffID, String staffName, Role role, Gender gender, int age, String username, String password) {
        super(staffID, staffName, gender, username , password , role);
        //this.m_staffID = staffID;
        this.m_age = age;
    }

    public String toString() {
        return "Staff ID : " + getUserID() + ", Name : " + getName() + ", Role " + getRole() +  ", Gender : " + getGender() + ", Age : " + m_age;
    }
}

package models;
import models.enums.Role;
import models.enums.Gender;


public class Staff extends User{

    private int m_age;

    public Staff(String  staffID, String staffName, Role role, Gender gender, int age, String password) {
        super(staffID, staffName, gender, password , role);
        //this.m_staffID = staffID;
        this.m_age = age;
    }
    
    public int getAge() {
        return m_age;
    }

    public void setAge(int newAge) {
        this.m_age = newAge;
    }

    @Override
    public String toString() {
        return "Staff ID : " + this.getUserID() + ", Name : " + getName() + ", Role " + getRole() + ", Gender : " + getGender() + ", Age : " + m_age;
    }
}

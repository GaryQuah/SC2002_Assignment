package models;
import models.enums.Role;
import models.enums.Gender;


public class Staff extends User{

    private String m_staffID;
    private String m_name;
    private Gender m_gender;
    private int m_age;

    public Staff(String username, String password, String staffId, String name, Role role, Gender gender, int age) {
        super(username, role, gender); // Calls the constructor of the User class
        this.m_staffID = staffId;
        this.m_name = name;
        this.m_gender = gender;
        this.m_age = age;
    }
}

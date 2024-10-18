package models;
import models.enums.Role;

public class Pharmacist extends User{
    public Pharmacist(String m_UserName) {
        super(m_UserName, Role.Pharmacist); // Calls the constructor of the User class
    }
}

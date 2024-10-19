package models;

import models.enums.Role;
import models.User;

public class Administrator extends User
{
    private String adminID;

    public Administrator(String m_UserName, String adminID) 
    {
        super(m_UserName, Role.Administrator); // Calls the constructor of the User class
        this.adminID = adminID;
    }

    // getter & setter functions
    public String getAdminID() {return this.adminID;}
    public void setAdminID(String adminID) {this.adminID = adminID;}
}

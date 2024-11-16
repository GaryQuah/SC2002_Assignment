package models;

import java.util.Vector;

import ServiceClasses.Appointment.Appointment;
import models.enums.Role;
import models.enums.Gender;
import models.UserIDManager;

/**
 * Abstract class representing a user in the system.
 *
 * This class serves as a base for specific user types like Patient, Doctor, Pharmacist, or Administrator.
 * It holds common properties such as user ID, name, gender, password, and role, along with methods to
 * manage these properties.
 */
public abstract class User implements IUserManagement {

    /** The unique user ID. */
    private String m_UserID;

    /** The name of the user. */
    private String m_Name;

    /** The gender of the user. */
    private Gender m_Gender;

    /** The password for the user. */
    private String m_PassWord;

    /** The role of the user (e.g., Patient, Doctor, Pharmacist, Administrator). */
    private Role m_Role;

    /**
     * Constructs a User with the specified details.
     *
     * @param userID the unique user ID.
     * @param name the name of the user.
     * @param gender the gender of the user.
     * @param passWord the password for the user.
     * @param role the role of the user (Patient, Doctor, etc.).
     */
    public User(String userID, String name, Gender gender, String passWord, Role role) {
        m_UserID = userID;
        m_Name = name;
        m_Gender = gender;
        m_PassWord = passWord;
        m_Role = role;

        // Extract numeric part from the user ID and update the ID manager for this role
        String numericString = userID.replaceAll("[^0-9]", "");
        int numericValue = Integer.parseInt(numericString);
        UserIDManager.getInstance().updateID(m_Role, numericValue);
    }

    // Getter methods for accessing user details

    /**
     * Gets the name of the user.
     *
     * @return the name of the user.
     */
    public String getName() {
        return this.m_Name;
    }

    /**
     * Gets the unique user ID.
     *
     * @return the user ID.
     */
    public String getUserID() {
        return this.m_UserID;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password of the user.
     */
    public String getPassword() {
        return this.m_PassWord;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role of the user.
     */
    public Role getRole() {
        return this.m_Role;
    }

    /**
     * Gets the gender of the user.
     *
     * @return the gender of the user.
     */
    public Gender getGender() {
        return this.m_Gender;
    }

    // Setter methods for modifying user details

    /**
     * Sets a new password for the user.
     *
     * @param newPassword the new password to set.
     */
    public void setPassword(String newPassword) {
        this.m_PassWord = newPassword;
    }

    /**
     * Sets a new name for the user.
     *
     * @param newName the new name to set.
     */
    public void setName(String newName) {
        this.m_Name = newName;
    }

    /**
     * Sets a new role for the user.
     *
     * @param newRole the new role to set (e.g., Patient, Doctor).
     */
    public void setRole(Role newRole) {
        this.m_Role = newRole;
    }

    /**
     * Sets a new gender for the user.
     *
     * @param newGender the new gender to set.
     */
    public void setGender(Gender newGender) {
        this.m_Gender = newGender;
    }

    /**
     * Sets a new user ID for the user.
     *
     * @param newUserID the new user ID to set.
     */
    public void setUserID(String newUserID) {
        this.m_UserID = newUserID;
    }

    /**
     * Abstract method that returns a string representation of the user.
     *
     * This method must be implemented by subclasses to return user-specific information.
     *
     * @return a string representation of the user.
     */
    public abstract String toString();
}

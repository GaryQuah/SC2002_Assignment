package models;

import models.enums.Role;
import models.enums.Gender;

/**
 * The {@code Staff} class represents a staff member within the system.
 * It extends the {@link User} class and adds additional attributes specific to staff.
 * <p>
 * This class includes details such as age and inherits properties related to user identity
 * including staff ID, name, gender, role, and password.
 * </p>
 */
public class Staff extends User {

    private int m_age;

    /**
     * Constructs a {@code Staff} member with the specified details.
     *
     * @param staffID the unique identifier for the staff member
     * @param staffName the name of the staff member
     * @param role the role of the staff member within the organization
     * @param gender the gender of the staff member
     * @param age the age of the staff member
     * @param password the password for the staff account
     */
    public Staff(String staffID, String staffName, Role role, Gender gender, int age, String password) {
        super(staffID, staffName, gender, password, role);
        this.m_age = age;
    }

    /**
     * Returns the age of the staff member.
     *
     * @return the age of the staff member
     */
    public int getAge() {
        return m_age;
    }

    /**
     * Sets the age of the staff member.
     *
     * @param newAge the new age of the staff member
     */
    public void setAge(int newAge) {
        this.m_age = newAge;
    }

    /**
     * Returns a string representation of the {@code Staff} member.
     * <p>
     * The string includes the staff ID, name, role, gender, and age.
     * </p>
     *
     * @return a string describing the staff member
     */
    @Override
    public String toString() {
        return "Staff ID : " + this.getUserID() +
                ", Name : " + getName() +
                ", Role : " + getRole() +
                ", Gender : " + getGender() +
                ", Age : " + m_age;
    }
}

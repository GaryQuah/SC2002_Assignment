package models;

import models.enums.Gender;
import models.enums.Role;

/**
 * The {@code Administrator} class represents an administrator staff member
 * within the system. It extends the {@link Staff} class and inherits its
 * properties and methods.
 * <p>
 * This class is used to create administrator objects with specific attributes
 * such as staff ID, name, role, gender, age, and password.
 * </p>
 */
public class Administrator extends Staff {

    /**
     * Constructs an {@code Administrator} with the specified details.
     *
     * @param staffId the unique identifier for the staff member
     * @param name the name of the administrator
     * @param role the role of the administrator within the organization
     * @param gender the gender of the administrator
     * @param age the age of the administrator
     * @param password the password for the administrator account
     */
    public Administrator(String staffId, String name, Role role, Gender gender, int age, String password) {
        super(staffId, name, role, gender, age, password);
    }

    /**
     * Returns a string representation of the {@code Administrator} object.
     * <p>
     * The string includes the staff ID, name, role, gender, age, and password.
     * </p>
     *
     * @return a string describing the administrator
     */
    @Override
    public String toString() {
        return "Administrator{" +
                "staffId='" + getUserID() + '\'' +
                ", name='" + getName() + '\'' +
                ", role=" + getRole() +
                ", gender=" + getGender() +
                ", age=" + getAge() +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}

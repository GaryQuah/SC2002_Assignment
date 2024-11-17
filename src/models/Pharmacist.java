package models;

import models.enums.Gender;
import models.enums.Role;

/**
 * The {@code Pharmacist} class represents a pharmacist staff member within the system.
 * It extends the {@link Staff} class, inheriting its attributes and methods.
 * <p>
 * This class is used to create pharmacist objects with specific attributes such as
 * staff ID, name, role, gender, age, and password.
 * </p>
 */
public class Pharmacist extends Staff {

    /**
     * Constructs a {@code Pharmacist} with the specified details.
     *
     * @param staffId the unique identifier for the pharmacist
     * @param name the name of the pharmacist
     * @param role the role of the pharmacist within the organization
     * @param gender the gender of the pharmacist
     * @param age the age of the pharmacist
     * @param password the password for the pharmacist account
     */
    public Pharmacist(String staffId, String name, Role role, Gender gender, int age, String password) {
        super(staffId, name, role, gender, age, password);
    }
}

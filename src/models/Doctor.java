package models;

import java.util.Vector;
import models.enums.Gender;
import models.enums.Role;

/**
 * The {@code Doctor} class represents a doctor staff member within the system.
 * It extends the {@link Staff} class and implements the {@link DoctorAction} interface.
 * <p>
 * This class provides functionality for managing a list of patients associated
 * with the doctor, in addition to inheriting staff-related attributes.
 * </p>
 */
public class Doctor extends Staff implements DoctorAction {

    //------------------ Variables -----------------------------
    private Vector<String> m_PatientNames;

    //------------------- Functions ---------------------------

    /**
     * Constructs a {@code Doctor} with the specified details.
     * <p>
     * The default password is set to "password" and the role is set to "Doctor".
     * </p>
     *
     * @param staffId the unique identifier for the doctor
     * @param name the name of the doctor
     * @param role the role of the doctor within the organization
     * @param gender the gender of the doctor
     * @param age the age of the doctor
     * @param password the password for the doctor account
     */
    public Doctor(String staffId, String name, Role role, Gender gender, int age, String password) {
        super(staffId, name, role, gender, age, password);
        m_PatientNames = new Vector<>(); // Initialize the patient names vector
    }

    /**
     * Adds a patient to the doctor's list of patients.
     *
     * @param m_PatientName the name of the patient to add
     */
    public void addPatient(String m_PatientName) {
        m_PatientNames.add(m_PatientName);
    }

    /**
     * Removes a patient from the doctor's list of patients.
     *
     * @param m_PatientName the name of the patient to remove
     */
    public void removePatient(String m_PatientName) {
        m_PatientNames.remove(m_PatientName);
    }
}

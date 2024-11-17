package models;

/**
 * The {@code DoctorAction} interface defines actions that a doctor can perform
 * related to managing their patients.
 * <p>
 * This interface includes methods for adding and removing patients from a doctor's
 * patient list, which can be implemented by any class representing a doctor.
 * </p>
 */
public interface DoctorAction {

    /**
     * Adds a patient to the doctor's list.
     *
     * @param patientName the name of the patient to be added
     */
    void addPatient(String patientName);

    /**
     * Removes a patient from the doctor's list.
     *
     * @param patientName the name of the patient to be removed
     */
    void removePatient(String patientName);
}

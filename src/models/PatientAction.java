package models;

/**
 * The PatientAction interface defines actions that can be performed by or on a patient.
 * <p>
 * This interface provides methods for retrieving and updating a patient's contact
 * information and emergency contact details.
 * </p>
 */
public interface PatientAction {
    String getContactInfo();
    void updateContactInfo(String newContactInfo);
    void updateEmergencyContact(String name, String relation, String number);
    String getEmergencyContactInfo();
}

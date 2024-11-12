package models;


public interface PatientAction {
    String getContactInfo();
    void updateContactInfo(String newContactInfo);
    void updateEmergencyContact(String name, String relation, String number);
    String getEmergencyContactInfo();
}

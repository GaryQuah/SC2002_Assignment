package models;

import models.enums.Gender;
import models.enums.Role;
import models.enums.BloodType;

//implements PatientInfo Interface
public class Patient extends User implements PatientAction {
    // ------------------ variables -----------------------------
    private String contactInformation;
    private MedicalRecord medicalRecord;
    private String dateOfBirth;
    private BloodType bloodType;

    private String emergencyContactName;
    private String emergencyContactRelation;
    private String emergencyContactNumber;
    // ------------------- Functions ---------------------------

    // Creates a "Patient" after passing the user's username. default password is
    // "password", default role is "Patient"

    public Patient(String patientID, String patientName, String DOB,
            Gender gender, BloodType bloodType, String contactInformation, String userName, String password) {
        super(patientID, patientName, gender, userName , password , Role.Patient);
        this.contactInformation = contactInformation;
        this.dateOfBirth = DOB;
        this.bloodType = bloodType;
        this.emergencyContactName = "No emergency contact name";
        this.emergencyContactRelation = "No emergency contact relation";
        this.emergencyContactNumber = "No emergency contact number";
    }

    // Overloading constructor for Patient with emergency contact details

    /**
     *
     * @param patientID The unique identifier for patient.
     * @param patientName   The name of patient.
     * @param DOB   The date of birth of the patient in string format.
     * @param gender     The genfer of patient.
     * @param bloodType  The blood type of the patient.
     * @param contactInformation     The contact information of the patient.
     * @param userName  The username for the patient.
     * @param password  The password for the patient
     * @param emergencyContactName  The name of patient's emergency contact
     * @param emergencyContactRelation  The relation between patient and the emergency contact
     * @param emergencyContactNumber    The patient's emergency contact phone number
     */
    public Patient(String patientID, String patientName, String DOB, 
            Gender gender, BloodType bloodType, String contactInformation,  String userName, String password, 
            String emergencyContactName, String emergencyContactRelation, String emergencyContactNumber) {
        super(patientID, patientName, gender, userName , password , Role.Patient);
        this.contactInformation = contactInformation;
        this.dateOfBirth = DOB;
        this.bloodType = bloodType;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactRelation = emergencyContactRelation;
        this.emergencyContactNumber = emergencyContactNumber;
    }

    // ------------------ setters ---------------------

    /**
     *
     * @param newContactInfo The new contact information of patient
     */
    public void updateContactInfo(String newContactInfo) {
        this.contactInformation = newContactInfo;
    }

    /**
     *
     * @param name The name of the patient's emergency contact
     * @param relation The relation between patient and emergency contact
     * @param number The patient's emergency contact's phone number
     */
    public void updateEmergencyContact(String name, String relation, String number) {
        this.emergencyContactName = name;
        this.emergencyContactRelation = relation;
        this.emergencyContactNumber = number;
    }

    // ------------------ getters ---------------------

    /**
     *
     * @return  returns the patient's emergency contact information
     */
    public String getEmergencyContactInfo() {
        return "Name: " + emergencyContactName + ", Relation: " + emergencyContactRelation + ", Contact Number: "
                + emergencyContactNumber;
    }

    /**
     *
     * @return  returns the patient's emergency contact name
     */
    public String getEmergencyContactName(){
        return emergencyContactName;
    }

    /**
     *
     * @return  returns the patient's emergency contact relation
     */
    public String getEmergencyContactRelation(){
        return emergencyContactRelation;
    }

    /**
     *
     * @return  returns the patient's emergency contact number
     */
    public String getEmergencyContactNumber(){
        return emergencyContactNumber;
    }

    /**
     *
     * @return  returns patient's contact information
     */
    public String getContactInfo() {
        return this.contactInformation;
    }

    /**
     *
     * @return  returns date of birth of patient
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * @return  return patient's blood type
     */
    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     *
     * @return  returns patient's entire information including emergency contact
     */
    @Override
    public String toString() {
        return "Patient ID :" + getUserID() + 
                ", Name : " + getName() + 
                ", Date of Birth : " + dateOfBirth +  
                ", gender : " + getGender() + 
                ", Blood Type : " + bloodType + 
                ", Contact information: " + contactInformation + 
                ", Emergency Contact Name: " + emergencyContactName +
                ", Emergency Contact Relation: " + emergencyContactRelation +
                ", Emergency Contact Number: " + emergencyContactNumber;
    }
}
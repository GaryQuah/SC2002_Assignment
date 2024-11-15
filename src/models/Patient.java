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
            Gender gender, BloodType bloodType, String contactInformation, String password) {
        super(patientID, patientName, gender, password , Role.Patient);
        this.contactInformation = contactInformation;
        this.dateOfBirth = DOB;
        this.bloodType = bloodType;
        this.emergencyContactName = "No emergency contact name";
        this.emergencyContactRelation = "No emergency contact relation";
        this.emergencyContactNumber = "No emergency contact number";
    }

    // Overloading constructor for Patient with emergency contact details

    public Patient(String patientID, String patientName, String DOB, 
            Gender gender, BloodType bloodType, String contactInformation, String password, 
            String emergencyContactName, String emergencyContactRelation, String emergencyContactNumber) {
        super(patientID, patientName, gender, password , Role.Patient);
        this.contactInformation = contactInformation;
        this.dateOfBirth = DOB;
        this.bloodType = bloodType;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactRelation = emergencyContactRelation;
        this.emergencyContactNumber = emergencyContactNumber;
    }

    // ------------------ setters ---------------------

    public void updateContactInfo(String newContactInfo) {
        this.contactInformation = newContactInfo;
    }

    public void updateEmergencyContact(String name, String relation, String number) {
        this.emergencyContactName = name;
        this.emergencyContactRelation = relation;
        this.emergencyContactNumber = number;
    }

    // ------------------ getters ---------------------

    public String getEmergencyContactInfo() {
        return "Name: " + emergencyContactName + ", Relation: " + emergencyContactRelation + ", Contact Number: "
                + emergencyContactNumber;
    }

    public String getEmergencyContactName(){
        return emergencyContactName;
    }

    public String getEmergencyContactRelation(){
        return emergencyContactRelation;
    }

    public String getEmergencyContactNumber(){
        return emergencyContactNumber;
    }

    public String getContactInfo() {
        return this.contactInformation;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

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
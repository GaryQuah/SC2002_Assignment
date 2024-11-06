package models;

import models.enums.Gender;
import models.enums.Role;
import models.enums.BloodType;

public class Patient extends User {
    // ------------------ variables -----------------------------
    private String contactInformation;
    private MedicalRecord medicalRecord;
    private int patientID;
    private String patientName;
    private String dateOfBirth;
    private BloodType bloodType;
    private Gender gender;

    private String emergencyContactName;
    private String emergencyContactRelation;
    private String emergencyContactNumber;
    // ------------------- Functions ---------------------------

    // Creates a "Patient" after passing the user's username. default password is
    // "password", default role is "Patient"

    public Patient(int patientID, String patientName, String DOB,
            Gender gender, BloodType bloodType, String contactInformation, String userName, String password) {
        super(userName, password, Role.Patient, gender);
        this.contactInformation = contactInformation;
        this.patientID = patientID;
        this.gender = gender;
        this.patientName = patientName;
        this.dateOfBirth = DOB;
        this.bloodType = bloodType;
    }

    // For testing - to remove
    public Patient(String name) {
        super("Name", "Pw", Role.Patient, Gender.Others);

        this.patientName = name;
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

    public String getContactInfo() {
        return this.contactInformation;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public int getPatientID() {
        return this.patientID;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

}
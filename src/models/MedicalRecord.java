package models;

import models.enums.BloodType;
import models.enums.Gender;

public class MedicalRecord {
    private String patientId;
    private String name;
    private String dateOfBirth;
    private Gender gender;
    private String contactNumber;
    private String emailAddress;
    private BloodType bloodType;
    private String pastDiagnoses;
    private String pastTreatments;

    // Constructor
    public MedicalRecord(String patientId, String name, String dateOfBirth, Gender gender, String contactNumber,
                         String emailAddress, BloodType bloodType, String pastDiagnoses, String pastTreatments) {
        this.patientId = patientId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.bloodType = bloodType;
        this.pastDiagnoses = pastDiagnoses;
        this.pastTreatments = pastTreatments;
    }

    // Getters and Setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public String getPastDiagnoses() {
        return pastDiagnoses;
    }

    public void setPastDiagnoses(String pastDiagnoses) {
        this.pastDiagnoses = pastDiagnoses;
    }

    public String getPastTreatments() {
        return pastTreatments;
    }

    public void setPastTreatments(String pastTreatments) {
        this.pastTreatments = pastTreatments;
    }
}

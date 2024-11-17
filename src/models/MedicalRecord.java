package models;

import models.enums.BloodType;
import models.enums.Gender;

/**
 * The {@code MedicalRecord} class represents a patient's medical record,
 * containing essential information related to the patient’s health history.
 * <p>
 * This class includes details such as the patient's ID, name, date of birth,
 * gender, contact information, blood type, and records of past diagnoses
 * and treatments.
 * </p>
 */
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

    /**
     * Constructs a {@code MedicalRecord} with the specified details.
     *
     * @param patientId the unique identifier for the patient
     * @param name the name of the patient
     * @param dateOfBirth the date of birth of the patient
     * @param gender the gender of the patient
     * @param contactNumber the contact number of the patient
     * @param emailAddress the email address of the patient
     * @param bloodType the blood type of the patient
     * @param pastDiagnoses the past medical diagnoses of the patient
     * @param pastTreatments the past treatments received by the patient
     */
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

    /**
     * Returns the unique identifier for the patient.
     *
     * @return the patient ID
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the unique identifier for the patient.
     *
     * @param patientId the new patient ID
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * Returns the name of the patient.
     *
     * @return the patient's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the patient.
     *
     * @param name the new name of the patient
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the date of birth of the patient.
     *
     * @return the patient's date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the patient.
     *
     * @param dateOfBirth the new date of birth of the patient
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the gender of the patient.
     *
     * @return the patient's gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender of the patient.
     *
     * @param gender the new gender of the patient
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Returns the contact number of the patient.
     *
     * @return the patient's contact number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets the contact number of the patient.
     *
     * @param contactNumber the new contact number of the patient
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Returns the email address of the patient.
     *
     * @return the patient's email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address of the patient.
     *
     * @param emailAddress the new email address of the patient
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the blood type of the patient.
     *
     * @return the patient's blood type
     */
    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * Sets the blood type of the patient.
     *
     * @param bloodType the new blood type of the patient
     */
    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Returns the past diagnoses of the patient.
     *
     * @return the patient's past diagnoses
     */
    public String getPastDiagnoses() {
        return pastDiagnoses;
    }

    /**
     * Sets the past diagnoses of the patient.
     *
     * @param pastDiagnoses the new past diagnoses of the patient
     */
    public void setPastDiagnoses(String pastDiagnoses) {
        this.pastDiagnoses = pastDiagnoses;
    }

    /**
     * Returns the past treatments received by the patient.
     *
     * @return the patient's past treatments
     */
    public String getPastTreatments() {
        return pastTreatments;
    }

    /**
     * Sets the past treatments received by the patient.
     *
     * @param pastTreatments the new past treatments of the patient
     */
    public void setPastTreatments(String pastTreatments) {
        this.pastTreatments = pastTreatments;
    }
}

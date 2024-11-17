package ServiceClasses;

import models.MedicalRecord;
import models.enums.BloodType;
import models.enums.Gender;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code MedicalRecordService} class provides utility methods for managing medical records,
 * including adding, updating, deleting, viewing, and retrieving all records. 
 * Medical records are stored and retrieved from a CSV file.
 */
public class MedicalRecordService {

    private static final String FILE_PATH = "src/data/MedicalRecord_List.csv"; // Path to the CSV file

    /**
     * Adds a new medical record to the CSV file.
     *
     * @param record The {@code MedicalRecord} object to be added.
     * @throws IOException If there is an error writing to the file.
     */
    public static void addMedicalRecord(MedicalRecord record) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(String.join(",",
                    record.getPatientId(),
                    record.getName(),
                    record.getDateOfBirth(),
                    record.getGender().toString(),
                    record.getContactNumber(),
                    record.getEmailAddress(),
                    record.getBloodType().toString(),
                    record.getPastDiagnoses(),
                    record.getPastTreatments()
            ) + "\n");
        }
    }

    /**
     * Retrieves all medical records from the CSV file.
     *
     * @return A list of {@code MedicalRecord} objects.
     * @throws IOException If there is an error reading the file.
     */
    public static List<MedicalRecord> getAllMedicalRecords() throws IOException {
        List<MedicalRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean isHeader = true; // Skip the header line
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",", -1); // Ensure all parts are read even if empty
                if (parts.length < 9) {
                    System.out.println("Malformed line skipped: " + line);
                    continue;
                }

                Gender gender;
                try {
                    gender = Gender.valueOf(parts[3].trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    gender = Gender.UNKNOWN; // Default to UNKNOWN if parsing fails
                }

                records.add(new MedicalRecord(
                        parts[0].trim(), // patientId
                        parts[1].trim(), // name
                        parts[2].trim(), // dateOfBirth
                        gender, // gender
                        parts[4].trim(), // contactNumber
                        parts[5].trim(), // emailAddress
                        BloodType.valueOf(parts[6].trim().toUpperCase()), // bloodType
                        parts[7].trim(), // pastDiagnoses
                        parts[8].trim()  // pastTreatments
                ));
            }
        }
        return records;
    }

    /**
     * Updates an existing medical record in the CSV file.
     *
     * @param updatedRecord The updated {@code MedicalRecord} object.
     * @throws IOException If there is an error reading or writing to the file.
     */
    public static void updateMedicalRecord(MedicalRecord updatedRecord) throws IOException {
        List<MedicalRecord> records = getAllMedicalRecords();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("Patient ID,Name,Date of Birth,Gender,Contact Number,Email Address,Blood Type,Past Diagnoses,Past Treatments\n");
    
            for (MedicalRecord record : records) {
                if (record.getPatientId().equals(updatedRecord.getPatientId())) {
                    Gender gender = validateGender(updatedRecord.getGender());
                    BloodType bloodType = validateBloodType(updatedRecord.getBloodType());
                    writer.write(String.join(",",
                            updatedRecord.getPatientId(),
                            updatedRecord.getName(),
                            updatedRecord.getDateOfBirth(),
                            gender.toString(),
                            updatedRecord.getContactNumber(),
                            updatedRecord.getEmailAddress(),
                            bloodType.toString(),
                            updatedRecord.getPastDiagnoses(),
                            updatedRecord.getPastTreatments()
                    ) + "\n");
                } else {
                    writer.write(String.join(",",
                            record.getPatientId(),
                            record.getName(),
                            record.getDateOfBirth(),
                            record.getGender().toString(),
                            record.getContactNumber(),
                            record.getEmailAddress(),
                            record.getBloodType().toString(),
                            record.getPastDiagnoses(),
                            record.getPastTreatments()
                    ) + "\n");
                }
            }
        }
    }

    /**
     * Deletes a medical record from the CSV file.
     *
     * @param patientId The ID of the patient whose record is to be deleted.
     * @throws IOException If there is an error reading or writing to the file.
     */
    public static void deleteMedicalRecord(String patientId) throws IOException {
        List<MedicalRecord> records = getAllMedicalRecords();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (MedicalRecord record : records) {
                if (!record.getPatientId().equals(patientId)) {
                    writer.write(String.join(",",
                            record.getPatientId(),
                            record.getName(),
                            record.getDateOfBirth(),
                            record.getGender().toString(),
                            record.getContactNumber(),
                            record.getEmailAddress(),
                            record.getBloodType().toString(),
                            record.getPastDiagnoses(),
                            record.getPastTreatments()
                    ) + "\n");
                }
            }
        }
    }

    /**
     * Displays the medical records for a specific patient.
     *
     * @param patientId The ID of the patient whose medical record is to be viewed.
     * @throws IOException If there is an error reading the file.
     */
    public static void viewMedicalRecord(String patientId) throws IOException {
        List<MedicalRecord> records = getAllMedicalRecords();
        System.out.println("------ Medical Records ------");

        boolean recordFound = false;

        for (MedicalRecord record : records) {
            if (record.getPatientId().equalsIgnoreCase(patientId.trim())) {
                printRecord(record);
                recordFound = true;
            }
        }

        if (!recordFound) {
            System.out.println("No medical records found for this patient.");
        }
    }

    /**
     * Prints the details of a single medical record.
     *
     * @param record The {@code MedicalRecord} object to be printed.
     */
    private static void printRecord(MedicalRecord record) {
        System.out.println("Patient ID: " + record.getPatientId());
        System.out.println("Name: " + record.getName());
        System.out.println("Date of Birth: " + record.getDateOfBirth());
        System.out.println("Gender: " + record.getGender());
        System.out.println("Contact Number: " + record.getContactNumber());
        System.out.println("Email Address: " + record.getEmailAddress());
        System.out.println("Blood Type: " + record.getBloodType());
        System.out.println("Past Diagnoses: " + record.getPastDiagnoses());
        System.out.println("Past Treatments: " + record.getPastTreatments());
        System.out.println("----------------------------");
    }

    /**
     * Validates and returns a valid {@code Gender} value.
     *
     * @param inputGender The input gender value.
     * @return A valid {@code Gender} value.
     */
    private static Gender validateGender(Gender inputGender) {
        return (inputGender != null) ? inputGender : Gender.UNKNOWN;
    }

    /**
     * Validates and returns a valid {@code BloodType} value.
     *
     * @param inputBloodType The input blood type value.
     * @return A valid {@code BloodType} value.
     */
    private static BloodType validateBloodType(BloodType inputBloodType) {
        return (inputBloodType != null) ? inputBloodType : BloodType.UNKNOWN;
    }
}

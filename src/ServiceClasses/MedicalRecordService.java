package ServiceClasses;

import models.MedicalRecord;
import models.enums.BloodType;
import models.enums.Gender;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordService {
    private static final String FILE_PATH = "src/data/MedicalRecord_List.csv"; // Update the file path

    // Create a new medical record
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

    // Read all medical records
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

    // Update a medical record
    public static void updateMedicalRecord(MedicalRecord updatedRecord) throws IOException {
        List<MedicalRecord> records = getAllMedicalRecords();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Write the header
            writer.write("Patient ID,Name,Date of Birth,Gender,Contact Number,Email Address,Blood Type,Past Diagnoses,Past Treatments\n");
    
            for (MedicalRecord record : records) {
                if (record.getPatientId().equals(updatedRecord.getPatientId())) {
                    // Write updated record
                    writer.write(String.join(",",
                            updatedRecord.getPatientId(),
                            updatedRecord.getName(),
                            updatedRecord.getDateOfBirth(),
                            updatedRecord.getGender().toString(),
                            updatedRecord.getContactNumber(),
                            updatedRecord.getEmailAddress(),
                            updatedRecord.getBloodType().toString(),
                            updatedRecord.getPastDiagnoses(),
                            updatedRecord.getPastTreatments()
                    ) + "\n");
                } else {
                    // Write original record back
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
    
    // Delete a medical record
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

    // View medical records for a specific patient
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

    // Print a single medical record
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
}

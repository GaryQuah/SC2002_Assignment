package ServiceClasses;

import models.MedicalRecord;
import java.io.*;
import java.util.*;

public class MedicalRecordService {
    private static final String FILE_PATH = "medical_records.csv";

    // Create a new medical record
    public static void addMedicalRecord(MedicalRecord record) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(record.getPatientId() + "," + record.getDoctorId() + "," + record.getDiagnosis() + ","
                    + record.getTreatment() + "," + record.getDate() + "\n");
        }
    }

    // Read all medical records
    public static List<MedicalRecord> getAllMedicalRecords() throws IOException {
        List<MedicalRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                records.add(new MedicalRecord(parts[0], parts[1], parts[2], parts[3], parts[4]));
            }
        }
        return records;
    }

    // Update a medical record
    public static void updateMedicalRecord(MedicalRecord updatedRecord) throws IOException {
        List<MedicalRecord> records = getAllMedicalRecords();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (MedicalRecord record : records) {
                if (record.getPatientId().equals(updatedRecord.getPatientId())) {
                    writer.write(updatedRecord.getPatientId() + "," + updatedRecord.getDoctorId() + ","
                            + updatedRecord.getDiagnosis() + "," + updatedRecord.getTreatment() + ","
                            + updatedRecord.getDate() + "\n");
                } else {
                    writer.write(record.getPatientId() + "," + record.getDoctorId() + "," + record.getDiagnosis() + ","
                            + record.getTreatment() + "," + record.getDate() + "\n");
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
                    writer.write(record.getPatientId() + "," + record.getDoctorId() + "," + record.getDiagnosis() + ","
                            + record.getTreatment() + "," + record.getDate() + "\n");
                }
            }
        }
    }
}
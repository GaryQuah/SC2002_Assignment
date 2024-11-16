package ServiceClasses.Database;

import java.util.ArrayList;
import input.CSVParse;
import models.MedicalRecord;
import models.enums.BloodType;
import models.enums.Gender;

public class MedicalRecordFileHandler extends FileHandler<MedicalRecord> {

    // Constructor
    public MedicalRecordFileHandler() {
        super("src\\data\\MedicalRecord_List.csv");
    }

    @Override
    public ArrayList<MedicalRecord> retrieveData() {
        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
        ArrayList<String[]> fileData = CSVParse.read(getFilePath(), true); // true to skip the header

        for (String[] row : fileData) {
            if (row.length >= 9) { // Ensure sufficient fields exist
                MedicalRecord record = new MedicalRecord(
                    row[0], // Patient ID
                    row[1], // Name
                    row[2], // Date of Birth
                    Gender.valueOf(row[3]), // Gender
                    row[4], // Contact Number
                    row[5], // Email Address
                    BloodType.valueOf(row[6]), // Blood Type
                    row[7], // Past Diagnoses
                    row[8]  // Past Treatments
                );
                medicalRecords.add(record);
            } else {
                System.out.println("Skipped a row due to insufficient data: " + String.join(",", row));
            }
        }
        setDataArray(medicalRecords);
        return medicalRecords;
    }

    @Override
    public void saveData() {
        ArrayList<String> data = new ArrayList<>();
        // Add CSV header
        data.add("PatientID,Name,DateOfBirth,Gender,ContactNumber,EmailAddress,BloodType,PastDiagnoses,PastTreatments");

        for (MedicalRecord record : getDataArray()) {
            data.add(
                record.getPatientId() + "," +
                record.getName() + "," +
                record.getDateOfBirth() + "," +
                record.getGender().toString() + "," +
                record.getContactNumber() + "," +
                record.getEmailAddress() + "," +
                record.getBloodType().toString() + "," +
                record.getPastDiagnoses() + "," +
                record.getPastTreatments()
            );
        }

        try {
            CSVParse.write(getFilePath(), data);
        } catch (Exception e) {
            System.out.println("Error saving medical records to file.");
            e.printStackTrace();
        }
    }

    public void addMedicalRecord(MedicalRecord record) {
        ArrayList<MedicalRecord> records = getDataArray();
        records.add(record);
        setDataArray(records);
        saveData();
    }

    public MedicalRecord getRecordByPatientId(String patientId) {
        ArrayList<MedicalRecord> records = getDataArray();
        for (MedicalRecord record : records) {
            if (record.getPatientId().equals(patientId)) {
                return record;
            }
        }
        return null;
    }

    public void updateMedicalRecord(MedicalRecord updatedRecord) {
        ArrayList<MedicalRecord> records = getDataArray();

        for (int i = 0; i < records.size(); i++) {
            MedicalRecord record = records.get(i);
            if (record.getPatientId().equals(updatedRecord.getPatientId())) {
                records.set(i, updatedRecord);
                break;
            }
        }

        setDataArray(records);
        saveData();
    }
}
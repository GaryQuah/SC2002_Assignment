package ServiceClasses.Database;

import java.util.ArrayList;

import input.CSVParse;
import models.MedicalRecord;
import models.enums.BloodType;
import models.enums.Gender;

/**
 * This class handles the retrieval and saving of medical record data to a CSV file.
 * It extends the generic {@link FileHandler} class, specializing in handling {@link MedicalRecord} objects.
 * This file handler interacts with the CSV file located at `src\\data\\MedicalRecord_List.csv`.
 *
 * The class provides methods for loading medical records from the file, saving updated data to the file,
 * adding new medical records, retrieving specific records by patient ID, and updating existing records.
 */
public class MedicalRecordFileHandler extends FileHandler<MedicalRecord> {

    /**
     * Constructor that initializes the file handler with the file path of the medical records CSV file.
     * The path is set to "src\\data\\MedicalRecord_List.csv" by default.
     */
    public MedicalRecordFileHandler() {
        super("src\\data\\MedicalRecord_List.csv");
    }

    /**
     * Retrieves medical records from the CSV file and converts them into a list of {@link MedicalRecord} objects.
     * Each row in the CSV corresponds to a single medical record. The method parses each row into a
     * {@link MedicalRecord} and adds it to the list.
     *
     * @return An {@link ArrayList} of {@link MedicalRecord} objects representing the data in the CSV file.
     */
    @Override
    public ArrayList<MedicalRecord> retrieveData() {
        ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
        ArrayList<String[]> fileData = CSVParse.read(getFilePath(), true); // true to skip the header

        // Iterate over each row of data in the CSV file
        for (String[] row : fileData) {
            if (row.length >= 9) { // Ensure sufficient fields exist
                // Creating MedicalRecord object from CSV row
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
                // Log message if the row doesn't have enough data
                System.out.println("Skipped a row due to insufficient data: " + String.join(",", row));
            }
        }
        setDataArray(medicalRecords);
        return medicalRecords;
    }

    /**
     * Saves the current medical records to the CSV file.
     * This method converts each {@link MedicalRecord} object into a CSV-compatible string
     * and writes the data back to the file. It includes a header row with column names.
     */
    @Override
    public void saveData() {
        ArrayList<String> data = new ArrayList<>();
        // Add CSV header
        data.add("PatientID,Name,DateOfBirth,Gender,ContactNumber,EmailAddress,BloodType,PastDiagnoses,PastTreatments");

        // Iterate over the medical records and convert them to CSV format
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
            // Write the data to the file
            CSVParse.write(getFilePath(), data);
        } catch (Exception e) {
            // Handle exception (log, rethrow, etc.)
            System.out.println("Error saving medical records to file.");
            e.printStackTrace();
        }
    }

    /**
     * Adds a new medical record to the list and saves the updated list to the CSV file.
     *
     * @param record The {@link MedicalRecord} object to be added.
     */
    public void addMedicalRecord(MedicalRecord record) {
        ArrayList<MedicalRecord> records = getDataArray();
        records.add(record);
        setDataArray(records);
        saveData();
    }

    /**
     * Retrieves a {@link MedicalRecord} by the patient's ID.
     * This method searches the list of medical records for a record matching the provided patient ID.
     *
     * @param patientId The ID of the patient whose medical record is to be retrieved.
     * @return The {@link MedicalRecord} object for the specified patient ID, or {@code null} if not found.
     */
    public MedicalRecord getRecordByPatientId(String patientId) {
        ArrayList<MedicalRecord> records = getDataArray();
        for (MedicalRecord record : records) {
            if (record.getPatientId().equals(patientId)) {
                return record;
            }
        }
        return null;
    }

    /**
     * Updates an existing medical record.
     * This method replaces the old record with the updated one, and then saves the updated list of records to the file.
     *
     * @param updatedRecord The {@link MedicalRecord} object containing the updated information.
     */
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

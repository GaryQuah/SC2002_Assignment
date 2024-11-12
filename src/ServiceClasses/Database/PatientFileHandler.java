package ServiceClasses.Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ServiceClasses.Database.FileHandler;
import models.Patient;
import models.enums.BloodType;
import models.enums.Gender;

public class PatientFileHandler extends FileHandler<Patient>
{
    public ArrayList<Patient> patientList = new ArrayList<>();

    public PatientFileHandler() {
        super("src/data/Patient_List.csv");
    }

    @Override
    public ArrayList<Patient> retrieveData() {
        ArrayList<Patient> patientList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(getFilePath()))) {
            String line;
            boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                if (isFirstRow) {
                    isFirstRow = false; // Skip the first row (header)
                    continue;
                }

                String[] row = line.split(",");
                try {
                    // Parse and create Patient object from CSV row
                    String userID = row[0];
                    String name = row[1];
                    String dateOfBirth = row[2];
                    Gender gender = Gender.valueOf(row[3]);
                    BloodType bloodType = BloodType.valueOf(row[4]);
                    String contactInfo = row[5];
                    String username = row[6];
                    String password = row[7];

                    // adding emergency info
                    String emergencyContactName = row.length > 8 ? row[8] : "";
                    String emergencyContactRelation = row.length > 9 ? row[9] : "";
                    String emergencyContactNumber = row.length > 10 ? row[10] : "";


                    Patient patient = new Patient(userID, name, dateOfBirth, gender, bloodType, contactInfo, username, password);
                    patient.updateEmergencyContact(emergencyContactName, emergencyContactRelation, emergencyContactNumber);

                    patientList.add(patient);
                } catch (Exception e) {
                    System.out.println("Error parsing row: " + Arrays.toString(row));
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Error retrieving Patient data");
            e.printStackTrace();
        }
        setDataArray(patientList);
        return patientList;
    }

    public void updatePatientInFile(Patient updatedPatient) {
        ArrayList<Patient> patientList = retrieveData(); // Load current data from CSV

        for (int i = 0; i < patientList.size(); i++) {
            Patient patient = patientList.get(i);
            if (patient.getUserID().equals(updatedPatient.getUserID())) {
                patient.updateContactInfo(updatedPatient.getContactInfo());
                patient.updateEmergencyContact(updatedPatient.getEmergencyContactName(), updatedPatient.getEmergencyContactRelation(), updatedPatient.getEmergencyContactNumber());
                break;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath()))) {
            // Write the header
            // added emergency contact info
            writer.write("Patient ID,Name,Date of Birth,Gender,Blood Type,Contact Information,Username,Password," +
                    "Emergency Contact Name,Emergency Contact Relation,Emergency Contact Number\n");

            // Write updated patient data
            for (Patient patient : patientList) {

                String bloodTypeString;
                switch (patient.getBloodType()) {
                    case A_POSITIVE: bloodTypeString = "A_POSITIVE"; break;
                    case A_NEGATIVE: bloodTypeString = "A_NEGATIVE"; break;
                    case B_POSITIVE: bloodTypeString = "B_POSITIVE"; break;
                    case B_NEGATIVE: bloodTypeString = "B_NEGATIVE"; break;
                    case AB_POSITIVE: bloodTypeString = "AB_POSITIVE"; break;
                    case AB_NEGATIVE: bloodTypeString = "AB_NEGATIVE"; break;
                    case O_POSITIVE: bloodTypeString = "O_POSITIVE"; break;
                    case O_NEGATIVE: bloodTypeString = "O_NEGATIVE"; break;
                    default: throw new IllegalArgumentException("Unknown blood type: " + patient.getBloodType());
                }

                String[] patientData = new String[]{
                        patient.getUserID(),
                        patient.getName(),
                        patient.getDateOfBirth(),
                        patient.getGender().toString(),
                        bloodTypeString,
                        patient.getContactInfo(),
                        patient.getUsername(),
                        patient.getPassword(),
                        patient.getEmergencyContactName(),
                        patient.getEmergencyContactRelation(),
                        patient.getEmergencyContactNumber()
                };
                writer.write(String.join(",", patientData));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating Patient data in file");
            e.printStackTrace();
        }
    }


    @Override
    public void saveData()
    {
        ArrayList<String[]> patientList = new ArrayList<>();
    
        // Convert Patient objects to String[] before saving
        for (Patient patient : getDataArray()) {
            String[] patientData = new String[8];
            patientData[0] = patient.getUserID();                            // Patient ID
            patientData[1] = patient.getName();                              // Name
            patientData[2] = patient.getDateOfBirth();                       // Date of Birth
            patientData[3] = patient.getGender().toString();                // Gender
            switch(patient.getBloodType()) {
                case A_POSITIVE: patientData[4] = "A_POSITIVE"; break;
                case A_NEGATIVE: patientData[4] = "A_NEGATIVE"; break;
                case B_POSITIVE: patientData[4] = "B_POSITIVE"; break;
                case B_NEGATIVE: patientData[4] = "B_NEGATIVE"; break;
                case AB_POSITIVE: patientData[4] = "AB_POSITIVE"; break;
                case AB_NEGATIVE: patientData[4] = "AB_NEGATIVE"; break;
                case O_POSITIVE: patientData[4] = "O_POSITIVE"; break;
                case O_NEGATIVE: patientData[4] = "O_NEGATIVE"; break;
            }
            patientData[5] = patient.getContactInfo();             // Contact Info
            patientData[6] = patient.getUserID();                            // Username
            patientData[7] = patient.getPassword();                         // Password
            patientList.add(patientData);
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath()))) {
            // Write the header row
            writer.write("Patient ID,Name,Date of Birth,Gender,Blood Type,Contact Information,Username,Password\n");
    
            // Write each patient data
            for (String[] patientData : patientList) {
                writer.write(String.join(",", patientData)); // Join each field with a comma
                writer.newLine();
            }
        } 
        catch (IOException e) {
            System.out.println("Error saving Patient data");
            e.printStackTrace();
        }
    }



    @Override
    public boolean checkLogin(String currentUserID, String currentUserPassword)
    {
        ArrayList<Patient> patientList = retrieveData();

        for (Patient patient : patientList) {
            if (patient.getUserID().equals(currentUserID) && patient.getPassword().equals(currentUserPassword)) {
                return true;
            }
        }

        System.out.println("Invalid username or password for Patient.");
        return false;
    }

    public void addPatient(Patient newPatient) {
        ArrayList<Patient> currentPatientList = getDataArray();
        currentPatientList.add(newPatient);
        setDataArray(currentPatientList);
        saveData();
    }

    public Patient getUserById(String userID) {
        ArrayList<Patient> patientList = getDataArray();
        for (Patient patient : patientList) {
            if (patient.getUserID().equals(userID)) {
                return patient;
            }
        }
        return null;
    }
}

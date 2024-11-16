package ServiceClasses.Database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import input.CSVParse;
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

        ArrayList<String[]> fileData = CSVParse.read(getFilePath(), true);

        for (String[] row : fileData) {
            String userID = row[0];
            String name = row[1];
            String dateOfBirth = row[2];
            Gender gender = Gender.valueOf(row[3]);
            BloodType bloodType = BloodType.valueOf(row[4]);
            String contactInfo = row[5];
            String password = row[6];
            String emergencyContactName = row[7];
            String emergencyContactRelation = row[8]; 
            String emergencyContactNumber = row[9];

            Patient patient = new Patient(userID, name, dateOfBirth, gender, bloodType, contactInfo, password, emergencyContactName, emergencyContactRelation, emergencyContactNumber);
            patientList.add(patient);
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
        ArrayList <String> data = new ArrayList <String> ();
        data.add("Patient ID,Name,Date of Birth,Gender,Blood Type,Contact Information,Password,Emergency Contact Name,Emergency Contact Relation,Emergency Contact Number");
    
        // Convert Patient objects to String[] before saving
        for (Patient patient : getDataArray()) {
            // String[] patientData = new String[11];
            data.add(            
                patient.getUserID() + "," +                            // Patient ID
                patient.getName() + "," +                              // Name
                patient.getDateOfBirth() + "," +                       // Date of Birth
                patient.getGender().toString() + "," +              // Gender
                patient.getBloodType().toString() + "," +
                patient.getContactInfo() + "," +             // Contact Info  
                patient.getPassword() + "," +                         // Password
                patient.getEmergencyContactName() + "," +            // Emergency Contact Name
                patient.getEmergencyContactRelation() + "," +        // Emergency Contact Relation
                patient.getEmergencyContactNumber()          // Emergency Contact Number
            );    
        }
        try {
            CSVParse.write(getFilePath(), data);
        } catch (Exception e) {}
    }

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

package view;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import models.Administrator;
import models.Patient;
import models.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Login {

    private static final String PATIENT_FILE_PATH = "src/data/Patient_List.csv";
    private static final String STAFF_FILE_PATH = "src/data/Staff_List.csv";

    private static List<String[]> patientData = new ArrayList<>();
    private static List<String[]> staffData = new ArrayList<>();

    private Login(Vector<User> m_UserList) { // On class instantiation, load user list into the main file.
        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                patientData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String[] row : patientData) {
            if (row.length >= 5) { // Ensure there are enough fields
                Patient patient = new Patient(row[0], row[1], row[2], row[3], row[4]);
                m_UserList.add(patient);
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(STAFF_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                staffData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String[] row : staffData) {
            if (row.length >= 5) { // Ensure there are enough fields
                switch (row[2]) {
                    case "Doctor":
                        Doctor doctor = new Doctor(row[0], row[1], row[2], row[3], row[4]);
                        break;
                    case "Pharmacist":
                        Pharmacist pharmacist = new Pharmacist(row[0], row[1], row[2], row[3], row[4]);
                        break;
                    case "Administrator":
                        Administrator admin = new Administrator(row[0], row[1], row[2], row[3], row[4]);
                    default:
                        break;
                }
                m_UserList.add(patient);
            }
        }
    }

}

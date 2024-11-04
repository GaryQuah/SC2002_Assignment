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

    //rivate Login(Vector<User> m_UserList) { // On class instantiation, load user list into the main file.
    private static void main(){
        Vector<User> m_UserList;

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
            if (row.length >= 7) { // Ensure there are enough fields
                //Patient ID, Name, DOB, Gender, BloodType, ContactInfo, Username, Password
                Patient patient = new Patient(row[0], row[1], row[2], row[3], row[4] , row[5] , row[6]);
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

        //StaffID, Name, Role, Gender, Age, Username, Password
        for (String[] row : staffData) {
            if (row.length >= 7) { 
                switch (row[2]) { //2nd Row is roles in .csv
                    case "Doctor":
                        Doctor doctor = new Doctor(row[0], row[1], row[2], row[3], row[4], row[5], row[6]);
                        m_UserList.add(doctor);
                        break;
                    case "Pharmacist":
                        Pharmacist pharmacist = new Pharmacist(row[0], row[1], row[2], row[3], row[4], row[5], row[6]);
                        m_UserList.add(pharmacist);
                        break;
                    case "Administrator":
                        Administrator admin = new Administrator(row[0], row[1], row[2], row[3], row[4], row[5], row[6]);
                    default:
                    m_UserList.add(admin);
                    System.out.println("Error - Invalid CSV row read");
                        break;
                }
            }
        }

        for (User user : m_UserList)
        {
            System.out.println(user);
        }
    }
}

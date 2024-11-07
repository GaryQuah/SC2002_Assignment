package view;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import models.Administrator;
import models.Doctor;
import models.Patient;
import models.Pharmacist;
import models.User;

import models.enums.BloodType;
import models.enums.Gender;
import models.enums.Role;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Arrays;

public class Login {

    private static final String PATIENT_FILE_PATH = "src/data/Patient_List.csv";
    private static final String STAFF_FILE_PATH = "src/data/Staff_List.csv";

    private static List<String[]> patientData = new ArrayList<>();
    private static List<String[]> staffData = new ArrayList<>();

    //rivate Login(Vector<User> m_UserList) { // On class instantiation, load user list into the main file.
    public static void main(String[] args) {
        Vector<User> m_UserList = new Vector<User>();
        System.out.println(m_UserList.size());

        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_FILE_PATH))) {
            String line;
            boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                if (isFirstRow) {
                    isFirstRow = false; // Skip the first row
                    continue; // Move to the next iteration
                }

                String[] row = line.split(",");
                patientData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error retrieving patient data");
        }

        for (String[] row : patientData) {
            System.out.println(Arrays.toString(row));

            System.out.println("Processing Patient data");
            if (row.length >= 7) { // Ensure there are enough fields
                //Patient ID, Name, DOB, Gender, BloodType, ContactInfo, Username, Password
                //public Patient(int patientID, String patientName, String DOB, Gender gender, BloodType bloodType, String ContactInformation, String userName, String password)
                Patient patient = new Patient(Integer.parseInt(row[0]), row[1], row[2],Gender.valueOf(row[3]), BloodType.valueOf(row[4]), row[5] , row[6], row[7]);
                m_UserList.add(patient);
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(STAFF_FILE_PATH))) {
            String line;
            boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                if (isFirstRow) {
                    isFirstRow = false; // Skip the first row
                    continue; // Move to the next iteration
                }

                String[] row = line.split(",");
                staffData.add(row);
            }
        } catch (IOException e) {
            System.out.println("Error retrieving Staff data");
            e.printStackTrace();
        }

        //StaffID, Name, Role, Gender, Age, Username, Password
        for (String[] row : staffData) {
            System.out.println(Arrays.toString(row));
            System.out.println("Processing Staff data");
            if (row.length >= 7) {
                switch (row[2]) { //2nd Row is roles in .csv
                    case "Doctor":
                    //public Doctor(String username, String password, String staffId, String name, Role role, Gender gender, int age)
                        System.out.println("Doctor ID : " + Integer.parseInt(row[0]));
                        Doctor doctor = new Doctor(Integer.parseInt(row[0]), row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]), Integer.parseInt(row[4]) , row[5], row[6]);
                        m_UserList.add(doctor);
                        System.out.println("Created Doctor : " + doctor);
                        break;
                    case "Pharmacist":
                        Pharmacist pharmacist = new Pharmacist(Integer.parseInt(row[0]), row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]), Integer.parseInt(row[4]) , row[5], row[6]);
                        m_UserList.add(pharmacist);
                        System.out.println("Created Pharmacist : " + pharmacist);
                        break;
                    case "Administrator":
                        Administrator admin = new Administrator(Integer.parseInt(row[0]), row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]), Integer.parseInt(row[4]) , row[5], row[6]);
                        m_UserList.add(admin);
                        System.out.println("Created Administrator : " + admin);
                        break;
                     default:
                        System.out.println("Error - Invalid CSV row read");
                        break;
                }
            }
        }

        for (User user : m_UserList)
        {
            System.out.println(user.toString());
        }
    }
}

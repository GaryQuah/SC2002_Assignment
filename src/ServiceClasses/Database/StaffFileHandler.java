package ServiceClasses.Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Administrator;
import models.Doctor;
import models.Pharmacist;
import models.Staff;
import ServiceClasses.Database.FileHandler;
import models.enums.Gender;
import models.enums.Role;

public class StaffFileHandler extends FileHandler
{
    private static final String FILE_PATH = "src/data/Staff_List.csv";

    public StaffFileHandler () {
        super("src/data/Patient_List.csv");
    }

    @Override
    public ArrayList<Staff> retrieveData() {
        List<String[]> staffData = new ArrayList<>();
        ArrayList<Staff> staffList = getDataArray();

        try (BufferedReader br = new BufferedReader(new FileReader(getFilePath()))) {
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
                        Doctor doctor = new Doctor(row[0], row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]), Integer.parseInt(row[4]) , row[5], row[6]);
                        staffList.add(doctor);
                        System.out.println("Created Doctor : " + doctor);
                        break;
                    case "Pharmacist":
                        Pharmacist pharmacist = new Pharmacist(row[0], row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]), Integer.parseInt(row[4]) , row[5], row[6]);
                        staffList.add(pharmacist);
                        System.out.println("Created Pharmacist : " + pharmacist);
                        break;
                    case "Administrator":
                        Administrator admin = new Administrator(row[0], row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]), Integer.parseInt(row[4]) , row[5], row[6]);
                        staffList.add(admin);
                        System.out.println("Created Administrator : " + admin);
                        break;
                    default:
                        System.out.println("Error - Invalid CSV row read");
                        break;
                }
            }
        }
        return staffList;
    }

    /*
    public static String getRoleById(String id)
    {
        // get the latest staff data from csv file
        staffData = getStaffData();
        for (String[] row : staffData) 
        {
            if (row[0].equals(id)) 
            {
                return row[2];
            }
        }
        return null;
    }

    public static boolean checkLogin(String currentUserID, String currentUserPassword)
    {
        // get the latest staff data from csv file
        String[] staffData = getUserById(currentUserID);

        if (staffData == null) 
        {
            System.out.println("Invalid User ID");
            return false;
        }
        else
        {
            System.out.println("here");
            if (staffData[6].equals(currentUserPassword))
            {
                System.out.println("here1");
                return true;
            }
            else
            {
                System.out.println("here2");
                System.out.println("Invalid Password");
                return false;
            }
        }
    }*/
}

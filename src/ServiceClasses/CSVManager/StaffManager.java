package ServiceClasses.CSVManager;

import models.enums.Role;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;

public class StaffManager {
    private static StaffManager instance; // singleton instance
    private static List<String[]> staffData = new ArrayList<>();
    private static final String FILE_PATH = "src/data/Staff_List.csv";

    private StaffManager() 
    {
        staffData.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                staffData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StaffManager getInstance() 
    {
        if (instance == null) {
            instance = new StaffManager();
        }
        return instance;
    }

    // Get Latest staff data from csv file
    public static void getLatest(String FILE_PATH) 
    {
        staffData.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] rows = line.split(",");
                staffData.add(rows);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    // Get Staff Data
    public static List<String[]> getStaffData() 
    {
        getLatest(FILE_PATH);
        return staffData;
    }

    public static String[] getUserById(String id)
    {
        getLatest(FILE_PATH);
        for (String[] row : staffData) 
        {
            if (row[0].equals(id)) 
            {
                return row;
            }
        }
        return null;
    }

    public static String getUserByName(String name)
    {
        getLatest(FILE_PATH);
        for (String[] row : staffData) 
        {
            if (row[1].equals(name)) 
            {
                return row[0] + " " + row[2] + " " + row[3] + " " + row[4];
            }
        }
        return null;
    }


    // Add Staff into CSV
    public void addStaff(String m_StaffID, String m_StaffName, String m_StaffRole, String m_StaffGender, int m_StaffAge) {
        getLatest(FILE_PATH);
        staffData.add(new String[] { m_StaffID, m_StaffName, m_StaffRole, m_StaffGender, Integer.toString(m_StaffAge) });
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            for (String col : staffData.get(staffData.size() - 1)) {
                writer.append(col)
                        .append(",");
            }
            writer.append("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Remove staff by ID
    public static void removeStaffById(String m_StaffID)
    {
        List<String[]> temp = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        getLatest(FILE_PATH);
        if(getUserById(m_StaffID) == null)
        {
            System.out.println("Staff ID     Not Found");
            return;
        }

        System.out.println("Remove This Staff?");
        System.out.println(Arrays.toString(StaffManager.getUserById(m_StaffID)));
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                for (String[] row : staffData) {    
                    if (row[0].equals(m_StaffID)) {
                        temp.add(row);
                    }
                }
                break;
            case 2:
                System.out.println("Staff Not Removed, Exiting");
                return;
            default:
                break;
        }
        staffData.removeAll(temp);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String[] row : staffData) {
                for (String column : row) {
                    writer.append(column)
                            .append(",");
                }
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Remove Staff by Name
    public static void removeStaffByName(String m_StaffName)
    {
        getLatest(FILE_PATH);
        if(getUserByName(m_StaffName) == null)
        {
            System.out.println("Staff Name Not Found");
            return;
        }
        staffData.remove(new String[] { m_StaffName });
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String[] row : staffData) {
                for (String column : row) {
                    writer.append(column)
                            .append(",");
                }
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Print Staff Data
    public static void printStaffData() 
    {
        getLatest(FILE_PATH);
        System.out.println("**********************************************************************");
        for (String[] row : staffData) {
            for (String column : row) {
                System.out.printf("%-15s", column);
            }
            System.out.println();
        }
        System.out.println("**********************************************************************");
    }

    /* 
     *  Print Staff_List.csv filtered by role/gender/age
     *  index: 2 for role, 3 for gender, 4 for age
     *  filter: role: "Doctor" or "Pharmacist" or "Administrator"
     *           gender: "Male" or "Female"
     *           age: int
    */ 
    public static void printFilterBy(int index, String filter)
    {
        getLatest(FILE_PATH);
        System.out.println("****************************************");
        for (String[] row : staffData) 
        {
            if (row[index].equals(filter)) 
            {
                System.out.println(row[0] + " " + row[1] + " " + row[2] + " " + row[3] + " " + row[4]);
            }
        }
        System.out.println("****************************************");
    }


    /*
     * modificationType: 1 for Staff Name, 2 for Staff Role, 3 for Staff Gender, 4 for Staff Age
     *               id: Staff ID
     *          editVal: new value for the modificationType
     */
    public void editStaffInformation(int modificationType, String id, String editVal)
    {
        String[] staff = getUserById(id);
        switch(modificationType)
        {
            // edit staff's Name
            case 1:
                staff[1] = editVal;
                break;

            // edit staff's Role
            case 2:
                staff[2] = editVal;
                break;

            // edit staff's Gender
            case 3:
                staff[3] = editVal;
                break;

            // edit staff's Age
            case 4:
                staff[4] = editVal;
                break;
            default:
                System.out.println("Invalid Choice");
                return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String column : staff) {
                writer.append(column)
                      .append(",");
            }
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}       
package ServiceClasses.CSVManager;

import models.Staffs;
import models.enums.Role;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.BufferedWriter;

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
    public List<String[]> getStaffData() 
    {
        getLatest(FILE_PATH);
        return staffData;
    }

    public static String getUserById(String id)
    {
        getLatest(FILE_PATH);
        for (String[] row : staffData) 
        {
            if (row[0].equals(id)) 
            {
                return row[1] + " " + row[2] + " " + row[3] + " " + row[4];
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
    public void removeStaffById(String m_StaffID)
    {
        getLatest(FILE_PATH);
        if(getUserById(m_StaffID) == null)
        {
            System.out.println("Staff ID Not Found");
            return;
        }
        staffData.remove(new String[] { m_StaffID });
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
    public void removeStaffByName(String m_StaffName)
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
    public void printStaffData() 
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
    Print Staff filter by role/gender/age
    index: 2 for role, 3 for gender, 4 for age
    filter: role: "Doctor" or "Pharmacist" or "Administrator"
            gender: "Male" or "Female"
            age: int
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
}       
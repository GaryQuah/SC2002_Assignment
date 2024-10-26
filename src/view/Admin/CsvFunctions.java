package view.Admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFunctions {
    private static List<String[]> staffData = new ArrayList<>();
    private static final String FILE_PATH = "src/data/Staff_List.csv";

    private static void loadIfNotExist()
    {
        if (staffData.size() == 0)
        {
            startUp(FILE_PATH);
        }
    }

    public static void startUp(String FILE_PATH) 
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

    public static List<String[]> getStaffData()
    {
        startUp(FILE_PATH);
        return staffData;
    }
    
    
    public static void printStaffData()
    {
        startUp(FILE_PATH);
        System.out.println("**********************************************************************");
        for (String[] row : staffData) 
        {
            for (String column : row) 
            {
                System.out.printf("%-15s", column);
            }
            System.out.println();
        }
        System.out.println("**********************************************************************");
    }

    public static void filterBy(int index, String filter)
    {
        startUp(FILE_PATH);
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

    public static String getUserById(String id)
    {
        startUp(FILE_PATH);
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
        startUp(FILE_PATH);
        for (String[] row : staffData) 
        {
            if (row[1].equals(name)) 
            {
                return row[0] + " " + row[2] + " " + row[3] + " " + row[4];
            }
        }
        return null;
    }

    public static boolean isDuplicateId(String id)
    {
        startUp(FILE_PATH);
        for (String[] row : staffData) 
        {
            if (row[0].equals(id)) 
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isDuplicateName(String name)
    {
        startUp(FILE_PATH);
        for (String[] row : staffData) 
        {
            if (row[1].equals(name)) 
            {
                return true;
            }
        }
        return false;
    }
}

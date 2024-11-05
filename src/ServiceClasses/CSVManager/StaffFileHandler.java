package ServiceClasses.CSVManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaffFileHandler 
{
    private static List<String[]> staffData = new ArrayList<>();
    private static final String FILE_PATH = "src/data/Staff_List.csv";

    private StaffFileHandler() {}

    // Get Latest staff data from csv file
    public static List<String[]> getStaffData() 
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
        return staffData;
    }

    public static void save(List<String[]> staffData)
    {
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

    public static String[] getUserById(String id)
    {
        // get the latest staff data from csv file
        staffData = getStaffData();
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
        // get the latest staff data from csv file
        staffData = getStaffData();        
        for (String[] row : staffData) 
        {
            if (row[1].equals(name)) 
            {
                return row[0] + " " + row[2] + " " + row[3] + " " + row[4];
            }
        }
        return null;
    }
}

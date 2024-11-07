package ServiceClasses.Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientFileHandler 
{
    private static List<String[]> patientData = new ArrayList<>();
    private static final String FILE_PATH = "src/data/Patient_List.csv";

    private PatientFileHandler() {}

    // Get Latest patient data from csv file
    public static List<String[]> getPatientData() 
    {
        patientData.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] rows = line.split(",");
                patientData.add(rows);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return patientData;
    }

    public static void save(List<String[]> patientData)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String[] row : patientData) {
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
        // get the latest patient data from csv file
        patientData = getPatientData();
        for (String[] row : patientData) 
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
        // get the latest patient data from csv file
        patientData = getPatientData();        
        for (String[] row : patientData) 
        {
            if (row[1].equals(name)) 
            {
                return row[0] + " " + row[2] + " " + row[3] + " " + row[4];
            }
        }
        return null;
    }
}

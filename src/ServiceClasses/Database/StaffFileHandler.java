package ServiceClasses.Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ServiceClasses.Database.FileHandler;

public class StaffFileHandler extends FileHandler
{
    private static final String FILE_PATH = "src/data/Staff_List.csv";

    private StaffFileHandler () {
        super("src/data/Patient_List.csv");
    }

    // Get Latest staff data from csv file
    public  String[] getUserById(String id)
    {
        // get the latest staff data from csv file
        for (String[] row : getData())
        {
            if (row[0].equals(id)) 
            {
                return row;
            }
        }
        return null;
    }

    public String getUserByName(String name)
    {
        // get the latest staff data from csv file
        for (String[] row : getData())
        {
            if (row[1].equals(name)) 
            {
                return row[0] + " " + row[2] + " " + row[3] + " " + row[4];
            }
        }
        return null;
    }
}

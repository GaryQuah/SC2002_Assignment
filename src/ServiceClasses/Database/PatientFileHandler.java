package ServiceClasses.Database;


import java.util.ArrayList;
import java.util.List;

import ServiceClasses.Database.FileHandler;

public class PatientFileHandler extends FileHandler
{
    private PatientFileHandler() {
        super("src/data/Patient_List.csv");
    }

    public  String[] getUserById(String id)
    {
        // get the latest patient data from csv file
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
        // get the latest patient data from csv file
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

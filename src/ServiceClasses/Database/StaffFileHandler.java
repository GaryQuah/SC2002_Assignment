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
    public StaffFileHandler () {
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

    public String getRoleById(String id)
    {
        // get the latest staff data from csv file
        for (String[] row : getData()) 
        {
            if (row[0].equals(id)) 
            {
                return row[2];
            }
        }
        return null;
    }

    public boolean checkLogin(String currentUserID, String currentUserPassword)
    {
        System.out.println("here");
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
    }
}

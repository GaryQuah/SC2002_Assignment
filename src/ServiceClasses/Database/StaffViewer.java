package ServiceClasses.Database;

import java.util.ArrayList;
import java.util.List;

public class StaffViewer 
{
    private static List<String[]> staffData = new ArrayList<>();

    // Print Staff Data
    public static void printStaffData() 
    {
        staffData = StaffFileHandler.getStaffData();
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
        staffData = StaffFileHandler.getStaffData();
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

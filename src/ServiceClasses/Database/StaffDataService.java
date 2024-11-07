package ServiceClasses.Database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StaffDataService 
{
    private static List<String[]> staffData = new ArrayList<>();

    // Add Staff into CSV
    public void addStaff(String m_StaffID, String m_StaffName, String m_StaffRole, String m_StaffGender, int m_StaffAge) 
    {
        staffData = StaffFileHandler.getStaffData();
        staffData.add(new String[] { m_StaffID, m_StaffName, m_StaffRole, m_StaffGender, Integer.toString(m_StaffAge) });
        StaffFileHandler.save(staffData);           
    }

    // Remove staff by ID
    public static void removeStaffById(String m_StaffID)
    {
        List<String[]> temp = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        staffData = StaffFileHandler.getStaffData();
        if(StaffFileHandler.getUserById(m_StaffID) == null)
        {
            System.out.println("Staff ID     Not Found");
            return;
        }

        System.out.println("Remove This Staff?");
        System.out.println(Arrays.toString(StaffFileHandler.getUserById(m_StaffID)));
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
        StaffFileHandler.save(staffData);
    }

    /*
     * modificationType: 1 for Staff Name, 2 for Staff Role, 3 for Staff Gender, 4 for Staff Age
     *               id: Staff ID
     *          editVal: new value for the modificationType
     */
    public void editStaffInformation(int modificationType, String id, String editVal)
    {
        String[] staff = StaffFileHandler.getUserById(id);
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
        StaffFileHandler.save(staffData);
    }
}

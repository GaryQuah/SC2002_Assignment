package view.Admin;

import java.util.Scanner;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class AdminRemoveMember {
    public static void main()
    {
        String FILE_PATH = "src/data/Staff_List.csv";
        List<String[]> staffData = CsvFunctions.getStaffData();
        Scanner sc = new Scanner(System.in);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
        int choice;
        String id = "";
        boolean found = false;
        List<String[]> temp = new ArrayList<>();


        //print staff data array
        for (String[] s : staffData) {
            System.out.println(Arrays.toString(s)); // Print each staff member as an array
        }

        System.out.println("1. Search Staff Members By ID");
        System.out.println("2. Search Staff Members By Name");
        System.out.println("3. Exit");
        choice = sc.nextInt();
        sc.nextLine();

        switch(choice)
        {
            case 1:
                System.out.println("Enter Staff ID: ");
                id = sc.nextLine();
                found = CsvFunctions.isDuplicateId(id);
                if (!found)
                {
                    System.out.println("User ID Not Found");
                    return;
                }
                break;
            case 2:
                System.out.println("Enter Staff Name: ");
                id = sc.nextLine();
                found = CsvFunctions.isDuplicateName(id);
                if (!found)
                {
                    System.out.println("Staff Name Not Found");
                    return;
                }
                break;
            case 3:
                System.out.println("Exiting");
                return;
            default:
                System.out.println("Invalid Choice");
                return;
        }
        if (found)
        {
            // Iterator<String[]> itr = staffData.iterator();
            System.out.println("Current Staff Details:");
            if (choice == 1)
            {
                System.out.println(CsvFunctions.getUserById(id));
                for (String[] row : staffData) {    
                    if (row[0].equals(id)) {
                        temp.add(row);
                    }
                }
            }
            else if (choice == 2)
            {
                System.out.println(CsvFunctions.getUserByName(id));
                for (String[] row : staffData) {    
                    if (row[1].equals(id)) {
                        temp.add(row);
                    }
                }
            }

            staffData.removeAll(temp);
            for (String[] s : staffData) {
                System.out.println(Arrays.toString(s)); // Print each staff member as an array
            }

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
    }
}

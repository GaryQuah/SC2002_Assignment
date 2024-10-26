package view.Admin;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Scanner;

public class AdminAddMember 
{
    public static void main()
    {
        boolean b = false;
        int choice, age;
        String role="";
        String gender="";
        String name="";
        String id="";
        String FILE_PATH = "src/data/Staff_List.csv";
        Scanner sc = new Scanner(System.in);
        // List<String[]> staffData = CsvFunctions.getStaffData();

        while(true)
        {
            System.out.println("Select New Staff Role: ");
            System.out.println("1. Doctor");
            System.out.println("2. Pharmacist");
            System.out.println("3. Administrator");
            System.out.println("4. Exit");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice)
            {
                case 1:
                    role = "Doctor";
                    break;
                case 2:
                    role = "Pharmacist";
                    break;
                case 3:
                    role = "Administrator";
                    break;
                case 4:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid Choice");
                    continue;
            }
            break;
        }

        while(true)
        {
            System.out.println("Select New Staff's Gender: ");
            System.out.println("1. Male");
            System.out.println("2. Female");
            System.out.println("3. Exit");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice)
            {
                case 1:
                    gender = "Male";
                    break;
                case 2:
                    gender = "Female";
                    break;
                case 3:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid Choice");
                    continue;
            }
            break;
        }

        do{
            System.out.println("Enter 3 Digit Staff's ID: ");
            id = role.charAt(0) + sc.nextLine();
            if (CsvFunctions.isDuplicateId(id))
            {
                System.out.println("ID Already Exists");
                continue;
            }
            b = Pattern.matches("[DPA]\\d{3}", id);
        } while(!b);

        System.out.println("Enter New Staff's Age: ");
        age = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter New Staff's Name: ");
        name = sc.nextLine();

        String[] newStaff = new String[]{id, name, role, gender, Integer.toString(age)};
        // staffData.add(newStaff);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            for (String col : newStaff) {
                    writer.append(col)
                          .append(",");
                }
                writer.append("\n");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
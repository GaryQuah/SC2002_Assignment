package view.Admin;
import java.util.Scanner;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class AdminUpdateMember {
    
    public static void main()
    {
        String FILE_PATH = "src/data/Staff_List.csv";
        Scanner sc = new Scanner(System.in);
        List<String[]> staffData = CsvFunctions.getStaffData();
        int choice;  
        String name = "";
        String id = "";
        boolean found = false;
        String[] staff = new String[5];

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
                name = sc.nextLine();
                found = CsvFunctions.isDuplicateName(name);
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
            System.out.println("Current Staff Details:");
            if (choice == 1)
            {
                System.out.println(CsvFunctions.getUserById(id));
                for (String[] row : staffData) 
                {
                    if (row[0].equals(id)) 
                    {
                        staff = staffData.get(staffData.indexOf(row));
                    }
                }
            }
            else if (choice == 2)
            {
                System.out.println(CsvFunctions.getUserByName(name));
                for (String[] row : staffData) 
                {
                    if (row[1].equals(name)) 
                    {
                        staff = staffData.get(staffData.indexOf(row));
                    }
                }
            }
            System.out.println("Select Details To Update:");
            System.out.println("1. Staff ID");
            System.out.println("2. Staff Name");
            System.out.println("3. Staff Role");
            System.out.println("4. Staff Gender");
            System.out.println("5. Staff Age");
            int updateChoice = sc.nextInt();
            sc.nextLine();

            switch(updateChoice)
            {
                case 1:
                    System.out.println("Enter New Staff ID: ");
                    String newId = sc.nextLine();
                    if (CsvFunctions.isDuplicateId(newId))
                    {
                        System.out.println("ID Already Exists");
                        return;
                    }
                    boolean b = Pattern.matches("[DPA]\\d{3}", newId);
                    if (!b)
                    {
                        System.out.println("Invalid ID");
                        return;
                    }
                    staff[0] = newId;
                    break;
                case 2:
                    System.out.println("Enter New Staff Name: ");
                    String newName = sc.nextLine();
                    staff[1] = newName;
                    break;
                case 3:
                    System.out.println("Select New Staff Role: ");
                    System.out.println("1. Doctor");
                    System.out.println("2. Pharmacist");
                    System.out.println("3. Administrator");
                    int roleChoice = sc.nextInt();
                    sc.nextLine();
                    String newRole;
                    switch(roleChoice)
                    {
                        case 1:
                            newRole = "Doctor";
                            break;
                        case 2:
                            newRole = "Pharmacist";
                            break;
                        case 3:
                            newRole = "Administrator";
                            break;
                        default:
                            System.out.println("Invalid Choice");
                            return;
                    }
                    staff[2] = newRole;
                    break;
                case 4:
                    System.out.println("Select New Staff Gender: ");
                    System.out.println("1. Male");
                    System.out.println("2. Female");
                    int genderChoice = sc.nextInt();
                    sc.nextLine();
                    String newGender;
                    switch(genderChoice)
                    {
                        case 1:
                            newGender = "Male";
                            break;
                        case 2:
                            newGender = "Female";
                            break;
                        default:
                            System.out.println("Invalid Choice");
                            return;
                    }
                    staff[3] = newGender;
                    break;
                case 5:
                    System.out.println("Enter New Staff Age: ");
                    String newAge = sc.nextLine();
                    staff[4] = newAge;
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            staffData.set(staffData.indexOf(staff), staff);
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

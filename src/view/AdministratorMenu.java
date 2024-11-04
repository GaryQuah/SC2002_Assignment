package view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Arrays;

import ServiceClasses.Appointment.AppointmentManager;
import ServiceClasses.Appointment.AppointmentStatus;
import ServiceClasses.CSVManager.StaffManager;

public class AdministratorMenu 
{
    public static void main(String[] args) 
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println("---------- Administrator Menu ----------");
            System.out.println("1. View & Manage Hospital Staff");
            System.out.println("2. View Appointment Details");
            System.out.println("3. View & Manage Medication Inventory");
            System.out.println("4. Approve Replenishment Requests");
            System.out.println("5. Logout");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    AdminManageStaff();
                    break;
                case 2:
                    System.out.println("View Appointment Details");
                    break;
                case 3:                    
                    System.out.println("View & Manage Medication Inventory");
                    break;
                case 4:
                    System.out.println("Approve Replenishment Requests");
                    break;                    
                case 5:
                    System.out.println("Logout");
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        } while (choice != 5);

        if(choice == 5)
        {
            System.out.println("Exiting System...");
        }
    }

    public static void AdminManageStaff()
    {
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("--------------------------------");
        System.out.println("View & Manage Hospital Staff");
        System.out.println("--------------------------------");
        System.out.println("1. Show Staff Members");
        System.out.println("2. Add Staff Members");
        System.out.println("3. Update Staff Members");
        System.out.println("4. Remove Staff Members");
        choice = sc.nextInt();

        switch(choice)
        {
            case 1:
                System.out.println("--------------------------------");
                System.out.println("Show Staff Members");
                System.out.println("--------------------------------");
                System.out.println("1. View All Staff Members");
                System.out.println("2. View Staff Members By Role");
                System.out.println("3. View Staff Members By Gender");
                System.out.println("4. View Staff Members By Age");
                int filterChoice = sc.nextInt();
                switch(filterChoice)
                {
                    case 1:
                        StaffManager.printStaffData();
                        break;
                    case 2:
                        System.out.println("--------------------------------");
                        System.out.println("Select Role");
                        System.out.println("--------------------------------");
                        System.out.println("1. Doctor");
                        System.out.println("2. Pharmacist");
                        System.out.println("3. Administrator");
                        int roleChoice = sc.nextInt();
                        switch(roleChoice)
                        {
                            case 1:
                                StaffManager.printFilterBy(2, "Doctor");
                                break;
                            case 2:
                                StaffManager.printFilterBy(2, "Pharmacist");  
                                 break;
                            case 3:
                                StaffManager.printFilterBy(2, "Administrator");  
                                break;
                            default:
                                System.out.println("Invalid Choice");
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("--------------------------------");
                        System.out.println("Select Gender");
                        System.out.println("--------------------------------");
                        System.out.println("1. Male");
                        System.out.println("2. Female");
                        int genderChoice = sc.nextInt();
                        switch(genderChoice)
                        {
                            case 1:
                                StaffManager.printFilterBy(3, "Male");
                                break;
                            case 2:
                                StaffManager.printFilterBy(3, "Female");
                                break;
                            default:
                                System.out.println("Invalid Choice");
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("--------------------------------");
                        System.out.println("Enter Age");
                        System.out.println("--------------------------------");
                        int age = sc.nextInt();
                        StaffManager.printFilterBy(4, Integer.toString(age));
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
                break;
            case 2:
                System.out.println("--------------------------------");
                System.out.println("Add Staff Members");
                System.out.println("--------------------------------");
                AdminAddMember();
                break;
            case 3:
                System.out.println("--------------------------------");
                System.out.println("Update Staff Members");
                System.out.println("--------------------------------");
                AdminUpdateMember();
                break;
            case 4:
                System.out.println("--------------------------------");
                System.out.println("Remove Staff Members");
                System.out.println("--------------------------------");
                AdminRemoveMember();
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

    public static void AdminAddMember()
    {
        boolean b = false;
        int choice, age;
        String role = "";
        String gender = "";
        String name = "";
        String id = "";
        String FILE_PATH = "src/data/Staff_List.csv";
        Scanner sc = new Scanner(System.in);
        List<String[]> staffData = StaffManager.getStaffData();

        while (true) {
            System.out.println("Select New Staff Role: ");
            System.out.println("1. Doctor");
            System.out.println("2. Pharmacist");
            System.out.println("3. Administrator");
            System.out.println("4. Exit");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
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

        while (true) {
            System.out.println("Select New Staff's Gender: ");
            System.out.println("1. Male");
            System.out.println("2. Female");
            System.out.println("3. Exit");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
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

        do {
            System.out.println("Enter 3 Digit Staff's ID: ");
            id = role.charAt(0) + sc.nextLine();
            if (StaffManager.getUserById(id) != null) {
                System.out.println("ID Already Exists");
                continue;
            }
            b = Pattern.matches("[DPA]\\d{3}", id);
        } while (!b);

        System.out.println("Enter New Staff's Age: ");
        age = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter New Staff's Name: ");
        name = sc.nextLine();

        String[] newStaff = new String[] { id, name, role, gender, Integer.toString(age) };
        staffData.add(newStaff);

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

    public static void AdminUpdateMember()
    {
        String FILE_PATH = "src/data/Staff_List.csv";
        Scanner sc = new Scanner(System.in);
        List<String[]> staffData = StaffManager.getStaffData();
        String name = "";
        String id = "";
        boolean found = false;
        String[] staff = new String[5];

        System.out.println("Enter Staff ID: ");
        id = sc.nextLine();
        found = (StaffManager.getUserById(id) != null);
        if (!found)
        {
            System.out.println("User ID Not Found");
            return;
        }

        if (found)
        {
            System.out.println("Current Staff Details:");
            System.out.println(Arrays.toString(StaffManager.getUserById(id)));
            for (String[] row : staffData) 
            {
                if (row[0].equals(id)) 
                {
                    staff = staffData.get(staffData.indexOf(row));
                }
            }
            

            System.out.println("Select Details To Update:");
            System.out.println("1. Staff Name");
            System.out.println("2. Staff Role");
            System.out.println("3. Staff Gender");
            System.out.println("4. Staff Age");
            int updateChoice = sc.nextInt();
            sc.nextLine();

            switch(updateChoice)
            {
                case 1:
                    System.out.println("Enter New Staff Name: ");
                    String newName = sc.nextLine();
                    staff[1] = newName; 
                    break;
                case 2:
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
                case 3:
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
                case 4:
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

    public static void AdminRemoveMember()
    {
        int choice;  
        String id = "";
        String FILE_PATH = "src/data/Staff_List.csv";
        Scanner sc = new Scanner(System.in);
        List<String[]> staffData = StaffManager.getStaffData();

        System.out.println("1. Enter Staff ID");
        System.out.println("2. Exit");
        choice = sc.nextInt();
        sc.nextLine();

        switch(choice)
        {
            case 1:
                System.out.println("Enter Staff ID: ");
                id = sc.nextLine();
                if (StaffManager.getUserById(id) == null)
                {
                    System.out.println("User ID Not Found");
                    return;
                }
                break;
            case 2:
                System.out.println("Exiting");
            default:
                System.out.println("Invalid Choice");
                return;
        }
        StaffManager.removeStaffById(id);
    }       

    public static void ViewAppointmentDetails()
    {
        AppointmentManager appointmentManager = AppointmentManager.getInstance(); 
        AppointmentStatus appointmentStatus = AppointmentStatus.ACCEPTED;
        appointmentManager.ViewAllAppointmentsByStatus(appointmentStatus);
    }
}

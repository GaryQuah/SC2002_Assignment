package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import ServiceClasses.Appointment.AppointmentManager;
import ServiceClasses.Appointment.AppointmentStatus;
import ServiceClasses.Database.DataBaseManager;
import ServiceClasses.inventory.InventoryControl;
import models.Administrator;
import models.Doctor;
import models.Pharmacist;
import models.Staff;
import models.User;
import models.UserIDManager;
import models.enums.Gender;
import models.enums.Role;


/**
 * AdministratorMenu class implements the Menu interface
 * and provides various administrative functionalities for managing hospital operations.
 * This includes managing staff, appointments, medication inventory, and replenishment requests.
 * 
 * <p>The class serves as the main entry point for administrators to interact with the system.
 */
public class AdministratorMenu implements Menu
{
    /** Singleton instance of AppointmentManager to manage appointments. */
    private static AppointmentManager appointmentManager = AppointmentManager.getInstance();

    /** Singleton instance of InventoryControl to manage inventory operations. */
    private static InventoryControl inventoryControl = InventoryControl.getInstance();

    /**
     * Displays the main administrator menu and handles user input to navigate 
     * between different administrative functionalities.
     *
     * @param loggedInUser the currently logged-in administrator {@link User}.
     */
    public void displayMenu(User loggedInUser)
    {

        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome " + loggedInUser.getName() + "!");
        do
        {
            System.out.println("=======================================");
            System.out.println("|         Administrator Menu          |");
            System.out.println("=======================================");
            System.out.println("1. View & Manage Hospital Staff");
            System.out.println("2. View Appointment Details");
            System.out.println("3. View & Manage Medication Inventory");
            System.out.println("4. Approve Replenishment Requests");
            System.out.println("5. Logout");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            DataBaseManager.getInstance().getInventoryFileHandler().retrieveData();
            switch(choice)
            {
                case 1:
                    AdminManageStaffMenu();
                    break;
                case 2:
                    ViewAppointmentDetailsMenu();
                    break;
                case 3:                    
                    AdminManageInventoryMenu(loggedInUser);
                    break;
                case 4:
                    
                    inventoryControl.approveReplenishmentRequest(loggedInUser);
                    
                    break;                    
                case 5:
                    System.out.println("Logout");
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            DataBaseManager.getInstance().getInventoryFileHandler().saveData();
        } while (choice != 5);

        if(choice == 5)
        {
            System.out.println("Exiting System...");
        }
    }

    /**
     * Displays the hospital staff management menu and provides options to 
     * view, add, update, and remove staff members.
     */

    public static void AdminManageStaffMenu()
    {
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("View & Manage Hospital Staff");
        System.out.println("=================================");
        System.out.println("1. Show Staff Members");
        System.out.println("2. Add Staff Members");
        System.out.println("3. Update Staff Members");
        System.out.println("4. Remove Staff Members");
        choice = sc.nextInt();

        switch(choice)
        {
            case 1:
                System.out.println("=================================");
                System.out.println("Show Staff Members");
                System.out.println("=================================");
                System.out.println("1. View All Staff Members");
                System.out.println("2. View Staff Members By Role");
                System.out.println("3. View Staff Members By Gender");
                System.out.println("4. View Staff Members By Age");
                int filterChoice = sc.nextInt();
                switch(filterChoice)
                {
                    case 1:
                        DataBaseManager.getInstance().getStaffFileHandler().printStaffData();
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
                                DataBaseManager.getInstance().getStaffFileHandler().printFilterBy("Doctor");
                                break;
                            case 2:
                                DataBaseManager.getInstance().getStaffFileHandler().printFilterBy("Pharmacist");
                                break;
                            case 3:
                                DataBaseManager.getInstance().getStaffFileHandler().printFilterBy("Administrator");
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
                                DataBaseManager.getInstance().getStaffFileHandler().printFilterBy("Male");
                                break;
                            case 2:
                                DataBaseManager.getInstance().getStaffFileHandler().printFilterBy("Female");
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
                        DataBaseManager.getInstance().getStaffFileHandler().printFilterBy(Integer.toString(age));
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
                break;
            case 2:
            System.out.println("=================================");
            System.out.println("Add Staff Members");
                System.out.println("=================================");
                AdminAddMember();
                break;
            case 3:
            System.out.println("=================================");
            System.out.println("Update Staff Members");
                System.out.println("=================================");
                AdminUpdateMemberMenu();
                break;
            case 4:
                System.out.println("=================================");
                System.out.println("Remove Staff Members");
                System.out.println("=================================");
                AdminRemoveMemberMenu();
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }


    /**
     * Adds a new staff member to the system with details such as role, gender, age, and name.
     */

    public static void AdminAddMember()
    {
        boolean b = false;
        int choice, age;
        String role = "";
        String gender = "";
        String name = "";
        String id = "";
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
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
                        return;  // Exit the program
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                        continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();  
            }
        }

        while (true) {
            try {
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
                        return;  // Exit the program
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                        continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();  // Clear the invalid input
            }
        }

        while (true) {
            try {
                System.out.println("Enter New Staff's Age: ");
                age = sc.nextInt();
                sc.nextLine();

                if (age < 18 || age > 65) {
                    System.out.println("Invalid age. Age should be between 18 and 65.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for age.");
                sc.nextLine();  // Clear the invalid input
            }
        }

        do
        {
            System.out.println("Enter New Staff's Name: ");
            name = sc.nextLine().trim();
        }while (name.isEmpty());

        Staff newStaff = null;
        switch (role) {
            case "Doctor":
                newStaff = new Doctor(UserIDManager.getInstance().generateUniqueID(Role.Doctor), name, Role.valueOf(role), Gender.valueOf(gender), age, "password");
                break;
            case "Pharmacist":
                newStaff = new Pharmacist(UserIDManager.getInstance().generateUniqueID(Role.Pharmacist), name, Role.valueOf(role), Gender.valueOf(gender), age, "password");
                break;
            case "Administrator":
                newStaff = new Administrator(UserIDManager.getInstance().generateUniqueID(Role.Administrator), name, Role.valueOf(role), Gender.valueOf(gender), age, "password");
                break;
            default:
                System.out.println("Invalid role selected.");
                return; 
        }
        DataBaseManager.getInstance().getStaffFileHandler().addStaff(newStaff);
        System.out.println("Staff " + id + " has been added.");
    }


    /**
     * Displays the staff update menu, allowing administrators to modify 
     * staff details such as name, role, gender, age, and password.
     */
    public static void AdminUpdateMemberMenu() {
        Scanner sc = new Scanner(System.in);
        String id = "";
        boolean found = false;
        Staff updatedStaff = null;
    
        // Request and validate Staff ID
        System.out.println("Enter Staff ID: ");
        id = sc.nextLine();
    
        // Fetch the current staff by ID
        Staff currentStaff = DataBaseManager.getInstance().getStaffFileHandler().getUserById(id);
        if (currentStaff == null) {
            System.out.println("User ID Not Found");
            return;
        }
    
        // Display current staff details
        System.out.println("Selected Staff Details:");
        System.out.printf("%-5s | " ,"ID: " + currentStaff.getUserID());
        System.out.printf("%-15s | " ,"Name: " + currentStaff.getName());
        System.out.printf("%-15s | " ,"Role: " + currentStaff.getRole());
        System.out.printf("%-15s | " ,"Gender: " + currentStaff.getGender());
        System.out.printf("%-5s | " ,"Age: " + currentStaff.getAge());
        System.out.printf("%-5s | " ,"Password: " + currentStaff.getPassword());
        System.out.println("");
    
        updatedStaff = new Staff(currentStaff.getUserID(), currentStaff.getName(), currentStaff.getRole(),
                                 currentStaff.getGender(), currentStaff.getAge(), currentStaff.getPassword());
    
        System.out.println("Select Details To Update:");
        System.out.println("1. Staff Name");
        System.out.println("2. Staff Role");
        System.out.println("3. Staff Gender");
        System.out.println("4. Staff Age");
        System.out.println("5. Staff Password");
        int updateChoice = sc.nextInt();
        sc.nextLine();
    
        
        switch (updateChoice) {
            case 1:
                System.out.println("Enter New Staff Name: ");
                String newName = sc.nextLine();
                updatedStaff.setName(newName); // Update name
                break;
            case 2:
                System.out.println("Select New Staff Role: ");
                System.out.println("1. Doctor");
                System.out.println("2. Pharmacist");
                System.out.println("3. Administrator");
                int roleChoice = sc.nextInt();
                sc.nextLine();
                Role newRole;
                switch (roleChoice) {
                    case 1:
                        newRole = Role.Doctor;
                        break;
                    case 2:
                        newRole = Role.Pharmacist;
                        break;
                    case 3:
                        newRole = Role.Administrator;
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        return;
                }
                updatedStaff.setRole(newRole); // Update role
                updatedStaff.setUserID(UserIDManager.getInstance().generateUniqueID(newRole));  // Update the user ID
                break;
            case 3:
                System.out.println("Select New Staff Gender: ");
                System.out.println("1. Male");
                System.out.println("2. Female");
                int genderChoice = sc.nextInt();
                sc.nextLine();
                Gender newGender;
                switch (genderChoice) {
                    case 1:
                        newGender = Gender.Male;
                        break;
                    case 2:
                        newGender = Gender.Female;
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        return;
                }
                updatedStaff.setGender(newGender); // Update gender
                break;
            case 4:
                System.out.println("Enter New Staff Age: ");
                int newAge = sc.nextInt();
                sc.nextLine();
                updatedStaff.setAge(newAge); // Update age
                break;
            case 5:
                System.out.println("Enter New Staff Password: ");
                String newPassword = sc.nextLine();
                updatedStaff.setPassword(newPassword); // Update password
                break;
            default:
                System.out.println("Invalid Choice");
                return;
        }
        DataBaseManager.getInstance().getStaffFileHandler().editStaff(id, updatedStaff);
    
        System.out.println("Staff member updated successfully.");
    }
    


    /**
     * Provides functionality to remove a staff member from the system
     * by specifying their unique staff ID.
     */

    public static void AdminRemoveMemberMenu()
    {
        int choice;  
        String id = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Enter Staff ID");
        System.out.println("2. Exit");
        choice = sc.nextInt();
        sc.nextLine();

        switch(choice)
        {
            case 1:
                System.out.println("Enter Staff ID: ");
                id = sc.nextLine();
                Staff staff = DataBaseManager.getInstance().getStaffFileHandler().getUserById(id);
                if (staff == null)
                {
                    System.out.println("User ID Not Found");
                    return;
                }
                System.out.println("Staff Details: \n" + staff);
                System.out.println("Delete Staff?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1)
                {
                    DataBaseManager.getInstance().getStaffFileHandler().deleteStaff(id);
                    System.out.println("Staff successfully deleted.");
                }
                else
                {
                    System.out.println("Cancel Deletion.");
                }
                break;
            case 2:
                System.out.println("Exiting");
            default:
                System.out.println("Invalid Choice");
                return;
        }
    }       


    /**
     * Displays the appointment details menu, allowing administrators to view
     * appointments by various statuses such as accepted, declined, or pending.
     */

    public static void ViewAppointmentDetailsMenu()
    {   
        System.out.println("--------------------------------");
        System.out.println("View Appointment Details");
        System.out.println("--------------------------------");
        System.out.println("1. View All Appointments");
        System.out.println("2. View Accepted Appointments");
        System.out.println("3. View Declined Appointments");
        System.out.println("4. View Pending Appointments");
        System.out.println("4. View Completed Appointments");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice)
        {
            case 1:
                appointmentManager.getAppointmentViewer().ViewAllAppointments();
                break;
            case 2:
                appointmentManager.getAppointmentViewer().ViewAllAppointmentsByStatus(AppointmentStatus.ACCEPTED);
                break;
            case 3:
                appointmentManager.getAppointmentViewer().ViewAllAppointmentsByStatus(AppointmentStatus.DECLINED);
                break;
            case 4:
                appointmentManager.getAppointmentViewer().ViewAllAppointmentsByStatus(AppointmentStatus.UNACCEPTED);
                break;
            case 5:
                appointmentManager.getAppointmentViewer().ViewAllAppointmentsByStatus(AppointmentStatus.COMPLETED);
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }


    /**
     * Displays the inventory management menu, providing options to view,
     * edit, and manage inventory levels and replenishment requests.
     *
     * @param loggedInUser the currently logged-in administrator {@link User}.
     */

    public static void AdminManageInventoryMenu(User loggedInUser)
    {
        System.out.println("--------------------------------");
        System.out.println("View And Manage Inventory");
        System.out.println("--------------------------------");
        System.out.println("1. View Inventory");
        System.out.println("2. Edit Medication");
        System.out.println("3. Edit Low Stock Alert Level");
        System.out.println("4. Approve Replenishment Requests");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice)
        {
            case 1:
                inventoryControl.showInventory();
                break;
            case 2:
                inventoryControl.add(loggedInUser);
                break;
            case 3:
                inventoryControl.edit(loggedInUser);
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }
}

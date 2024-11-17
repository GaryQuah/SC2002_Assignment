package ServiceClasses.Database;

import java.util.ArrayList;

import input.CSVParse;
import models.Administrator;
import models.Doctor;
import models.Pharmacist;
import models.Staff;
import models.enums.Gender;
import models.enums.Role;



/**
 * Handles file operations for managing staff data, including reading,
 * saving, and modifying staff information stored in a CSV file.
 * <p>
 * This class supports multiple staff roles, such as Doctor, Pharmacist,
 * and Administrator. It allows for adding, editing, deleting, and filtering
 * staff data based on various criteria.
 */

public class StaffFileHandler extends FileHandler<Staff> {
    /**
     * Constructs a new StaffFileHandler with the specified file path for staff data.
     */
    public StaffFileHandler() {
        super("src/data/Staff_List.csv");
    }

    /**
     * Reads staff data from the CSV file and populates a list of Staff objects.
     * The method also determines the specific type of staff (Doctor, Pharmacist,
     * Administrator) based on their role.
     *
     * @return A list of Staff objects retrieved from the CSV file.
     */
    @Override
    public ArrayList<Staff> retrieveData() {
        ArrayList<Staff> staffList = new ArrayList<>();
        ArrayList<String[]> fileData = CSVParse.read(getFilePath(), true);

        for (String[] row : fileData) {
            if (row.length >= 5) {
                Staff staff = null;
                // Based on role, create the correct staff object and add it to the list
                switch (row[2]) {
                    case "Doctor":
                        staff = new Doctor(row[0], row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]),
                                Integer.parseInt(row[4]), row[5]);
                        break;
                    case "Pharmacist":
                        staff = new Pharmacist(row[0], row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]),
                                Integer.parseInt(row[4]), row[5]);
                        break;
                    case "Administrator":
                        staff = new Administrator(row[0], row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]),
                                Integer.parseInt(row[4]), row[5]);
                        break;
                    default:
                        System.out.println("Invalid role found: " + row[2]);
                        continue;
                }
                // Add the created staff object to the staffList
                staffList.add(staff);
            }
        }
        setDataArray(staffList);
        return staffList;
    }


    /**
     * Saves the current list of staff data to the CSV file.
     * The method writes staff details, including ID, name, role, gender, age, and password.
     */
    @Override
    public void saveData() {
        // Get the current staff data
        ArrayList<String> data = new ArrayList<String>();
        data.add("Staff ID,Name,Role,Gender,Age,Password");
        // ArrayList<String[]> staffList = new ArrayList<>();

        for (Staff staff : getDataArray()) {

            data.add(staff.getUserID() + "," + // Staff ID
                    staff.getName() + "," + // Name
                    staff.getRole().toString() + "," + // Role
                    staff.getGender().toString() + "," + // Gender
                    String.valueOf(staff.getAge()) + "," + // Age
                    staff.getPassword() // Password
            );
        }

        try {
            CSVParse.write(getFilePath(), data);
        } catch (Exception e) {
        }
    }


    /**
     * Verifies login credentials by matching the provided user ID and password
     * against the stored data.
     *
     * @param currentUserID       The user ID to check.
     * @param currentUserPassword The password to check.
     * @return {@code true} if the credentials are valid, {@code false} otherwise.
     */
    public boolean checkLogin(String currentUserID, String currentUserPassword) {
        ArrayList<Staff> staffList = retrieveData();

        for (Staff staff : staffList) {
            if (staff.getUserID().equals(currentUserID) && staff.getPassword().equals(currentUserPassword)) {
                return true;
            }
        }

        System.out.println("Invalid username or password for Staff.");
        return false; // Login failed
    }


    /**
     * Retrieves a Staff object by its user ID.
     *
     * @param userId The user ID of the staff member to retrieve.
     * @return The Staff object if found, or {@code null} if not found.
     */
    public Staff getUserById(String userId) {
        ArrayList<Staff> staffList = retrieveData();
        for (Staff staff : staffList) {
            if (staff.getUserID().equals(userId)) {
                return staff;
            }
        }
        return null;
    }


    /**
     * Adds a new staff member to the list and updates the CSV file.
     *
     * @param newStaff The Staff object to add.
     */
    public void addStaff(Staff newStaff) {
        ArrayList<Staff> currentStaffList = getDataArray();
        currentStaffList.add(newStaff);
        setDataArray(currentStaffList);
        saveData();
    }


    /**
     * Deletes a staff member from the list and updates the CSV file.
     *
     * @param userID The user ID of the staff member to delete.
     */
    public void deleteStaff(String userID) {
        // Get the current list of staff members
        ArrayList<Staff> currentStaffList = getDataArray();

        // Find the staff member with the given userID and remove them
        Staff staffToRemove = null;
        for (Staff staff : currentStaffList) {
            if (staff.getUserID().equals(userID)) {
                staffToRemove = staff;
                break;
            }
        }

        // If the staff member was found, remove them
        if (staffToRemove != null) {
            currentStaffList.remove(staffToRemove);
            setDataArray(currentStaffList); // Update the list
            saveData(); // Save the updated data to CSV
            System.out.println("Staff with ID " + userID + " has been deleted.");
        } else {
            System.out.println("Staff with ID " + userID + " not found.");
        }
    }


    /**
     * Updates the details of an existing staff member and saves the changes to the CSV file.
     *
     * @param userID        The user ID of the staff member to update.
     * @param updatedStaff The updated Staff object.
     */
    public void editStaff(String userID, Staff updatedStaff) {
        // Get the current list of staff members
        ArrayList<Staff> currentStaffList = getDataArray();

        // Find the staff member with the given userID and update their details
        boolean staffFound = false;
        for (int i = 0; i < currentStaffList.size(); i++) {
            Staff staff = currentStaffList.get(i);
            if (staff.getUserID().equals(userID)) {
                // Replace the old staff with the updated staff
                currentStaffList.set(i, updatedStaff);
                staffFound = true;
                break;
            }
        }

        // If the staff member was found, update the list and save it
        if (staffFound) {
            setDataArray(currentStaffList); // Update the list
            saveData(); // Save the updated data to CSV
            System.out.println("Staff with ID " + userID + " has been updated.");
        } else {
            System.out.println("Staff with ID " + userID + " not found.");
        }
    }


    /**
     * Prints the data of all staff members to the console.
     */
    public void printStaffData() {
        ArrayList<Staff> staffList = getDataArray();
        System.out.println("-------------------------------------------------------------");
        for (Staff staff : staffList) {
            // System.out.println(staff.getUserID() + " " + staff.getName() + " " + staff.getRole() + " " + staff.getGender() + " " + staff.getAge());
            System.out.printf("%-7s %-15s %-17s %-8s %-5s\n", staff.getUserID(), staff.getName(), staff.getRole(), staff.getGender(), staff.getAge());
        }
        System.out.println("-------------------------------------------------------------");
    }


    /**
     * Filters and prints staff data based on a given criterion, such as role, gender, or age.
     *
     * @param filter The filter value to match.
     */
    public void printFilterBy(String filter)
    {
        ArrayList<Staff> staffList = getDataArray();
        boolean found;
        System.out.println("-------------------------------------------------------------");
        for (Staff staff : staffList) {
            // check if role or gender matches
            found = staff.getRole().toString().equalsIgnoreCase(filter) || staff.getGender().toString().equalsIgnoreCase(filter);

            //error handling for int value
            try{
                found = found || Integer.parseInt(filter) == staff.getAge();
            } catch (NumberFormatException e) {}

            if (found) {
                System.out.println(staff.getUserID() + " " + staff.getName() + " " + staff.getRole() + " "
                        + staff.getGender() + " " + staff.getAge());    
            }
        }
        System.out.println("-------------------------------------------------------------");
    }
}

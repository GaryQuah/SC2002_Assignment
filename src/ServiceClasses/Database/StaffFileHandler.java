package ServiceClasses.Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import input.CSVParse;
import models.Administrator;
import models.Doctor;
import models.Pharmacist;
import models.Staff;
import models.enums.Gender;
import models.enums.Role;

public class StaffFileHandler extends FileHandler<Staff> {
    public StaffFileHandler() {
        super("src/data/Staff_List.csv");
    }

    @Override
    public ArrayList<Staff> retrieveData() {
        ArrayList<Staff> staffList = new ArrayList<>();
        ArrayList<String[]> fileData = CSVParse.read(getFilePath(), true);

        for (String[] row : fileData) {
            if (row.length >= 7) {
                Staff staff = null;
                // Based on role, create the correct staff object and add it to the list
                switch (row[2]) {
                    case "Doctor":
                        staff = new Doctor(row[0], row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]),
                                Integer.parseInt(row[4]), row[5], row[6]);
                        break;
                    case "Pharmacist":
                        staff = new Pharmacist(row[0], row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]),
                                Integer.parseInt(row[4]), row[5], row[6]);
                        break;
                    case "Administrator":
                        staff = new Administrator(row[0], row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]),
                                Integer.parseInt(row[4]), row[5], row[6]);
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

    @Override
    public void saveData() {
        // Get the current staff data
        ArrayList<String> data = new ArrayList<String>();
        data.add("Staff ID,Name,Role,Gender,Age,Username,Password\r");
        // ArrayList<String[]> staffList = new ArrayList<>();

        for (Staff staff : getDataArray()) {

            data.add(staff.getUserID() + "," + // Staff ID
                    staff.getName() + "," + // Name
                    staff.getRole().toString() + "," + // Role
                    staff.getGender().toString() + "," + // Gender
                    String.valueOf(staff.getAge()) + "," + // Age
                    staff.getUserID() + "," + // Username
                    staff.getPassword() // Password
            );
        }

        // Write the updated list to the CSV file
        try {
            CSVParse.write(getFilePath(), data);
        } catch (Exception e) {
        }
    }

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

    public Staff getUserById(String userId) {
        ArrayList<Staff> staffList = retrieveData();
        for (Staff staff : staffList) {
            if (staff.getUserID().equals(userId)) {
                return staff;
            }
        }
        return null;
    }

    public void addStaff(Staff newStaff) {
        ArrayList<Staff> currentStaffList = getDataArray();
        currentStaffList.add(newStaff);
        setDataArray(currentStaffList);
        saveData();
    }

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

    public void printStaffData() {
        ArrayList<Staff> staffList = getDataArray();
        System.out.println("-------------------------------------------------------------");
        for (Staff staff : staffList) {
            // System.out.println(staff.getUserID() + " " + staff.getName() + " " + staff.getRole() + " " + staff.getGender() + " " + staff.getAge());
            System.out.printf("%-7s %-15s %-17s %-8s %-5s\n", staff.getUserID(), staff.getName(), staff.getRole(), staff.getGender(), staff.getAge());
        }
        System.out.println("-------------------------------------------------------------");
    }

    public void printFilterByRole(String filter) {
        ArrayList<Staff> staffList = getDataArray();
        System.out.println("-------------------------------------------------------------");
        for (Staff staff : staffList) {
            if (staff.getRole().toString().equals(filter)) {
                System.out.println(staff.getUserID() + " " + staff.getName() + " " + staff.getRole() + " "
                        + staff.getGender() + " " + staff.getAge());
            }
        }
        System.out.println("-------------------------------------------------------------");
    }

    public void printFilterByGender(String filter) {
        ArrayList<Staff> staffList = getDataArray();
        System.out.println("-------------------------------------------------------------");
        for (Staff staff : staffList) {
            if (staff.getGender().toString().equals(filter)) {
                System.out.println(staff.getUserID() + " " + staff.getName() + " " + staff.getRole() + " "
                        + staff.getGender() + " " + staff.getAge());
            }
        }
        System.out.println("-------------------------------------------------------------");
    }

    public void printFilterByAge(String filter) {
        ArrayList<Staff> staffList = getDataArray();
        int age = Integer.parseInt(filter);
        System.out.println("-------------------------------------------------------------");
        for (Staff staff : staffList) {
            if (staff.getAge() == age) {
                System.out.println(staff.getUserID() + " " + staff.getName() + " " + staff.getRole() + " "
                        + staff.getGender() + " " + staff.getAge());
            }
        }
        System.out.println("-------------------------------------------------------------");
    }
}

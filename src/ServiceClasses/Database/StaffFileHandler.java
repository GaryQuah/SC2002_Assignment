package ServiceClasses.Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Administrator;
import models.Doctor;
import models.Pharmacist;
import models.Staff;
import ServiceClasses.Database.FileHandler;
import models.enums.Gender;
import models.enums.Role;

public class StaffFileHandler extends FileHandler<Staff>
{
    public StaffFileHandler () {
        super("src/data/Staff_List.csv");
    }

    @Override
    public ArrayList<Staff> retrieveData() {
        ArrayList<Staff> staffList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(getFilePath()))) {
            String line;
            boolean isFirstRow = true;  // Skip the first row (headers)
            while ((line = br.readLine()) != null) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                String[] row = line.split(",");
                if (row.length >= 7) {
                    Staff staff = null;
                    // Based on role, create the correct staff object and add it to the list
                    switch (row[2]) {
                        case "Doctor":
                            staff = new Doctor(row[0], row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]), Integer.parseInt(row[4]), row[5], row[6]);
                            break;
                        case "Pharmacist":
                            staff = new Pharmacist(row[0], row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]), Integer.parseInt(row[4]), row[5], row[6]);
                            break;
                        case "Administrator":
                            staff = new Administrator(row[0], row[1], Role.valueOf(row[2]), Gender.valueOf(row[3]), Integer.parseInt(row[4]), row[5], row[6]);
                            break;
                        default:
                            System.out.println("Invalid role found: " + row[2]);
                            continue;
                    }
                    // Add the created staff object to the staffList
                    staffList.add(staff);
                }
            }
        } catch (IOException e) {
            System.out.println("Error retrieving Staff data");
            e.printStackTrace();
        }

        setDataArray(staffList);
        return staffList;
    }

    @Override
    public void saveData() {
        // Get the current staff data
        ArrayList<String[]> staffList = new ArrayList<>();

        for (Staff staff : getDataArray()) {
            String[] staffData = new String[7];  // Assuming there are 7 fields
            staffData[0] = staff.getUserID();  // Staff ID
            staffData[1] = staff.getName();  // Name
            staffData[2] = staff.getRole().toString();  // Role
            staffData[3] = staff.getGender().toString();  // Gender
            staffData[4] = String.valueOf(staff.getAge());  // Age
            staffData[5] = staff.getUserID();  // Username
            staffData[6] = staff.getPassword();  // Password

            staffList.add(staffData);  // Add to the list of staff data
        }

        // Write the updated list to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath()))) {
            writer.write("Staff ID,Name,Role,Gender,Age,Username,Password\r\n");

            // Write each staff data to the file
            for (String[] staffData : staffList) {
                writer.write(String.join(",", staffData));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving Staff data");
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkLogin(String currentUserID, String currentUserPassword)
    {
        ArrayList<Staff> staffList = retrieveData();

        
        for (Staff staff : staffList) {
            if (staff.getUserID().equals(currentUserID) && staff.getPassword().equals(currentUserPassword)) {
                return true; 
            }
        }

        System.out.println("Invalid username or password for Staff.");
        return false;  // Login failed
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
        System.out.println(currentStaffList);
        setDataArray(currentStaffList);  
        System.out.println(currentStaffList);
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
            setDataArray(currentStaffList);  // Update the list
            saveData();  // Save the updated data to CSV
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
            setDataArray(currentStaffList);  // Update the list
            saveData();  // Save the updated data to CSV
            System.out.println("Staff with ID " + userID + " has been updated.");
        } else {
            System.out.println("Staff with ID " + userID + " not found.");
        }
    }

    public void printStaffData() {
        ArrayList<Staff> staffList = getDataArray();
        for (Staff staff : staffList) {
            System.out.println(staff.getUserID() + " " + staff.getName() + " " + staff.getRole() + " " + staff.getGender() + " " + staff.getAge());
        }
    }

    public void printFilterByRole(String filter) {
        ArrayList<Staff> staffList = getDataArray();
        System.out.println("****************************************");
        for (Staff staff : staffList) {
            if (staff.getRole().toString().equals(filter)) {
                System.out.println(staff.getUserID() + " " + staff.getName() + " " + staff.getRole() + " " + staff.getGender() + " " + staff.getAge());
            }
        }
        System.out.println("****************************************");
    }

    public void printFilterByGender(String filter) {
        ArrayList<Staff> staffList = getDataArray();
        System.out.println("****************************************");
        for (Staff staff : staffList) {
            if (staff.getGender().toString().equals(filter)) {
                System.out.println(staff.getUserID() + " " + staff.getName() + " " + staff.getRole() + " " + staff.getGender() + " " + staff.getAge());
            }
        }
        System.out.println("****************************************");
    }

    public void printFilterByAge(String filter) {
        ArrayList<Staff> staffList = getDataArray();
        int age = Integer.parseInt(filter);
        System.out.println("****************************************");
        for (Staff staff : staffList) {
            if (staff.getAge() == age) {
                System.out.println(staff.getUserID() + " " + staff.getName() + " " + staff.getRole() + " " + staff.getGender() + " " + staff.getAge());
            }
        }
        System.out.println("****************************************");
    }
}

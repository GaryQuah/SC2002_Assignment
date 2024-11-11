package ServiceClasses.SignUp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import ServiceClasses.Database.PatientFileHandler;
import ServiceClasses.Database.StaffFileHandler;

import java.io.IOException;



public class PTSignup 
{
    //sign up page for patient 
    private PTSignup() {}

    public static void main(String[] args) {
        List<String[]> patientData = PatientFileHandler.getPatientData();
        Scanner sc = new Scanner(System.in);
        int choice;
        String DOB;

        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        do {
            System.out.println("Enter your DOB (DD/MM/YYYY): ");
            DOB = sc.nextLine();
        } while (!validateDate(DOB));

        do {
            System.out.println("Enter your gender: ");
            System.out.println("1. Male");
            System.out.println("2. Female");
            choice = sc.nextInt();
            sc.nextLine();

        } while (choice < 1 || choice > 2);
        String gender = (choice == 1) ? "Male" : "Female";

        do {
            System.out.println("Enter your blood type: ");
            System.out.println("1. A+");
            System.out.println("2. A-");
            System.out.println("3. B+");
            System.out.println("4. B-");
            System.out.println("5. AB+");
            System.out.println("6. AB-");
            System.out.println("7. O+");
            System.out.println("8. O-");
            choice = sc.nextInt();
            sc.nextLine();

        } while (choice < 1 || choice > 8);
        String bloodType = (choice == 1) ? "A_POSITIVE" : (choice == 2) ? "A_NEGATIVE" : (choice == 3) ? "B_POSITIVE" : (choice == 4) ? "B_NEGATIVE" : (choice == 5) ? "AB_POSITIVE" : (choice == 6) ? "AB_NEGATIVE" : (choice == 7) ? "O_POSITIVE" : (choice == 8) ? "O_NEGATIVE" : "";
        
        System.out.println("Enter your contact information: ");
        String contactInfo = sc.nextLine();

        System.out.println("Enter your username: ");
        String username = sc.nextLine();

        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        
        String[] newPatient = new String[] { name, DOB, gender, bloodType, contactInfo, username, password };
        patientData.add(newPatient);
        PatientFileHandler.save(patientData);
    }

    public static boolean validateDate(String DOB)
    {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            Date date = format.parse(DOB);
            return true;
        } catch (ParseException e) {
            System.out.println("Invalid Date");
            return false;
        }
    }

    public static int getLastID(List<String[]> patientData)
    {
        int lastID = 0;
        for (String[] patient : patientData)
        {
            int currentID = Integer.parseInt(patient[0]);
            if (currentID > lastID)
                lastID = currentID;
        }
        System.out.println(lastID);
        return lastID;
    }
}

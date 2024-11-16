package ServiceClasses.SignUp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import ServiceClasses.Database.DataBaseManager;
import models.Patient;
import models.UserIDManager;
import models.enums.BloodType;
import models.enums.Gender;
import models.enums.Role;



public class PTSignup 
{
    //sign up page for patient 
    public PTSignup() {}

    public void signUp() {
        Scanner sc = new Scanner(System.in);
        int choice;
        String DOB;

        System.out.println("Welcome to the Patient Sign-Up Page");
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        do {
            System.out.println("Enter your DOB (DD/MM/YYYY): ");
            DOB = sc.nextLine();
        } while (!validateDate(DOB));

        Gender gender = null;
        do {
            System.out.println("Enter your gender: ");
            System.out.println("1. Male");
            System.out.println("2. Female");
            System.out.println("3. Others");
            choice = sc.nextInt();
            sc.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    gender = Gender.Male;
                    break;
                case 2:
                    gender = Gender.Female;
                    break;
                case 3:
                    gender = Gender.Others;
                    break;
                default:
                    System.out.println("Invalid choice, please select again.");
            }
        } while (gender == null);  // Ensure a valid gender is chosen

        BloodType bloodType = null;
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

            switch (choice) {
                case 1:
                    bloodType = BloodType.A_POSITIVE;
                    break;
                case 2:
                    bloodType = BloodType.A_NEGATIVE;
                    break;
                case 3:
                    bloodType = BloodType.B_POSITIVE;
                    break;
                case 4:
                    bloodType = BloodType.B_NEGATIVE;
                    break;
                case 5:
                    bloodType = BloodType.AB_POSITIVE;
                    break;
                case 6:
                    bloodType = BloodType.AB_NEGATIVE;
                    break;
                case 7:
                    bloodType = BloodType.O_POSITIVE;
                    break;
                case 8:
                    bloodType = BloodType.O_NEGATIVE;
                    break;
                default:
                    System.out.println("Invalid choice, please select again.");
            }
        } while (bloodType == null);

        System.out.println("Enter your contact information: ");
        String contactInfo = sc.nextLine();

        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        System.out.println("Enter your emergency contact name: ");
        String emergencyContactName = sc.nextLine();

        System.out.println("Enter your emergency contact relation: ");
        String emergencyContactRelation = sc.nextLine().trim();

        System.out.println("Enter your emergency contact number: ");        
        String emergencyContactNumber = sc.nextLine().trim();

        Patient newPatient = new Patient(UserIDManager.getInstance().generateUniqueID(Role.Patient), 
                                name, DOB, gender, bloodType, contactInfo, password, emergencyContactName, emergencyContactRelation, emergencyContactNumber);
        
        DataBaseManager.getInstance().getPatientFileHandler().addPatient(newPatient);
        System.out.println("Account created successfully!");
        System.out.println("Login with User ID " + newPatient.getUserID() + " and your password.");

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
}

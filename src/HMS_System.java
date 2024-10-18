import java.util.Vector;
import java.util.Scanner;

public class HMS_System {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vector<Appointment> AppointmentManager;
        Vector<User> ListOfUsers;

        //Create code here to open up file to retrieve locally stored users

        System.out.println("Welcome To The HMS System - Please Enter Your ID and Password");
        String ID = sc.nextLine();
        String Password = sc.nextLine();

        //Get role from users in the system
        
        
        String role = "";

        do{
            switch(role){
                case "Patient":
                    break;
                case "Doctor":
                    break;
                case "Pharmacist":
                    break;
                case "Administrator":
                    break;
                case "Exit":
                    System.out.println("Thank You For Using The System. See You Again Soon.");
                    break;
                default:
                    System.out.println("Inavlid Role Entered. Please key in a valid role");
                    break;
            }
        }while(role != "Exit");
    }

    public void PatientMenu()
    {

    }

    public void DoctorMenu()
    {

    }

    public void PharmacistMenu()
    {

    }

    public void AdministratorMenu()
    {

    }
}

import java.util.Vector;
import java.util.Scanner;

public class HMS_System {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vector<Appointment> AppointmentManager;

        System.out.println("Welcome To The HMS System - Please Enter Your Role - Enter Exit To Close The System");
        String role = sc.nextLine();

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
}

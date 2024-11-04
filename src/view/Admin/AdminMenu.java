package view.Admin;

import java.util.Scanner;
import models.Administrator;

public class AdminMenu 
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
                    AdminManageStaff.main();
                    break;
                case 2:
                    System.out.println("View Appointment Details");
                    AdminViewAppointment.main(args);;
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
            System.out.println("Thank You For Using The System. See You Again Soon.");
        }
    }       
}
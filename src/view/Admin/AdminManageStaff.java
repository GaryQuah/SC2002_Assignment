package view.Admin;
import java.util.Scanner;


public class AdminManageStaff {
    public static void main()
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
                        CsvFunctions.printStaffData();
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
                                CsvFunctions.filterBy(2, "Doctor");
                                break;
                            case 2:
                                 CsvFunctions.filterBy(2, "Pharmacist");  
                                 break;
                            case 3:
                                CsvFunctions.filterBy(2, "Administrator");  
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
                                CsvFunctions.filterBy(3, "Male");
                                break;
                            case 2:
                                CsvFunctions.filterBy(3, "Female");
                                break;
                            default:
                                System.out.println("Invalid Choice");
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("View Staff Members By Age");
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
                AdminAddMember.main();
                break;
            case 3:
                System.out.println("--------------------------------");
                System.out.println("Update Staff Members");
                System.out.println("--------------------------------");
                AdminUpdateMember.main();
                break;
            case 4:
                System.out.println("Remove Staff Members");
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }
}

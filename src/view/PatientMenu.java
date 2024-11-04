package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ServiceClasses.MedicalRecordService;
import ServiceClasses.Appointment.AppointmentManager;
import models.MedicalRecord;
import models.Patient;

public class PatientMenu {

    public static void main(String[] args) { // rename function

        int choice;
        Scanner sc = new Scanner(System.in);

        // example for testing
        Patient patient = new Patient("john_doe",);

        do {
            System.out.println("--------------------------------");
            System.out.println("Patient Menu");
            System.out.println("--------------------------------");
            System.out.println("1. View Medical Record");
            System.out.println("2. Update Personal Information");
            System.out.println("3. View Available Appointment Slots");
            System.out.println("4. Schedule an Appointment");
            System.out.println("5. Reschedule an Apppointment");
            System.out.println("6. Cancel an Appointment");
            System.out.println("7. View Scheduled Appointments");
            System.out.println("8. View Past Appointment Outcome Records");
            System.out.println("9. Logout");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                System.out.println("Name: " + patient.getPatientName());
                System.out.println("Patient ID: " + patient.getPatientID());
                System.out.println("Date of Birth: " + patient.getDateOfBirth());
                System.out.println("Gender: " + patient.getGender());
                System.out.println("Contact Information: " + patient.getContactInfo());
                System.out.println("Blood Type: " + patient.getBloodType());
                MedicalRecordService.viewMedicalRecord(patient);
                break;

                case 2: {
                    System.out.println("Please enter new contact information: ");
                    String newContactInfo = sc.nextLine();
                    patient.updateContactInfo(newContactInfo);
                    System.out.println("Personal information updated successfully.");

                    break;
                }

                case 3: {
                    System.out.print("Enter doctor name: ");
                    String doctorName = sc.nextLine();
                    AppointmentManager.getInstance().ViewAvailableDates(doctorName);
                    break;
                }

                case 4: {
                
                    System.out.print("Enter doctor name: ");
                    String doctorName = sc.nextLine();
                    System.out.print("Enter appointment date (yyyy-mm-dd): ");
                    String date = sc.nextLine();
                    System.out.print("Enter time slot (HH:mm): ");
                    String timeSlot = sc.nextLine();
                    System.out.print("Enter appointment type: ");
                    String type = sc.nextLine();
                    sc.nextLine(); // consume newline

                    AppointmentManager.getInstance().ScheduleAppointment(doctorName, patient.getPatientName(),date,timeSlot,type);                    
                    break;
                }

                case 5: {
                    System.out.print("Enter doctor name: ");
                    String rescheduleDoctorName = sc.nextLine();
                    System.out.print("Enter current appointment date (yyyy-mm-dd): ");
                    String oldDate = sc.nextLine();
                    System.out.print("Enter current time slot (HH:mm): ");
                    String oldTimeSlot = sc.nextLine();
                    System.out.print("Enter new appointment date (yyyy-mm-dd): ");
                    String newDate = sc.nextLine();
                    System.out.print("Enter new time slot (HH:mm): ");
                    String newTimeSlot = sc.nextLine();
                    System.out.print("Enter appointment type: ");
                    String newType = sc.nextLine();
                    AppointmentManager.getInstance().ReScheduleAppointment(rescheduleDoctorName, patient.getPatientName(), newDate, newTimeSlot,
                    newType,
                    oldDate, oldTimeSlot);                    
                    break;
                        }
        
                case 6: {
                    System.out.print("Enter doctor name: ");
                    String cancelDoctorName = sc.nextLine();
                    System.out.print("Enter appointment date (yyyy-mm-dd): ");
                    String cancelDate = sc.nextLine();
                    System.out.print("Enter time slot (HH:mm): ");
                    String cancelTimeSlot = sc.nextLine();
                    AppointmentManager.getInstance().CancelAppointment(cancelDoctorName, patient.getPatientName(),cancelDate, cancelTimeSlot);        
                    break;
                        }
        
                case 7:
                    AppointmentManager.getInstance().ViewPatientAppointments(patient.getPatientName());
                    break;
        
                case 8:
                 // update to past patient appoointment records 
                    break;
        
                case 9:
                    System.out.println("Logging out...");
                    break;
        
                default:
                    System.out.println("Invalid choice. Please try again.");
                    }
                } while (choice != 9);
        
            }
}
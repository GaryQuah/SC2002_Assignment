package view;

import java.util.Scanner;

import ServiceClasses.Database.PatientFileHandler;
import input.IntInput;
import ServiceClasses.Appointment.AppointmentManager;
import models.Patient;
import models.User;

public class PatientMenu implements Menu{

    private Patient patient;
    private PatientFileHandler patientFileHandler;

    public PatientMenu(Patient patient){

        this.patient = patient;
        this.patientFileHandler = new PatientFileHandler();
    }

    public void displayMenu(User user){ // rename function

        int choice;
        Scanner sc = new Scanner(System.in);

        // for testing
        /*String patientID = "P0000";
        String patientName = "John";
        String DOB = "01/01/2000";
        Gender gender = Gender.Male;
        BloodType bloodType = BloodType.O_POSITIVE;
        String contactInformation = "123-456-7890";
        String userName = "john123";
        String password = "idk";

        Patient patient = new Patient(patientID, patientName, DOB, gender, bloodType, contactInformation, userName,
                password);*/

        AppointmentManager appointmentManager = AppointmentManager.getInstance();

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

            choice = IntInput.integer("Option");
            

            switch (choice) {
                case 1:
                    System.out.println("=================================");
                    System.out.println("          MEDICAL RECORD         ");
                    System.out.println("=================================");
                    System.out.println("Name: " + patient.getUserID());
                    System.out.println("Name: " + patient.getUserID());
                    System.out.println("Patient ID: " + patient.getUserID());
                    System.out.println("Date of Birth: " + patient.getDateOfBirth());
                    System.out.println("Gender: " + patient.getGender());
                    System.out.println("Contact Information: " + patient.getContactInfo());
                    System.out.println("Emergency Contact:" + patient.getEmergencyContactInfo());
                    System.out.println("Blood Type: " + patient.getBloodType());
                    /*try {
                        MedicalRecordService.viewMedicalRecord(patient);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }*/
                    break;

                case 2: {

                    System.out.println("Update Personal Information");
                    System.out.println("1. Update Contact Information");
                    System.out.println("2. Update Emergency Contact Information");
                    System.out.print("Choose an option: ");
                    int updateChoice = sc.nextInt();
                    sc.nextLine(); // consume newline

                    if (updateChoice == 1) {
                        System.out.println("Please enter new contact information: ");
                        String newContactInfo = sc.nextLine();
                        patient.updateContactInfo(newContactInfo);
                        System.out.println("Personal information updated successfully.");
                        patientFileHandler.updatePatientInFile(patient); //update csv

                    } else if (updateChoice == 2) {
                        System.out.println("Please enter emergency contact name: ");
                        String emergencyName = sc.nextLine();
                        System.out.println("Please enter emergency contact relation: ");
                        String emergencyRelation = sc.nextLine();
                        System.out.println("Please enter emergency contact number: ");
                        String emergencyNumber = sc.nextLine();
                        patient.updateEmergencyContact(emergencyName, emergencyRelation, emergencyNumber);
                        patientFileHandler.updatePatientInFile(patient); //update csv
                    } else {
                        System.out.println("Invalid choice. Returning to main menu.");
                    }
                    break;
                }

                case 3: {
                    System.out.print("Enter doctor name: ");
                    String doctorName = sc.nextLine();
                    appointmentManager.getAppointmentViewer().ViewAvailableDates(doctorName);
                    break;
                }

                case 4: {

                    System.out.print("Enter doctor name: ");
                    String doctorName = sc.nextLine();
                    //System.out.print("Enter appointment date (yyyy-mm-dd): ");
                    System.out.print("Enter appointment date (dd-mm-yyyy): ");
                    String date = sc.nextLine();
                    System.out.print("Enter time slot (HH:mm) in 30 minute intervals: ");
                    String timeSlot = sc.nextLine();
                    System.out.print("Enter appointment type: ");
                    String type = sc.nextLine();

                    boolean success = appointmentManager.getAppointmentScheduler().ScheduleAppointment(
                            doctorName, patient.getName(), date, timeSlot, type);

                    if (success)
                        System.out.println("Appointment has been successfully scheduled.");
                    else
                        System.out.println("Appointment failed to be scheduled.");
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
                    boolean rescheduleSuccess = appointmentManager.getAppointmentScheduler().ReScheduleAppointment(
                            rescheduleDoctorName, patient.getName(), oldDate, oldTimeSlot, newType, newDate,
                            newTimeSlot);

                    if (rescheduleSuccess) {
                        System.out.println("Appointment rescheduled successfully.");
                    } else {
                        System.out.println("Failed to reschedule appointment.");
                    }
                    break;
                }

                case 6: {
                    System.out.print("Enter doctor name: ");
                    String cancelDoctorName = sc.nextLine();
                    System.out.print("Enter appointment date (yyyy-mm-dd): ");
                    String cancelDate = sc.nextLine();
                    System.out.print("Enter time slot (HH:mm): ");
                    String cancelTimeSlot = sc.nextLine();
                    boolean cancelSuccess = appointmentManager.getAppointmentScheduler().CancelAppointment(
                            cancelDoctorName, patient.getName(), cancelDate, cancelTimeSlot);

                    if (cancelSuccess) {
                        System.out.println("Appointment cancelled successfully.");
                    } else {
                        System.out.println("Failed to cancel appointment.");
                    }
                    break;
                }

                case 7:
                    appointmentManager.getAppointmentViewer().ViewPatientAppointments(patient.getName());
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
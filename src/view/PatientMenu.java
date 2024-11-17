package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import ServiceClasses.AppointmentOutcome.AppoinmentOutcomeControl;
import ServiceClasses.Database.DataBaseManager;
import ServiceClasses.Database.OutcomeFileHandler;
import ServiceClasses.Database.PatientFileHandler;
import input.IntInput;
import ServiceClasses.Appointment.AppointmentManager;
import models.Patient;
import models.User;

/**
 * The PatientMenu class provides a menu for patients to view and manage their medical records, 
 * appointments, and personal information. This class implements the {@code Menu} interface.
 */
public class PatientMenu implements Menu {

    private Patient patient;
    private PatientFileHandler patientFileHandler = DataBaseManager.getInstance().getPatientFileHandler();
    private OutcomeFileHandler outcomeFileHandler = DataBaseManager.getInstance().getOutcomeFileHandler();

    /**
     * Constructor for the PatientMenu.
     *
     * @param patient The patient object for whom the menu is displayed.
     */
    public PatientMenu(Patient patient) {
        this.patient = patient;
    }

    /**
     * Displays the patient-specific menu and handles user input to perform various operations.
     * <p>
     * The method runs in a loop until the user chooses to log out.
     * </p>
     *
     * @param user The user object for whom the menu is displayed.
     */
    public void displayMenu(User user) {

        int choice;
        Scanner sc = new Scanner(System.in);
        AppointmentManager appointmentManager = AppointmentManager.getInstance();
        outcomeFileHandler.retrieveData();

        do {
            System.out.println("--------------------------------");
            System.out.println("Patient Menu");
            System.out.println("--------------------------------");
            System.out.println("1. View Medical Record");
            System.out.println("2. Update Personal Information");
            System.out.println("3. View Available Appointment Slots");
            System.out.println("4. Schedule an Appointment");
            System.out.println("5. Reschedule an Appointment");
            System.out.println("6. Cancel an Appointment");
            System.out.println("7. View Scheduled Appointments");
            System.out.println("8. View Past Appointment Outcome Records");
            System.out.println("9: Change Password");
            System.out.println("10. Logout");

            choice = IntInput.integer("Option");

            switch (choice) {
                case 1:
                    displayMedicalRecord();
                    break;

                case 2:
                    updatePersonalInformation(sc);
                    break;

                case 3:
                    displayAvailableAppointmentSlots();
                    break;

                case 4:
                    scheduleAppointment(sc, appointmentManager);
                    break;

                case 5:
                    rescheduleAppointment(sc, appointmentManager);
                    break;

                case 6:
                    cancelAppointment(sc, appointmentManager);
                    break;

                case 7:
                    viewScheduledAppointments(appointmentManager);
                    break;

                case 8:
                    viewPastAppointmentOutcomes();
                    break;

                case 9:
                    changePassword(sc);
                    break;

                case 10:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            outcomeFileHandler.saveData();
        } while (choice != 10);
    }

    /**
     * Displays the patient's medical record.
     */
    private void displayMedicalRecord() {
        System.out.println("=================================");
        System.out.println("          MEDICAL RECORD         ");
        System.out.println("=================================");
        System.out.println("Name: " + patient.getName());
        System.out.println("Patient ID: " + patient.getUserID());
        System.out.println("Date of Birth: " + patient.getDateOfBirth());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Contact Information: " + patient.getContactInfo());
        System.out.println("Emergency Contact: " + patient.getEmergencyContactInfo());
        System.out.println("Blood Type: " + patient.getBloodType());
        AppoinmentOutcomeControl.getInstance().viewMedicalRecordsByPatient(patient);
    }

    /**
     * Updates the patient's personal or emergency contact information.
     *
     * @param sc The scanner object for user input.
     */
    private void updatePersonalInformation(Scanner sc) {
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
            patientFileHandler.updatePatientInFile(patient);

        } else if (updateChoice == 2) {
            System.out.println("Please enter emergency contact name: ");
            String emergencyName = sc.nextLine();
            System.out.println("Please enter emergency contact relation: ");
            String emergencyRelation = sc.nextLine();
            System.out.println("Please enter emergency contact number: ");
            String emergencyNumber = sc.nextLine();
            patient.updateEmergencyContact(emergencyName, emergencyRelation, emergencyNumber);
            patientFileHandler.updatePatientInFile(patient);
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    /**
     * Displays all available appointment slots for all doctors.
     */
    private void displayAvailableAppointmentSlots() {
        HashMap<String, HashMap<String, ArrayList<String>>> availability = AppointmentManager.getInstance()
                .getAppointmentScheduler().getAllDoctorAvailabilities();

        System.out.println("Available Appointment Slots:");

        if (availability.isEmpty()) {
            System.out.println("No doctors have set their availability yet.");
            return;
        }

        for (String doctorName : availability.keySet()) {
            System.out.println("Doctor: " + doctorName);
            HashMap<String, ArrayList<String>> dates = availability.get(doctorName);
            for (String date : dates.keySet()) {
                ArrayList<String> slots = dates.get(date);
                if (!slots.isEmpty()) {
                    System.out.println("Date: " + date);
                    System.out.println("Available Times: ");
                    for (int i = 0; i < slots.size(); i++) {
                        System.out.print(slots.get(i));
                        if ((i + 1) % 5 == 0 || i == slots.size() - 1) {
                            System.out.println();
                        } else {
                            System.out.print(", ");
                        }
                    }
                }
            }
        }
    }

    /**
     * Schedules an appointment for the patient with a specified doctor, date, and time.
     *
     * @param sc                 The scanner object for user input.
     * @param appointmentManager The AppointmentManager instance.
     */
    private void scheduleAppointment(Scanner sc, AppointmentManager appointmentManager) {
        System.out.print("Enter doctor name: ");
        String doctorName = sc.nextLine();
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
    }

    /**
     * Reschedules an existing appointment for the patient.
     *
     * @param sc                 The scanner object for user input.
     * @param appointmentManager The AppointmentManager instance.
     */
    private void rescheduleAppointment(Scanner sc, AppointmentManager appointmentManager) {
        System.out.print("Enter doctor name: ");
        String rescheduleDoctorName = sc.nextLine();
        System.out.print("Enter current appointment date (dd-mm-yyyy): ");
        String oldDate = sc.nextLine();
        System.out.print("Enter current time slot (HH:mm): ");
        String oldTimeSlot = sc.nextLine();
        System.out.print("Enter new appointment date (dd-mm-yyyy): ");
        String newDate = sc.nextLine();
        System.out.print("Enter new time slot (HH:mm): ");
        String newTimeSlot = sc.nextLine();
        System.out.print("Enter appointment type: ");
        String newType = sc.nextLine();

        boolean rescheduleSuccess = appointmentManager.getAppointmentScheduler().ReScheduleAppointment(
                rescheduleDoctorName, patient.getName(), oldDate, oldTimeSlot, newType, newDate, newTimeSlot);

        if (rescheduleSuccess) {
            System.out.println("Appointment rescheduled successfully.");
        } else {
            System.out.println("Failed to reschedule appointment.");
        }
    }

    /**
     * Cancels an appointment for the patient.
     *
     * @param sc                 The scanner object for user input.
     * @param appointmentManager The AppointmentManager instance.
     */
    private void cancelAppointment(Scanner sc, AppointmentManager appointmentManager) {
        System.out.print("Enter doctor name: ");
        String cancelDoctorName = sc.nextLine();
        System.out.print("Enter appointment date (dd-mm-yyyy): ");
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
    }

    /**
     * Views all scheduled appointments for the patient.
     *
     * @param appointmentManager The AppointmentManager instance.
     */
    private void viewScheduledAppointments(AppointmentManager appointmentManager) {
        appointmentManager.getAppointmentViewer().ViewPatientAppointments(patient.getName());
    }

    /**
     * Views past appointment outcomes for the patient.
     */
    private void viewPastAppointmentOutcomes() {
        AppoinmentOutcomeControl.getInstance().viewAppoinmentOutcomes(patient);
    }

    /**
     * Changes the patient's password.
     */
    private void changePassword(Scanner sc) {
        System.out.println("Enter new password: ");
        String newPassword = sc.nextLine();
        patient.setPassword(newPassword);
        patientFileHandler.updatePatientInFile(patient);
        System.out.println("Password changed successfully.");
    }
}

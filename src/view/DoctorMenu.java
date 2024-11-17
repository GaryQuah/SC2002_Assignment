package view;

import ServiceClasses.Appointment.Appointment;
import ServiceClasses.Appointment.AppointmentManager;
import ServiceClasses.AppointmentOutcome.AppoinmentOutcomeControl;
import ServiceClasses.Database.DataBaseManager;
import input.IntInput;
import models.Doctor;
import models.User;
import java.util.Scanner;

/**
 * The {@code DoctorMenu} class provides a menu interface for doctors to
 * manage their appointments, availability, and patient medical records.
 * This class implements the {@code Menu} interface.
 * <p>
 * It allows doctors to perform various actions such as:
 * <ul>
 *     <li>Viewing and updating patient medical records</li>
 *     <li>Managing their personal schedule and availability</li>
 *     <li>Accepting or declining appointment requests</li>
 *     <li>Recording outcomes for completed appointments</li>
 * </ul>
 */
public class DoctorMenu implements Menu {

    /**
     * Prints the menu header and the available options for doctors.
     */
    public void header() {
        System.out.println();
        System.out.println("---------- Doctor Menu ----------");
        System.out.println("1. View Patient Medical Records");
        System.out.println("2. Update Patient Medical Records");
        System.out.println("3. View Personal Schedule");
        System.out.println("4. Set Availability for Appointments");
        System.out.println("5. Accept or Decline Appointment Requests");
        System.out.println("6. View Upcoming Appointments");
        System.out.println("7. Record Appointment Outcome");
        System.out.println("8. Logout");
    }

    /**
     * Displays the menu for the doctor, allowing them to perform various
     * operations based on their selection.
     *
     * @param loggedInUser The currently logged-in user. This method ensures the
     *                     user is a doctor before proceeding.
     */
    @Override
    public void displayMenu(User loggedInUser) {
        if (!(loggedInUser instanceof Doctor)) {
            System.out.println("Access Denied: Only Doctors can access this menu.");
            return;
        }

        Doctor doctor = (Doctor) loggedInUser;
        int choice;

        do {
            DataBaseManager.getInstance().getInventoryFileHandler().retrieveData();
            DataBaseManager.getInstance().getOutcomeFileHandler().retrieveData();
            DataBaseManager.getInstance().getappointmentFileHandler().retrieveData();

            header();
            choice = IntInput.integer("Option");

            switch (choice) {
                case 1 -> viewPatientMedicalRecords(doctor);
                case 2 -> updatePatientMedicalRecords(doctor);
                case 3 -> viewPersonalSchedule(doctor);
                case 4 -> setAvailability(doctor);
                case 5 -> handleAppointmentRequests(doctor);
                case 6 -> viewUpcomingAppointments(doctor);
                case 7 -> recordAppointmentOutcome(doctor);
                case 8 -> System.out.println("Logout...");
                default -> System.out.println("Invalid Option. Please try again.");
            }
            DataBaseManager.getInstance().getInventoryFileHandler().saveData();
            DataBaseManager.getInstance().getOutcomeFileHandler().saveData();
            DataBaseManager.getInstance().getappointmentFileHandler().saveData();
        } while (choice != 8);
    }

    /**
     * Allows the doctor to view the medical records of their patients.
     *
     * @param doctor The currently logged-in doctor.
     */
    private void viewPatientMedicalRecords(Doctor doctor) {
        AppoinmentOutcomeControl.getInstance().viewMedicalRecordsByDoctor(doctor);
    }

    /**
     * Allows the doctor to update the medical records of their patients.
     *
     * @param doctor The currently logged-in doctor.
     */
    private void updatePatientMedicalRecords(Doctor doctor) {
        AppoinmentOutcomeControl.getInstance().edit(doctor);
    }

    /**
     * Displays the personal schedule of the doctor, including upcoming
     * appointments.
     *
     * @param doctor The currently logged-in doctor.
     */
    private void viewPersonalSchedule(Doctor doctor) {
        System.out.println("Fetching your personal schedule...");
        AppointmentManager.getInstance().getAppointmentViewer().ViewDoctorAppointments(doctor.getName());
    }

    /**
     * Allows the doctor to set their availability for patient appointments.
     *
     * @param doctor The currently logged-in doctor.
     */
    private void setAvailability(Doctor doctor) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the date (dd-MM-yyyy): ");
        String date = sc.nextLine();
        System.out.print("Enter your available start time (HH:mm): ");
        String startTime = sc.nextLine();
        System.out.print("Enter your available end time (HH:mm): ");
        String endTime = sc.nextLine();

        boolean success = AppointmentManager.getInstance().getAppointmentScheduler()
                .SetDoctorAvailability(doctor.getName(), date, startTime, endTime);

        if (success) {
            System.out.println("Availability updated successfully.");
        } else {
            System.out.println("Failed to update availability.");
        }
    }

    /**
     * Allows the doctor to accept or decline appointment requests from patients.
     *
     * @param doctor The currently logged-in doctor.
     */
    private void handleAppointmentRequests(Doctor doctor) {
        System.out.println("Managing appointment requests...");
        AppointmentManager.getInstance().getAppointmentScheduler().ManageDoctorAppointments(doctor.getName());
    }

    /**
     * Displays the upcoming appointments for the doctor.
     *
     * @param doctor The currently logged-in doctor.
     */
    private void viewUpcomingAppointments(Doctor doctor) {
        System.out.println("Your upcoming appointments:");
        AppointmentManager.getInstance().getAppointmentViewer().ViewUpcomingAppointments(doctor.getName());
    }

    /**
     * Allows the doctor to record the outcome of a completed appointment.
     *
     * @param doctor The currently logged-in doctor.
     */
    private void recordAppointmentOutcome(Doctor doctor) {
        int appointmentId = IntInput.integer("Enter Appointment ID");
        Appointment appointment = AppointmentManager.getInstance().getAppointmentScheduler()
                .getAppointmentByID(appointmentId);
        if (appointment == null) {
            System.out.println("Invalid Appointment ID.");
        } else {
            System.out.println("Found." + appointment.getDoctorName());
            AppoinmentOutcomeControl.getInstance().create(doctor, appointment);
        }
    }
}

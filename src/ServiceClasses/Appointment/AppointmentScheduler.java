package ServiceClasses.Appointment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The AppointmentScheduler class is responsible for scheduling, managing, and rescheduling
 * appointments for doctors and patients. It supports functionalities such as managing doctor
 * availability, checking for conflicts in scheduling, and handling appointment requests.
 */
public class AppointmentScheduler {

    // List of all appointments
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    // Date and time formats used for validating and parsing input
    private final String dateFormat = "dd-MM-yyyy";
    private final String timeFormat = "HH:mm";

    // Doctor availability mapping
    private HashMap<String, ArrayList<String>> doctorAvailability = new HashMap<>();

    /**
     * Constructor for initializing AppointmentScheduler with an existing list of appointments.
     *
     * @param AppointmentList List of appointments to manage.
     */
    public AppointmentScheduler(ArrayList<Appointment> AppointmentList) {
        this.AppointmentList = AppointmentList;
    }

    /**
     * Manages appointments for a given doctor. Displays a list of appointments and allows
     * the doctor to accept or decline them.
     *
     * @param doctorName The name of the doctor whose appointments are to be managed.
     */
    public void ManageDoctorAppointments(String doctorName) {
        Scanner sc = new Scanner(System.in);
        boolean hasAppointments = false;

        System.out.println("Managing appointments for Dr. " + doctorName);
        for (int i = 0; i < AppointmentList.size(); i++) {
            Appointment appointment = AppointmentList.get(i);

            if (appointment.getDoctorName().equals(doctorName)) {
                hasAppointments = true;
                System.out.println("Appointment ID: " + appointment.getAppointmentID());
                System.out.println("Patient Name: " + appointment.getPatientName());
                System.out.println("Date: " + appointment.getAppointmentDate());
                System.out.println("Time: " + appointment.getTimeSlot());
                System.out.println("Status: " + appointment.getAppointmentStatus());
                System.out.println("-------------------------------------");

                System.out.println("1. Accept Appointment");
                System.out.println("2. Decline Appointment");
                System.out.println("3. Skip");

                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        appointment.setAppointmentStatus(AppointmentStatus.ACCEPTED);
                        System.out.println("Appointment Accepted.");
                        break;

                    case 2:
                        appointment.setAppointmentStatus(AppointmentStatus.DECLINED);
                        System.out.println("Appointment Declined.");
                        break;

                    case 3:
                        System.out.println("Skipping Appointment.");
                        break;

                    default:
                        System.out.println("Invalid Choice. Skipping Appointment.");
                        break;
                }
            }
        }

        if (!hasAppointments) {
            System.out.println("No appointments found for Dr. " + doctorName);
        }
    }

    /**
     * Checks for an existing appointment for a specific doctor on a given date and time.
     * If an appointment exists, its index is returned; otherwise, -1 is returned.
     *
     * @param m_doctorName The name of the doctor.
     * @param m_date The date of the appointment.
     * @param m_timeSlot The time of the appointment.
     * @return The index of the existing appointment, or -1 if no appointment is found.
     */
    private int CheckForExistingAppointment(String m_doctorName, String m_date, String m_timeSlot) {
        System.out.println("Checking for : Doctor " + m_doctorName + " Date : " + m_date + " Time : " + m_timeSlot);
        for (int i = 0; i < AppointmentList.size(); ++i) {
            System.out.println("Comparing against : Doctor " + AppointmentList.get(i).getDoctorName()
                    + " Date : " + AppointmentList.get(i).getAppointmentDate()
                    + " Time : " + AppointmentList.get(i).getTimeSlot());

            if (AppointmentList.get(i).getDoctorName().equals(m_doctorName)
                    && AppointmentList.get(i).getTimeSlot().equals(m_timeSlot)
                    && AppointmentList.get(i).getAppointmentDate().equals(m_date)) {
                // If the appointment exists in the system but was declined, return -1
                if (AppointmentList.get(i).getAppointmentStatus() == AppointmentStatus.DECLINED)
                    return -1;

                return i; // Appointment exists
            }
        }
        return -1; // No appointment found
    }

    /**
     * Sets the availability for a doctor, specifying a time range during which the doctor is available.
     *
     * @param doctorName The name of the doctor.
     * @param startTime The start time of the doctor's availability.
     * @param endTime The end time of the doctor's availability.
     * @return true if the availability was successfully set, false if the time format was invalid.
     */
    public boolean SetDoctorAvailability(String doctorName, String startTime, String endTime) {
        // Validate input time formats
        if (!isValidTime(startTime) || !isValidTime(endTime)) {
            System.out.println("Invalid time format! Please use " + timeFormat);
            return false;
        }

        // Initialize or update the availability list for the doctor
        ArrayList<String> availability = doctorAvailability.getOrDefault(doctorName, new ArrayList<>());
        availability.add("From: " + startTime + " To: " + endTime);
        doctorAvailability.put(doctorName, availability);

        System.out.println("Availability updated for Dr. " + doctorName);
        return true;
    }

    /**
     * Validates if the given date input is in the correct format (dd-MM-yyyy) and is in the future.
     *
     * @param input The date string to validate.
     * @return true if the date is valid and in the future, false otherwise.
     */
    private boolean isValidDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate parsedDate = LocalDate.parse(input, formatter);
            LocalDate today = LocalDate.now(); // Get today's date
            return parsedDate.isAfter(today); // Ensure the date is in the future
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Validates if the given time input is in the correct format (HH:mm).
     *
     * @param input The time string to validate.
     * @return true if the time is valid, false otherwise.
     */
    private boolean isValidTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
        try {
            LocalTime.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Retrieves the availability of a doctor, including all scheduled time slots.
     *
     * @param doctorName The name of the doctor.
     * @return A list of availability time slots for the doctor.
     */
    public ArrayList<String> GetDoctorAvailability(String doctorName) {
        return doctorAvailability.getOrDefault(doctorName, new ArrayList<>());
    }

    /**
     * Schedules a new appointment for a doctor and a patient, ensuring there are no conflicts with
     * existing appointments.
     *
     * @param m_doctorName The name of the doctor.
     * @param m_patientName The name of the patient.
     * @param m_date The date of the appointment.
     * @param m_timeSlot The time of the appointment.
     * @param m_appointmentType The type of the appointment.
     * @return true if the appointment was successfully scheduled, false if there was a conflict.
     */
    public boolean ScheduleAppointment(String m_doctorName, String m_patientName, String m_date, String m_timeSlot,
                                       String m_appointmentType) {
        if (!isValidDate(m_date) || !isValidTime(m_timeSlot)) {
            System.out.println("Invalid date and time format input! Please provide a proper format : " + dateFormat
                    + " " + timeFormat);
            return false;
        }

        // Check for existing appointments
        int indexChecker = CheckForExistingAppointment(m_doctorName, m_date, m_timeSlot);
        if (indexChecker == -1) {
            AppointmentList.add(new Appointment(m_doctorName, m_patientName, m_date, m_timeSlot, m_appointmentType));
            System.out.println("Successfully Added Appointment. Doctor: " + m_doctorName + ", Patient: " + m_patientName
                    + ", Date: " + m_date + ", Time: " + m_timeSlot);
            return true;
        }

        System.out.println("Failed to add appointment. Conflict exists.");
        return false;
    }

    /**
     * Reschedules an existing appointment by removing the old appointment and scheduling a new one.
     *
     * @param m_doctorName The name of the doctor.
     * @param m_patientName The name of the patient.
     * @param m_oldDate The original date of the appointment.
     * @param m_oldTimeSlot The original time of the appointment.
     * @param m_appointmentType The type of the appointment.
     * @param m_newDate The new date of the appointment.
     * @param m_newTimeSlot The new time of the appointment.
     * @return true if the appointment was successfully rescheduled, false otherwise.
     */
    public boolean ReScheduleAppointment(String m_doctorName, String m_patientName, String m_oldDate,
                                         String m_oldTimeSlot, String m_appointmentType, String m_newDate, String m_newTimeSlot) {
        int indexChecker = CheckForExistingAppointment(m_doctorName, m_oldDate, m_oldTimeSlot);
        if (indexChecker != -1) {
            AppointmentList.remove(indexChecker);
            return ScheduleAppointment(m_doctorName, m_patientName, m_newDate, m_newTimeSlot, m_appointmentType);
        } else {
            System.out.println("Unable to re-schedule appointment. Appointment does not exist.");
            return false;
        }
    }

    /**
     * Cancels an existing appointment.
     *
     * @param m_doctorName The name of the doctor.
     * @param m_patientName The name of the patient.
     * @param m_date The date of the appointment.
     * @param m_timeSlot The time of the appointment.
     * @return true if the appointment was successfully canceled, false otherwise.
     */
    public boolean CancelAppointment(String m_doctorName, String m_patientName, String m_date, String m_timeSlot) {
        int indexChecker = CheckForExistingAppointment(m_doctorName, m_date, m_timeSlot);

        if (indexChecker == -1) {
            System.out.println("Unable to cancel appointment. Appointment does not exist.");
            return false;
        } else {
            AppointmentList.remove(indexChecker);
            System.out.println("Successfully Cancelled Appointment.");
            return true;
        }
    }

    /**
     * Updates the status of an appointment request.
     *
     * @param m_doctorName The name of the doctor.
     * @param m_appointmentID The ID of the appointment.
     * @param m_AppointmentStatus The new status of the appointment.
     * @return true if the status was successfully updated, false otherwise.
     */
    public boolean updateAppointmentRequestStatus(String m_doctorName, int m_appointmentID,
                                                  AppointmentStatus m_AppointmentStatus) {
        for (int i = 0; i < AppointmentList.size(); ++i) {
            if (AppointmentList.get(i).getDoctorName().equals(m_doctorName)
                    && AppointmentList.get(i).getAppointmentID() == m_appointmentID) {
                AppointmentList.get(i).setAppointmentStatus(m_AppointmentStatus);
                System.out.println("Doctor: " + m_doctorName + " successfully updated appointment ID " + m_appointmentID);
                return true;
            }
        }

        System.out.println("Unable to update appointment request - Invalid doctor or appointment ID");
        return false;
    }

    /**
     * Records the outcome of an appointment. The implementation of this method can be expanded
     * based on requirements.
     */
    public void recordAppointmentOutcome() {
        // Update appointments' medical records here
    }
}

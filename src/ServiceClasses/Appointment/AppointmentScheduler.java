package ServiceClasses.Appointment;

import ServiceClasses.Database.DataBaseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The AppointmentScheduler class manages appointment scheduling,
 * rescheduling, cancellations, and doctor availability.
 */
public class AppointmentScheduler {

    // List of all appointments
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    // Date and time formats used for validating and parsing input
    private final String dateFormat = "dd-MM-yyyy";
    private final String timeFormat = "HH:mm";

    // Doctor availability mapping
    private HashMap<String, HashMap<String, ArrayList<String>>> doctorAvailability = new HashMap<>();

    /**
     * Constructor to initialize the AppointmentScheduler with an existing list of appointments.
     *
     * @param AppointmentList The list of existing appointments.
     */
    public AppointmentScheduler(ArrayList<Appointment> AppointmentList) {
        this.AppointmentList = AppointmentList;
    }

    /**
     * Manages appointment requests for a specific doctor.
     *
     * @param doctorName The name of the doctor whose appointments are being managed.
     */
    public void ManageDoctorAppointments(String doctorName) {
        Scanner sc = new Scanner(System.in);
        boolean hasAppointments = false;

        System.out.println("Managing appointments for Dr. " + doctorName);
        for (Appointment appointment : AppointmentList) {
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
                    case 1 -> {
                        appointment.setAppointmentStatus(AppointmentStatus.ACCEPTED);
                        System.out.println("Appointment Accepted.");
                    }
                    case 2 -> {
                        appointment.setAppointmentStatus(AppointmentStatus.DECLINED);
                        System.out.println("Appointment Declined.");
                    }
                    case 3 -> System.out.println("Skipping Appointment.");
                    default -> System.out.println("Invalid Choice. Skipping Appointment.");
                }
            }
        }

        if (!hasAppointments) {
            System.out.println("No appointments found for Dr. " + doctorName);
        }
    }

    /**
     * Checks for an existing appointment for a specific doctor on a given date and time.
     *
     * @param m_doctorName The name of the doctor.
     * @param m_date       The date of the appointment.
     * @param m_timeSlot   The time slot of the appointment.
     * @return The index of the appointment if found, otherwise -1.
     */
    private int CheckForExistingAppointment(String m_doctorName, String m_date, String m_timeSlot) {
        for (int i = 0; i < AppointmentList.size(); ++i) {
            if (AppointmentList.get(i).getDoctorName().equals(m_doctorName)
                    && AppointmentList.get(i).getTimeSlot().equals(m_timeSlot)
                    && AppointmentList.get(i).getAppointmentDate().equals(m_date)) {
                if (AppointmentList.get(i).getAppointmentStatus() == AppointmentStatus.DECLINED) {
                    return -1;
                }
                return i;
            }
        }
        return -1;
    }

    /**
     * Sets the availability of a doctor for a specific date and time range.
     *
     * @param doctorName The name of the doctor.
     * @param date       The date of availability.
     * @param startTime  The start time of availability.
     * @param endTime    The end time of availability.
     * @return true if availability is successfully set, false otherwise.
     */
    public boolean SetDoctorAvailability(String doctorName, String date, String startTime, String endTime) {
        if (!isValidDate(date) || !isValidTime(startTime) || !isValidTime(endTime)) {
            System.out.println("Invalid date or time format!");
            return false;
        }

        HashMap<String, ArrayList<String>> availabilityByDate = doctorAvailability.getOrDefault(doctorName,
                new HashMap<>());
        ArrayList<String> slots = generateTimeSlots(startTime, endTime);
        availabilityByDate.put(date, slots);
        doctorAvailability.put(doctorName, availabilityByDate);

        System.out.println("Availability updated for Dr. " + doctorName + " on " + date);
        return true;
    }

    /**
     * Retrieves the availability of a doctor.
     *
     * @param doctorName The name of the doctor.
     * @return A map of dates and available time slots.
     */
    public HashMap<String, ArrayList<String>> GetDoctorAvailability(String doctorName) {
        return doctorAvailability.getOrDefault(doctorName, new HashMap<>());
    }

    /**
     * Retrieves the available time slots for a doctor on a specific date.
     *
     * @param doctorName The name of the doctor.
     * @param date       The date of availability.
     * @return A list of available time slots for the specified date.
     */
    public ArrayList<String> GetDoctorAvailability(String doctorName, String date) {
        HashMap<String, ArrayList<String>> availabilityByDate = doctorAvailability.getOrDefault(doctorName,
                new HashMap<>());
        return availabilityByDate.getOrDefault(date, new ArrayList<>());
    }

    /**
     * Retrieves the available appointment slots for all doctors.
     *
     * @return A map containing doctors' names, dates, and available time slots.
     */
    public HashMap<String, HashMap<String, ArrayList<String>>> getAllDoctorAvailabilities() {
        HashMap<String, HashMap<String, ArrayList<String>>> filteredAvailability = new HashMap<>();

        for (String doctorName : doctorAvailability.keySet()) {
            HashMap<String, ArrayList<String>> dates = doctorAvailability.get(doctorName);
            HashMap<String, ArrayList<String>> availableDates = new HashMap<>();

            for (String date : dates.keySet()) {
                ArrayList<String> allSlots = new ArrayList<>(dates.get(date));
                ArrayList<String> availableSlots = new ArrayList<>();

                for (String slot : allSlots) {
                    if (!isSlotBooked(doctorName, date, slot)) {
                        availableSlots.add(slot);
                    }
                }

                if (!availableSlots.isEmpty()) {
                    availableDates.put(date, availableSlots);
                }
            }

            if (!availableDates.isEmpty()) {
                filteredAvailability.put(doctorName, availableDates);
            }
        }

        return filteredAvailability;
    }

    /**
     * Checks if a specific time slot is already booked.
     *
     * @param doctorName The name of the doctor.
     * @param date       The date of the slot.
     * @param slot       The time slot.
     * @return true if the slot is booked, false otherwise.
     */
    private boolean isSlotBooked(String doctorName, String date, String slot) {
        for (Appointment appointment : AppointmentList) {
            if (appointment.getDoctorName().equals(doctorName) &&
                    appointment.getAppointmentDate().equals(date) &&
                    appointment.getTimeSlot().equals(slot)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a list of time slots between a start time and an end time.
     *
     * @param startTime The start time.
     * @param endTime   The end time.
     * @return A list of time slots in 30-minute intervals.
     */
    private ArrayList<String> generateTimeSlots(String startTime, String endTime) {
        ArrayList<String> timeSlots = new ArrayList<>();
        try {
            LocalTime start = LocalTime.parse(startTime);
            LocalTime end = LocalTime.parse(endTime);

            while (!start.isAfter(end)) {
                timeSlots.add(start.toString());
                start = start.plusMinutes(30);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format: " + e.getMessage());
        }
        return timeSlots;
    }

    /**
     * Validates if the given date is in the correct format and in the future.
     *
     * @param input The date string to validate.
     * @return true if the date is valid and in the future, false otherwise.
     */
    private boolean isValidDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate parsedDate = LocalDate.parse(input, formatter);
            LocalDate today = LocalDate.now();
            return parsedDate.isAfter(today);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Validates if the given time is in the correct format.
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
     * Schedules a new appointment for a doctor and a patient.
     *
     * @param doctorName     The name of the doctor.
     * @param patientName    The name of the patient.
     * @param date           The date of the appointment.
     * @param timeSlot       The time slot of the appointment.
     * @param appointmentType The type of the appointment.
     * @return true if the appointment was successfully scheduled, false otherwise.
     */
    public boolean ScheduleAppointment(String doctorName, String patientName, String date, String timeSlot,
                                       String appointmentType) {
        // Validate date and time format
        if (!isValidDate(date) || !isValidTime(timeSlot)) {
            System.out.println("Invalid date or time format input! Please use the correct format.");
            return false;
        }

        // Check if the slot is within the doctor's availability
        HashMap<String, ArrayList<String>> doctorAvailabilityByDate = doctorAvailability.get(doctorName);
        if (doctorAvailabilityByDate == null || !doctorAvailabilityByDate.containsKey(date)) {
            System.out.println("The doctor has not set availability for this date.");
            return false;
        }

        ArrayList<String> availableSlots = doctorAvailabilityByDate.get(date);
        if (!availableSlots.contains(timeSlot)) {
            System.out.println("The selected time slot is not available.");
            return false;
        }

        // Check for existing appointments
        if (CheckForExistingAppointment(doctorName, date, timeSlot) != -1) {
            System.out.println("The selected time slot is already booked.");
            return false;
        }

        // Schedule the appointment
        AppointmentList.add(new Appointment(doctorName, patientName, date, timeSlot, appointmentType));
        System.out.println("Successfully added appointment. Doctor: " + doctorName + ", Patient: " + patientName
                + ", Date: " + date + ", Time: " + timeSlot);
        DataBaseManager.getInstance().getappointmentFileHandler().saveData();
        return true;
    }

    /**
     * Reschedules an existing appointment.
     *
     * @param doctorName     The name of the doctor.
     * @param patientName    The name of the patient.
     * @param oldDate        The original date of the appointment.
     * @param oldTimeSlot    The original time slot of the appointment.
     * @param appointmentType The type of the appointment.
     * @param newDate        The new date of the appointment.
     * @param newTimeSlot    The new time slot of the appointment.
     * @return true if the appointment was successfully rescheduled, false otherwise.
     */
    public boolean ReScheduleAppointment(String doctorName, String patientName, String oldDate,
                                         String oldTimeSlot, String appointmentType, String newDate, String newTimeSlot) {
        int indexChecker = CheckForExistingAppointment(doctorName, oldDate, oldTimeSlot);
        if (indexChecker != -1) {
            AppointmentList.remove(indexChecker);
            return ScheduleAppointment(doctorName, patientName, newDate, newTimeSlot, appointmentType);
        }

        System.out.println("Unable to reschedule. Appointment does not exist.");
        return false;
    }

    /**
     * Cancels an existing appointment.
     *
     * @param doctorName  The name of the doctor.
     * @param patientName The name of the patient.
     * @param date        The date of the appointment.
     * @param timeSlot    The time slot of the appointment.
     * @return true if the appointment was successfully canceled, false otherwise.
     */
    public boolean CancelAppointment(String doctorName, String patientName, String date, String timeSlot) {
        int indexChecker = CheckForExistingAppointment(doctorName, date, timeSlot);
        if (indexChecker != -1) {
            AppointmentList.remove(indexChecker);
            System.out.println("Appointment successfully cancelled.");
            DataBaseManager.getInstance().getappointmentFileHandler().saveData();
            return true;
        }

        System.out.println("Unable to cancel. Appointment does not exist.");
        return false;
    }

    /**
     * Retrieves an appointment by its ID.
     *
     * @param appointmentID The ID of the appointment to retrieve.
     * @return The Appointment object if found, otherwise null.
     */
    public Appointment getAppointmentByID(int appointmentID) {
        for (Appointment appointment : AppointmentList) {
            if (appointment.getAppointmentID() == appointmentID) {
                return appointment;
            }
        }
        return null;
    }
}

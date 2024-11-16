package ServiceClasses.Appointment;

import ServiceClasses.Database.DataBaseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AppointmentScheduler {

    // List of all appointments
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    // Date and time formats used for validating and parsing input
    private final String dateFormat = "dd-MM-yyyy";
    private final String timeFormat = "HH:mm";

    // Doctor availability mapping
    private HashMap<String, HashMap<String, ArrayList<String>>> doctorAvailability = new HashMap<>();

    public AppointmentScheduler(ArrayList<Appointment> AppointmentList) {
        this.AppointmentList = AppointmentList;
    }

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

    private int CheckForExistingAppointment(String m_doctorName, String m_date, String m_timeSlot) {
        for (int i = 0; i < AppointmentList.size(); ++i) {
            if (AppointmentList.get(i).getDoctorName().equals(m_doctorName)
                    && AppointmentList.get(i).getTimeSlot().equals(m_timeSlot)
                    && AppointmentList.get(i).getAppointmentDate().equals(m_date)) {
                if (AppointmentList.get(i).getAppointmentStatus() == AppointmentStatus.DECLINED)
                    return -1;
                return i;
            }
        }
        return -1;
    }

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

    public HashMap<String, ArrayList<String>> GetDoctorAvailability(String doctorName) {
        return doctorAvailability.getOrDefault(doctorName, new HashMap<>());
    }

    public ArrayList<String> GetDoctorAvailability(String doctorName, String date) {
        HashMap<String, ArrayList<String>> availabilityByDate = doctorAvailability.getOrDefault(doctorName,
                new HashMap<>());
        return availabilityByDate.getOrDefault(date, new ArrayList<>());
    }

    public ArrayList<String> GetDoctorAvailableSlots(String doctorName, String date) {
        ArrayList<String> availableSlots = new ArrayList<>();
        ArrayList<String> slotsForDate = GetDoctorAvailability(doctorName, date);

        for (String slot : slotsForDate) {
            if (!isSlotBooked(doctorName, date, slot)) {
                availableSlots.add(slot);
            }
        }
        return availableSlots;
    }

    public HashMap<String, HashMap<String, ArrayList<String>>> getAllDoctorAvailabilities() {
        HashMap<String, HashMap<String, ArrayList<String>>> filteredAvailability = new HashMap<>();

        for (String doctorName : doctorAvailability.keySet()) {
            HashMap<String, ArrayList<String>> dates = doctorAvailability.get(doctorName);
            HashMap<String, ArrayList<String>> availableDates = new HashMap<>();

            for (String date : dates.keySet()) {
                ArrayList<String> allSlots = new ArrayList<>(dates.get(date)); // Copy all slots
                ArrayList<String> availableSlots = new ArrayList<>();

                for (String slot : allSlots) {
                    if (!isSlotBooked(doctorName, date, slot)) {
                        availableSlots.add(slot); // Add only unbooked slots
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

    private boolean isSlotBooked(String doctorName, String date, String slot) {
        for (Appointment appointment : AppointmentList) {
            if (appointment.getDoctorName().equals(doctorName) &&
                    appointment.getAppointmentDate().equals(date) &&
                    appointment.getTimeSlot().equals(slot)) {
                return true; // Slot is booked
            }
        }
        return false; // Slot is available
    }

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

    private boolean isValidTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
        try {
            LocalTime.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean ScheduleAppointment(String m_doctorName, String m_patientName, String m_date, String m_timeSlot,
            String m_appointmentType) {
        if (!isValidDate(m_date) || !isValidTime(m_timeSlot)) {
            System.out.println("Invalid date and time format input! Please use " + dateFormat + " " + timeFormat);
            return false;
        }

        int indexChecker = CheckForExistingAppointment(m_doctorName, m_date, m_timeSlot);
        if (indexChecker == -1) {
            AppointmentList.add(new Appointment(m_doctorName, m_patientName, m_date, m_timeSlot, m_appointmentType));
            System.out.println("Appointment successfully scheduled!");
            DataBaseManager.getInstance().getappointmentFileHandler().saveData();
            return true;
        }

        System.out.println("Failed to schedule appointment. Conflict exists.");
        return false;
    }

    public boolean ReScheduleAppointment(String m_doctorName, String m_patientName, String m_oldDate,
            String m_oldTimeSlot, String m_appointmentType, String m_newDate, String m_newTimeSlot) {
        int indexChecker = CheckForExistingAppointment(m_doctorName, m_oldDate, m_oldTimeSlot);
        if (indexChecker != -1) {
            AppointmentList.remove(indexChecker);
            return ScheduleAppointment(m_doctorName, m_patientName, m_newDate, m_newTimeSlot, m_appointmentType);
        }

        System.out.println("Unable to reschedule. Appointment does not exist.");
        return false;
    }

    public boolean CancelAppointment(String m_doctorName, String m_patientName, String m_date, String m_timeSlot) {
        int indexChecker = CheckForExistingAppointment(m_doctorName, m_date, m_timeSlot);
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
     * Retrieves an Appointment by its ID.
     *
     * @param appointmentID The ID of the appointment to retrieve.
     * @return The Appointment object if found; otherwise, null.
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

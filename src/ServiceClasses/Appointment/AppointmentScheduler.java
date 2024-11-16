package ServiceClasses.Appointment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AppointmentScheduler {
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    private final String dateFormat = "dd-MM-yyyy";
    private final String timeFormat = "HH:mm";
    private HashMap<String, ArrayList<String>> doctorAvailability = new HashMap<>();

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

    // Checks for existing appointments in the list. If there is an appointment,
    // returns index of appointment, no appointment returns -1.
    private int CheckForExistingAppointment(String m_doctorName, String m_date, String m_timeSlot) {
        System.out.println("Checking for : Doctor " + m_doctorName + " Date : " + m_date + " Time : " + m_timeSlot);
        for (int i = 0; i < AppointmentList.size(); ++i) {
            System.out.println("Comparing against : Doctor " + AppointmentList.get(i).getDoctorName()
                    + " Date : " + AppointmentList.get(i).getAppointmentDate()
                    + " Time : " + AppointmentList.get(i).getTimeSlot());

            if (AppointmentList.get(i).getDoctorName().equals(m_doctorName)
                    && AppointmentList.get(i).getTimeSlot().equals(m_timeSlot)
                    && AppointmentList.get(i).getAppointmentDate().equals(m_date)) {
                // If the appointment exists in the system but is rejected, return as available
                // for scheduling, if not return that the appointment exists.
                if (AppointmentList.get(i).getAppointmentStatus() == AppointmentStatus.DECLINED)
                    return -1;

                return i;
            }
        }
        return -1;
    }

    // Method to set availability for a doctor
    public boolean SetDoctorAvailability(String doctorName, String startTime, String endTime) {
        // Validate input time formats
        if (!isValidTime(startTime) || !isValidTime(endTime)) {
            System.out.println("Invalid time format! Please use " + timeFormat);
            return false;
        }

        // Check or initialize the availability list for the doctor
        ArrayList<String> availability = doctorAvailability.getOrDefault(doctorName, new ArrayList<>());

        // Add the availability time slots
        availability.add("From: " + startTime + " To: " + endTime);
        doctorAvailability.put(doctorName, availability);

        System.out.println("Availability updated for Dr. " + doctorName);
        return true;
    }

    private boolean isValidDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            // Parse the input date using the formatter
            LocalDate parsedDate = LocalDate.parse(input, formatter);
            LocalDate today = LocalDate.now(); // Get today's date

            // Check if the parsed date is before today's date
            if (parsedDate.isAfter(today)) {
                return true;
            } else {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false; // Return false if parsing fails
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

    public ArrayList<String> GetDoctorAvailability(String doctorName) {
        return doctorAvailability.getOrDefault(doctorName, new ArrayList<>());
    }

    // Public Methods
    // Schedules an appointment - returns true if successfully scheduled an
    // appointment, returns false if unable to schedule appointment - doctor has an
    // appointment at that time slot & date
    public boolean ScheduleAppointment(String m_doctorName, String m_patientName, String m_date, String m_timeSlot,
            String m_appointmentType) {
        if (!isValidDate(m_date) && !isValidTime(m_timeSlot)) {
            System.out.println("Invalid date and time format input! Please provide a proper format : " + dateFormat
                    + " " + timeFormat);
            return false;
        } else if (!isValidDate(m_date)) {
            System.out.println("Invalid date! Please provide a proper format for date following : " + dateFormat);
            return false;
        } else if (!isValidTime(m_timeSlot)) {
            System.out.println("Invalid time! Please provide a proper format for time following : " + timeFormat);
            return false;
        }

        int indexChecker = CheckForExistingAppointment(m_doctorName, m_date, m_timeSlot);

        if (indexChecker == -1) {
            AppointmentList.add(new Appointment(/* AppointmentManager.getInstance().getNewID(), */
                    m_doctorName, m_patientName, m_date, m_timeSlot, m_appointmentType)); // Add a new appointment
                                                                                          // directly into the
                                                                                          // AppointmentList.
            System.out.println("Successfully Added Appointment Into The System. Doctor : " + m_doctorName
                    + " Patient : " + m_patientName + " Date : "
                    + m_date + " Time : " + m_timeSlot);
            System.out.println("New AppointmentList Length: " + AppointmentList.size());
            return true;
        }

        System.out.println(
                "Failed To Add Appointment Into The System. " + m_doctorName + m_patientName + m_date + m_timeSlot);
        return false;
    }

    // Reschedules an appointment - if the appointment is found, remove the
    // appointment at the index and then call ScheduleAppointment to add it to the
    // list.
    public boolean ReScheduleAppointment(String m_doctorName, String m_patientName, String m_oldDate,
            String m_oldTimeSlot,
            String m_appointmentType, String m_newDate, String m_newTimeSlot) {
        int indexChecker = CheckForExistingAppointment(m_doctorName, m_oldDate, m_oldTimeSlot);
        if (indexChecker != -1) {
            AppointmentList.remove(indexChecker);

            return ScheduleAppointment(m_doctorName, m_patientName, m_newDate, m_newTimeSlot, m_appointmentType);
        } else {
            System.out.println("Unable to re-schedule appointment. Appointment does not exist in system.");
            return false;
        }
    }

    // Removes an appointment in the system.
    public boolean CancelAppointment(String m_doctorName, String m_patientName, String m_date, String m_timeSlot) {
        int indexChecker = CheckForExistingAppointment(m_doctorName, m_date, m_timeSlot);

        if (indexChecker == -1) {
            System.out.println("Unable to cancel appointment. Appointment does not exist in system.");
            return false;
        } else {
            AppointmentList.remove(indexChecker);
            System.out.println("Successfully Cancelled Appointment.");
            return true;
        }
    }

    public boolean updateAppointmentRequestStatus(String m_doctorName, int m_appointmentID,
            AppointmentStatus m_AppointmentStatus) {
        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments for the appointment id
        // and doctor name is same
        {
            if (AppointmentList.get(i).getDoctorName().equals(m_doctorName)
                    && AppointmentList.get(i).getAppointmentID() == m_appointmentID) {
                AppointmentList.get(i).setAppointmentStatus(m_AppointmentStatus);
                System.out.println(
                        "Doctor :" + m_doctorName + "Successfully Accepted the appointment ID of " + m_appointmentID);
                return true;
            }
        }

        System.out.println("Unable to accept the request - Invalid doctor or appointment ID");
        return false;
    }

    public void recordAppointmentOutcome() // Update appointments medical record here
    {

    }
}

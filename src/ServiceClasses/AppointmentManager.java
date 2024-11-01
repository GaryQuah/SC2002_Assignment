package ServiceClasses;

import java.util.Vector;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import models.Doctor;

//Clean Slate Based Appointment Manager
public class AppointmentManager {
     // Singleton instance
    private static final AppointmentManager instance = new AppointmentManager();
    
    public static AppointmentManager getInstance() {
        return instance;
    }

    // Private constructor to prevent instantiation
    private AppointmentManager() {
    }

    private int newAppointmentID = 0;

    private Vector<Appointment> AppointmentList = new Vector<Appointment>();

    // to view past appointment of patient
    public Vector<Appointment> getPastAppointments(String patientName) {
        Vector<Appointment> pastAppointments = new Vector<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Appointment appointment : AppointmentList) {
            if (appointment.getPatientName().equals(patientName)) {
                LocalDate appointmentDate = LocalDate.parse(appointment.getAppointmentDate(), formatter);
                if (appointmentDate.isBefore(today)) {
                    pastAppointments.add(appointment);
                }
            }
        }
        return pastAppointments;
    }

    // Private Methods
    // Checks for existing appointments in the list. If there is an appointment,
    // returns index of appointment, no appointment returns -1.
    private int CheckForExistingAppointment(String m_doctorName, String m_date, String m_timeSlot) {
        for (int i = 0; i < AppointmentList.size(); ++i) {
            if (AppointmentList.get(i).getDoctorName().equals(m_doctorName)
                    && AppointmentList.get(i).getTimeSlot().equals(m_timeSlot)
                    && AppointmentList.get(i).getAppointmentDate().equals(m_date)) {
                // If the appointment exists in the system but is rejected, return as available
                // for scheduling, if not return that the appointment exists.
                if (AppointmentList.get(i).appointmentStatus() == -1)
                    return -1;

                return i;
            }
        }
        return -1;
    }

    // Public Methods
    // Schedules an appointment - returns true if successfully scheduled an
    // appointment, returns false if unable to schedule appointment - doctor has an
    // appointment at that time slot & date
    public boolean ScheduleAppointment(String m_doctorName, String m_patientName, String m_date, String m_timeSlot,
            String m_appointmentType) {
        int indexChecker = CheckForExistingAppointment(m_doctorName, m_date, m_timeSlot);

        if (indexChecker == -1) {
            newAppointmentID++; // If we are saving local data, need to save this number and load this to ensure
                                // that all appointments are "unique"
            AppointmentList.add(new Appointment(m_doctorName, m_patientName, m_date, m_timeSlot, m_appointmentType,
                    newAppointmentID)); // Add a new appointment directly into the AppointmentList.
            System.out.println("Successfully Added Appointment Into The System. " + m_doctorName + m_patientName
                    + m_date + m_timeSlot);
            return true;
        }

        System.out.println(
                "Failed To Add Appointment Into The System. " + m_doctorName + m_patientName + m_date + m_timeSlot);
        return false;
    }

    // Re schedules an appointment - if the appointment is found, remove the
    // appointment at the index and then call ScheduleAppointment to add it to the
    // list.
    public boolean ReScheduleAppointment(String m_doctorName, String m_patientName, String m_date, String m_timeSlot,
            String m_appointmentType, String m_oldDate, String m_oldTimeSlot) {
        int indexChecker = CheckForExistingAppointment(m_doctorName, m_date, m_timeSlot);
        if (indexChecker != -1) {
            AppointmentList.remove(indexChecker);

            return ScheduleAppointment(m_doctorName, m_patientName, m_date, m_timeSlot, m_appointmentType);
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

    // View available dates - prints out all the available date and time slots. if
    // no appointment, prints out message that the doctor is free.
    public void ViewAvailableDates(String m_doctorName) {
        Vector<String> unAvailableDates = new Vector<String>();
        Vector<String> unAvailableTimeSlots = new Vector<String>();

        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments, make sure the doctor
                                                         // dosent have an appointment on the date and
        {
            if (AppointmentList.elementAt(i).getDoctorName().equals(m_doctorName)) {
                unAvailableDates.add(AppointmentList.elementAt(i).getAppointmentDate());
                unAvailableTimeSlots.add(AppointmentList.elementAt(i).getTimeSlot());
            }
        }

        if (unAvailableDates.size() > 0) {
            System.out.println("The doctor is unavailable during these dates and timeslots:");

            for (int i = 0; i < unAvailableDates.size(); ++i) {
                System.out.println(
                        "Date : " + unAvailableDates.elementAt(i) + " Time : " + unAvailableTimeSlots.elementAt(i));
            }
        } else {
            System.out
                    .println("The doctor has no appointments scheduled. He is free for all working hours time slots.");
        }
    }

    // View appointments that have the doctors name in them
    public void ViewDoctorAppointments(String m_doctorName) {
        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments, make sure the doctor
                                                         // dosent have an appointment on the date and
        {
            if (AppointmentList.elementAt(i).getDoctorName().equals(m_doctorName)) {
                System.out.println(AppointmentList.elementAt(i));
            }
        }
    }

    // View appointments that have the patients name in them
    // view schedules appointments minus past appointments
    public void ViewPatientAppointments(String m_patientName) {
        LocalDate today = LocalDate.now(); // Get today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments, make sure the doctor
                                                         // dosent have an appointment on the date and
        {
            if (AppointmentList.elementAt(i).getDoctorName().equals(m_patientName)) {
                LocalDate appointmentDate = LocalDate.parse(AppointmentList.elementAt(i).getAppointmentDate(),
                        formatter);
                if (!appointmentDate.isBefore(today)) {
                    System.out.println(AppointmentList.elementAt(i));
                }
            }
        }
    }

    // Lets doctor update the status of an appointment. 0 is pending, 1 is accepted,
    // -1 is decline
    public boolean updateAppointmentRequestStatus(String m_doctorName, int m_appointmentID, int m_AppointmentStatus) {
        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments for the appointment id
                                                         // and doctor name is same
        {
            if (AppointmentList.get(i).getDoctorName().equals(m_doctorName)
                    && AppointmentList.get(i).getAppointmentID() == m_appointmentID) {
                AppointmentList.get(i).UpdateAppointmentStatus(m_doctorName, m_AppointmentStatus);
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

    public void ViewAllAppointmentsByStatus(int accepted) { //Sort by appointment status - for admin : Appointment Outcome Record (for completed appointments) 
        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments, make sure the doctor
                                                         // dosent have an appointment on the date and
        {
            if (AppointmentList.elementAt(i).appointmentStatus() == accepted) {
                System.out.println(AppointmentList.elementAt(i));
            }
        }   
    }
}

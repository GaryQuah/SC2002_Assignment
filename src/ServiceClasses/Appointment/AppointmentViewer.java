package ServiceClasses.Appointment;

import java.util.ArrayList;

/**
 * Class responsible for viewing and displaying appointment details.
 * It allows the viewing of upcoming appointments, available dates, and the appointments
 * associated with specific doctors or patients. It also provides methods to view appointments
 * by status and view all scheduled appointments.
 */
public class AppointmentViewer {

    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    /**
     * Constructor for AppointmentViewer.
     * Initializes the AppointmentViewer with a list of appointments.
     *
     * @param AppointmentList The list of appointments to view.
     */
    public AppointmentViewer(ArrayList<Appointment> AppointmentList) {
        this.AppointmentList = AppointmentList;
    }

    /**
     * Displays all upcoming appointments for a specified doctor.
     * Only appointments that are either unaccepted or accepted are shown.
     *
     * @param doctorName The name of the doctor whose upcoming appointments are to be viewed.
     */
    public void ViewUpcomingAppointments(String doctorName) {
        boolean hasAppointments = false;

        System.out.println("Upcoming Appointments for Dr. " + doctorName + ":");
        for (Appointment appointment : AppointmentList) {
            if (appointment.getDoctorName().equals(doctorName)
                    && (appointment.getAppointmentStatus() == AppointmentStatus.UNACCEPTED
                    || appointment.getAppointmentStatus() == AppointmentStatus.ACCEPTED)) {
                hasAppointments = true;
                System.out.println("Appointment ID: " + appointment.getAppointmentID());
                System.out.println("Patient Name: " + appointment.getPatientName());
                System.out.println("Date: " + appointment.getAppointmentDate());
                System.out.println("Time: " + appointment.getTimeSlot());
                System.out.println("-------------------------------------");
            }
        }

        if (!hasAppointments) {
            System.out.println("No upcoming appointments found for Dr. " + doctorName);
        }
    }

    /**
     * Displays all appointments with a specific status.
     * Primarily used by the admin to view completed appointments or other specific statuses.
     *
     * @param m_AppointmentStatus The appointment status to filter by.
     */
    public void ViewAllAppointmentsByStatus(AppointmentStatus m_AppointmentStatus) {
        for (int i = 0; i < AppointmentList.size(); ++i) {
            if (AppointmentList.get(i).getAppointmentStatus() == m_AppointmentStatus) {
                System.out.println(AppointmentList.get(i));
            }
        }
    }

    /**
     * Displays the available dates and time slots for a specific doctor.
     * It checks for all existing appointments and marks unavailable dates and time slots.
     *
     * @param m_doctorName The name of the doctor whose availability is being checked.
     */
    public void ViewAvailableDates(String m_doctorName) {
        ArrayList<String> unAvailableDates = new ArrayList<>();
        ArrayList<String> unAvailableTimeSlots = new ArrayList<>();

        for (int i = 0; i < AppointmentList.size(); ++i) {
            if (AppointmentList.get(i).getDoctorName().equals(m_doctorName)) {
                unAvailableDates.add(AppointmentList.get(i).getAppointmentDate());
                unAvailableTimeSlots.add(AppointmentList.get(i).getTimeSlot());
            }
        }

        if (unAvailableDates.size() > 0) {
            System.out.println("The doctor is unavailable during these dates and timeslots:");
            for (int i = 0; i < unAvailableDates.size(); ++i) {
                System.out.println("Date : " + unAvailableDates.get(i) + ", Time : " + unAvailableTimeSlots.get(i));
            }
        } else {
            System.out.println("The doctor has no appointments scheduled. He is free for all working hours time slots.");
        }
    }

    /**
     * Displays all appointments for a specific doctor.
     * This includes all appointments regardless of their status.
     *
     * @param m_doctorName The name of the doctor whose appointments are to be viewed.
     */
    public void ViewDoctorAppointments(String m_doctorName) {
        int appointmentCount = 0;

        for (int i = 0; i < AppointmentList.size(); ++i) {
            if (AppointmentList.get(i).getDoctorName().equals(m_doctorName)) {
                System.out.println(AppointmentList.get(i));
                appointmentCount++;
            }
        }

        if (appointmentCount == 0)
            System.out.println("No appointments have been scheduled for the doctor " + m_doctorName);
    }

    /**
     * Displays all appointments for a specific patient.
     * This includes all appointments scheduled for the patient.
     *
     * @param m_patientName The name of the patient whose appointments are to be viewed.
     */
    public void ViewPatientAppointments(String m_patientName) {
        int appointmentCount = 0;

        for (int i = 0; i < AppointmentList.size(); ++i) {
            if (AppointmentList.get(i).getPatientName().equals(m_patientName)) {
                System.out.println(AppointmentList.get(i));
                appointmentCount++;
            }
        }

        if (appointmentCount == 0) {
            System.out.println("No appointments have been scheduled for the patient " + m_patientName);
        }
    }

    /**
     * Displays all appointments in the system.
     * This method prints the details of all scheduled appointments.
     */
    public void ViewAllAppointments() {
        for (int i = 0; i < AppointmentList.size(); ++i) {
            System.out.println(AppointmentList.get(i));
        }
    }
}

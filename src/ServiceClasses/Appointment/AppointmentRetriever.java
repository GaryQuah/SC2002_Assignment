package ServiceClasses.Appointment;

import java.util.ArrayList;

/**
 * The AppointmentRetriever class is responsible for retrieving appointments from a list
 */
public class AppointmentRetriever {

    // List of appointments to be managed and retrieved
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    /**
     * Constructor that initializes the AppointmentRetriever with a list of appointments.
     *
     * @param AppointmentList The list of appointments to be managed and retrieved.
     */
    public AppointmentRetriever(ArrayList<Appointment> AppointmentList) {
        this.AppointmentList = AppointmentList;
    }

    /**
     * Retrieves an appointment from the list based on the provided appointment ID.
     * If no appointment with the given ID is found, it returns null.
     *
     * @param appointmentID The ID of the appointment to be retrieved.
     * @return The Appointment object with the given ID, or null if no appointment with that ID exists.
     */
    public Appointment getAppointmentByID(int appointmentID) {
        for (Appointment appointment : AppointmentList) {
            if (appointment.getAppointmentID() == appointmentID) {
                return appointment;
            }
        }
        return null;  // If appointment with given ID is not found
    }
}

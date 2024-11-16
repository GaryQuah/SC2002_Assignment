package ServiceClasses.Appointment;

import ServiceClasses.AppointmentOutcome.AppoinmentOutcomeControl;
import java.util.ArrayList;

public class AppointmentManager {
    // Singleton instance
    private static AppointmentManager instance;
    private int MaxID = 1;

    // Appointment-related components
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();
    private AppointmentScheduler appointmentScheduler = new AppointmentScheduler(AppointmentList);
    private AppointmentStatusUpdater appointmentStatusUpdater = new AppointmentStatusUpdater(AppointmentList);
    private AppointmentViewer appointmentViewer = new AppointmentViewer(AppointmentList);
    private AppoinmentOutcomeControl appointmentOutcomeControl = AppoinmentOutcomeControl.getInstance();

    /**
     * Returns the singleton instance of the AppointmentManager.
     * If it doesn't exist, it will initialize it.
     *
     * @return The singleton instance of AppointmentManager.
     */
    public static AppointmentManager getInstance() {
        if (instance == null) {
            instance = new AppointmentManager();
        }
        return instance;
    }

    // Private constructor to prevent instantiation from outside
    private AppointmentManager() {}

    /**
     * Returns the AppointmentScheduler instance.
     *
     * @return AppointmentScheduler
     */
    public AppointmentScheduler getAppointmentScheduler() {
        return appointmentScheduler;
    }

    /**
     * Returns the AppointmentViewer instance.
     *
     * @return AppointmentViewer
     */
    public AppointmentViewer getAppointmentViewer() {
        return appointmentViewer;
    }

    /**
     * Returns the AppoinmentOutcomeControl instance.
     *
     * @return AppoinmentOutcomeControl
     */
    public AppoinmentOutcomeControl getAppointmentOutcomeControl() {
        return appointmentOutcomeControl;
    }

    /**
     * Generates and returns a new unique appointment ID.
     *
     * @return int New unique appointment ID.
     */
    public int getNewID() {
        System.out.println("Getting newID.");
        return MaxID++;
    }

    /**
     * Updates the maximum ID if a higher value is provided.
     *
     * @param value The value to update the maximum ID to.
     */
    public void updateMaxID(int value) {
        System.out.println("Updating Max ID");
        if (value >= instance.MaxID) {
            instance.MaxID = value + 1;
        }
    }

    /**
     * Returns the list of appointments.
     *
     * @return ArrayList of Appointment objects.
     */
    public ArrayList<Appointment> getAppointmentList() {
        return AppointmentList;
    }
}

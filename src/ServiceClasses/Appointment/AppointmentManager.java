package ServiceClasses.Appointment;

import ServiceClasses.AppointmentOutcome.AppoinmentOutcomeControl;
import java.util.ArrayList;

/**
 * The AppointmentManager class is responsible for managing and tracking the appointments.
 * It acts as a singleton and provides methods to manage appointments, generate unique IDs,
 * and interact with related components such as the AppointmentScheduler, AppointmentViewer,
 * and AppointmentOutcomeControl, adhering to SRP principle
 */
public class AppointmentManager {

    // Singleton instance of AppointmentManager
    private static AppointmentManager instance;

    // Maximum Appointment ID tracker
    private int MaxID = 1;

    // List of all appointments
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    // Appointment-related components
    private AppointmentScheduler appointmentScheduler = new AppointmentScheduler(AppointmentList);
    private AppointmentViewer appointmentViewer = new AppointmentViewer(AppointmentList);
    private AppoinmentOutcomeControl appointmentOutcomeControl = AppoinmentOutcomeControl.getInstance();

    // Private constructor to prevent instantiation from outside
    private AppointmentManager() {}

    /**
     * Returns the singleton instance of the AppointmentManager.
     * If it doesn't exist, it will initialize and return it.
     *
     * @return The singleton instance of AppointmentManager.
     */
    public static AppointmentManager getInstance() {
        if (instance == null) {
            instance = new AppointmentManager();
        }
        return instance;
    }

    /**
     * Returns the AppointmentScheduler instance.
     * The AppointmentScheduler is responsible for scheduling appointments.
     *
     * @return The AppointmentScheduler instance.
     */
    public AppointmentScheduler getAppointmentScheduler() {
        return appointmentScheduler;
    }

    /**
     * Returns the AppointmentViewer instance.
     * The AppointmentViewer is responsible for displaying appointments.
     *
     * @return The AppointmentViewer instance.
     */
    public AppointmentViewer getAppointmentViewer() {
        return appointmentViewer;
    }

    /**
     * Returns the AppoinmentOutcomeControl instance.
     * The AppoinmentOutcomeControl manages the outcomes or results of appointments.
     *
     * @return The AppoinmentOutcomeControl instance.
     */
    public AppoinmentOutcomeControl getAppointmentOutcomeControl() {
        return appointmentOutcomeControl;
    }

    /**
     * Generates and returns a new unique appointment ID.
     * Each time this method is called, a new ID is generated sequentially.
     *
     * @return A new unique appointment ID.
     */
    public int getNewID() {
        return MaxID++;
    }

    /**
     * Updates the maximum ID if a higher value is provided.
     * This ensures that appointment IDs continue to increment correctly.
     *
     * @param value The value to update the maximum ID to.
     */
    public void updateMaxID(int value) {
        if (value >= instance.MaxID) {
            instance.MaxID = value + 1;
        }
    }

    /**
     * Returns the list of all appointments managed by this AppointmentManager.
     *
     * @return An ArrayList containing all Appointment objects.
     */
    public ArrayList<Appointment> getAppointmentList() {
        return AppointmentList;
    }
}

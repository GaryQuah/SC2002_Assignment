package ServiceClasses.Appointment;

import java.util.Vector;

//Clean Slate Based Appointment Manager
public class AppointmentManager {
    
    private int newAppointmentID = 0;
    // Singleton instance
    private static AppointmentManager instance;

    public static AppointmentManager getInstance() {

         if (instance == null) {
            instance = new AppointmentManager();
        }
        return instance;
    }

    // Private constructor to prevent instantiation for Singleton
    private AppointmentManager() 
    {
    }

    private Vector<Appointment> AppointmentList = new Vector<Appointment>();

    private AppointmentScheduler appointmentScheduler = new AppointmentScheduler(AppointmentList);
    private AppointmentStatusUpdater appointmentStatusUpdater = new AppointmentStatusUpdater(AppointmentList);
    private AppointmentViewer appointmentViewer = new AppointmentViewer(AppointmentList);
    private AppointmentRetriever appointmentRetriever = new AppointmentRetriever(AppointmentList);

    public AppointmentScheduler getAppointmentScheduler()
    {
        return appointmentScheduler;
    }

    public AppointmentStatusUpdater getAppointmentStatusUpdater()
    {
        return appointmentStatusUpdater;
    }

    public AppointmentViewer getAppointmentViewer()
    {
        return appointmentViewer;
    }

    public AppointmentRetriever getAppointmentRetriever()
    {
        return appointmentRetriever;
    }
}

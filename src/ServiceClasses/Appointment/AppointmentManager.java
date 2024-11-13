package ServiceClasses.Appointment;

import java.util.ArrayList;

//Clean Slate Based Appointment Manager
public class AppointmentManager {
    
    // Singleton instance
    private static AppointmentManager instance;
    private int MaxID = 1;

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

    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

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

    public int getNewID(){
        System.out.println("Getting newID.");
        MaxID++;
        return MaxID;
    }

    public void updateMaxID(int comp)
    {
        System.out.println("Updating Max ID");
        if (comp >= instance.MaxID)
            instance.MaxID = comp;
    }

    public ArrayList<Appointment> getAppointmentList()
    {
        return AppointmentList;
    }

}

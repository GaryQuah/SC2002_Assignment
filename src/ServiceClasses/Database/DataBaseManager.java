package ServiceClasses.Database;

import ServiceClasses.Appointment.AppointmentManager;

import ServiceClasses.Appointment.AppointmentScheduler;
import ServiceClasses.Appointment.AppointmentStatusUpdater;
import ServiceClasses.Database.PatientFileHandler;
import ServiceClasses.Database.StaffFileHandler;

public class DataBaseManager {

    private PatientFileHandler patientFileHandler = new PatientFileHandler();
    private StaffFileHandler staffFileHandler = new StaffFileHandler();
    private static DataBaseManager instance;

    public static DataBaseManager getInstance() {

        if (instance == null) {
            instance = new DataBaseManager();
        }
        return instance;
    }

    // Private constructor to prevent instantiation for Singleton
    private DataBaseManager()
    {
    }

    public PatientFileHandler getPatientFileHandler()
    {
        return patientFileHandler;
    }

    public StaffFileHandler getStaffFileHandler()
    {
        return staffFileHandler;
    }

}

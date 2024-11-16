package ServiceClasses.Database;

public class DataBaseManager {

    private PatientFileHandler patientFileHandler = new PatientFileHandler();
    private StaffFileHandler staffFileHandler = new StaffFileHandler();
    private AppointmentFileHandler appointmentFileHandler = new AppointmentFileHandler();
    private InventoryFileHandler inventoryFileHandler = new InventoryFileHandler();
    private OutcomeFileHandler outcomeFileHandler = new OutcomeFileHandler();
    private MedicalRecordFileHandler medicalRecordFileHandler = new MedicalRecordFileHandler();

    private static DataBaseManager instance;

    public static DataBaseManager getInstance() {

        if (instance == null) {
            instance = new DataBaseManager();
        }
        return instance;
    }

    // Private constructor to prevent instantiation for Singleton
    private DataBaseManager() {
    }

    public PatientFileHandler getPatientFileHandler() {
        return patientFileHandler;
    }

    public StaffFileHandler getStaffFileHandler() {
        return staffFileHandler;
    }

    public AppointmentFileHandler getappointmentFileHandler() {
        return appointmentFileHandler;
    }

    public InventoryFileHandler getInventoryFileHandler() {
        return inventoryFileHandler;
    }

    public OutcomeFileHandler getOutcomeFileHandler() {
        return outcomeFileHandler;
    }

    public MedicalRecordFileHandler getMedicalRecordFileHandler() {
        return medicalRecordFileHandler;
    }   
}

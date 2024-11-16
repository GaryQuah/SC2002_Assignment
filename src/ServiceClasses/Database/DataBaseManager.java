package ServiceClasses.Database;

/**
 * Singleton class that manages all the file handlers for various data types in the application.
 * The `DataBaseManager` is responsible for providing access to handlers for managing patient records, staff records,
 * appointment outcomes, inventory, and medical records. The class follows the Singleton design pattern, ensuring
 * there is only one instance of the manager to control the data access.
 */
public class DataBaseManager {

    // File handlers for different data types
    private PatientFileHandler patientFileHandler = new PatientFileHandler();
    private StaffFileHandler staffFileHandler = new StaffFileHandler();
    private AppointmentFileHandler appointmentFileHandler = new AppointmentFileHandler();
    private InventoryFileHandler inventoryFileHandler = new InventoryFileHandler();
    private OutcomeFileHandler outcomeFileHandler = new OutcomeFileHandler();
    private MedicalRecordFileHandler medicalRecordFileHandler = new MedicalRecordFileHandler();

    // Singleton instance
    private static DataBaseManager instance;

    /**
     * Gets the singleton instance of the `DataBaseManager` class.
     * If the instance doesn't exist, it will be created.
     *
     * @return The single instance of the `DataBaseManager` class.
     */
    public static DataBaseManager getInstance() {
        if (instance == null) {
            instance = new DataBaseManager();
        }
        return instance;
    }

    // Private constructor to prevent direct instantiation from outside.
    private DataBaseManager() {
    }

    /**
     * Gets the file handler responsible for managing patient data.
     *
     * @return The `PatientFileHandler` instance.
     */
    public PatientFileHandler getPatientFileHandler() {
        return patientFileHandler;
    }

    /**
     * Gets the file handler responsible for managing staff data.
     *
     * @return The `StaffFileHandler` instance.
     */
    public StaffFileHandler getStaffFileHandler() {
        return staffFileHandler;
    }

    /**
     * Gets the file handler responsible for managing appointment data.
     *
     * @return The `AppointmentFileHandler` instance.
     */
    public AppointmentFileHandler getappointmentFileHandler() {
        return appointmentFileHandler;
    }

    /**
     * Gets the file handler responsible for managing inventory data.
     *
     * @return The `InventoryFileHandler` instance.
     */
    public InventoryFileHandler getInventoryFileHandler() {
        return inventoryFileHandler;
    }

    /**
     * Gets the file handler responsible for managing appointment outcome data.
     *
     * @return The `OutcomeFileHandler` instance.
     */
    public OutcomeFileHandler getOutcomeFileHandler() {
        return outcomeFileHandler;
    }

    /**
     * Gets the file handler responsible for managing medical records data.
     *
     * @return The `MedicalRecordFileHandler` instance.
     */
    public MedicalRecordFileHandler getMedicalRecordFileHandler() {
        return medicalRecordFileHandler;
    }
}

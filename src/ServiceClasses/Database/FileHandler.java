package ServiceClasses.Database;

import java.util.ArrayList;

/**
 * Abstract class for handling data operations for a specific data type.
 * This class is intended to be extended for handling data retrieval and storage for various entities
 * (e.g., patients, appointments, outcomes, etc.). It provides the framework for retrieving and saving data
 * while allowing specific implementations for different data types.
 *
 * @param <T> The type of data this handler will manage (e.g., Patient, Appointment, Outcome).
 */
public abstract class FileHandler<T> {

    // List to hold the data of type T
    private ArrayList<T> dataArray = new ArrayList<>();

    // File path to the data storage location (e.g., database, file system)
    private String FILE_PATH;

    /**
     * Constructor for initializing the file handler with a file path.
     *
     * @param FILE_PATH The file path or location where the data will be stored or retrieved from.
     */
    public FileHandler(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }

    /**
     * Abstract method for retrieving data from the storage.
     * Subclasses must implement this method to define how data is retrieved.
     *
     * @return An ArrayList containing the data of type T.
     */
    public abstract ArrayList<T> retrieveData();

    /**
     * Abstract method for saving data to the storage.
     * Subclasses must implement this method to define how data is saved.
     */
    public abstract void saveData();

    // Optional method for checking login credentials (commented out here)
    // public abstract boolean checkLogin(String username, String password);

    /**
     * Gets the file path where data is stored or retrieved from.
     *
     * @return The file path as a String.
     */
    public String getFilePath() {
        return FILE_PATH;
    }

    /**
     * Gets the current list of data held by this file handler.
     *
     * @return An ArrayList containing the data of type T.
     */
    public ArrayList<T> getDataArray() {
        return dataArray;
    }

    /**
     * Sets the data array to the specified list of data.
     *
     * @param dataArray The ArrayList containing the data of type T to set.
     */
    public void setDataArray(ArrayList<T> dataArray) {
        this.dataArray = dataArray;
    }
}

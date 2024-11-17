package ServiceClasses.Database;

import java.util.ArrayList;

import ServiceClasses.inventory.InventoryBuild;
import ServiceClasses.inventory.InventoryControl;
import ServiceClasses.inventory.Prescription;
import input.CSVParse;

/**
 * Handles the reading and saving of inventory data for prescriptions.
 * This class interacts with the CSV file located at a predefined file path to
 * manage prescription inventory data. It extends the generic {@link FileHandler} class
 * to provide concrete implementations for retrieving and saving prescription data.
 *
 * The inventory data is stored in the form of {@link Prescription} objects, which are
 * parsed from the CSV file on retrieval and saved back to the CSV file when the data is
 * updated.
 */
public class InventoryFileHandler extends FileHandler<Prescription> {

    /**
     * Constructor that initializes the file handler with the path to the inventory CSV file.
     * The path is set to "src\\data\\Medicine_List.csv" by default.
     */
    public InventoryFileHandler() {
        super("src\\data\\Medicine_List.csv");
    }

    /**
     * Retrieves prescription data from the CSV file and converts it into a list of {@link Prescription} objects.
     * This method reads the file located at the file path specified in the constructor,
     * parses the data, and creates {@link Prescription} objects, which are stored in the
     * {@link InventoryControl} instance.
     *
     * @return An ArrayList of {@link Prescription} objects representing the inventory data.
     */
    @Override
    public ArrayList<Prescription> retrieveData() {
        InventoryControl.getInstance().getInventory().clear();
        ArrayList<Prescription> results = new ArrayList<>();
        try {
            // Reading the CSV file to get raw inventory data
            ArrayList<String[]> inventoryData = CSVParse.read(getFilePath(), false);
            // Building Prescription objects from the raw CSV data
            results = (new InventoryBuild()).buildMany(inventoryData);
            // Updating the inventory in the InventoryControl instance
            InventoryControl.getInstance().setInventory(results);
        } catch (Exception e) {
            // Handle exception appropriately (log, rethrow, etc.)
        }
        return results;
    }

    /**
     * Saves the current prescription inventory data to the CSV file.
     * This method takes the current inventory from the {@link InventoryControl} instance,
     * converts it into a list of comma-separated strings, and writes it back to the CSV file.
     */
    @Override
    public void saveData() {
        ArrayList<String> data = new ArrayList<>();

        // Converting each Prescription object into a CSV-compatible string
        for (Prescription prescription : InventoryControl.getInstance().getInventory()) {
            data.add(
                    prescription.getItemName() + "," +
                            prescription.getItemID() + "," +
                            prescription.getStockLevel() + "," +
                            prescription.getLowStockAlertLevel() + "," +
                            prescription.getStockStatus().toString() + "," +
                            prescription.getRestockAmount()
            );
        }
        InventoryControl.getInstance().getInventory().clear();
        try {
            // Writing the data back to the CSV file
            CSVParse.write(getFilePath(), data);
        } catch (Exception e) {
        }
    }
}

package ServiceClasses.Database;

import java.util.ArrayList;

import ServiceClasses.AppointmentOutcome.AppoinmentOutcomeControl;
import ServiceClasses.AppointmentOutcome.AppointmentOutcome;
import ServiceClasses.AppointmentOutcome.AppointmentOutcomeBuild;
import input.CSVParse;
import input.HashParse;

/**
 * This class handles the retrieval and saving of appointment outcome data to a CSV file.
 * It extends the generic {@link FileHandler} class, specializing in handling {@link AppointmentOutcome} objects.
 * The file is located at `src\\data\\Appointment_Outcome_List.csv`.
 *
 * It provides methods to load the appointment outcomes from the file, save updated outcomes back to the file,
 * and ensure that the data is properly parsed and stored.
 */
public class OutcomeFileHandler extends FileHandler<AppointmentOutcome> {

    /**
     * Constructor that initializes the file handler with the file path of the appointment outcome CSV file.
     * The path is set to "src\\data\\Appointment_Outcome_List.csv" by default.
     */
    public OutcomeFileHandler() {
        super("src\\data\\Appointment_Outcome_List.csv");
    }

    /**
     * Retrieves appointment outcomes from the CSV file and converts them into a list of {@link AppointmentOutcome} objects.
     * Each row in the CSV file corresponds to a single appointment outcome. The method parses each row into an
     * {@link AppointmentOutcome} and adds it to the list.
     *
     * @return An {@link ArrayList} of {@link AppointmentOutcome} objects representing the data in the CSV file.
     */
    @Override
    public ArrayList<AppointmentOutcome> retrieveData() {

        ArrayList<AppointmentOutcome> results = new ArrayList<>();
        try {
            ArrayList<String[]> data = CSVParse.read(getFilePath(), false);  // Read without skipping the header
            results = (new AppointmentOutcomeBuild()).buildMany(data);  // Convert rows into AppointmentOutcome objects
            AppoinmentOutcomeControl.getInstance().setAppointmentOutcomes(results);  // Store in control

        } catch (Exception e) {
            // Handle exceptions (e.g., file not found, parsing errors)
            e.printStackTrace();
        }

        return results;
    }

    /**
     * Saves the current appointment outcomes to the CSV file.
     * This method converts each {@link AppointmentOutcome} object into a CSV-compatible string
     * and writes the data back to the file.
     *
     * @see CSVParse#write(String, ArrayList)
     */
    @Override
    public void saveData() {
        ArrayList<String> data = new ArrayList<>();
        // Iterate over each appointment outcome and convert it into a CSV format string
        for (AppointmentOutcome outcome : AppoinmentOutcomeControl.getInstance().getAppointmentOutcomes()) {

            data.add(
                    outcome.getAppointmentID() + "," +
                            outcome.getPatientName() + "," +
                            outcome.getDoctorName() + "," +
                            outcome.getServiceType() + "," +
                            outcome.getDateTime() + "," +
                            HashParse.toString(outcome.getPrescribedMedications()) + "," +
                            outcome.getConsultationNotes() + "," +
                            outcome.getStatus().toString()
            );
        }

        try {
            // Write the data to the file
            CSVParse.write(getFilePath(), data);
        } catch (Exception e) {
            // Handle exceptions (e.g., file write errors)
            e.printStackTrace();
        }
    }
}

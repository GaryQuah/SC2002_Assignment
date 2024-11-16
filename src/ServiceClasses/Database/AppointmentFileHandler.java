package ServiceClasses.Database;

import ServiceClasses.Appointment.Appointment;
import ServiceClasses.Appointment.AppointmentManager;
import ServiceClasses.Appointment.AppointmentStatus;
import input.CSVParse;

import java.util.ArrayList;

/**
 * This class is responsible for handling the file operations related to appointments.
 * It reads and writes appointment data to/from a CSV file. The data is stored in the
 * AppointmentManager's appointment list. The class extends FileHandler to implement
 * the retrieval and saving of appointment data.
 */
public class AppointmentFileHandler extends FileHandler<Appointment> {

    /**
     * Constructor that initializes the file path for appointment data.
     * The file path is passed to the superclass constructor to specify where
     * the appointment data CSV file is located.
     */
    public AppointmentFileHandler() {
        super("src/data/Appointment_List.csv");
    }

    /**
     * Retrieves appointment data from the CSV file and adds it to the AppointmentManager's list.
     * This method reads the CSV file, processes each row, and creates an Appointment object
     * for each row. The Appointment objects are then added to the AppointmentManager's
     * appointment list.
     *
     * @return An empty list (since the appointment data is stored directly in the AppointmentManager).
     */
    @Override
    public ArrayList<Appointment> retrieveData() {
        // Fetch the appointment list from the AppointmentManager
        ArrayList<Appointment> appointmentList = AppointmentManager.getInstance().getAppointmentList();

        // Read the data from the CSV file
        ArrayList<String[]> fileData = CSVParse.read(getFilePath(), true);
        appointmentList.clear();

        // Iterate through the rows of the CSV file and create Appointment objects
        for (String[] row : fileData) {
            // System.out.println(Arrays.toString(row));
            // System.out.println("Processing appointment Data data");

            // Create a new Appointment object using the data from the CSV file
            Appointment apt = new Appointment(
                    Integer.parseInt(row[0]), // Appointment ID
                    row[1], // Doctor Name
                    row[2], // Patient Name
                    row[3], // Appointment Date
                    row[4], // Time Slot
                    row[5], // Appointment Type
                    AppointmentStatus.valueOf(row[6]) // Appointment Status (converted from string)
            );

            // Add the created appointment to the AppointmentManager's list
            appointmentList.add(apt);
            // System.out.println("Created Appointment : " + apt.toString());
        }

        // Return null since the appointments are added directly to the AppointmentManager's list
        return null;
    }

    /**
     * Saves the current appointment data to the CSV file.
     * This method converts the appointment data in the AppointmentManager's list
     * into CSV format and writes it to the specified file. It writes the header
     * followed by each appointment's data.
     */
    @Override
    public void saveData() {
        // List to store the CSV data
        ArrayList<String> data = new ArrayList<String>();

        // Fetch the current list of appointments
        ArrayList<Appointment> appointmentList = AppointmentManager.getInstance().getAppointmentList();

        // Add the CSV header
        data.add("ID,Doctor Name,Patient Name,Date,Time Slot,Appointment Type,Appointment Status");

        // Add each appointment's data as a CSV row
        for (Appointment appointment : appointmentList) {
            data.add(
                    appointment.getAppointmentID() + "," +
                            appointment.getDoctorName() + "," +
                            appointment.getPatientName() + "," +
                            appointment.getAppointmentDate() + "," +
                            appointment.getTimeSlot() + "," +
                            appointment.getAppointmentType() + "," +
                            appointment.getAppointmentStatus()
            );
        }

        // Write the CSV data to the file
        try {
            CSVParse.write(getFilePath(), data);
        } catch (Exception e) {
            // Handle exceptions (e.g., file write errors)
            e.printStackTrace();
        }
    }
}

package ServiceClasses.AppointmentOutcome;

import java.util.ArrayList;
import java.util.HashMap;

import ServiceClasses.Appointment.Appointment;
import ServiceClasses.inventory.InventoryControl;
import input.HashParse;
import input.Scan;
import models.User;
import models.enums.Status;

public class AppointmentOutcomeBuild {

    /**
     * Builds an AppointmentOutcome object based on the provided user and
     * appointment data.
     *
     * @param user        the user creating the appointment outcome
     * @param appointment the appointment details
     * @return an AppointmentOutcome object with the given user and appointment
     *         details
     */
    public AppointmentOutcome build(User user, Appointment appointment) {
        int appoinmentID = appointment.getAppointmentID();
        String patientName = appointment.getPatientName();
        String doctorName = appointment.getDoctorName();
        String serviceType = appointment.getAppointmentType();
        String dateTime = appointment.getAppointmentDate();

        // Select medications from inventory, associating them with the outcome
        HashMap<String, Integer> prescribedMedications = InventoryControl.getInstance().selectMedication(user);

        // Prompt the user for a consultation note
        System.out.printf("Consultation Note: ");
        String consultationNote = Scan.scan.nextLine();
        return new AppointmentOutcome(appoinmentID, patientName, doctorName, serviceType, dateTime,
                prescribedMedications, consultationNote);
    }

    /**
     * Builds an AppointmentOutcome object from an array of strings containing
     * appointment data.
     * 
     * @param data an array of strings representing appointment outcome data
     * @return an AppointmentOutcome object created from the data, or null if data
     *         is invalid
     */
    public AppointmentOutcome build(String[] data) {

        // Ensure the data has enough fields to build an AppointmentOutcome
        if (data.length < 7)
            return null;

        // Case with 7 data fields, without status
        if (data.length == 7) {
            int appoinmentID = Integer.parseInt(data[0]);
            String patientName = data[1];
            String doctorName = data[2];
            String serviceType = data[3];
            String dateTime = data[4];
            HashMap<String, Integer> prescribedMedications = HashParse.fromString(data[5]);
            String consultationNote = data[6];
            return new AppointmentOutcome(appoinmentID, patientName, doctorName, serviceType, dateTime,
                    prescribedMedications, consultationNote);
        }

        // Case with 8 data fields, with status
        else if (data.length == 8) {
            int appoinmentID = Integer.parseInt(data[0]);
            String patientName = data[1];
            String doctorName = data[2];
            String serviceType = data[3];
            String dateTime = data[4];
            HashMap<String, Integer> prescribedMedications = HashParse.fromString(data[5]);
            String consultationNote = data[6];
            Status status = Status.fromString(data[7]);
            return new AppointmentOutcome(appoinmentID, patientName, doctorName, serviceType, dateTime,
                    prescribedMedications, consultationNote, status);
        }

        else
            return null;

    }

    /**
     * Builds multiple AppointmentOutcome objects from a list of string arrays
     * containing appointment data.
     *
     * @param data a list of string arrays, each representing appointment outcome
     *             data
     * @return an ArrayList of AppointmentOutcome objects created from the data
     */
    public ArrayList<AppointmentOutcome> buildMany(ArrayList<String[]> data) {
        ArrayList<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();

        for (String[] item : data) {
            appointmentOutcomes.add(build(item));
        }

        return appointmentOutcomes;
    }

}

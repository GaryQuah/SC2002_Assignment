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

    public AppointmentOutcome build(User user, Appointment appointment) {
        int appoinmentID = appointment.getAppointmentID();
        String patientName = appointment.getPatientName();
        String doctorName = appointment.getDoctorName();
        String serviceType = appointment.getAppointmentType();
        String dateTime = appointment.getAppointmentDate();
        HashMap<String, Integer> prescribedMedications = InventoryControl.getInstance().selectMedication(user);
        System.out.printf("Consultation Note: ");
        String consultationNote = Scan.scan.nextLine();
        return new AppointmentOutcome(appoinmentID, patientName, doctorName, serviceType, dateTime,
                prescribedMedications, consultationNote);
    }

    public AppointmentOutcome build(String[] data) {
        if (data.length < 7)
            return null;

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

    public ArrayList<AppointmentOutcome> buildMany(ArrayList<String[]> data) {
        ArrayList<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();

        for (String[] item : data) {
            appointmentOutcomes.add(build(item));
        }

        return appointmentOutcomes;
    }

}

package ServiceClasses.Database;

import ServiceClasses.Appointment.Appointment;
import ServiceClasses.Appointment.AppointmentManager;

import ServiceClasses.Appointment.AppointmentStatus;
import input.CSVParse;

import java.util.ArrayList;
import java.util.Arrays;

public class AppointmentFileHandler extends FileHandler<Appointment> {
    public AppointmentFileHandler() {
        super("src/data/Appointment_List.csv");
    }

    @Override
    public ArrayList<Appointment> retrieveData() {

        ArrayList<Appointment> appointmentList = AppointmentManager.getInstance().getAppointmentList();

        ArrayList<String[]> fileData = CSVParse.read(getFilePath(), true);

        for (String[] row : fileData) {
            System.out.println(Arrays.toString(row));
            System.out.println("Processing appointment Data data");

            Appointment apt = new Appointment(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4], row[5],
                    AppointmentStatus.valueOf(row[6]));
            appointmentList.add(apt);
            System.out.println("Created Appointment : " + apt.toString());
        }
        return null;
    }

    @Override
    public void saveData() {
        ArrayList<String> data = new ArrayList<String>();
        ArrayList<Appointment> appointmentList = AppointmentManager.getInstance().getAppointmentList();
        data.add("ID,Doctor Name,Patient Name,Date,Time Slot,Appointment Type,Appointment Status");

        for (Appointment appointment : appointmentList) {
            data.add(appointment.getAppointmentID() + "," +
                    appointment.getDoctorName() + "," +
                    appointment.getPatientName() + "," +
                    appointment.getAppointmentDate() + "," +
                    appointment.getTimeSlot() + "," +
                    appointment.getAppointmentType() + "," +
                    appointment.getAppointmentStatus());
        }
        try {
            CSVParse.write(getFilePath(), data);
        } catch (Exception e) {
        }
        // You can return the list of appointments, or modify as needed
    }
}

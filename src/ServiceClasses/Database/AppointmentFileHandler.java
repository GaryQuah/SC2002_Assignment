package ServiceClasses.Database;

import ServiceClasses.Appointment.Appointment;
import ServiceClasses.Appointment.AppointmentManager;

import models.Staff;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppointmentFileHandler extends FileHandler<Appointment> {
    public AppointmentFileHandler() {
        super("src/data/Appointment_List.csv");
    }

    @Override
    public ArrayList<Appointment> retrieveData() {
        List<String[]> fileData = new ArrayList<>();
        //Creates a reference to the appointment list in the appointment manager. whatever is
        // updated there updates here, vice versa
        ArrayList<Appointment> appointmentList = AppointmentManager.getInstance().getAppointmentList();

        try (BufferedReader br = new BufferedReader(new FileReader(getFilePath()))) {
            String line;
            boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                if (isFirstRow) {
                    isFirstRow = false; // Skip the first row
                    continue; // Move to the next iteration
                }

                String[] row = line.split(",");
                fileData.add(row);
            }
        } catch (IOException e) {
            System.out.println("Error retrieving appointment data");
            e.printStackTrace();
        }

        for (String[] row : fileData) {
            System.out.println(Arrays.toString(row));
            System.out.println("Processing appointment Data data");

            Appointment apt = new Appointment(Integer.parseInt(row[0]), row[1],row[2],row[3],row[4],row[5]);
            appointmentList.add(apt);
            System.out.println("Created Appointment : " + apt.toString());
            break;
        }
        return null;
    }

    @Override
    public void saveData() {
        ArrayList<Appointment> appointmentList = AppointmentManager.getInstance().getAppointmentList();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), false))) {
            // Write header
            writer.write("ID,Doctor Name, Patient Name, Date,Time Slot,Status\n");

    //int m_AppointmentID, String m_DoctorName, String m_PatientName, String m_AppointmentDate, String m_TimeSlot, m_Status
            //
            // Write appointment data
            for (Appointment appointment : appointmentList) {
                String line = appointment.getAppointmentID() + "," +
                        appointment.getDoctorName() + "," +
                        appointment.getDoctorName() + "," +
                        appointment.getPatientName() + "," +
                        appointment.getAppointmentDate() + "," +
                        appointment.getTimeSlot() + "," +
                        appointment.getAppointmentStatus();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving appointment data");
            e.printStackTrace();
        }
         // You can return the list of appointments, or modify as needed
    }
}

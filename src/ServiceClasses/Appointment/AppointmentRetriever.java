package ServiceClasses.Appointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AppointmentRetriever {

    private static AppointmentManager instance;
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    public AppointmentRetriever(ArrayList<Appointment> AppointmentList)
    {
        this.AppointmentList = AppointmentList;
    }

    /*
    public ArrayList<Appointment> getPastAppointments(String patientName) {
        ArrayList<Appointment> pastAppointments = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Appointment appointment : AppointmentList) {
            if (appointment.getPatientName().equals(patientName)) {
                LocalDate appointmentDate = LocalDate.parse(appointment.getAppointmentDate(), formatter);
                if (appointmentDate.isBefore(today)) {
                    pastAppointments.add(appointment);
                }
            }
        }
        return pastAppointments;
    }*/

    public Appointment getAppointmentByID (int appointmentID){
        for(Appointment appointment : AppointmentList){
            if(appointment.getAppointmentID() == appointmentID){
                return appointment;
            }
        }
        return null;
    }
}

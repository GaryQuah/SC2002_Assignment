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

    public Appointment getAppointmentByID (int appointmentID){
        for(Appointment appointment : AppointmentList){
            if(appointment.getAppointmentID() == appointmentID){
                return appointment;
            }
        }
        return null;
    }
}

package ServiceClasses.Appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AppointmentStatusUpdater {
    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    public AppointmentStatusUpdater(ArrayList<Appointment> AppointmentList)
    {
        this.AppointmentList = AppointmentList;
    }
}

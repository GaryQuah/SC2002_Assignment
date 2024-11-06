package ServiceClasses.Appointment;

import java.util.Vector;

public class AppointmentStatusUpdater {
    private Vector<Appointment> AppointmentList = new Vector<Appointment>();

    public AppointmentStatusUpdater(Vector<Appointment> AppointmentList)
    {
        this.AppointmentList = AppointmentList;
    }
}

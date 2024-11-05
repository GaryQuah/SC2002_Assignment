package ServiceClasses.Appointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class AppointmentRetriever {

    private Vector<Appointment> AppointmentList = new Vector<Appointment>();

    public AppointmentRetriever(Vector<Appointment> AppointmentList)
    {
        this.AppointmentList = AppointmentList;
    }

    public Vector<Appointment> retrievePastAppointments(String patientName) {
        Vector<Appointment> pastAppointments = new Vector<>();
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
    }
}

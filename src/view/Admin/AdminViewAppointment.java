package view.Admin;
import ServiceClasses.AppointmentManager;

public class AdminViewAppointment {
    public static void main(String[] args) {
        AppointmentManager appointmentManager = AppointmentManager.getInstance(); 
        appointmentManager.ViewAllAppointmentsByStatus(1); 
    }
}

package ServiceClasses.Appointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class AppointmentViewer {
   
    private Vector<Appointment> AppointmentList;
   
    public AppointmentViewer(Vector<Appointment> AppointmentList)
    {
        this.AppointmentList = AppointmentList;
    }

    public void ViewAllAppointmentsByStatus(AppointmentStatus m_AppointmentStatus) { //Sort by appointment status - for admin : Appointment Outcome Record (for completed appointments) 
        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments, make sure the doctor
                                                         // dosent have an appointment on the date and
        {
            if (AppointmentList.elementAt(i).appointmentStatus() == AppointmentStatus.ACCEPTED) {
                System.out.println(AppointmentList.elementAt(i));
            }
        }   
    }

    // View available dates - prints out all the available date and time slots. if
    // no appointment, prints out message that the doctor is free.
    public void ViewAvailableDates(String m_doctorName) {
        Vector<String> unAvailableDates = new Vector<String>();
        Vector<String> unAvailableTimeSlots = new Vector<String>();

        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments, make sure the doctor
                                                         // dosent have an appointment on the date and
        {
            if (AppointmentList.elementAt(i).getDoctorName().equals(m_doctorName)) {
                unAvailableDates.add(AppointmentList.elementAt(i).getAppointmentDate());
                unAvailableTimeSlots.add(AppointmentList.elementAt(i).getTimeSlot());
            }
        }

        if (unAvailableDates.size() > 0) {
            System.out.println("The doctor is unavailable during these dates and timeslots:");

            for (int i = 0; i < unAvailableDates.size(); ++i) {
                System.out.println(
                        "Date : " + unAvailableDates.elementAt(i) + " Time : " + unAvailableTimeSlots.elementAt(i));
            }
        } else {
            System.out
                    .println("The doctor has no appointments scheduled. He is free for all working hours time slots.");
        }
    }

    // View appointments that have the doctors name in them
    public void ViewDoctorAppointments(String m_doctorName) {
        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments, make sure the doctor
                                                         // dosent have an appointment on the date and
        {
            if (AppointmentList.elementAt(i).getDoctorName().equals(m_doctorName)) {
                System.out.println(AppointmentList.elementAt(i));
            }
        }
    }

    // View appointments that have the patients name in them
    // view schedules appointments minus past appointments
    public void ViewPatientAppointments(String m_patientName) {
        LocalDate today = LocalDate.now(); // Get today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments, make sure the doctor
                                                         // dosent have an appointment on the date and
        {
            if (AppointmentList.elementAt(i).getDoctorName().equals(m_patientName)) {
                LocalDate appointmentDate = LocalDate.parse(AppointmentList.elementAt(i).getAppointmentDate(),
                        formatter);
                if (!appointmentDate.isBefore(today)) {
                    System.out.println(AppointmentList.elementAt(i));
                }
            }
        }
    }

    public void ViewAllAppointments() 
    {
        for (int i = 0; i < AppointmentList.size(); ++i) 
        {
            System.out.println(AppointmentList.elementAt(i));
        }
    }

     // to view past appointment of patient
     /* 
     public Vector<Appointment> getPastAppointments(String patientName) {
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
    }*/
}

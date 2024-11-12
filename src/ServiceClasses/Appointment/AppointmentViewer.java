package ServiceClasses.Appointment;

import java.util.ArrayList;

public class AppointmentViewer {

    private ArrayList<Appointment> AppointmentList = new ArrayList<>();

    public AppointmentViewer(ArrayList<Appointment> AppointmentList)
    {
        this.AppointmentList = AppointmentList;
    }

    public void ViewAllAppointmentsByStatus(AppointmentStatus m_AppointmentStatus) { //Sort by appointment status - for admin : Appointment Outcome Record (for completed appointments)
        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments, make sure the doctor
        // doesn't have an appointment on the date and
        {
            if (AppointmentList.get(i).getAppointmentStatus() == AppointmentStatus.ACCEPTED) {
                System.out.println(AppointmentList.get(i));
            }
        }
    }

    // View available dates - prints out all the available date and time slots. if
    // no appointment, prints out message that the doctor is free.
    public void ViewAvailableDates(String m_doctorName) {
        ArrayList<String> unAvailableDates = new ArrayList<String>();
        ArrayList<String> unAvailableTimeSlots = new ArrayList<String>();

        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments, make sure the doctor
        // doesn't have an appointment on the date and
        {
            if (AppointmentList.get(i).getDoctorName().equals(m_doctorName)) {
                unAvailableDates.add(AppointmentList.get(i).getAppointmentDate());
                unAvailableTimeSlots.add(AppointmentList.get(i).getTimeSlot());
            }
        }

        if (unAvailableDates.size() > 0) {
            System.out.println("The doctor is unavailable during these dates and timeslots:");

            for (int i = 0; i < unAvailableDates.size(); ++i) {
                System.out.println(
                        "Date : " + unAvailableDates.get(i) + " Time : " + unAvailableTimeSlots.get(i));
            }
        } else {
            System.out
                    .println("The doctor has no appointments scheduled. He is free for all working hours time slots.");
        }
    }

    // View appointments that have the doctor's name in them
    public void ViewDoctorAppointments(String m_doctorName) {
        int appointmentCount = 0;

        for (int i = 0; i < AppointmentList.size(); ++i) // Check through all the appointments, make sure the doctor
        // doesn't have an appointment on the date and
        {
            if (AppointmentList.get(i).getDoctorName().equals(m_doctorName)) {
                System.out.println(AppointmentList.get(i));
                appointmentCount++;
            }
        }

        if (appointmentCount == 0)
            System.out.println("No appointments have been scheduled for the doctor " + m_doctorName);
    }

    // View appointments that have the patient's name in them
    // view scheduled appointments minus past appointments
    public void ViewPatientAppointments(String m_patientName) {
        int appointmentCount = 0;

        for (int i = 0; i < AppointmentList.size(); ++i)
        {
            if (AppointmentList.get(i).getPatientName().equals(m_patientName))
            {
                System.out.println(AppointmentList.get(i));
                appointmentCount++;
            }
        }

        if (appointmentCount == 0)
        {
            System.out.println("No appointments have been scheduled for the patient " + m_patientName);
            ViewAllAppointments();
        }
    }

    public void ViewAllAppointments()
    {
        for (int i = 0; i < AppointmentList.size(); ++i)
        {
            System.out.println(AppointmentList.get(i));
        }
    }

    // to view past appointments of a patient
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
    } */
}

package ServiceClasses.AppointmentOutcome;

import java.util.ArrayList;

import models.User;
import models.enums.Role;

public class AppointmentOutcomeDisplay {

    /**
     * Prints a header row customized based on the role of the user.
     * 
     * @param user the user whose role determines the displayed columns
     */
    public void header(User user) {

        if (user.getRole() == Role.Administrator) {
            System.out.printf("APPOINTMENT OUTCOMES");
            for (int i = 0; i < 111; i++)
                System.out.printf("-");
            System.out.println();

            System.out.printf("%-5s |", "No."); // New index column
            System.out.printf("%-15s |", "Appointment ID");
            System.out.printf(" %-16s |", "Appointment Date");
            System.out.printf(" %-15s |", "Patient Name");
            System.out.printf(" %-15s |", "Doctor Name");
            System.out.printf(" %-15s |", "Type of Service");
            System.out.printf(" %-30s |", "Medication");
            System.out.printf(" %-15s |", "Consultation Notes");
            System.out.printf(" %-10s\n", "Status");

        }

        else if (user.getRole() == Role.Doctor) {
            System.out.printf("DOCTOR APPOINTMENT OUTCOMES");
            System.out.println();
            System.out.printf("%-5s |", "No."); // New index column
            System.out.printf("%-15s |", "Appointment ID");
            System.out.printf(" %-16s |", "Appointment Date");
            System.out.printf(" %-15s |", "Patient Name");
            System.out.printf(" %-15s |", "Type of Service");
            System.out.printf(" %-30s |", "Medication");
            System.out.printf(" %-15s\n", "Consultation Notes");
        }

        else if (user.getRole() == Role.Patient) {
            System.out.printf("MEDICAL RECORDS");
            for (int i = 0; i < 94; i++)
                System.out.printf("-");
            System.out.println();
            System.out.printf("%-5s |", "No."); // New index column
            System.out.printf("%-15s |", "Appointment ID");
            System.out.printf(" %-16s |", "Appointment Date");
            System.out.printf(" %-15s |", "Doctor Name");
            System.out.printf(" %-25s |", "Type of Service");
            // System.out.printf(" %-30s |", "Medication");
            System.out.printf(" %-15s\n", "Consultation Notes");
        }

        else if (user.getRole() == Role.Pharmacist) {
            System.out.printf("APPOINTMENT OUTCOMES");

            System.out.println();

            System.out.printf("%-5s |", "No."); // New index column
            System.out.printf("%-15s |", "Appointment ID");
            System.out.printf(" %-16s |", "Appointment Date");
            System.out.printf(" %-15s |", "Patient Name");
            System.out.printf(" %-15s |", "Doctor Name");
            System.out.printf(" %-30s |", "Medication");
            System.out.printf(" %-10s\n", "Status");
        }

    }

    /**
     * Prints a single appointment outcome, formatted based on the user’s role.
     * 
     * @param appointmentOutcome the outcome to display
     * @param index              the index to label the row in the display
     * @param user               the user whose role determines displayed details
     */
    public void printOutcome(AppointmentOutcome appointmentOutcome, int index, User user) {
        if (appointmentOutcome == null)
            return;

        if (user.getRole() == Role.Doctor) {
            System.out.printf("%-5d |", index); // Print the index number
            System.out.printf("%-15d |", appointmentOutcome.getAppointmentID());
            System.out.printf(" %-16s |", appointmentOutcome.getDateTime());
            System.out.printf(" %-15s |", appointmentOutcome.getPatientName());
            System.out.printf(" %-15s |", appointmentOutcome.getServiceType());
            System.out.printf(" %-30s |", appointmentOutcome.getPrescribedMedications());
            System.out.printf(" %-15s\n", appointmentOutcome.getConsultationNotes());
        }

        else if (user.getRole() == Role.Patient) {
            System.out.printf("%-5d |", index); // Print the index number
            System.out.printf("%-15d |", appointmentOutcome.getAppointmentID());
            System.out.printf(" %-16s |", appointmentOutcome.getDateTime());
            System.out.printf(" %-15s |", appointmentOutcome.getDoctorName());
            System.out.printf(" %-25s |", appointmentOutcome.getServiceType());
            // System.out.printf(" %-30s |", appointmentOutcome.getPrescribedMedications());
            System.out.printf(" %-15s\n", appointmentOutcome.getConsultationNotes());

        } else if (user.getRole() == Role.Administrator) {
            System.out.printf("%-5d |", index); // Print the index number
            System.out.printf("%-15d |", appointmentOutcome.getAppointmentID());
            System.out.printf(" %-16s |", appointmentOutcome.getDateTime());
            System.out.printf(" %-15s |", appointmentOutcome.getPatientName());
            System.out.printf(" %-15s |", appointmentOutcome.getDoctorName());
            System.out.printf(" %-15s |", appointmentOutcome.getServiceType());
            System.out.printf(" %-30s |", appointmentOutcome.getPrescribedMedications());
            System.out.printf(" %-15s |", appointmentOutcome.getConsultationNotes());
            System.out.printf(" %-10s\n", appointmentOutcome.getStatus());

        }

        else if (user.getRole() == Role.Pharmacist) {
            System.out.printf("%-5d |", index); // Print the index number
            System.out.printf("%-15d |", appointmentOutcome.getAppointmentID());
            System.out.printf(" %-16s |", appointmentOutcome.getDateTime());
            System.out.printf(" %-15s |", appointmentOutcome.getPatientName());
            System.out.printf(" %-15s |", appointmentOutcome.getDoctorName());
            System.out.printf(" %-30s |", appointmentOutcome.getPrescribedMedications());
            System.out.printf(" %-10s\n", appointmentOutcome.getStatus());
        }
    }

    /**
     * Displays all appointment outcomes for the specified user.
     * 
     * @param appointmentOutcomes list of outcomes to display
     * @param user                the user viewing the outcomes
     */
    public void printOutcomes(ArrayList<AppointmentOutcome> appointmentOutcomes, User user) {
        if (appointmentOutcomes.size() == 0) {
            System.out.println("Not Outcomes found in database");
            return;
        }

        header(user);
        for (int i = 0; i < appointmentOutcomes.size(); i++) {
            printOutcome(appointmentOutcomes.get(i), i + 1, user);
        }

    }

}

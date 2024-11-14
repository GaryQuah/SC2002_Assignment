package ServiceClasses.AppointmentOutcome;

import java.util.ArrayList;

import models.User;
import models.enums.Role;

public class AppointmentOutcomeDisplay {

    public void header(User user) {

        if (user.getRole() == Role.Administrator || user.getRole()==Role.Pharmacist){
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

        else if (user.getRole() == Role.Doctor){
            System.out.printf("APPOINTMENT OUTCOME BY DOCTOR "+user.getName());
            // for (int i = 0; i < 96; i++)
            //     System.out.printf("-");
            System.out.println();
            System.out.printf("%-5s |", "No."); // New index column
            System.out.printf("%-15s |", "Appointment ID");
            System.out.printf(" %-16s |", "Appointment Date");
            System.out.printf(" %-15s |", "Patient Name");
            System.out.printf(" %-15s |", "Type of Service");
            System.out.printf(" %-30s |", "Medication");
            System.out.printf(" %-15s\n", "Consultation Notes");
        }

        else if (user.getRole() == Role.Patient){
            System.out.printf("MEDICAL RECORDS OF "+user.getName());
            for (int i = 0; i < 96; i++)
                System.out.printf("-");
            System.out.println();
            System.out.printf("%-5s |", "No."); // New index column
            System.out.printf("%-15s |", "Appointment ID");
            System.out.printf(" %-16s |", "Appointment Date");
            System.out.printf(" %-15s |", "Doctor Name");
            System.out.printf(" %-15s |", "Type of Service");
            System.out.printf(" %-30s |", "Medication");
            System.out.printf(" %-15s\n", "Consultation Notes");
        }
    }

    public void printOutcome(AppointmentOutcome appointmentOutcome, int index, User user) {
        if (appointmentOutcome == null) return;

        // if(user.getRole() == Role.Administrator or ){
        //     System.out.printf("%-5d |", index); // Print the index number
        //     System.out.printf("%-5s |", appointmentOutcome.getAppointmentID());
        //     System.out.printf(" %-10s |", appointmentOutcome.getDateTime());
        //     System.out.printf(" %-10d |", appointmentOutcome.getPatientName());
        //     System.out.printf(" %-10d |", appointmentOutcome.getDoctorName());
        //     System.out.printf(" %-15d |", appointmentOutcome.getServiceType());
        //     System.out.printf(" %-15d |", appointmentOutcome.getPrescribedMedications());
        //     System.out.printf(" %-15s\n", appointmentOutcome.getConsultationNotes());
        // }

        if(user.getRole() == Role.Doctor){
            System.out.printf("%-5d |", index); // Print the index number
            System.out.printf("%-15d |", appointmentOutcome.getAppointmentID());
            System.out.printf(" %-16s |", appointmentOutcome.getDateTime());
            System.out.printf(" %-15s |", appointmentOutcome.getPatientName());
            System.out.printf(" %-15s |", appointmentOutcome.getServiceType());
            System.out.printf(" %-30s |", appointmentOutcome.getPrescribedMedications());
            System.out.printf(" %-15s\n", appointmentOutcome.getConsultationNotes());
            }

        else if(user.getRole() == Role.Patient){
            System.out.printf("%-5d |", index); // Print the index number
            System.out.printf("%-15d |", appointmentOutcome.getAppointmentID());
            System.out.printf(" %-16s |", appointmentOutcome.getDateTime());
            System.out.printf(" %-15s |", appointmentOutcome.getDoctorName());
            System.out.printf(" %-15s |", appointmentOutcome.getServiceType());
            System.out.printf(" %-30s |", appointmentOutcome.getPrescribedMedications());
            System.out.printf(" %-15s\n", appointmentOutcome.getConsultationNotes());
            
        } else {
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
    }
    

    public void printOutcomes(ArrayList<AppointmentOutcome> appointmentOutcomes, User user) {
        if (appointmentOutcomes.size() == 0) { 
            System.out.println("Not Outcomes found in database");
            return;
        }

        header(user);
        for (int i = 0; i < appointmentOutcomes.size(); i++){
            printOutcome(appointmentOutcomes.get(i), i+1, user);
        }

    
    }

}

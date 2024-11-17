package ServiceClasses.AppointmentOutcome;

import java.util.ArrayList;
import input.DateParse;

public class AppointmentOutcomeSort {

    /**
     * Displays sorting options for the user to choose a sorting field.
     */
    public void sortMenu() {
        System.out.println("Select Sort Method:");
        System.out.println("1 - Patient Name");
        System.out.println("2 - Doctor Name");
        System.out.println("3 - Appointment Date (Default)");
    }

    /** The order of sorting available to the user */
    public void orderMenu() {
        System.out.println("Select Order:");
        System.out.println("1 - Ascending (Default)");
        System.out.println("2 - Descending");
    }

    /**
     * Sorts a list of appointment outcomes by patient name.
     * 
     * @param outcomes    the list of appointment outcomes to sort
     * @param orderOption 1 for ascending, 2 for descending
     * @return sorted list of appointment outcomes
     */
    public ArrayList<AppointmentOutcome> sortByPatientName(ArrayList<AppointmentOutcome> outcomes, int orderOption) {
        switch (orderOption) {
            case 1:
                outcomes.sort((outcome1, outcome2) -> outcome1.getPatientName().toLowerCase()
                        .compareTo(outcome2.getPatientName().toLowerCase()));
                break;
            default:
                outcomes.sort((outcome1, outcome2) -> -outcome1.getPatientName().toLowerCase()
                        .compareTo(outcome2.getPatientName().toLowerCase()));
                break;
        }
        return outcomes;
    }

    /**
     * Sorts a list of appointment outcomes by doctor name.
     * 
     * @param outcomes    the list of appointment outcomes to sort
     * @param orderOption 1 for ascending, 2 for descending
     * @return sorted list of appointment outcomes
     */
    public ArrayList<AppointmentOutcome> sortByDoctorName(ArrayList<AppointmentOutcome> outcomes, int orderOption) {
        switch (orderOption) {
            case 1:
                outcomes.sort((outcome1, outcome2) -> outcome1.getDoctorName().toLowerCase()
                        .compareTo(outcome2.getDoctorName().toLowerCase()));
                break;
            default:
                outcomes.sort((outcome1, outcome2) -> -outcome1.getDoctorName().toLowerCase()
                        .compareTo(outcome2.getDoctorName().toLowerCase()));
                break;
        }
        return outcomes;
    }

    /**
     * Sorts a list of appointment outcomes by appointment date.
     * 
     * @param outcomes    the list of appointment outcomes to sort
     * @param orderOption 1 for ascending, 2 for descending
     * @return sorted list of appointment outcomes
     */
    public ArrayList<AppointmentOutcome> sortByAppointmentDate(ArrayList<AppointmentOutcome> outcomes,
            int orderOption) {

        switch (orderOption) {
            case 1:
                outcomes.sort((outcome1, outcome2) -> DateParse.date(outcome1.getDateTime())
                        .compareTo(DateParse.date(outcome2.getDateTime())));
                break;
            default:
                outcomes.sort((outcome1, outcome2) -> -DateParse.date(outcome1.getDateTime())
                        .compareTo(DateParse.date(outcome2.getDateTime())));
                break;
        }
        return outcomes;
    }

}

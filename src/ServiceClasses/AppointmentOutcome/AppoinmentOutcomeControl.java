package ServiceClasses.AppointmentOutcome;

import java.util.ArrayList;
import java.util.HashMap;

import ServiceClasses.Appointment.Appointment;
import ServiceClasses.inventory.InventoryControl;
import input.IntInput;
import input.Scan;
import models.User;
import models.enums.Role;
import models.enums.Status;

public class AppoinmentOutcomeControl implements IAppoinmentOutcome, IAppoinmentOutcomeControl {

    private ArrayList<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();

    private static AppoinmentOutcomeControl instance;

    /**
     * Provides a singleton instance of AppointmentOutcomeControl.
     *
     * @return the singleton instance of AppointmentOutcomeControl
     */
    public static AppoinmentOutcomeControl getInstance() {
        if (instance == null) {
            instance = new AppoinmentOutcomeControl();
        }
        return instance;
    }

    /**
     * Checks if the user has the role of Doctor.
     *
     * @param user the user to check
     * @return true if the user is a Doctor, otherwise false
     */
    private Boolean isDoctor(User user) {
        if (user.getRole() == Role.Doctor)
            return true;
        System.out.println("Only Doctor are permitted to conduct this operation. ");
        return false;
    }

    /**
     * Checks if the user has the role of Pharmacist.
     *
     * @param user the user to check
     * @return true if the user is a Pharmacist, otherwise false
     */
    private Boolean isPharmacist(User user) {
        if (user.getRole() == Role.Pharmacist)
            return true;
        System.out.println("Only Pharmacist are permitted to conduct this operation. ");
        return false;
    }

    /**
     * Checks if the user has the role of Administrator.
     *
     * @param user the user to check
     * @return true if the user is an Administrator, otherwise false
     */
    private Boolean isAdmin(User user) {
        if (user.getRole() == Role.Administrator)
            return true;
        System.out.println("Only Admin are permitted to conduct this operation. ");
        return false;
    }

    /**
     * Gets the list of all appointment outcomes.
     *
     * @return the list of appointment outcomes
     */
    public ArrayList<AppointmentOutcome> getAppointmentOutcomes() {
        return appointmentOutcomes;
    }

    /**
     * Sets a new list of appointment outcomes.
     *
     * @param appointmentOutcomes the new list of appointment outcomes
     */

    public void setAppointmentOutcomes(ArrayList<AppointmentOutcome> appointmentOutcomes) {
        this.appointmentOutcomes = appointmentOutcomes;
    }

    /**
     * Creates a new appointment outcome if the user is a Doctor and the appointment
     * does not already exist.
     *
     * @param user        the user creating the appointment
     * @param appointment the appointment details
     */
    @Override
    public void create(User user, Appointment appointment) {
        if (!isDoctor(user))
            return;

        AppointmentOutcome newOutcome = appointmentOutcomeBuild.build(user, appointment);

        for (AppointmentOutcome outcome : appointmentOutcomes) {
            if (outcome.getAppointmentID() == newOutcome.getAppointmentID()) {
                System.out.println("This Appointment existed inside the database.");
                return;
            }
        }
        appointmentOutcomes.add(newOutcome);
        appointmentOutcomeSort.sortByAppointmentDate(appointmentOutcomes, 0);
        System.out.println("New Appointment added.");
    }

    /**
     * Selects an appointment outcome for the user by displaying all outcomes.
     *
     * @param user the user selecting the appointment
     * @return the selected appointment outcome, or null if invalid
     */
    public AppointmentOutcome select(User user) {
        (new AppointmentOutcomeDisplay()).printOutcomes(appointmentOutcomes, user);
        if (appointmentOutcomes.size() == 0) {
            return null;
        }

        int index = IntInput.integer("Select Appoinment");

        if (1 <= index && index <= appointmentOutcomes.size()) {
            return appointmentOutcomes.get(index - 1);
        }

        System.out.println("Invalid selection.");
        return null;
    }

    /**
     * Selects an appointment outcome from a specified list for the user.
     *
     * @param user     the user selecting the appointment
     * @param outcomes the list of outcomes to choose from
     * @return the selected appointment outcome, or null if invalid
     */
    public AppointmentOutcome select(User user, ArrayList<AppointmentOutcome> outcomes) {
        (new AppointmentOutcomeDisplay()).printOutcomes(outcomes, user);
        if (outcomes.size() == 0) {

            return null;
        }

        int index = IntInput.integer("Select Appoinment");

        if (1 <= index && index <= outcomes.size()) {
            return outcomes.get(index - 1);
        }

        System.out.println("Invalid selection.");
        return null;
    }

    /**
     * Allows a doctor to edit an existing appointment outcome by updating service
     * type, medication, or consultation note.
     *
     * @param user the doctor editing the appointment outcome
     */
    @Override
    public void edit(User user) {
        if (!isDoctor(user))
            return;

        AppointmentOutcome appointmentOutcome = select(user, getOutcomeByDoctorName(user.getName()));

        if (appointmentOutcome == null)
            return;

        System.out.println("Select Attribute to Edit:");
        System.out.println("1 - Type of Service");
        System.out.println("2 - Medication");
        System.out.println("3 - Consultation Note");

        int option = IntInput.integer("Option");

        switch (option) {
            case 1:
                System.out.printf("Edit Type of Service: ");
                String serviceType = Scan.scan.nextLine();
                appointmentOutcome.setServiceType(serviceType);
                break;

            case 2:
                appointmentOutcome.setPrescribedMedications(InventoryControl.getInstance().selectMedication(user));
                break;

            case 3:
                System.out.printf("New Consultation Note: ");
                String consultationNote = Scan.scan.nextLine();
                appointmentOutcome.setConsultationNotes(consultationNote);
                break;

            default:
                break;
        }

    }

    /**
     * Allows an admin to delete an appointment outcome.
     *
     * @param user the admin deleting the outcome
     */
    @Override
    public void delete(User user) {
        if (!isAdmin(user))
            return;

        System.out.println("Please appointment outcome that you want to remove.");
        AppointmentOutcome appointmentOutcome = select(user);

        appointmentOutcomes.remove(appointmentOutcome);
    }

    /**
     * Displays all appointment outcomes for a user.
     *
     * @param user the user viewing the outcomes
     */
    @Override
    public void viewAppoinmentOutcomes(User user) {
        appointmentOutcomeSort.sortByAppointmentDate(appointmentOutcomes, 0);
        appointmentOutcomeDisplay.printOutcomes(appointmentOutcomes, user);
    }

    /**
     * Displays a doctor's medical records based on patient name.
     *
     * @param user the doctor viewing medical records
     */
    @Override
    public void viewMedicalRecordsByDoctor(User user) {
        ArrayList<AppointmentOutcome> SortedOutcome = appointmentOutcomeSort
                .sortByPatientName(getOutcomeByDoctorName(user.getName()), 0);
        appointmentOutcomeDisplay.printOutcomes(SortedOutcome, user);
    }

    /**
     * Displays a patient's medical records based on doctor name.
     *
     * @param user the patient viewing medical records
     */
    @Override
    public void viewMedicalRecordsByPatient(User user) {
        ArrayList<AppointmentOutcome> SortedOutcome = appointmentOutcomeSort
                .sortByDoctorName(getOutcomeByPatientName(user), 0);
        appointmentOutcomeDisplay.printOutcomes(SortedOutcome, user);
    }

    /**
     * Displays a pharmacist's pending medical records, sorted by appointment date.
     *
     * @param user the pharmacist viewing medical records
     */
    public void viewMedicalRecordsByPharmacist(User user) {
        ArrayList<AppointmentOutcome> SortedOutcome = appointmentOutcomeSort
                .sortByAppointmentDate(getOutcomeByPharmacist(), 0);
        appointmentOutcomeDisplay.printOutcomes(SortedOutcome, user);
    }

    /**
     * Retrieves outcomes based on a specific doctor’s name.
     *
     * @param name the doctor's name
     * @return a list of appointment outcomes for that doctor
     */
    public ArrayList<AppointmentOutcome> getOutcomeByDoctorName(String name) {
        ArrayList<AppointmentOutcome> result = new ArrayList<>();
        for (AppointmentOutcome outcome : appointmentOutcomes) {
            if (outcome.getDoctorName().equalsIgnoreCase(name)) {
                result.add(outcome);
            }
        }
        if (result.size() == 0) {
            return new ArrayList<>();
        } else {
            return result;
        }

    }

    /**
     * Retrieves outcomes based on a specific patient’s name.
     *
     * @param user the patient user
     * @return a list of appointment outcomes for that patient
     */
    public ArrayList<AppointmentOutcome> getOutcomeByPatientName(User user) {
        ArrayList<AppointmentOutcome> result = new ArrayList<>();
        for (AppointmentOutcome outcome : appointmentOutcomes) {
            if (outcome.getPatientName().equalsIgnoreCase(user.getName())) {
                result.add(outcome);
            }
        }
        if (result.size() == 0) {
            return new ArrayList<>();
        } else {
            return result;
        }

    }

    /**
     * Retrieves outcomes pending for a pharmacist.
     *
     * @return a list of pending appointment outcomes
     */
    public ArrayList<AppointmentOutcome> getOutcomeByPharmacist() {
        ArrayList<AppointmentOutcome> result = new ArrayList<>();
        for (AppointmentOutcome outcome : appointmentOutcomes) {
            if (outcome.getStatus() == Status.PENDING) {
                result.add(outcome);
            }
        }
        appointmentOutcomeSort.sortByAppointmentDate(result, 0);
        if (result.size() == 0) {
            return new ArrayList<>();
        } else {
            return result;
        }

    }

    /**
     * Updates the prescription status if the user is a pharmacist.
     *
     * @param user the pharmacist updating the status
     */
    @Override
    public void updatePrescriptionStatus(User user) {
        if (!isPharmacist(user))
            return;

        ArrayList<AppointmentOutcome> result = getOutcomeByPharmacist();
        if (result.size() == 0) {
            System.out.println("No Pending Medication dispense request.");
            return;
        }
        
        AppointmentOutcome appointmentOutcome = select(user, result);
        HashMap<String, Integer> medicationMap = appointmentOutcome.getPrescribedMedications();
        Status status = InventoryControl.getInstance().dispenseMedicine(medicationMap);
        appointmentOutcome.setStatus(status);
        appointmentOutcomeDisplay.printOutcomes(result, user);

    }

}

package ServiceClasses.AppointmentOutcome;

import java.util.ArrayList;
import java.util.HashMap;

import ServiceClasses.Appointment.Appointment;
import ServiceClasses.inventory.InventoryControl;
import input.IntInput;
import input.Scan;
import models.Patient;
import models.User;
import models.enums.Role;
import models.enums.Status;

public class AppoinmentOutcomeControl implements IAppoinmentOutcome, IAppoinmentOutcomeControl {

    private ArrayList<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();

    public ArrayList<AppointmentOutcome> getAppointmentOutcomes() {
        return appointmentOutcomes;
    }

    public void setAppointmentOutcomes(ArrayList<AppointmentOutcome> appointmentOutcomes) {
        this.appointmentOutcomes = appointmentOutcomes;
    }

    private static AppoinmentOutcomeControl instance;

    public static AppoinmentOutcomeControl getInstance() {
        if (instance == null) {
            instance = new AppoinmentOutcomeControl();
        }
        return instance;
    }

    private Boolean isDoctor(User user) {
        if (user.getRole() == Role.Doctor)
            return true;
        System.out.println("Only Doctor are permitted to conduct this operation. ");
        return false;
    }

    private Boolean isPharmacist(User user) {
        if (user.getRole() == Role.Pharmacist)
            return true;
        System.out.println("Only Pharmacist are permitted to conduct this operation. ");
        return false;
    }

    private Boolean isAdmin(User user) {
        if (user.getRole() == Role.Administrator)
            return true;
        System.out.println("Only Admin are permitted to conduct this operation. ");
        return false;
    }

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

    @Override
    public void edit(User user) {
        if (!isDoctor(user))
            return;

        AppointmentOutcome appointmentOutcome = select(user);

        if (appointmentOutcome == null)
            return;

        System.out.println("Select Attribute to Edit:");
        System.out.println("1 - Type of Service");
        System.out.println("2 - Medication");
        System.out.println("3 - Consulatation Note");

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
                appointmentOutcome.setServiceType(consultationNote);
                break;

            default:
                break;
        }

    }

    @Override
    public void delete(User user) {
        if (!isAdmin(user))
            return;

        System.out.println("Please appointment outcome that you want to remove.");
        AppointmentOutcome appointmentOutcome = select(user);

        appointmentOutcomes.remove(appointmentOutcome);
    }

    @Override
    public void viewAppoinmentOutcomes(User user) {
        appointmentOutcomeSort.sortByAppointmentDate(appointmentOutcomes, 0);
        appointmentOutcomeDisplay.printOutcomes(appointmentOutcomes, user);
    }

    @Override
    public void viewMedicalRecordsByDoctor(User user) {
        ArrayList<AppointmentOutcome> SortedOutcome = appointmentOutcomeSort.sortByPatientName(getOutcomeByDoctorName(user.getName()), 0);
        appointmentOutcomeDisplay.printOutcomes(SortedOutcome, user);
    }

    @Override
    public void viewMedicalRecordsByPatient(User user) {
        ArrayList<AppointmentOutcome> SortedOutcome = appointmentOutcomeSort.sortByDoctorName(getOutcomeByPatientName(user), 0);
        appointmentOutcomeDisplay.printOutcomes(SortedOutcome, user);
    }

    public void viewMedicalRecordsByPharmacist(User user) {
        ArrayList<AppointmentOutcome> SortedOutcome = appointmentOutcomeSort.sortByAppointmentDate(getOutcomeByPharmacist(), 0);
        appointmentOutcomeDisplay.printOutcomes(SortedOutcome, user);
    }

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

    @Override
    public void updatePrescriptionStatus(User user) {
        if (!isPharmacist(user))
            return;
        
        // appointmentOutcomeSort.sortByAppointmentDate(appointmentOutcomes, 0);
        // ArrayList<AppointmentOutcome> result = getOutcomeByPharmacist();
        ArrayList<AppointmentOutcome> result = getOutcomeByPharmacist();
        if(result.size()==0){
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

package ServiceClasses.AppointmentOutcome;

import models.User;

public interface IAppoinmentOutcomeControl {

    public AppointmentOutcomeDisplay appointmentOutcomeDisplay = new AppointmentOutcomeDisplay();

    /**
     * View all appointment outcomes based on the user role.
     * 
     * @param user the current user requesting to view outcomes
     */
    public void viewAppoinmentOutcomes(User user);

    /**
     * View medical records filtered by the doctor, displaying only relevant data
     * for doctors.
     * 
     * @param user the current user (doctor) requesting to view patient records
     */
    public void viewMedicalRecordsByDoctor(User user);

    /**
     * View personal medical records filtered by the patient.
     * 
     * @param user the current user (patient) requesting to view their own records
     */

    public void viewMedicalRecordsByPatient(User user);

    /**
     * Update the prescription status for a specific appointment outcome, based on
     * the user's actions.
     * Typically used by pharmacists or administrators.
     * 
     * @param user the current user updating the prescription status
     */
    public void updatePrescriptionStatus(User user);

}

package ServiceClasses.AppointmentOutcome;

import models.User;

public interface IAppoinmentOutcomeControl {

    public AppoinmentOutcomeControl appoinmentOutcomeControl = new AppoinmentOutcomeControl();

    public AppointmentOutcomeDisplay appointmentOutcomeDisplay = new AppointmentOutcomeDisplay();

    public void viewAppoinmentOutcomes(User user);

    public void viewMedicalRecordsByDoctor(User user);

    public void viewMedicalRecordsByPatient(User user);

    public void updatePrescriptionStatus(User user);

}

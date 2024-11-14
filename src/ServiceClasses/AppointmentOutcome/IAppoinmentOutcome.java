package ServiceClasses.AppointmentOutcome;

import ServiceClasses.Appointment.Appointment;
import models.User;

public interface IAppoinmentOutcome {

    public AppointmentOutcomeBuild appointmentOutcomeBuild = new AppointmentOutcomeBuild();

    public AppointmentOutcomeSort appointmentOutcomeSort = new AppointmentOutcomeSort();

    //add new outcome by doctor
    // public void add(User user);


    //edit existing outcome by doctor
    public void edit(User user);

    //delete outcome by admin only
    public void delete(User user);

    // create a new outcome by doctor
    public void create(User user, Appointment appointment);

}

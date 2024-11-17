package ServiceClasses.AppointmentOutcome;

import ServiceClasses.Appointment.Appointment;
import models.User;

/**
 * The {@code IAppoinmentOutcome} interface defines methods for managing appointment outcomes.
 * <p>
 * This interface provides a contract for operations related to creating, editing, and deleting
 * appointment outcomes by doctors and administrators.
 * </p>
 */
public interface IAppoinmentOutcome {

    /**
     * An instance of {@link AppointmentOutcomeBuild} to build appointment outcomes.
     */
    public AppointmentOutcomeBuild appointmentOutcomeBuild = new AppointmentOutcomeBuild();

    /**
     * An instance of {@link AppointmentOutcomeSort} to sort appointment outcomes.
     */
    public AppointmentOutcomeSort appointmentOutcomeSort = new AppointmentOutcomeSort();

    /**
     * Edits an existing appointment outcome by a doctor.
     *
     * @param user the doctor performing the edit
     */
    public void edit(User user);

    /**
     * Deletes an appointment outcome, allowed only for administrators.
     *
     * @param user the administrator performing the deletion
     */
    public void delete(User user);

    /**
     * Creates a new appointment outcome by a doctor.
     *
     * @param user the doctor creating the outcome
     * @param appointment the appointment associated with the outcome
     */
    public void create(User user, Appointment appointment);
}

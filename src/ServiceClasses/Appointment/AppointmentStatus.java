package ServiceClasses.Appointment;

/**
 * Enum representing the possible statuses of an appointment.
 * Each status is associated with an index value for internal use and easy comparison.
 */

public enum AppointmentStatus {
    /**
     * Status representing an unaccepted appointment.
     * The appointment is not yet confirmed by the doctor.
     */
    UNACCEPTED(0),
    /**
     * Status representing an accepted appointment.
     * The doctor has accepted the appointment.
     */
    ACCEPTED(1),
    /**
     * Status representing a declined appointment.
     * The doctor has declined the appointment request.
     */
    DECLINED(-1),
    /**
     * Status representing a completed appointment.
     * The appointment has been completed and the necessary follow-up or medical action has been taken.
     */
    COMPLETED(2);

    private final int index;

    // Constructor to assign index
    AppointmentStatus(int index) {
        this.index = index;
    }

    /**
     * Retrieves the index value associated with the appointment status.
     *
     * @return The integer index representing the status.
     */
    // Method to get the index
    public int index() {
        return index;
    }
}

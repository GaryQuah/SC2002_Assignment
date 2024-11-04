package ServiceClasses.Appointment;

public enum AppointmentStatus {
    UNACCEPTED(0), 
    ACCEPTED(1), 
    DECLINED(-1), 
    COMPLETED(2);

    private final int index;

    // Constructor to assign index
    AppointmentStatus(int index) {
        this.index = index;
    }

    // Method to get the index
    public int index() {
        return index;
    }
}

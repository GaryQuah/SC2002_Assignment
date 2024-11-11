package ServiceClasses.Database;

import ServiceClasses.Database.FileHandler;

import java.io.File;

public class AppointmentFileHandler extends File {
    public AppointmentFileHandler() {
        super("src/data/Appointment_List.csv");
    }
}

package view;

import models.Patient;
import models.Staff;
import models.User;

public class MenuFactory {
    public static Menu createMenu(User user) {
        if (user instanceof Patient) {
            return new PatientMenu((Patient) user);
        } else if (user instanceof Staff) {
            Staff staffUser = (Staff) user;
            switch (staffUser.getRole()) {
                case Doctor:
                    return new DoctorMenu();
                case Pharmacist:
                    return new PharmacistMenu();
                case Administrator:
                    return new AdministratorMenu();
                default:
                    throw new IllegalArgumentException("Unsupported staff role: " + staffUser.getRole());
            }
        }
        throw new IllegalArgumentException("Unsupported user type.");
    }
}

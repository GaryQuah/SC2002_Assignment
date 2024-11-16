package view;

import models.Patient;
import models.Staff;
import models.User;

/**
 * MenuFactory class responsible for creating menu instances based on the user's role
 */


public class MenuFactory {

    /**
     * Creates a specific menu instance based on the user's role
     *
     * <p>
     * This method checks the type of the provided user object and returns the appropriate menu:
     * </p>
     *
     * @param user The user object for which a menu is to be created.
     * @return A specific implementation of the Menu interface
     */
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

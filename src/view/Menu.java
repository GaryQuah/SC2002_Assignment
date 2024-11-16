package view;

import models.User;

/**
 * Interface representing a menu that can be displayed based on a user's details
 * <p>
 * Classes implementing this interface should provide a specific implementation of the
 * displayMenu method to handle specific user menu displays.
 * </p>
 */
public interface Menu {
    public void displayMenu(User user);
}

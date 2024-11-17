package models;

/**
 * The {@code IUserManagement} interface defines methods for managing user accounts.
 * <p>
 * This interface provides a contract for implementing classes to handle user-related
 * operations, such as setting a new password.
 * </p>
 */
public interface IUserManagement {

    /**
     * Sets a new password for the user.
     *
     * @param newPassword the new password to be set for the user
     */
    void setPassword(String newPassword);
}

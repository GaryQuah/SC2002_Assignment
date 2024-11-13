package models;

public interface IUserManagement {
    boolean ValidateUser(String username, String password);
    void updatePassword(String newPassword);
}


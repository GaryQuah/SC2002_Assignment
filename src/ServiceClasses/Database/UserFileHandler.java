package ServiceClasses.Database;

import models.User;

import java.util.ArrayList;

public class UserFileHandler extends FileHandler<User> {
    @Override
    public ArrayList<User> retrieveData() {
        return null;
    }

    @Override
    public void saveData() {

    }

    @Override
    public boolean checkLogin(String username, String password) {
        return false;
    }
}

package models;
import models.enums.Gender;
import models.enums.Role;

public class Pharmacist extends Staff{

    public Pharmacist(String staffId, String name, Role role, Gender gender, int age, String username, String password) {
        super(staffId, name, role, gender, age, username, password);
    }
}


package models;
import models.enums.Gender;
import models.enums.Role;

public class Pharmacist extends Staff{

    public Pharmacist(String username, String password, String staffId, String name, Role role, Gender gender, int age) {
        super(username, password, staffId, name, role, gender, age);
    }
    
}


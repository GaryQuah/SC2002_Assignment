package models;

import models.enums.Gender;
import models.enums.Role;

public class Administrator extends Staff
{
    public Administrator(String staffId, String name, Role role, Gender gender, int age, String password) {
        super(staffId, name, role, gender, age, password);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "staffId='" + getUserID() + '\'' +
                ", name='" + getName() + '\'' +
                ", role=" + getRole() +
                ", gender=" + getGender() +
                ", age=" + getAge() +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}

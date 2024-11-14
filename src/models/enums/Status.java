package models.enums;

public enum Status {
    OK, LOWSTOCK, RESTOCK, PENDING, COMPLETED;

    public String toString() {
        switch (this) { //this is role datatype
            case OK: return "OK";
            case LOWSTOCK: return "LOWSTOCK";
            case RESTOCK: return "RESTOCK";
            case PENDING: return "PENDING";
            case COMPLETED: return "COMPLETED";
            default: return null;
        }
    }

    public static Status fromString(String role) {
        role = role.toUpperCase(); //they take input string role and switch it according to the case
        switch (role.toUpperCase()) {
            case "OK": return OK;
            case "LOWSTOCK": return LOWSTOCK;
            case "RESTOCK": return RESTOCK;
            case "PENDING": return PENDING;
            case "COMPLETED": return COMPLETED;
            default: return null;
        }
    }  

}

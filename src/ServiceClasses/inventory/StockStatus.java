package ServiceClasses.inventory;

public enum StockStatus {
    OK, LOWSTOCK, RESTOCK;

    public String toString() {
        switch (this) { //this is role datatype
            case OK: return "OK";
            case LOWSTOCK: return "LOWSTOCK";
            case RESTOCK: return "RESTOCK";
            default: return null;
        }
    }

    public static StockStatus fromString(String role) {
        role = role.toUpperCase(); //they take input string role and switch it according to the case
        switch (role.toUpperCase()) {
            case "OK": return OK;
            case "LOWSTOCK": return LOWSTOCK;
            case "RESTOCK": return RESTOCK;
            default: return null;
        }
    }  

}

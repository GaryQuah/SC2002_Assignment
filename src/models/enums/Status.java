package models.enums;

/**
 * Enum representing different statuses for items or processes in the system.
 *
 * This enum includes various statuses such as OK, LOWSTOCK, RESTOCK, PENDING, and DISPENSED.
 * It also provides utility methods for converting between string representations and enum constants.
 */
public enum Status {

    /** Status indicating the item is in an acceptable condition. */
    OK,

    /** Status indicating the item is running low on stock. */
    LOWSTOCK,

    /** Status indicating the item needs to be restocked. */
    RESTOCK,

    /** Status indicating the item is pending processing or action. */
    PENDING,

    /** Status indicating the item has been dispensed. */
    DISPENSED;

    /**
     * Converts the current Status enum to its string representation.
     *
     * @return the string representation of the current status.
     */
    @Override
    public String toString() {
        switch (this) {
            case OK: return "OK";
            case LOWSTOCK: return "LOWSTOCK";
            case RESTOCK: return "RESTOCK";
            case PENDING: return "PENDING";
            case DISPENSED: return "DISPENSED";
            default: return null;
        }
    }

    /**
     * Converts a string to its corresponding Status enum constant.
     *
     * @param role the string representation of the status.
     * @return the corresponding Status enum constant, or null if no match is found.
     */
    public static Status fromString(String role) {
        role = role.toUpperCase(); // Converts the input string to uppercase to ensure case-insensitive matching
        switch (role) {
            case "OK": return OK;
            case "LOWSTOCK": return LOWSTOCK;
            case "RESTOCK": return RESTOCK;
            case "PENDING": return PENDING;
            case "DISPENSED": return DISPENSED;
            default: return null;
        }
    }
}

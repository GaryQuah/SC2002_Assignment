package models.enums;

/**
 * Enum representing different blood types.
 * Each enum value corresponds to a specific blood type with a symbol.
 *
 * This enum also provides functionality to retrieve the symbol for each blood type
 * and to return a string representation of the blood type.
 */
public enum BloodType {

    /** Blood type A positive. */
    A_POSITIVE("A+"),

    /** Blood type A negative. */
    A_NEGATIVE("A-"),

    /** Blood type B positive. */
    B_POSITIVE("B+"),

    /** Blood type B negative. */
    B_NEGATIVE("B-"),

    /** Blood type AB positive. */
    AB_POSITIVE("AB+"),

    /** Blood type AB negative. */
    AB_NEGATIVE("AB-"),

    /** Blood type O positive. */
    O_POSITIVE("O+"),

    /** Blood type O negative. */
    O_NEGATIVE("O-"),

    /** Unknown blood type. */
    UNKNOWN("Unknown"); // Added UNKNOWN value

    /** The symbol representing the blood type. */
    private final String symbol;

    /**
     * Constructs a BloodType enum with the provided symbol.
     *
     * @param symbol the symbol representing the blood type.
     */
    BloodType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Retrieves the symbol of the blood type.
     *
     * @return the symbol of the blood type as a String.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns a string representation of the blood type.
     *
     * @return a string representing the name of the blood type in the format
     *         "A_POSITIVE", "A_NEGATIVE", etc.
     */
    @Override
    public String toString() {
        switch (this) {
            case A_POSITIVE: return "A_POSITIVE";
            case A_NEGATIVE: return "A_NEGATIVE";
            case B_POSITIVE: return "B_POSITIVE";
            case B_NEGATIVE: return "B_NEGATIVE";
            case AB_POSITIVE: return "AB_POSITIVE";
            case AB_NEGATIVE: return "AB_NEGATIVE";
            case O_POSITIVE: return "O_POSITIVE";
            case O_NEGATIVE: return "O_NEGATIVE";
            case UNKNOWN: return "UNKNOWN"; // Handle UNKNOWN
            default: return null;
        }
    }
}

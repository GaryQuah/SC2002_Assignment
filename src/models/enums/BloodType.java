package models.enums;

public enum BloodType {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");

    private final String symbol;

    BloodType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;

    }

    @Override
    public String toString() {
        switch (this) {
            case A_POSITIVE: return "A_POSITIVE";
            case A_NEGATIVE: return "A_NEGATIVE";
            case B_POSITIVE: return "B_POSITIVE";
            case B_NEGATIVE: return "B_NEGATIVE";
            case AB_POSITIVE: return "AB_POSITIVE";
            case AB_NEGATIVE: return "AB_NEGATIVE";
            case O_POSITIVE: return "0_POSITIVE";
            case O_NEGATIVE: return "O_NEGATIVE";

            default: return null;
        }
    }
}
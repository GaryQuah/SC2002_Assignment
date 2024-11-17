package input;

import java.util.HashMap;

/**
 * The {@code HashParse} class provides methods for converting between
 * a {@link HashMap} and its string representation, making it easier
 * to serialize and deserialize key-value pairs where keys are
 * {@code String} and values are {@code Integer}.
 */
public class HashParse {

    /**
     * Converts a {@link HashMap} of {@code String} to {@code Integer}
     * into a string format.
     * <p>
     * The resulting string consists of key-value pairs separated by
     * hyphens (e.g., {@code "key1:value1-key2:value2"}). If the
     * input map is empty, this method returns a single dash ("-").
     * </p>
     *
     * @param data the {@link HashMap} to convert
     * @return a string representation of the map, or "-" if empty
     */
    public static String toString(HashMap<String, Integer> data) {
        if (data.isEmpty()) {
            return "-";
        }
        StringBuilder result = new StringBuilder();
        for (HashMap.Entry<String, Integer> entry : data.entrySet()) {
            result.append(entry.getKey()).append(":").append(entry.getValue()).append("-");
        }
        return result.substring(0, result.length() - 1); // Remove the trailing '-'
    }

    /**
     * Parses a string representation of key-value pairs into a
     * {@link HashMap} of {@code String} to {@code Integer}.
     * <p>
     * The input string should follow the format with pairs separated by
     * hyphens and each key-value pair separated by a colon (e.g.,
     * {@code "key1:value1-key2:value2"}). If the input is empty or
     * equals "-", an empty map is returned.
     * </p>
     *
     * @param data the string representation of the key-value pairs
     * @return a {@link HashMap} containing the parsed key-value pairs
     * @throws NumberFormatException if any value cannot be converted to an {@code Integer}
     */
    public static HashMap<String, Integer> fromString(String data) {
        HashMap<String, Integer> result = new HashMap<>();
        if (data.isEmpty() || data.equals("-")) {
            return result; // Return empty if data is empty
        }
        String[] pairs = data.split("-");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":"); // Split key and value using ':'
            if (keyValue.length == 2) { // Ensure there are exactly two elements
                String itemID = keyValue[0];
                int quantity = Integer.parseInt(keyValue[1]); // Parse the quantity as an integer
                result.put(itemID, quantity);
            }
        }
        return result;
    }
}

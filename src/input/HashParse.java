package input;

import java.util.HashMap;

public class HashParse {

    public static String toString(HashMap<String, Integer> data) {
        if (data.isEmpty())
            return "-";
        StringBuilder result = new StringBuilder();
        for (HashMap.Entry<String, Integer> entry : data.entrySet()) {
            result.append(entry.getKey()).append(":").append(entry.getValue()).append("-"); // Using ':' to separate
                                                                                            // key-value pairs
        }
        return result.substring(0, result.length() - 1); // Remove the trailing '-'
    }

    public static HashMap<String, Integer> fromString(String data) {
        HashMap<String, Integer> result = new HashMap<>();
        if (data.isEmpty() || data.equals("-"))
            return result; // Return empty if data is empty
        String[] pairs = data.split("-");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":"); // Split key and value using ':'
            if (keyValue.length == 2) { // Ensure that there are exactly two elements
                String itemID = keyValue[0];
                int quantity = Integer.parseInt(keyValue[1]); // Parse the quantity as an integer
                result.put(itemID, quantity);
            }
        }
        return result;
    }

}

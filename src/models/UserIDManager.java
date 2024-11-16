package models;

import java.util.HashMap;
import java.util.Map;
import models.enums.Role;

/**
 * Singleton class responsible for managing user IDs.
 *
 * This class ensures that unique user IDs are generated for each role (Patient, Doctor, Pharmacist, Administrator).
 * It maintains a map of role prefixes (e.g., "PT" for Patient, "D" for Doctor) and tracks the maximum generated ID
 * for each role to ensure uniqueness and correct sequencing.
 */
public class UserIDManager {

    /** The singleton instance of the UserIDManager. */
    private static UserIDManager instance;

    /** Map that stores the maximum generated ID for each role. */
    private Map<Role, Integer> maxIDs;

    /** Map that stores the prefix for each role. */
    private Map<Role, String> rolePrefixMap;

    /**
     * Private constructor to initialize the UserIDManager.
     * Initializes the maps for role prefixes and max IDs.
     */
    private UserIDManager() {
        maxIDs = new HashMap<>();
        rolePrefixMap = new HashMap<>();

        // Define role prefixes
        rolePrefixMap.put(Role.Patient, "PT");
        rolePrefixMap.put(Role.Pharmacist, "PH");
        rolePrefixMap.put(Role.Doctor, "D");
        rolePrefixMap.put(Role.Administrator, "A");

        // Initialize max IDs for each role
        for (Role role : rolePrefixMap.keySet()) {
            maxIDs.put(role, 0);
        }
    }

    /**
     * Returns the singleton instance of UserIDManager.
     *
     * @return the singleton instance of UserIDManager.
     */
    public static UserIDManager getInstance() {
        if (instance == null) {
            instance = new UserIDManager();
        }
        return instance;
    }

    /**
     * Generates a unique user ID for the specified role.
     *
     * The ID consists of a role-specific prefix followed by a 4-digit number
     * that increments with each new ID generated for that role.
     *
     * @param role the role for which the ID is being generated.
     * @return a unique user ID string for the specified role.
     * @throws IllegalArgumentException if the role is invalid.
     */
    public synchronized String generateUniqueID(Role role) {
        String rolePrefix = rolePrefixMap.get(role);
        if (rolePrefix == null) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }

        // Ensure the role exists in maxIDs
        maxIDs.putIfAbsent(role, 0);

        // Increment the ID and store it for the specific role.
        int newID = maxIDs.get(role) + 1;

        maxIDs.put(role, newID);

        // Generate the formatted ID string with 4 digits
        return rolePrefix + String.format("%04d", newID);
    }

    /**
     * Updates the maximum ID for the specified role.
     *
     * This method allows updating the max ID for a given role if the provided value is greater than the current max ID.
     *
     * @param role the role for which the max ID needs to be updated.
     * @param Value the new value for the max ID of the given role.
     * @throws IllegalArgumentException if the role does not exist.
     */
    public synchronized void updateID(Role role, int Value) {
        // Check if the role exists in the maxIDs map
        if (!maxIDs.containsKey(role)) {
            throw new IllegalArgumentException("Role not found: " + role);
        }

        // Get the current max ID for the given role
        int currentMaxID = maxIDs.get(role);

        // Update the max ID only if the new value is greater
        if (Value >= currentMaxID) {
            maxIDs.put(role, Value);
        }
    }
}

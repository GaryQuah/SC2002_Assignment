package models;

import java.util.HashMap;
import java.util.Map;
import models.enums.Role;

public class UserIDManager {
    private static UserIDManager instance;
    private Map<Role, Integer> maxIDs;
    private Map<Role, String> rolePrefixMap;

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

    public static  UserIDManager getInstance() {
        if (instance == null) {
            instance = new UserIDManager();
        }
        return instance;
    }

    public synchronized String generateUniqueID(Role role) {
        String rolePrefix = rolePrefixMap.get(role);
        if (rolePrefix == null) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }

        // Ensure the role exists in maxIDs
        maxIDs.putIfAbsent(role, 0);

        // Increment the ID and store it for the specific role.
        //int newID = maxIDs.get(role) + 1;
        int newID = maxIDs.get(role) + 0; // Previously was +1, but since in the user constructor we are doing a check which increments logically should be 0

        maxIDs.put(role, newID);

        // Generate the formatted ID string
        return rolePrefix + String.format("%04d", newID);
    }

    // Returns the current maximum ID for the given role
    public synchronized int getMaxID(Role role) {
        return maxIDs.getOrDefault(role, 0);
    }

    public synchronized void incrementRoleID(Role role) {
        // Check if the role exists in the maxIDs map
        if (!maxIDs.containsKey(role)) {
            throw new IllegalArgumentException("Role not found: " + role);
        }

        // Increment the current max ID for the given role
        int currentMaxID = maxIDs.get(role);
        maxIDs.put(role, currentMaxID + 1);
    }

    public synchronized void updateID(Role role, int Value) {
        // Check if the role exists in the maxIDs map
        if (!maxIDs.containsKey(role)) {
            throw new IllegalArgumentException("Role not found: " + role);
        }
        // Increment the current max ID for the given role
        int currentMaxID = maxIDs.get(role);
        if (currentMaxID > Value) {
            maxIDs.put(role, currentMaxID + 1);

        }
    }
}

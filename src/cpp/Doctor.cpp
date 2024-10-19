#include <string>
#include <vector>

#include "User.h"

class Doctor : public User {
private:
    std::vector<std::string> m_PatientNames;  // C++ equivalent of Java's Vector<String>

public:
    // Constructor that calls the User class constructor
    Doctor(const std::string& userName)
        : User(userName, Role::Doctor) {}

    // Function to add a patient name
    void addPatient(const std::string& patientName) {
        m_PatientNames.push_back(patientName);  // Use push_back in C++ for adding elements
    }

    // Function to remove a patient name
    void removePatient(const std::string& patientName) {
        auto it = std::find(m_PatientNames.begin(), m_PatientNames.end(), patientName);
        if (it != m_PatientNames.end()) {
            m_PatientNames.erase(it);  // Remove element from vector if found
        }
    }
};

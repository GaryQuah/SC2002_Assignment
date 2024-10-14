#include "User.h"

enum class Role {
    Patient,
    Doctor,
    Pharmacist,
    Administrator
};

enum class Gender {
    Male, Female, Others
};

class Appointment; // Forward declaration of Appointment class

class User {
private:
    std::string m_UserName;
    std::string m_PassWord;
    Role* m_Role;  // Assuming Role is a class
    std::vector<Appointment*> m_Appointments; // Assuming Appointment is a class

public:
    // Constructor
    User(const std::string& userName, Role* role)
        : m_UserName(userName), m_Role(role), m_PassWord("password") {}

    // Users can validate their login credentials
    bool ValidateUser(const std::string& userName, const std::string& passWord) {
        return (this->m_UserName == userName && this->m_PassWord == passWord);
    }

    // Getter for the role
    Role* getRole() const {
        return this->m_Role;
    }

    // Destructor if needed (depends on how Role and Appointment are managed)
    virtual ~User() {}
};

#include <string>
#include <vector>

// Define the Role enum class
enum class Role {
    Patient,
    Doctor,
    Pharmacist,
    Administrator
};

// Forward declaration of the Appointment class
class Appointment;

class User {
private:
    std::string m_UserName;
    std::string m_PassWord;
    Role* m_Role;
    std::vector<Appointment*> m_Appointments;
public:
    // Constructor
    User(const std::string userName, Role role);

    // Validate the user's credentials
    bool ValidateUser(const std::string& userName, const std::string& passWord);

    // Getter for the role
    Role* getRole() const;

    // Virtual destructor
    virtual ~User();
};

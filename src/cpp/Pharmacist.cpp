#include <string>
#include "User.h"  // Include the User header

class Pharmacist : public User {
public:
    // Constructor that calls the User class constructor
    Pharmacist(const std::string& userName)
        : User(userName, Role::Pharmacist) {}  // Use Role::Pharmacist in C++
};

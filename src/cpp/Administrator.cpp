#include <string>
#include "User.h"

class Administrator : public User {
public:
    // Constructor that calls the User class constructor
    Administrator(const std::string& userName)
        : User(userName, Role::Administrator) {}  // Use Role::Administrator in C++ (scope resolution)
};

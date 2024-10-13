// ---- System headers here
#include <iostream>
#include <string>
#include <vector>
// ----- File headers here

//#include "Appointment.h"  // Make sure to include the appropriate header for Appointment
#include "User.h"         // Include the User header for user role management
//#include "Doctor.h"       // Include the Doctor header
//#include "Administrator.h" // Include the Administrator header
// Include other role-related headers as needed

int main() {
    std::vector<Appointment*> AppointmentManager; // Use pointers for dynamic management if needed
    std::string role;

    std::cout << "Welcome To The HMS System - Please Enter Your Role - Enter Exit To Close The System" << std::endl;

    // Get the initial role input
    std::getline(std::cin, role); // Use getline for input

    do {
        if (role == "Patient") {
            // Handle Patient role actions
        } 
        else if (role == "Doctor") {
            // Handle Doctor role actions
        } 
        else if (role == "Pharmacist") {
            // Handle Pharmacist role actions
        } 
        else if (role == "Administrator") {
            // Handle Administrator role actions
        } 
        else if (role == "Exit") {
            std::cout << "Thank You For Using The System. See You Again Soon." << std::endl;
        } 
        else {
            std::cout << "Invalid Role Entered. Please key in a valid role" << std::endl;
        }

        // Ask for the role again at the end of the loop
        std::getline(std::cin, role);
    } while (role != "Exit");

    // Clean up dynamically allocated memory if needed
    for (auto appointment : AppointmentManager) {
        delete appointment; }

    return 0;
}

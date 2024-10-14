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

using namespace std;

int main() {
    vector<Appointment*> AppointmentManager; // Use pointers for dynamic management if needed
    string role;

    cout << "Welcome To The HMS System - Please Enter Your Role (Patient | Doctor | Pharmacist | Administraotr | - Enter Exit To Close The System" << std::endl;

    // Get the initial role input
    getline(std::cin, role); // Use getline for input

    do {
        if (role == "Patient") {
            PatientMenu();
        } 
        else if (role == "Doctor") {
            DoctorMenu();
        } 
        else if (role == "Pharmacist") {
            PharmacistMenu();
        } 
        else if (role == "Administrator") {
            AdministratorMenu();
        } 
        else if (role == "Exit") {
            cout << "Thank You For Using The System. See You Again Soon." << endl;
        } 
        else {
            cout << "Invalid Role Entered. Please key in a valid role" << endl;
        }
    } while (role != "Exit");

    // Clean up dynamically allocated memory if needed
    for (auto appointment : AppointmentManager) {
        delete appointment; }

    return 0;
}

void PatientMenu()
{
    int userOption = -1;
    
    while (userOption == -1)
    {
        cout << "● Option 1 : View Medical Record" << endl;
        cout << "● Option 2 : View Available Appointment Slots" << endl;
        cout << "● Option 3 : Schedule an Appointment" << endl;
        cout << "● Option 4 : Reschedule an Appointment" << endl;
        cout << "● Option 5 : Cancel an Appointment" << endl;
        cout << "● Option 6 : View Medical Record" << endl;
        cout << "● Option 7 : View Scheduled Appointments" << endl;
        cout << "● Option 8 : View Past Appointment Outcome Records" << endl;
        cout << "● Option 9 : Logout" << endl;

        switch (userOption)
        {
            case 1:
                cout << "Selected : View Medical Record" << endl;
                break;
            case 2:
                cout << "Selected : View Available Appointment Slots" << endl;
                break;
            case 3:
                cout << "Selected : Schedule an Appointment" << endl;
                break;
            case 4:
                cout << "Selected : Reschedule an Appointment" << endl;
                break;
            case 5:
                cout << "Selected : Cancel an Appointment" << endl;
                break;
            case 6:
                cout << "Selected : View Medical Record" << endl;
                break;
            case 7:
                cout << "Selected : View Scheduled Appointments" << endl;
                break;
            case 8:
                cout << "Selected : View Past Appointment Outcome Records" << endl;
                break;
            case 9:
                cout << "Selected : Logout" << endl;
                break;
            default:
                userOption = -1;
                cout << "INVALID OPTION SELECTED! KEY IN AN APPROPRIATE OPTION" << endl;
                break;
        }
    }
}

void DoctorMenu()
{
    int userOption = -1;
    
    while (userOption == -1)
    {
        cout << "Welcome To The Doctor Menu" << endl;

        cout << "● Option 1 : View Patient Medical Records" << endl;
        cout << "● Option 2 : Update Patient Medical Records" << endl;
        cout << "● Option 3 : View Personal Schedule" << endl;
        cout << "● Option 4 : Set Availability for Appointments" << endl;
        cout << "● Option 5 : Accept or Decline Appointment Requests" << endl;
        cout << "● Option 6 : View Upcoming Appointments" << endl;
        cout << "● Option 7 : Record Appointment Outcome" << endl;
        cout << "● Option 8 : Logout " << endl;

        switch (userOption)
        {
            case 1:
                cout << "Selected : View Patient Medical Records" << endl;
                break;
            case 2:
                cout << "Selected : Update Patient Medical Records" << endl;
                break;
            case 3:
                cout << "Selected : View Personal Schedule" << endl;
                break;
            case 4:
                cout << "Selected : Set Availability for Appointments" << endl;
                break;
            case 5:
                cout << "Selected : Accept or Decline Appointment Requests" << endl;
                break;
            case 6:
                cout << "Selected : View Upcoming Appointments" << endl;
                break;
            case 7:
                cout << "Selected : Record Appointment Outcome" << endl;
                break;
            case 8:
                cout << "Selected : Logout" << endl;
                break;
            default:
                userOption = -1;
                cout << "INVALID OPTION SELECTED! KEY IN AN APPROPRIATE OPTION" << endl;
                break;
        }
    }
}

void PharmacistMenu()
{
    int userOption = -1;
    
    while (userOption == -1)
    {
        cout << "Welcome To The Pharmacist Menu" << endl;

        cout << "● Option 1 : View Appointment Outcome Record" << endl;
        cout << "● Option 2 : Update Prescription Status" << endl;
        cout << "● Option 3 : View Medication Inventory" << endl;
        cout << "● Option 4 : Submit Replenishment Request" << endl;
        cout << "● Option 5 : Logout " << endl;

        switch (userOption)
        {
            case 1:
                cout << "Selected : View Appointment Outcome Record" << endl;
                break;
            case 2:
                cout << "Selected : Update Prescription Status" << endl;
                break;
            case 3:
                cout << "Selected : View Medication Inventory" << endl;
                break;
            case 4:
                cout << "Selected : Submit Replenishment Request" << endl;
                break;
            case 5:
                cout << "Selected : Logout" << endl;
                break;
            default:
                userOption = -1;
                cout << "INVALID OPTION SELECTED! KEY IN AN APPROPRIATE OPTION" << endl;
                break;
        }
    }
}

void AdministratorMenu()
{
    int userOption = -1;
    
    while (userOption == -1)
    {
        cout << "Welcome To The Administrator Menu" << endl;

        cout << "● Option 1 : View and Manage Hospital Staff" << endl;
        cout << "● Option 2 : View Appointments details" << endl;
        cout << "● Option 3 : View and Manage Medication Inventory" << endl;
        cout << "● Option 4 : Approve Replenishment Requests" << endl;
        cout << "● Option 5 : Logout " << endl;

        switch (userOption)
        {
            case 1:
                cout << "Selected : View and Manage Hospital Staff" << endl;
                break;
            case 2:
                cout << "Selected : View Appointments details" << endl;
                break;
            case 3:
                cout << "Selected : View and Manage Medication Inventory" << endl;
                break;
            case 4:
                cout << "Selected : Approve Replenishment Requests" << endl;
                break;
            case 5:
                cout << "Selected : Logout" << endl;
                break;
            default:
                userOption = -1;
                cout << "INVALID OPTION SELECTED! KEY IN AN APPROPRIATE OPTION" << endl;
                break;
        }
    }
}
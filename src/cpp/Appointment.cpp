#include <string>

class Appointment {
private:
    std::string m_DoctorName;
    std::string m_AppointmentDate;
    std::string m_TimeSlot;
    bool m_Accepted;

public:
    // Constructor
    Appointment(const std::string& doctorName, const std::string& appointmentDate, const std::string& timeSlot)
        : m_DoctorName(doctorName), m_AppointmentDate(appointmentDate), m_TimeSlot(timeSlot), m_Accepted(false) {}

    // Additional methods can be added if needed
};

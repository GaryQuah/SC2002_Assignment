public class Patient extends User{
    //------------------ Variables -----------------------------
    private String m_PatientID;
    private String m_Name;
    private String m_DateOfBirth;
    private Gender m_Gender;
    private String m_EmailAddress;
    private int m_PhoneNumber;
    private String m_BloodType;

    //------------------- Functions ---------------------------

    //Creates a "Patient" after passing the user's username. default password is "password", default role is "Patient"
    public Patient(String m_UserName) {
        super(m_UserName, Role.Patient); // Calls the constructor of the User class
    }
   
    //To Add Past Diagnoses and treatments when its clearer in the document what it will e
    //m_Diagnoses
    //m_Treatments

    //Patients can update non-medical personal information such as email address and contact number. 
    public void UpdateEmail(String m_EmailString)
    {
        this.m_EmailAddress= m_EmailString;
    }

    public void UpdatePhoneNumber(int m_PhoneNumber)
    {
        this.m_PhoneNumber = m_PhoneNumber;
    }

    //Patients are not allowed to modify the past diagnoses, prescribed medications, treatments or blood type.. 
}

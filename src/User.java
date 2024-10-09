public abstract class User{
    //Users must log in to the system using their unique hospital ID and a default password ("password"). 
    public String m_UserName;
    public String m_PassWord;

    //Users will have roles such as Patient, Doctor, Pharmacist or Administrator.
    public Role m_Role;

    //Users can change their password after their initial login.
    public User(String m_UserName, Role m_Role){
        this.m_UserName = m_UserName;
        this.m_Role = m_Role;
        this.m_PassWord = "password";
    }

    public boolean ValidateUser(String m_UserName, String m_PassWord)
    {
        if (this.m_UserName == m_UserName && this.m_PassWord == m_PassWord)
        {
            return true;
        }
        else return false;
    }
}
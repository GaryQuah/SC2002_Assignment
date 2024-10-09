public class Administrator extends User{
    public Administrator(String m_UserName) {
        super(m_UserName, Role.Administrator); // Calls the constructor of the User class
    }
}

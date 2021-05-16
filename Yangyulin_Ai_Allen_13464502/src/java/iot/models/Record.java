package iot.models;

/**
 *
 * @author Think
 */
public class Record
{
    private String email;
    private String login;
    private String logout;

    public Record(String email, String login)
    {
        this.email = email;
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }
    
    
    
    
}



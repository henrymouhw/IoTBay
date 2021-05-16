package iot.controller;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Think
 */
public class Validator implements Serializable
{
    private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9+]))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";
    private String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
    private String passwordPattern = "[a-z0-9]{4,}";
    
    public Validator(){}
    
    public boolean validate(String pattern, String input)
    {
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);
        return match.matches();
    }
    
    public boolean validateEmail(String email)
    {
        return validate(emailPattern, email);
    }
    
    public boolean validateName(String name)
    {
        return validate(namePattern, name);
    }
    
    public boolean validatePassword(String password)
    {
        return validate(passwordPattern, password);
    }
    
    public void clear(HttpSession session)
    {
        session.setAttribute("emailErr","Enter email");
       session.setAttribute("passErr", "Enter password");
       session.setAttribute("existErr", " ");
       session.setAttribute("nameErr", "Enter Name");
       session.setAttribute("success"," ");
       session.setAttribute("outcomelErr"," ");
       session.setAttribute("updated","");
    }
    
}

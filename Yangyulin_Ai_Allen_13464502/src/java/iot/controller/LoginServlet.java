package iot.controller;

import java.io.IOException;
import java.sql.SQLException;

//import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import iot.models.dao.UserManager;
import iot.models.dao.RecordManager;
import iot.models.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
/**
/**
 *
 * @author Think
 */
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserManager user_manager= (UserManager)session.getAttribute("user_manager");
        RecordManager record_manager= (RecordManager)session.getAttribute("record_manager");
        Date reDate = new Date(System.currentTimeMillis());
        String login = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(reDate);
        
        
        validator.clear(session);
        
        if(!validator.validateEmail(email))
        {
            session.setAttribute("emailErr", "Error: Email format incorrect");
            request.getRequestDispatcher("Login.jsp").include(request, response);
        }
        else if(!validator.validatePassword(password))
        {
            session.setAttribute("passErr", "Error: Password format incorrect");
            request.getRequestDispatcher("Login.jsp").include(request, response);
        }
        else
        {
            try{
                User user = user_manager.findUser(email);
                if(user==null){
                    session.setAttribute("existErr", "User does not exist in the Database!");
                    request.getRequestDispatcher("Login.jsp").include(request, response);
                } else if(!user_manager.checkUser(email,password)){
                     session.setAttribute("passErr", "Password is not match!");
                    request.getRequestDispatcher("Login.jsp").include(request, response);
                } else if(user.getStatus().equals("active"))
                {
                    if(user.getUserType().equals("Customer"))
                    {
                        Customer customer = user_manager.findCustomer(email);
                        session.setAttribute("user", customer);
                    }
                    else
                    {
                        Staff staff = user_manager.findStaff(email);
                        session.setAttribute("user", staff);
                    }
                    record_manager.addRecord(email, login);
                    Record record = new Record(email, login);
                    session.setAttribute("currentRecord", record);
                    session.setAttribute("logEmail", email);
                    request.getRequestDispatcher("Main.jsp").include(request, response);
                }
                else
                {
                   session.setAttribute("existErr", "Your account is deactivate!");
                    request.getRequestDispatcher("Login.jsp").include(request, response);
                }
            }catch (SQLException | NullPointerException ex){
                System.out.println(ex.getMessage() == null ? "User does not exist" : "welcome");
            }
        }
        
        
    }
    
}

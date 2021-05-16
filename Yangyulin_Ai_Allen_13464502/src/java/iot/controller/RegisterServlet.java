package iot.controller;

import java.io.IOException;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

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
 *
 * @author Think
 */
public class RegisterServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String name = request.getParameter("name");
        String email = request.getParameter("email");      
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String type = request.getParameter("type"); 
        String occupation = "";
        String phone = request.getParameter("phone");
        String page = "CRF.jsp";
        UserManager user_manager = (UserManager)session.getAttribute("user_manager");
        RecordManager record_manager = (RecordManager)session.getAttribute("record_manager");
        Date reDate = new Date(System.currentTimeMillis());
        String login = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(reDate);
        
        validator.clear(session);
             
        if(type.equals("Customer"))
        {
            page = "CRF.jsp"; 
           
        }
        else
        {
            page = "SRF.jsp";
            occupation = request.getParameter("occupation");
        }
        
        if(!validator.validateEmail(email))
        {
            session.setAttribute("emailErr", "Error: Email format incorrect");
            request.getRequestDispatcher(page).include(request, response);
        }
        else if(!validator.validateName(name))
        {
            session.setAttribute("nameErr", "Error: Name format incorrect");
            request.getRequestDispatcher(page).include(request, response);
        }
        else if(!validator.validatePassword(password))
        {
            session.setAttribute("passErr", "Error: Password format incorrect");
            request.getRequestDispatcher(page).include(request, response);
        }
        else
        {          
            try{
             
                User exist = user_manager.findUser(email);
                if(exist != null)
                {
                    session.setAttribute("existErr","Student already exists in the Database!");
                    request.getRequestDispatcher(page).include(request, response);
                }
                else
                {
                    user_manager.addUser(email, password, type, name, gender, dob,phone);
                    
                    if(type.equals("Customer"))
                    {
                        Customer customer = new Customer(email, password, type, name, gender, dob,"active",phone);
                        session.setAttribute("user", customer);  
                    }
                    else if(type.equals("Staff"))
                    {
                        Staff staff = new Staff(email, password, type, name, gender, dob,"active",phone);
                        if(occupation != null)
                        {
                            user_manager.updateOccupation(email, occupation);
                            staff.setOccupation(occupation);
                        }
                        session.setAttribute("user", staff);  
                    }
                    else
                    {
                        User user = new User(email, password, type, name, gender, dob,"active",phone);
                        session.setAttribute("user", user);                
                    }            
                    record_manager.addRecord(email, login);
                    Record record = new Record(email, login);
                    session.setAttribute("currentRecord", record);
                    session.setAttribute("record", record);
                    session.setAttribute("logEmail", email);
                    request.getRequestDispatcher("PersonalInfo.jsp").include(request, response);
                }
            } catch(SQLException ex){
               Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
    }
    
}
 
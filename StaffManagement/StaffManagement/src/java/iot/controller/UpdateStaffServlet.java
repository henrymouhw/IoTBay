package iot.controller;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import iot.models.dao.*; 
import iot.models.StaffRecord;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateStaffServlet extends HttpServlet{

       @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
           HttpSession session = request.getSession();
           String name = request.getParameter("name");
           String email = request.getParameter("email");
           String position = request.getParameter("position");
           String address = request.getParameter("address");
           String status = request.getParameter("status");
           StaffRecord staff = new StaffRecord(name, email, position, address, status);
           StaffDBManager sManager = (StaffDBManager) session.getAttribute("sManager");
           Validator validator = new Validator();
           validator.clear(session);
           
              if(!validator.validateEmail(email))
        {
            session.setAttribute("emailErr", "Error: Email format incorrect");
            request.getRequestDispatcher("editStaff.jsp").include(request, response);
        }
        
        else if(!validator.validateName(name))
        {
            session.setAttribute("nameErr", "Error: Name format incorrect");
            request.getRequestDispatcher("editStaff.jsp").include(request, response);
        }
        else {
           try{
              if(staff != null) {
                  session.setAttribute("staff1", staff);
                  sManager.updateStaff(name, email, position, address, status);
                  session.setAttribute("updated", "Update was successful");
                  request.getRequestDispatcher("editStaff.jsp").include(request, response);
              } else{
                  session.setAttribute("updated", "Update was not successful!");
                  request.getRequestDispatcher("editStaff.jsp").include(request, response);
             }
           } catch (SQLException ex) {
              Logger.getLogger(UpdateStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
          response.sendRedirect("editStaff.jsp");
       }
     }

}
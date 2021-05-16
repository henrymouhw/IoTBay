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
import iot.models.dao.StaffDBManager;
import iot.models.StaffRecord;

public class AddStaffServlet extends HttpServlet {
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();
       Validator validator = new Validator();
       String name = request.getParameter("name");
       String email = request.getParameter("email");
       String position = request.getParameter("position");
       String address = request.getParameter("address");
       String status = request.getParameter("status");
       StaffDBManager sManager = (StaffDBManager) session.getAttribute("sManager");
       validator.clear(session);

       if(!validator.validateEmail(email)){
          session.setAttribute("emailErr", "Error: Email format is incorrect");
          request.getRequestDispatcher("addStaff.jsp").include(request, response);
       } else if (!validator.validateName(name)){
          session.setAttribute("nameErr", "Error: Name format is incorrect");
          request.getRequestDispatcher("addStaff.jsp").include(request, response);
       }  else {
           try{
            StaffRecord exist = sManager.findStaffbyEmail(email);
            if(exist != null) {
                 session.setAttribute("existErr", "Staff Record already exists in the Database!");
                 request.getRequestDispatcher("addStaff.jsp").include(request, response);
            } else {
                 sManager.addStaff(name, email, position, address, status);
                 StaffRecord staff = new StaffRecord(name, email, position, address, status);
                 session.setAttribute("staff", staff);
                 request.getRequestDispatcher("staffInfoManagement.jsp").include(request, response);
            }
        } catch (SQLException ex){
            Logger.getLogger(AddStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       }      
   }
}
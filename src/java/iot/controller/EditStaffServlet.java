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

public class EditStaffServlet extends HttpServlet{

      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            StaffDBManager sManager = (StaffDBManager) session.getAttribute("sManager");
            Validator validator = new Validator();
            validator.clear(session);

            StaffRecord staff = null;
            
                
            try{
                   staff = sManager.findStaffbyEmail(email);
                   if(staff!=null){
                   session.setAttribute("staff1", staff);
                   request.getRequestDispatcher("editStaff.jsp").include(request, response);
                   }
            } catch (SQLException ex) {
                  Logger.getLogger(EditStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
                  System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
            }
            response.sendRedirect("editStaff.jsp");
      
     }

}
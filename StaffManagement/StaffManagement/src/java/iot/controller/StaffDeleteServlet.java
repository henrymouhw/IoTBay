package iot.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import iot.models.dao.*; 
import iot.models.StaffRecord;
import java.util.ArrayList;




public class StaffDeleteServlet extends HttpServlet {
       @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           
           HttpSession session = request.getSession();
           StaffDBManager sManager = (StaffDBManager)session.getAttribute("sManager");
           String email = request.getParameter("email");

            try{
              sManager.deleteStaff(email);
              ArrayList<StaffRecord> s = sManager.fetchStaff();
              session.setAttribute("Staffs", s);
              response.sendRedirect("StaffDetails.jsp");
        } catch (SQLException ex) {
                
               
        }

   }
}
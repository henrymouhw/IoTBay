package iot.controller;

import javax.servlet.RequestDispatcher;
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


   public class StaffDetailsServlet extends HttpServlet {
       @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           
           HttpSession session = request.getSession();
           StaffDBManager sManager = (StaffDBManager)session.getAttribute("sManager");
           
           try{
               ArrayList<StaffRecord> s = sManager.fetchStaff();
               session.setAttribute("Staffs", s);
               request.getRequestDispatcher("StaffDetails.jsp").include(request, response);
           } catch (SQLException | NullPointerException ex) {
               
           
       }

   }
}
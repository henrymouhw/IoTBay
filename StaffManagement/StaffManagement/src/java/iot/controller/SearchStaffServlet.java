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
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchStaffServlet extends HttpServlet{
      @Override
      public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         HttpSession session = request.getSession();
         String name = request.getParameter("name");
         String position = request.getParameter("position");
         StaffDBManager sManager = (StaffDBManager) session.getAttribute("sManager");
         ArrayList<StaffRecord> staffs = new ArrayList<StaffRecord>();
         
         try{
             if(name != null){
                 staffs = sManager.fetchStaffbyName(name);
             } else {
                 staffs = sManager.fetchStaffbyPosition(position);
             }
                 session.setAttribute("Staffs", staffs);
                 request.getRequestDispatcher("StaffDetails.jsp").include(request, response);
         } catch (SQLException | NullPointerException ex) {
                 Logger.getLogger(SearchStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
      }
}
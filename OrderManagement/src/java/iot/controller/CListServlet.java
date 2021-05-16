/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

     import iot.models.CustRecord;

     import iot.models.dao.DBCustRManager;
import java.util.*;
/**
 *
 * @author lin
 */
public class CListServlet extends HttpServlet {
 @Override   
 protected void doGet(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException { 
     HttpSession session = request.getSession();
     DBCustRManager manager = (DBCustRManager) session.getAttribute("crManager");
     try{
     LinkedList<CustRecord> c = manager.fetchCustRecords();
     session.setAttribute("CustRecords", c);
     request.getRequestDispatcher("CustomerList.jsp").include(request,response);
     } catch(SQLException | NullPointerException ex){
          Logger.getLogger(CListServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
     
 }
}

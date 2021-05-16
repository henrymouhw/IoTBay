/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.models.Payment;
import iot.models.dao.PaymentManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lin
 */
public class EditPaymentServlet extends HttpServlet {
     @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
      
      HttpSession session = request.getSession();
      String id = request.getParameter("payment_id");
      Integer payment_id = Integer.valueOf((id == null || "".equals(id))?"0" : id);
      PaymentManager manager = (PaymentManager) session.getAttribute("pManager"); 
      try{
          Payment p = manager.findPayment(Integer.parseInt(id));
          session.setAttribute("Payment", p);
         request.getRequestDispatcher("updatePayment.jsp").include(request,response);
      }catch(SQLException | NullPointerException ex){
              Logger.getLogger(EditPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
             
           }
      
  
      
      }

}

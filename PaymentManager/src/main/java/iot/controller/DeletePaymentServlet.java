/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import java.sql.SQLException;

     import java.util.logging.Level;

     import java.util.logging.Logger;

     import javax.servlet.ServletException;

     import javax.servlet.http.HttpServlet;

     import javax.servlet.http.HttpServletRequest;

     import javax.servlet.http.HttpServletResponse;

     import javax.servlet.http.HttpSession;
     import iot.models.Payment;
     import iot.models.dao.PaymentManager;
     import java.io.IOException;
     import java.util.*;

/**
 *
 * @author 74152
 */


public class DeletePaymentServlet extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
     
            HttpSession session = request.getSession();
            String id = request.getParameter("payment_id");
             String email =(String) session.getAttribute("logEmail");
            PaymentManager manager = (PaymentManager) session.getAttribute("pManager");
            try {
                manager.deletePayment(Integer.parseInt(id));
                LinkedList<Payment> details = new LinkedList<Payment>();
                
                details= manager.fetchPayments(email);
                session.setAttribute("Payments",details);
                  request.getRequestDispatcher("paymentList.jsp").include(request,response);
            }catch(SQLException | NullPointerException ex){
                Logger.getLogger(DeletePaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
      }
   }



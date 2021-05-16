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
 * @author lin
 */
public class UpdatePaymentServlet extends HttpServlet {

     @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException { 
      HttpSession session = request.getSession();
      //Validator validator = new Validator();
       String id = request.getParameter("payment_id");
       String paymethod = request.getParameter("paymethod");
       String cardnumber = request.getParameter("cardnumber");
       String expirydate = request.getParameter("expirydate");
       String cvv = request.getParameter("cvv");
       String fullname = request.getParameter("fullname");
       Validator v = new Validator();
       v.clear(session);
       PaymentManager manager = (PaymentManager) session.getAttribute("pManager");
          try {
              int payment_id = Integer.parseInt(id);
                 manager.updatePayment(payment_id,  paymethod,  cardnumber,  expirydate,  cvv, fullname);
                  Payment pa = manager.findPayment(payment_id);
                     session.setAttribute("Payment", pa);
                  session.setAttribute("updated", "update was sucessful");
                  request.getRequestDispatcher("updatePayment.jsp").include(request,response);
          }catch(SQLException | NullPointerException ex){
              Logger.getLogger(UpdatePaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
              
           }
        }
        
      }
    


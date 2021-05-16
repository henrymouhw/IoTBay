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
import iot.models.Payment;
import iot.models.Order;
import iot.models.dao.PaymentManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author 74152
 */

public class AddPaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Validator validator = new Validator();
        
        String paymethod = request.getParameter("paymethod");
        String cardnumber = request.getParameter("cardnumber");
        String expirydate = request.getParameter("expirydate");
        String cvv = request.getParameter("cvv");
        String fullname = request.getParameter("fullname");
        PaymentManager manager = (PaymentManager) session.getAttribute("pManager");
        Order order = (Order)session.getAttribute("orderToPay");
        String email = (String)session.getAttribute("logEmail");
 

            try {
               
                    java.sql.Date reDate = new java.sql.Date(System.currentTimeMillis());
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(reDate);
                  
                   manager.addPayment(paymethod, cardnumber, expirydate, cvv, fullname,order.getId(),date,order.getTotal());
                 
                   Payment payment = manager.findLastPayment(email);
                    session.setAttribute("payment", payment);
                    request.getRequestDispatcher("paymentConfirmation.jsp").include(request, response);
                ///}
                
           }catch (SQLException ex) {
          //      Logger.getLogger(AddPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        }
            
}
    


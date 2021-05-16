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
    import iot.models.Payment;
    import iot.models.dao.PaymentManager;
    import java.util.*;

/**
 *
 * @author per
 */
public class SearchPaymentServlet extends HttpServlet {
      @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
      HttpSession session = request.getSession();
      String id = request.getParameter("payment_id");
      String date = request.getParameter("date");
         String email = (String) session.getAttribute("logEmail");
      
      PaymentManager manager = (PaymentManager) session.getAttribute("pManager");
      LinkedList<Payment> detials = new LinkedList<Payment>();
      
          try{
                if(id!=null){
                  int a = Integer.parseInt(id);
                  detials = manager.fetchPaymentsByPaymentid(a);
                } 
                if(date!=null){
                  detials = manager.fetchPaymentsByDate(date,email);
                 
                }
                  session.setAttribute("Payments",detials);
                  request.getRequestDispatcher("paymentList.jsp").include(request,response);
            
          }catch(SQLException | NullPointerException ex){
               Logger.getLogger(SearchPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
      
    }
    
}

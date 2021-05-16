/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

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
import java.util.*;
import iot.models.Payment;
import iot.models.Order;
import iot.models.OrderItem;
/**
 *
 * @author lin
 */
public class toPaymentServlet extends HttpServlet {
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        HttpSession session = request.getSession();
        PaymentManager manager = (PaymentManager) session.getAttribute("pManager");
        String email = (String) session.getAttribute("logEmail");
        String id = request.getParameter("orderId");
        try{
            int orderId = Integer.parseInt(id);
            Order o = manager.findOrder(orderId);
           session.setAttribute("orderToPay", o);
           request.getRequestDispatcher("addPayment.jsp").include(request, response);
        }catch (SQLException | NullPointerException ex) {
      Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
     }

}

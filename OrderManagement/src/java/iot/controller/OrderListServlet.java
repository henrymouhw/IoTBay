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

     import iot.models.Order;

     import iot.models.dao.OrderManager;
import java.util.*;

public class OrderListServlet extends HttpServlet {
 @Override   
 protected void doGet(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException { 
      HttpSession session = request.getSession();
        OrderManager oManager= (OrderManager)session.getAttribute("orderManager");
        String email = (String) session.getAttribute("logEmail");
        Validator v = new Validator();
        
        try{ 
            LinkedList<Order> orders = oManager.findOrderList(email);
            session.setAttribute("orders", orders);
     
            request.getRequestDispatcher("orderList.jsp").include(request, response);
        }catch (SQLException | NullPointerException ex) {
      Logger.getLogger(OrderListServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
 
 }

}
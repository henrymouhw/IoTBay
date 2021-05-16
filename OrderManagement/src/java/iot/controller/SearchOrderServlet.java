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

     import iot.models.Order;

     import iot.models.dao.OrderManager;
     import java.util.*;

/**
 *
 * @author lin
 */
public class SearchOrderServlet extends HttpServlet {
      @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
      HttpSession session = request.getSession();
      String num = request.getParameter("num");
      String date = request.getParameter("date");
      OrderManager manager = (OrderManager) session.getAttribute("orderManager");
      String email = (String) session.getAttribute("logEmail");
      LinkedList<Order> orders = new LinkedList<Order>();
      
          try{
                if(num!=null){
                  int no = Integer.parseInt(num);
         
                  orders = manager.fetchOrderListByNum(email, no);
                } else{
                   orders = manager.fetchOrderListByDate(email, date);
                }
                  session.setAttribute("orders",orders);
                  request.getRequestDispatcher("orderList.jsp").include(request,response);
            
          }catch(SQLException | NullPointerException ex){
               Logger.getLogger(SearchOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
      
    }
    
}

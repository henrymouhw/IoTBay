/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.models.dao.OrderManager;
import iot.models.dao.productDBManager;
import iot.models.*;
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

/**
 *
 * @author lin
 */
public class OrderEditServlet extends HttpServlet {

  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        OrderManager oManager= (OrderManager)session.getAttribute("orderManager");
        productDBManager pManager = (productDBManager) session.getAttribute("manager");
        String option = request.getParameter("option");
        String name = request.getParameter("name");
        String email = (String) session.getAttribute("logEmail");
        Validator v = new Validator();
        v.clear(session);
        try {
            Order order = oManager.findOrder(oManager.findLastOrderId());
            product p = pManager.findProductByName(name);
            LinkedList<OrderItem> items = order.getItems();
            OrderItem item = items.get(0);
              for(OrderItem it: items){
                   if(name.equals(it.getItemName())){
                       item = it;
                       break;
                   }
                }
            if(option.equals("plus")){
               if(p.getProductStock()==0){
                   request.getRequestDispatcher("Cart.jsp").include(request, response);
               } else{
                   oManager.updateItem(item.getId(), 1);
                   pManager.updateProduct(p.getProductName(), p.getProductBrand(),p.getProductType(),p.getProductDescription(),p.getProductPrice(),p.getProductStock()-1);
                   order = oManager.findOrder(oManager.findLastOrderId());
                   session.setAttribute("order", order);
                  request.getRequestDispatcher("Cart.jsp").include(request, response);
               }  
            } else{
                  if(item.getQuantity()>0){
                        oManager.updateItem(item.getId(), -1);
                        pManager.updateProduct(p.getProductName(), p.getProductBrand(),p.getProductType(),p.getProductDescription(),p.getProductPrice(),p.getProductStock()+1);
                        order = oManager.findOrder(oManager.findLastOrderId());
                        session.setAttribute("order", order);
                        request.getRequestDispatcher("Cart.jsp").include(request, response);
                  }else{
                       request.getRequestDispatcher("Cart.jsp").include(request, response);
                  }
            }
        }catch (SQLException | NullPointerException ex) {
      Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

}

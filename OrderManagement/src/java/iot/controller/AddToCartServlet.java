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
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        OrderManager oManager= (OrderManager)session.getAttribute("orderManager");
        productDBManager pManager = (productDBManager) session.getAttribute("manager");
        String pname = request.getParameter("name");
        String email = (String) session.getAttribute("logEmail");
        try {
              product p = pManager.findProductByName(pname);
              Order order = (Order)session.getAttribute("order");
              boolean flag = false;
              pManager.updateProduct(pname, p.getProductBrand(),p.getProductType(),p.getProductDescription(),p.getProductPrice() ,p.getProductStock()-1);
              ArrayList<product> products = pManager.fetchProduct();
               LinkedList<OrderItem> items = oManager.findItems(oManager.findLastOrderId());
               for (OrderItem item: items){
                   if(item.getItemName().equals(p.getProductName())){
                       oManager.updateItem(item.getId(), 1);
                       flag = true;
                       break;
                   }
               }
               if(!flag){
                   oManager.addItem(oManager.findLastOrderId(), 1, pname);
               }
               
               session.setAttribute("Products", products);
               order = oManager.findOrder(oManager.findLastOrderId());
               session.setAttribute("order", order);
               request.getRequestDispatcher("customerProductPage.jsp").include(request, response);
        }catch (SQLException | NullPointerException ex) {
      Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        
    }
  
}

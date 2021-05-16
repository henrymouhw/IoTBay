/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import iot.models.dao.*; 
import iot.models.product;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Jiale Xue
 */
    public class productDeleteServlet extends HttpServlet {
       @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           
           HttpSession session = request.getSession();
           productDBManager manager = (productDBManager)session.getAttribute("manager");
           String name = request.getParameter("name");
            try{
             // manager.deleteProduct(name);
              manager.cancelProduct(name);
              ArrayList<product> p = manager.fetchAllProduct();
              session.setAttribute("Products", p);
              response.sendRedirect("productDetails.jsp");
        } catch (SQLException ex) {
                
               
        }

   }
}
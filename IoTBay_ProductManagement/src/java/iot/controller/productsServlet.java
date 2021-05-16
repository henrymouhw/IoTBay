package iot.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import iot.models.dao.productDBManager;
import iot.models.product;
import java.util.ArrayList;

   public class productsServlet extends HttpServlet {
       @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           
           HttpSession session = request.getSession();
           productDBManager manager = (productDBManager)session.getAttribute("manager");

               try{
               ArrayList<product> p = manager.fetchProduct();      
               session.setAttribute("Products", p);
               request.getRequestDispatcher("productDetails.jsp").include(request, response);
           } catch (SQLException | NullPointerException ex) {

           }

   }
}
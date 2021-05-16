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
import iot.models.dao.productDBManager;
import iot.models.product;
import java.util.ArrayList;

public class addProductServlet extends HttpServlet {
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
   
       HttpSession session = request.getSession();
       Validator validator = new Validator();
       String name = request.getParameter("productName");
       String brand = request.getParameter("productBrand");
       String type = request.getParameter("productType");
       String description = request.getParameter("productDescription");
       Double price = Double.parseDouble(request.getParameter("productPrice"));
       int stock = Integer.parseInt(request.getParameter("productStock"));
       productDBManager manager = (productDBManager)session.getAttribute("manager");
       validator.clear(session);
       
       if(!validator.validateName(name)) {
           session.setAttribute("nameErr", "Error: Name format is incorrect");
           request.getRequestDispatcher("addProduct.jsp").include(request, response);
       } else {
           try {
               product exist = manager.findProductByName(name);
               if(exist!=null) {
                   session.setAttribute("existErr", "This product already exists in the list.");
                   request.getRequestDispatcher("addProduct.jsp").include(request, response);
               } else {
                   manager.addProduct(name, brand, type, description, price, stock);
                   product product = new product(name, brand, type, description, price, stock,"selling");
                    ArrayList<product> p = manager.fetchAllProduct();
                   session.setAttribute("Products", p);
                   request.getRequestDispatcher("productDetails.jsp").include(request, response);
               }
               
           } catch (SQLException ex) {
               Logger.getLogger(addProductServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
   }
           
           
           
           
           
}
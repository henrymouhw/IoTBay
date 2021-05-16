package iot.controller;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import iot.models.dao.*; 
import iot.models.product;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jiale Xue
 */
public class editProductServlet extends HttpServlet {
    
     @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
          HttpSession session = request.getSession();
          String name = request.getParameter("name");
         
          productDBManager manager = (productDBManager)session.getAttribute("manager");
          Validator validator = new Validator();
          validator.clear(session);
          product Product = null;
          
          try{ 
              Product=manager.findProductByName(name);
              if(Product!=null) {
                session.setAttribute("productss", Product);
                request.getRequestDispatcher("editProduct.jsp").include(request, response);
              }
              
          } catch (SQLException ex) {
                Logger.getLogger(editProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
          }
          response.sendRedirect("editProduct.jsp");
      }
    
}

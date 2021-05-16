/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.models.dao.productDBManager;
import iot.models.product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yang
 */
public class productSearchServlet extends HttpServlet {
            
    @Override
      public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            HttpSession session = request.getSession();
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String userType  = request.getParameter("Usertype");
            productDBManager manager = (productDBManager)session.getAttribute("manager");
            ArrayList<product> Product = new ArrayList<product>();
            try {
                if(userType.equals("customer")){
                    if(name!=null) {
                        Product = manager.fetchProductByName(name);
                    }else {
                        Product = manager.fetchProductByType(type);
                    }
                } else{
                    if(name!=null) {
                        Product = manager.fetchAllProductByName(name);
                    }else {
                        Product = manager.fetchAllProductByType(type);
                    }
                }
                session.setAttribute("Products", Product);
                request.getRequestDispatcher("productDetails.jsp").include(request, response);
            } catch (SQLException | NullPointerException ex) {
                 Logger.getLogger(productSearchServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

}

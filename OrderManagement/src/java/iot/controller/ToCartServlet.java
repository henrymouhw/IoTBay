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
public class ToCartServlet extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        OrderManager oManager= (OrderManager)session.getAttribute("orderManager");
        productDBManager pManager = (productDBManager) session.getAttribute("manager");
        String email = (String) session.getAttribute("logEmail");
        try{
        Order order = oManager.findLastOrder(email);
        session.setAttribute("order", order);
        request.getRequestDispatcher("Cart.jsp").include(request, response);
        }catch (SQLException | NullPointerException ex){
         Logger.getLogger(ToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

}

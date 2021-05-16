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

     import iot.models.CustRecord;

     import iot.models.dao.DBCustRManager;
   
/**
 *
 * @author lin
 */
public class AddCustomerServlet extends HttpServlet {
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException { 
      HttpSession session = request.getSession();
      Validator validator = new Validator();
      String email = request.getParameter("email");
      String name = request.getParameter("name");
      String address = request.getParameter("address");
      String type = request.getParameter("type");
      String status = request.getParameter("status");
      DBCustRManager manager = (DBCustRManager) session.getAttribute("crManager");
      CustRecord customer = null;
      validator.clear(session);
      if(!validator.validateEmail(email)){
          session.setAttribute("emailErr", "Email format incorrect");
          request.getRequestDispatcher("addCustomer.jsp").include(request,response);
      } else if(!validator.validateName(name)){
          session.setAttribute("nameErr","Name format incorrect");
          request.getRequestDispatcher("addCustomer.jsp").include(request,response);
      } else{
          try {
              customer = manager.findCustRecord(email);
              if(customer != null){
                  session.setAttribute("existErr","the email is exists in database");
                  request.getRequestDispatcher("addCustomer.jsp").include(request,response);
              } else{
                  manager.addCustRecord(email, name, type, address,status);
                  session.setAttribute("success","the Customer is add into database");
                  request.getRequestDispatcher("addCustomer.jsp").include(request,response);
              }
          }catch(SQLException | NullPointerException ex){
                Logger.getLogger(AddCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
      }
    }

}

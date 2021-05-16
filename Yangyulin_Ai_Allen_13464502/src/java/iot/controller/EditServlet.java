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
import java.util.*;
import iot.models.dao.UserManager;
import iot.models.*;
import iot.models.dao.OrderManager;
import iot.models.dao.productDBManager;
/**
 *
 * @author Think
 */
public class EditServlet extends HttpServlet
{
    //@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        UserManager user_manager = (UserManager) session.getAttribute("user_manager");
        Validator v = new Validator();
        v.clear(session);
        User user = null;
        try{
            user = user_manager.findUser(email);
            if(user != null)
            {
                if(password != null)
                {
                    user_manager.updatePassword(email,password);
                }
                if(name != null)
                {
                     user_manager.updateName(email,name);
                }
                if(dob != null)
                {
                    user_manager.updateDob(email,dob);
                }
                user = user_manager.findUser(email);
                session.setAttribute("user", user);
                session.setAttribute("updated","Updated sucessfully");
                request.getRequestDispatcher("Edit.jsp").include(request, response);
            }
            else
            {
                session.setAttribute("existErr", "Student does not exist in the Database!");
                request.getRequestDispatcher("Edit.jsp").include(request, response);
            }
        } catch(SQLException ex){
            Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
        }
 
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        UserManager uManager = (UserManager) session.getAttribute("user_manager");
        OrderManager oManager= (OrderManager)session.getAttribute("orderManager");
        productDBManager pManager = (productDBManager) session.getAttribute("manager");
        String email = (String)session.getAttribute("logEmail");
        String option = request.getParameter("option");
        try{
            
        if(option.equals("cancel")){
            Order order = oManager.findOrder(oManager.findLastOrderId());
            if(order!=null && order.getStatus().equals("processing")){
                LinkedList<OrderItem> items = oManager.findItems(oManager.findLastOrderId());
                for(OrderItem item: items){
                    product p = pManager.findProductByName(item.getItemName());
                    pManager.updateProduct(p.getProductName(), p.getProductBrand(), p.getProductType(),p.getProductDescription(),p.getProductPrice(),p.getProductStock()+item.getQuantity());
                }
                oManager.updateOrder(oManager.findLastOrderId(),"cancel");
                uManager.deleteUser(email);
               request.getRequestDispatcher("notice.jsp").include(request, response);
            }else{
                 uManager.deleteUser(email);
                 request.getRequestDispatcher("notice.jsp").include(request, response);
            }
            
        } else{
            request.getRequestDispatcher("Edit.jsp").include(request, response);
        }
        }catch (SQLException | NullPointerException ex) {
      Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }
    
}

package iot.controller;

import java.io.IOException;
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
public class updateProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            
            HttpSession session = request.getSession();
            String name = request.getParameter("productName");
            String brand = request.getParameter("productBrand");
            String type = request.getParameter("productType");
            String description = request.getParameter("productDescription");
            Double price = Double.parseDouble(request.getParameter("productPrice"));
            int stock = Integer.parseInt(request.getParameter("productStock"));
            product Product = new product(name, brand, type, description, price, stock,"selling");
            productDBManager manager = (productDBManager)session.getAttribute("manager");
             String state = request.getParameter("state");
            Validator validator = new Validator();
            validator.clear(session);
            
            if(!validator.validateName(name)) {
           session.setAttribute("nameErr", "Error: Name format is incorrect");
           request.getRequestDispatcher("editProduct.jsp").include(request, response);
       } else {
            try{
                if(Product!=null) {
                    manager.updateProduct(name, brand, type, description, price, stock);
                    if(state.equals("cancel")){
                        manager.cancelProduct(name);
                        Product.setState("Cancel");
                    }else{
                        manager.activeProduct(name);
                        Product.setState("selling");
                    }
                      session.setAttribute("productss", Product);
                    session.setAttribute("updated", "Update was successful");
                    request.getRequestDispatcher("editProduct.jsp").include(request, response);
                } else {
                    session.setAttribute("updated", "Update was not successful!");
                    request.getRequestDispatcher("editProduct.jsp").include(request, response);
                }
           } catch (SQLException ex) {
               Logger.getLogger(addProductServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
           response.sendRedirect("editProduct.jsp");
    }

}
}

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
import iot.models.Payment;
import iot.models.Order;
import iot.models.dao.PaymentManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author 74152
 */

public class paymentSubmitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PaymentManager manager = (PaymentManager) session.getAttribute("pManager");
        String id = request.getParameter("id");
 

            try {
               int paymentId = Integer.parseInt(id);
               manager.submitPayment(paymentId);
                request.getRequestDispatcher("Main.jsp").include(request, response);
           }catch (SQLException ex) {
          //      Logger.getLogger(AddPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        }
            
}
    


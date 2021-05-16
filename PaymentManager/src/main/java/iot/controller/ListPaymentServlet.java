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
    import iot.models.dao.PaymentManager;
    import java.util.*;

    public class ListPaymentServlet extends HttpServlet {
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        HttpSession session = request.getSession();
        PaymentManager manager = (PaymentManager) session.getAttribute("pManager");
        String email = (String) session.getAttribute("logEmail");
        try{ 
            LinkedList<Payment> p = manager.fetchPayments(email);
            session.setAttribute("Payments",p);
            request.getRequestDispatcher("paymentList.jsp").include(request, response);
            }catch (SQLException | NullPointerException ex) {
      Logger.getLogger(ListPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
 
    }

    
}
package iot.controller;

import iot.models.dao.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Think
 */
public class StaffServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        UserManager user_manager= (UserManager)session.getAttribute("user_manager");
        
        if(code.equals("123"))
        {
            request.getRequestDispatcher("SRF.jsp").include(request, response);
        }
        else
        {
            request.getRequestDispatcher("SRF_Authentication.jsp").include(request, response);
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.models.Record;
import iot.models.dao.RecordManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Think
 */
public class AccessLogServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        
        RecordManager record_manager = (RecordManager) session.getAttribute("record_manager");
        
        try{
            LinkedList<Record> record = record_manager.matchRecord(email);
            session.setAttribute("record",record);

        } catch (SQLException ex) {
            Logger.getLogger(AccessLogServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
           request.getRequestDispatcher("AccessLog.jsp").include(request, response);
    }
    
        

}

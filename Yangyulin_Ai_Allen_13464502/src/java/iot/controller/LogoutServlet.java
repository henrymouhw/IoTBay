package iot.controller;

import iot.models.Record;
import iot.models.dao.RecordManager;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class LogoutServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        RecordManager record_manager = (RecordManager)session.getAttribute("record_manager");
        Date reDate = new Date(System.currentTimeMillis());
        String logout = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(reDate);
        Record record = (Record) session.getAttribute("currentRecord");
        try{
            if(record != null){
            record_manager.updateRecord(record.getEmail(), record.getLogin(), logout);
            } 
        }catch(SQLException ex){
             Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        session.invalidate();
        request.getRequestDispatcher("index.jsp").include(request, response);
    }
    
}

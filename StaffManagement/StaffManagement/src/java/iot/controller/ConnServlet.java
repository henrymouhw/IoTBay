package iot.controller;

import iot.models.dao.DBConnector;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import iot.models.dao.*;
/**
 *
 * @author Think
 */
public class ConnServlet extends HttpServlet
{
    private DBConnector db;
    private UserManager user_manager;
    private RecordManager record_manager;
    private productDBManager manager;
    private Connection conn;
    private OrderManager odb;
    private DBCustRManager crm;
    private StaffDBManager sdb;
    
    @Override 
    public void init()
    {
        try{
            db = new DBConnector();
        }catch (ClassNotFoundException | SQLException ex){
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        conn = db.openConnection();
        try{
            user_manager = new UserManager(conn);
            record_manager = new RecordManager(conn);
            manager = new productDBManager(conn);
            odb = new OrderManager(conn);
            crm = new DBCustRManager(conn);
            sdb = new StaffDBManager(conn);
            
        }catch(SQLException ex){
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        session.setAttribute("user_manager", user_manager);
        session.setAttribute("record_manager", record_manager);
        session.setAttribute("manager", manager);
        session.setAttribute("orderManager",odb);
        session.setAttribute("crManager", crm);
        session.setAttribute("sManager",sdb);
    }
    
    @Override
    public void destroy()
    {
        try{
            db.closeConnection();
        }catch(SQLException ex){
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

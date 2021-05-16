package iot.models.dao;

import iot.models.Record;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Think
 */
public class RecordManager 
{
    private Statement st;
    
    public RecordManager(Connection conn) throws SQLException
    {
        st = conn.createStatement();
    }
    
    public int findRecord(String email, String login) throws SQLException
    {
       String fetch = "select * from GROUP45.RECORD where email = '" + email + "' AND loginDT = '" + login + "'";
       ResultSet rs = st.executeQuery(fetch);
      
       int id = 0;
        
        while(rs.next())
        {
            id = rs.getInt(1);

        }
        return id;
    }


    public LinkedList<Record> matchRecord(String email) throws SQLException
    {
       String fetch = "select * from GROUP45.RECORD where email = '" + email + "'";
       ResultSet rs = st.executeQuery(fetch);
       LinkedList<Record> records = new LinkedList();
        
        while(rs.next())
        {
            String login = rs.getString(3);
            String logout = rs.getString(4);
            Record record = new Record(email, login);
            record.setLogout(logout);
            records.add(record);
        }
        return records;
    }
    
    
    
    public void addRecord(String email, String login) throws SQLException
    {
        st.executeUpdate("INSERT INTO GROUP45.RECORD " + "values (DEFAULT, '" + email + "','" + login + "','NULL')" );
    }
    
    public void updateRecord(String email, String login, String logout) throws SQLException
    {
        st.executeUpdate("UPDATE GROUP45.RECORD SET logoutDT='" + logout + "' WHERE email='" + email + "' and logindt='" + login + "'" );
    }
    
    public void deleteRecord(int id) throws SQLException
    {
        st.executeUpdate("DELETE FROM GROUP45.RECORD WHERE recordid='" + id + "'" );
    }
    
    public boolean checkRecord(String email) throws SQLException
    {
        String fetch = "select * from GROUP45.RECORD WHERE EMAIL = '" + email + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while(rs.next())
        {
            String userEmail = rs.getString(2);
            if(userEmail.equals(email))
            {
                return true;
            }
        }
        return false;
    }
}

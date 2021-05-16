/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.models.dao;
import iot.models.StaffRecord;
import java.sql.*;
import java.util.ArrayList;


public class StaffDBManager {
     private Statement st;
     
     public StaffDBManager(Connection conn) throws SQLException {
         st = conn.createStatement();
     }

     public StaffRecord findStaffbyName(String sName) throws SQLException {
         String fetch = "select * from GROUP45.Staff where name = '" + sName + "'";
         ResultSet rs = st.executeQuery(fetch);
   
         while (rs.next()){
             String name = rs.getString(1);
             if(name.equals(sName)){
                   String email = rs.getString(2);
                   String position = rs.getString(3);
                   String address = rs.getString(4);
                   String status = rs.getString(5);
                   return new StaffRecord(name, email, position, address, status);
             }
         }
         return null;
     }
    
     public StaffRecord findStaffbyPosition(String sPosition) throws SQLException {
         String fetch = "select * from GROUP45.Staff where position = '" + sPosition + "'";
         ResultSet rs = st.executeQuery(fetch);
   
         while (rs.next()){
             String position = rs.getString(3);
             if(position.equals(sPosition)){
                   String name = rs.getString(1);
                   String email = rs.getString(2);
                   String address = rs.getString(4);
                   String status = rs.getString(5);
                   return new StaffRecord(name, email, position, address, status);
             }
         }
         return null;
     }

     public void addStaff(String name, String email, String position, String address, String status) throws SQLException{
            st.executeUpdate("INSERT INTO GROUP45.Staff " + "VALUES ('" + name + "', '" + email + "', '" + position + "', '" + address + "', '" + status + "')");
     }   

     public void updateStaff(String name, String email, String position, String address, String status) throws SQLException{
            st.executeUpdate("UPDATE GROUP45.Staff SET NAME='" + name + "', POSITION='" + position + "', ADDRESS='" + address + "', STATUS='" + status + "' WHERE EMAIL='" + email + "'");
     }  
     
     public void deleteStaff(String email) throws SQLException {
            st.executeUpdate("DELETE FROM GROUP45.Staff WHERE EMAIL='" + email + "'");
     }

     public ArrayList<StaffRecord> fetchStaff() throws SQLException {
             String fetch = "select * from GROUP45.STAFF";
             ResultSet rs = st.executeQuery(fetch);
             ArrayList<StaffRecord> temp = new ArrayList();

             while(rs.next()){
                 String name = rs.getString(1);
                 String email = rs.getString(2);
                 String position = rs.getString(3);
                 String address = rs.getString(4);
                 String status = rs.getString(5);
                 temp.add(new StaffRecord(name, email, position, address, status));
             }
            return temp;
     }
    
     public boolean checkStaff(String sEmail) throws SQLException {
             String fetch = "select * from GROUP45.Staff where EMAIL = '" + sEmail + "'";
             ResultSet rs = st.executeQuery(fetch);
 
             while(rs.next()){
                   String email = rs.getString(2); 
                   if(email.equals(sEmail)){
                        return true;
                   }
             }
             return false;
     }

    public StaffRecord findStaff(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<StaffRecord> fetchProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<StaffRecord> fetchStaffbyName(String name) throws SQLException {
             String fetch = "select * from GROUP45.STAFF WHERE NAME='" + name + "'";
             ResultSet rs = st.executeQuery(fetch);
             ArrayList<StaffRecord> temp = new ArrayList();

             while(rs.next()){
                 String email = rs.getString(2);
                 String position = rs.getString(3);
                 String address = rs.getString(4);
                 String status = rs.getString(5);
                 temp.add(new StaffRecord(name, email, position, address, status));
             }
            return temp;
     }


public ArrayList<StaffRecord> fetchStaffbyPosition(String position) throws SQLException {
             String fetch = "select * from GROUP45.STAFF WHERE POSITION='" + position + "'";
             ResultSet rs = st.executeQuery(fetch);
             ArrayList<StaffRecord> temp = new ArrayList();

             while(rs.next()){
                 String name = rs.getString(1);
                 String email = rs.getString(2);
                 String address = rs.getString(4);
                 String status = rs.getString(5);
                 temp.add(new StaffRecord(name, email, position, address, status));
             }
            return temp;
     }

public StaffRecord findStaffbyEmail(String sEmail) throws SQLException {
         String fetch = "select * from GROUP45.Staff where email = '" + sEmail + "'";
         ResultSet rs = st.executeQuery(fetch);
   
         while (rs.next()){
             String email = rs.getString(2);
             if(email.equals(sEmail)){
                   String name = rs.getString(1);
                   String position = rs.getString(3);
                   String address = rs.getString(4);
                   String status = rs.getString(5);
                   return new StaffRecord(name, email, position, address, status);
             }
         }
         return null;
     }
    
}
package iot.models.dao;

import iot.models.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Think
 */
public class UserManager 
{
    private Statement st;
    
    public UserManager(Connection conn) throws SQLException
    {
        st = conn.createStatement();
    }
    
    public User findUser(String email) throws SQLException
    {
            String fetch = "select * from GROUP45.IOTUSER where email = '" + email + "'";
            ResultSet rs = st.executeQuery(fetch);
        
            while (rs.next())
            {
                String userEmail = rs.getString(2);
            
                if(userEmail.equals(email))
                {
                    String password = rs.getString(3);
                    String userType = rs.getString(4);
                    String userName = rs.getString(5);
                    String userGender = rs.getString(6);
                    String userDob = rs.getString(7);
                    String status = rs.getString(8);
                    String phone = rs.getString(9);
                    return new User(userEmail, password, userType, userName, userGender, userDob,status,phone);
                }
                    
            }
        
            return null;
    }
    
     public Customer findCustomer(String email) throws SQLException
    {
            String fetch = "select * from GROUP45.IOTUSER where email = '" + email + "'";
            ResultSet rs = st.executeQuery(fetch);
        
            while (rs.next())
            {
                String userEmail = rs.getString(2);
            
                if(userEmail.equals(email))
                {
                    String password = rs.getString(3);
                    String userType = rs.getString(4);
                    String userName = rs.getString(5);
                    String userGender = rs.getString(6);
                    String userDob = rs.getString(7);
                    String status = rs.getString(8);
                    String phone = rs.getString(9);
                    return new Customer(userEmail, password, userType, userName, userGender, userDob,status,phone);
                }
                    
            }
            return null;
    }
     
      public Staff findStaff(String email) throws SQLException
    {
            String fetch = "select * from GROUP45.IOTUSER where email = '" + email + "'";
            ResultSet rs = st.executeQuery(fetch);
        
            while (rs.next())
            {
                String userEmail = rs.getString(2);
            
                if(userEmail.equals(email))
                {
                    String password = rs.getString(3);
                    String userType = rs.getString(4);
                    String userName = rs.getString(5);
                    String userGender = rs.getString(6);
                    String userDob = rs.getString(7);
                    String status = rs.getString(8);
                    String phone = rs.getString(9);
                    return new Staff(userEmail, password, userType, userName, userGender, userDob,status,phone);
                }
                    
            }
        
            return null;
    }
    
    public void addUser(String email, String password, String type, String name, String gender, String dob,String phone) throws SQLException
    {
        st.executeUpdate("INSERT INTO GROUP45.IOTUSER " + "values (DEFAULT, '" + email + "','" + password + "','" + type + "','" + name  + "','" + gender +  "','" + dob +"','" +"active"+"','"+phone+"',NULL,NULL,NULL)" );
    }
    
    public void updateUser(String email, String password, String type, String name, String gender, String dob,String phone) throws SQLException
    {
        st.executeUpdate("UPDATE GROUP45.IOTUSER SET password='" + password + "',usertype='" + type + "',username='" + name + "',gender='" + gender + "',dob='" + dob + "', phone='"+phone+"' WHERE EMAIL='" + email + "'" );
    }
    
            
    public void deleteUser(String email) throws SQLException
    {
       st.executeUpdate("UpDATE group45.iotuser set status='cancel' WHERE email ='"+email+"'");
    }
    
     public void updatePassword(String email, String password) throws SQLException
    {
        st.executeUpdate("UPDATE GROUP45.IOTUSER SET password='" + password + "' WHERE EMAIL='" + email + "'" );
    }
    public void updateName(String email, String name) throws SQLException
    {
        st.executeUpdate("UPDATE GROUP45.IOTUSER SET username='" + name + "' WHERE EMAIL='" + email + "'" );
    }
    public void updateDob(String email, String dob) throws SQLException
    {
        st.executeUpdate("UPDATE GROUP45.IOTUSER SET dob='" + dob + "' WHERE EMAIL='" + email + "'" );
    }
    public void updatePhone(String email, String phone) throws SQLException
    {
        st.executeUpdate("UPDATE GROUP45.IOTUSER SET phone='" + phone + "' WHERE EMAIL='" + email + "'" );
    }
    
    public void updateOccupation(String email, String occupation) throws SQLException
    {
        st.executeUpdate("UPDATE GROUP45.IOTUSER SET occupation='" + occupation + "' WHERE EMAIL='" + email + "'" );
    }
    //public ArrayList<Student> fectStudents() throws SQLException
    //{
     //   String fetch = "select * from STUDENT";
       // ResultSet rs = st.executeQuery(fetch);
        //ArrayList<Student> temp = new ArrayList();
        
        //while(rs.next())
        //{
         //   String name = rs.getString(1);
           // String email = rs.getString(2);
            //String password = rs.getString(3);
            //String dob = rs.getString(4);
            //temp.add(new Student(name, email, password, dob));
        //}
        //return temp;
    //}
    
    public boolean checkUser(String email, String password) throws SQLException
    {
        String fetch = "select * from GROUP45.IOTUSER WHERE EMAIL = '" + email + "' and password = '" + password + "'";
        ResultSet rs = st.executeQuery(fetch);
        while(rs.next())
        {
            String userEmail = rs.getString(2);
            String userPass = rs.getString(3);
            if(userEmail.equals(email) && userPass.equals(password))
            {
                return true;
            }
        }
        return false;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.models.dao;
import iot.models.Payment;
import iot.models.Order;
import iot.models.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class PaymentManager 
{
    private Statement st;
    
    public PaymentManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }
    
     public LinkedList<OrderItem> findItems(int id) throws SQLException{
        String fetch = "Select Item_ID,GROUP45.ORDERITEM.pNAME,pPrice,quantity FROM GROUP45.PRODUCT, GROUP45.ORDERITEM WHERE ORDER_ID = " + id + "AND  GROUP45.PRODUCT.pName = GROUP45.ORDERITEM.pName";
        ResultSet rs = st.executeQuery(fetch);
        LinkedList<OrderItem> items = new LinkedList<OrderItem>();
        while(rs.next()){
            int itemId = rs.getInt(1);
            String name = rs.getString(2);
            double price = rs.getDouble(3);
            int quantity = rs.getInt(4);
            items.add(new OrderItem(itemId,name,price,quantity));
        }
        return items;
    }
    
     public Order findOrder(int id) throws SQLException {
        LinkedList <OrderItem> items = findItems(id);
        String fetch = "Select * From ORDERS WHERE ORDER_ID = "+id;
         ResultSet rs = st.executeQuery(fetch);
         Order o = null;
         while(rs.next()){
            String date = rs.getString(2);
            String status = rs.getString(3);
            String email = rs.getString(4);
            o = new Order(date,items,status,email);
            o.setId(id);
         }
         return o;
    }
    
    public LinkedList<Order> findSubmitOrderList(String email)throws SQLException {
        String fetch = "select * from Group45.Orders Where email='"+email+"' And Status='submit' ";
        ResultSet rs = st.executeQuery(fetch);
        LinkedList<Integer> ids = new LinkedList<Integer>();
        while(rs.next()){
            int id = rs.getInt(1);
            ids.add(id);
        }
        LinkedList<Order> orders = new LinkedList<Order>();
        for(int i: ids){
            orders.add(findOrder(i));
        }
        return orders;
    }
   
   public Payment findPayment(int payment_id) throws SQLException{
        String fetch = "select * FROM GROUP45.PAYMENT WHERE payment_id="+payment_id;
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()){
            int listPayment_id = rs.getInt(1);
            if(listPayment_id ==payment_id){
                String paymethod = rs.getString(2);
                String cardnumber = rs.getString(3);
                String expirydate = rs.getString(4);
                String cvv = rs.getString(5);
                String fullname = rs.getString(6);
                String date = rs.getString(8);
                String paystatus = rs.getString(9);
                Double amount = rs.getDouble(10);
 
                return new Payment(listPayment_id,paymethod,cardnumber,expirydate,cvv,fullname,date,paystatus,amount);
            }
        }
        return null;
    }
    
    //public void addPayment(String cardnumber, String expirydate, String cvv, String fullname) throws SQLException {//code for add-operation       
     //   st.executeUpdate("INSERT INTO GROUP45.Card " + "VALUES ('" + cardnumber + "','" + expirydate + "', '" + cvv + "', '" + fullname + "')");   
    //}
    //得
    public void addPayment(String paymethod, String cardnumber, String expirydate, String cvv, String fullname,int oId, String date,double amount) throws SQLException {//code for add-operation       
        //st.executeUpdate("INSERT INTO GROUP45.PAYMENT " + "VALUES ('" + paymethod + "', '" + cardnumber + "','" + expirydate + "', '" + cvv + "', '" + fullname + "')"); 
       st.executeUpdate("INSERT INTO GROUP45.PAYMENT(paymethod,cardnumber,expirydate,cvv,fullname,order_id,paydt,paystatus,amount) " + "VALUES ( '" + paymethod + "', '" + cardnumber + "','" + expirydate + "', '" + cvv + "', '" + fullname + "',"+oId+ ",'" + date + "','processing',"+amount+")"); 
    }
    
    public Payment findLastPayment(String email) throws SQLException {
        String fetch = "select Payment_ID,PayMethod,Cardnumber,expirydate,cvv,fullName,Group45.payment.order_id,payDT,payStatus,amount from GROUP45.PAYMENT,Group45.Orders where email='"+email+"' And GROUP45.orders.order_Id = Group45.payment.order_ID";
        ResultSet rs = st.executeQuery(fetch);
        Payment p =null;
        while(rs.next()){
                int listPayment_id = rs.getInt(1);
                String paymethod = rs.getString(2);
                String cardnumber = rs.getString(3);
                String expirydate = rs.getString(4);
                String cvv = rs.getString(5);
                String fullname = rs.getString(6);
                String date = rs.getString(8);
                String paystatus = rs.getString(9);
                Double amount = rs.getDouble(10);
              p = new Payment(listPayment_id,paymethod,cardnumber,expirydate,cvv,fullname,date,paystatus,amount);
        }
        return p;
    }
  
    public LinkedList<Payment> fetchPayments(String email) throws SQLException {
        String fetch = "select Payment_ID,PayMethod,Cardnumber,expirydate,cvv,fullName,Group45.payment.order_id,payDT,payStatus,amount from GROUP45.PAYMENT,Group45.Orders where email='"+email+"' And GROUP45.orders.order_Id = Group45.payment.order_ID";
        ResultSet rs = st.executeQuery(fetch);
        LinkedList<Payment> p = new LinkedList<Payment>();
        while(rs.next()){
                int listPayment_id = rs.getInt(1);
                String paymethod = rs.getString(2);
                String cardnumber = rs.getString(3);
                String expirydate = rs.getString(4);
                String cvv = rs.getString(5);
                String fullname = rs.getString(6);
                String date = rs.getString(8);
                String paystatus = rs.getString(9);
                Double amount = rs.getDouble(10);
             p.add(new Payment(listPayment_id,paymethod,cardnumber,expirydate,cvv,fullname,date,paystatus,amount));
        }
        return p;
    }
    public void updatePayment(int payment_id, String paymethod, String cardnumber, String expirydate, String cvv, String fullname)throws SQLException{
         String fetch = "update group45.payment set  paymethod='"+paymethod+"', cardnumber ='"+cardnumber+"', expirydate='" + expirydate+"',cvv='" +cvv+"', fullname='"+fullname+"' Where payment_id = "+payment_id;
         st.executeUpdate(fetch);
    } 
    
    public void deletePayment(int payment_id) throws SQLException{ 
        //st.executeUpdate("DELETE FROM GROUP45.PAYMENT WHERE Payment_id='" + payment_id + "'");
        st.executeUpdate("DELETE FROM GROUP45.PAYMENT WHERE payment_id="+payment_id);
    }
    
    //public void updatePayment(String paymethod, String cardnumber, String expirydate, String cvv, String fullname) throws SQLException {       //code for update-operation   
    //   st.executeUpdate("UPDATE ISDUSER.Students SET NAME='" + paymethod + "',Password='" + cardnumber + "',dob='" + expirydate + "' WHERE EMAIL='" + email + "'"); 
    //}

    public LinkedList<Payment> fetchPayments() throws SQLException {
        String fetch = "select * FROM GROUP45.PAYMENT";
        ResultSet rs = st.executeQuery(fetch);
        
        LinkedList<Payment> temp = new LinkedList<Payment>();
    
        while (rs.next()) {
           int listPayment_id = rs.getInt(1);
                String paymethod = rs.getString(2);
                String cardnumber = rs.getString(3);
                String expirydate = rs.getString(4);
                String cvv = rs.getString(5);
                String fullname = rs.getString(6);
                int orderId = rs.getInt(7);
                String date = rs.getString(8);
                String paystatus = rs.getString(9);
                Double amount = rs.getDouble(10);
              temp.add (new Payment(listPayment_id,paymethod,cardnumber,expirydate,cvv,fullname,date,paystatus,amount));
           
        }
        return temp;
}
     public LinkedList<Payment> fetchPaymentsByPaymentid(int payment_id) throws SQLException{
        String fetch = "select * FROM GROUP45.PAYMENT WHERE payment_id="+payment_id;
        ResultSet rs = st.executeQuery(fetch);
        LinkedList<Payment> temp = new LinkedList<Payment>();
        while(rs.next()){
            int listPayment_id = rs.getInt(1);
                String paymethod = rs.getString(2);
                String cardnumber = rs.getString(3);
                String expirydate = rs.getString(4);
                String cvv = rs.getString(5);
                String fullname = rs.getString(6);
                int orderId = rs.getInt(7);
                String date = rs.getString(8);
                String paystatus = rs.getString(9);
                Double amount = rs.getDouble(10);
              temp.add (new Payment(listPayment_id,paymethod,cardnumber,expirydate,cvv,fullname,date,paystatus,amount));
           
        }
        return temp;
    }
    public void submitPayment(int id) throws SQLException{
        String fetch = "Update GROUP45.PAYMENT Set PAYSTATUS='submit' WHERE PAYMENT_ID = " +id;
        st.executeUpdate(fetch);
    }
     
     
     
    public LinkedList<Payment> fetchPaymentsByDate(String date,String email) throws SQLException{
        String fetch = "select Payment_ID,PayMethod,Cardnumber,expirydate,cvv,fullName,Group45.payment.order_id,payDT,payStatus,amount from GROUP45.PAYMENT,Group45.Orders where email='"+email+"' And GROUP45.orders.order_Id = Group45.payment.order_ID And Group45.payment.payDT like '%"+ date+"%'";
        ResultSet rs = st.executeQuery(fetch);
        LinkedList<Payment> temp = new LinkedList<Payment>();
        while(rs.next()){
           int listPayment_id = rs.getInt(1);
                String paymethod = rs.getString(2);
                String cardnumber = rs.getString(3);
                String expirydate = rs.getString(4);
                String cvv = rs.getString(5);
                String fullname = rs.getString(6);
                int orderId = rs.getInt(7);
                String paystatus = rs.getString(9);
                Double amount = rs.getDouble(10);
              temp.add (new Payment(listPayment_id,paymethod,cardnumber,expirydate,cvv,fullname,date,paystatus,amount));
           
        }
        return temp;
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.models.dao;
import iot.models.OrderItem;
import iot.models.Order;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderManager 
{
    private Statement st;
    private productDBManager pdb;
    public OrderManager(Connection conn) throws SQLException
    {
        st = conn.createStatement();
        pdb = new productDBManager(conn);
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
    
 
    
    public int findLastOrderId()throws SQLException{
       String fetch = "Select Order_ID From Group45.Orders Where Order_ID = (select max(Order_ID) from Group45.Orders)";
       ResultSet rs = st.executeQuery(fetch);
       while(rs.next()){
           return rs.getInt(1);
       }
       return 0;
    }
    public void addItem(int orderID,int quantity,String pName)throws SQLException{
         String fetch = "Insert into GROUP45.OrderItem (ORDER_ID,QUANTITY,pNAME) Values("+ orderID+"," + quantity +",'"+pName+"')";
          st.executeUpdate(fetch);
    }
    public void updateItem(int itemId,int quantity) throws SQLException{
        String fetch = "Update Group45.ORDERITEM SET QUANTITY = Quantity + "+quantity+" Where ITEM_ID = " + itemId;
        st.executeUpdate(fetch);
    }
    public OrderItem findItem(int id)throws SQLException{
         String fetch = "Select GROUP45.ORDERITEM.pNAME,pPrice,quantity FROM GROUP45.PRODUCT, GROUP45.ORDERITEM WHERE Item_ID = " + id + "AND  GROUP45.PRODUCT.pName = GROUP45.ORDERITEM.pName";
        ResultSet rs = st.executeQuery(fetch);
        while(rs.next()){
             String name = rs.getString(2);
            double price = rs.getDouble(3);
            int quantity = rs.getInt(4);
            return new OrderItem(id,name,price,quantity);
        }
        return null;
    }
    
    public void deleteItem(int id)throws SQLException{
        String fetch = "DELETE FROM Group45.OrderItem Where ITEM_ID = "+id;
        st.executeUpdate(fetch);
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
    
    public void addOrder(String date, String status, String email)throws SQLException{
        String fetch = "Insert into GROUP45.ORDERS (ORDERDT,STATUS,EMAIL) VALUES('"+date +"','"+ status+"','"+email+"')";
         st.executeUpdate(fetch);
    }
    
    public LinkedList<Order> findOrderList(String email)throws SQLException {
        String fetch = "select * from Group45.Orders Where email='"+email+"'";
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
    
    public LinkedList<Order> fetchOrderListByNum(String email,int num)throws SQLException {
        String fetch = "select * from Group45.Orders Where email='"+email+"' AND Order_ID ="+num;
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
    
    public LinkedList<Order> fetchOrderListByDate(String email,String date)throws SQLException {
        String fetch = "select * from Group45.Orders Where email='"+email+"' And Orderdt like '%"+date+"%'";
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
    
    public Order findLastOrder(String email) throws SQLException{
       //  Where Order_ID = (select max(Order_ID) from Group45.Orders
        String fetch = "Select * From GROUP45.ORDERS WHERE EMAIL='"+email+"' And Order_ID = (select max(Order_ID) from Group45.Orders WHERE email ='"+email+"')";
    
        ResultSet rs = st.executeQuery(fetch);
        while(rs.next()){
           int id = rs.getInt(1);
           String date = rs.getString(2);
           String status = rs.getString(3);
           LinkedList <OrderItem> items = findItems(id);
           return new Order(date,items,status,email);
       }
        return null;
        
    }
    
    public void updateOrder(int id,String status) throws SQLException{
        java.sql.Date reDate = new java.sql.Date(System.currentTimeMillis());
        String date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(reDate);
        String fetch = "Update GROUP45.Orders SET STATUS='"+ status+"', ORDERDT = '"+ date +"' WHERE ORDER_ID = "+ id;
        st.executeUpdate(fetch);
    }
    
    
   
}
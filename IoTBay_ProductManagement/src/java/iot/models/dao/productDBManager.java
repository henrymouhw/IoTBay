package iot.models.dao;
import java.sql.*;
import iot.models.*;
import java.util.ArrayList;

/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class productDBManager {

    private Statement st;
   
    public productDBManager(Connection conn) throws SQLException {       
       st = conn.createStatement();   
    }
    
    //Search product by name
    public product findProductByName(String name) throws SQLException{
        
        String fetch = "SELECT * from GROUP45.PRODUCT where pName = '" + name + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while(rs.next()) {
            
            String productName = rs.getString(1);
            
            if(productName.equals(name)) {
        
                String productBrand = rs.getString(2);
                String productType = rs.getString(3);
                String productDescription = rs.getString(4);
                Double productPrice = rs.getDouble(5);
                int productStock = rs.getInt(6);
                String productState = rs.getString(7);
                return new product(productName, productBrand, productType, productDescription, productPrice, productStock,productState);
            }
        }
        
        return null;
        
    }
    
    //Search product by brand
    public product findProductByType(String type) throws SQLException {
        
        String fetch = "SELECT * from GROUP45.PRODUCT where pType = '" + type + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while(rs.next()) {
            
            String productType = rs.getString(3);
            
            if(productType.equals(type)) {
                
                String productName = rs.getString(1);
                String productBrand = rs.getString(2);
                String productDescription = rs.getString(4);
                Double productPrice = rs.getDouble(5);
                int productStock = rs.getInt(6);
                String state = rs.getString(7);
                
                return new product(productName, productBrand, productType, productDescription, productPrice, productStock,state);
            
            }
        }
        return null; 
    }
    
    //Add product into the database
    public void addProduct(String name, String brand, String type, String description, Double price, int stock) throws SQLException {
        st.executeUpdate("INSERT INTO GROUP45.PRODUCT " + "VALUES ('" + name + "', '" + brand + "', '" + type + "', '" + description + "', " + price + ", " + stock + ",'"+"selling"+"')");
    }
    //Update product database
    public void updateProduct(String name, String brand, String type, String description, Double price, int stock) throws SQLException {
        st.executeUpdate("UPDATE GROUP45.PRODUCT SET pBRAND ='" + brand +"', pTYPE ='" + type + "', pDESCRIPTION ='" + description + "', pPRICE = "+ price +" , pSTOCK =" + stock + " WHERE pNAME ='" + name + "'");
    }
    
    public void cancelProduct(String name)throws SQLException{
        st.executeUpdate("UPDATE Group45.product SET PSTATE = 'Cancel' WHERE PNAME ='"+ name +"'");
    }
    
     public void activeProduct(String name)throws SQLException{
        st.executeUpdate("UPDATE Group45.product SET PSTATE = 'selling' WHERE PNAME ='"+ name +"'");
    }
    //Delete product from database
    public void deleteProduct(String name) throws SQLException {
        st.executeUpdate("DELETE FROM GROUP45.Product WHERE pName ='" + name + "'");
    }
    
    //Get all products
    public ArrayList<product> fetchProduct() throws SQLException { // cancel product will not include
        
        String fetch = "select * from GROUP45.PRODUCT WHERE PSTATE = 'selling'";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<product> temp = new ArrayList();
        
        while(rs.next()) {
            String name = rs.getString(1);
            String brand = rs.getString(2);
            String type = rs.getString(3);
            String description = rs.getString(4);
            Double price = rs.getDouble(5);
            int stock = rs.getInt(6);
            String state = rs.getString(7);
            temp.add(new product(name, brand, type, description, price, stock,state));        
        }
        return temp;
    }
    
    public ArrayList<product> fetchAllProduct() throws SQLException {
        
        String fetch = "select * from GROUP45.PRODUCT";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<product> temp = new ArrayList();
        while(rs.next()) {
            String name = rs.getString(1);
            String brand = rs.getString(2);
            String type = rs.getString(3);
            String description = rs.getString(4);
            Double price = rs.getDouble(5);
            int stock = rs.getInt(6);
            String state = rs.getString(7);
            temp.add(new product(name, brand, type, description, price, stock,state));
        }
        return temp;
    }
    
    
    //Check product
    public boolean checkProduct(String pName) throws SQLException {
        String fetch = "select * from GROUP45.PRODUCT WHERE pName = '" + pName + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while(rs.next()){
            String name = rs.getString(1);
            if(name.equals(pName)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<product> fetchProductByName(String name) throws SQLException {
        String fetch = "select * from GROUP45.PRODUCT where pname='" + name +"' And pstate='selling'";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<product> temp = new ArrayList();
        
        while(rs.next()) {
            String brand = rs.getString(2);
            String type = rs.getString(3);
            String description = rs.getString(4);
            Double price = rs.getDouble(5);
            int stock = rs.getInt(6);
            String state = rs.getString(7);
            temp.add(new product(name, brand, type, description, price, stock,state));
        }
        return temp;
    }
        public ArrayList<product> fetchAllProductByName(String name) throws SQLException {
        String fetch = "select * from GROUP45.PRODUCT where pname='" + name +"'";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<product> temp = new ArrayList();
        
        while(rs.next()) {
            String brand = rs.getString(2);
            String type = rs.getString(3);
            String description = rs.getString(4);
            Double price = rs.getDouble(5);
            int stock = rs.getInt(6);
            String state = rs.getString(7);
            temp.add(new product(name, brand, type, description, price, stock,state));
        }
        return temp;
    }
    
    public ArrayList<product> fetchProductByType(String type) throws SQLException {
        String fetch = "SELECT * FROM GROUP45.PRODUCT WHERE pTYPE='" + type + "' And pSTATE = 'selling' ";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<product> temp = new ArrayList();
        
        while(rs.next()) {
            String name = rs.getString(1);
            String brand = rs.getString(2);
            String description = rs.getString(4);
            Double price = rs.getDouble(5);
            int stock = rs.getInt(6);
            String state = rs.getString(7);
            if(state.equals("selling"))
            temp.add(new product(name, brand, type, description, price, stock,state));
        }
        return temp;
    }
    
    public ArrayList<product> fetchAllProductByType(String type) throws SQLException {
        String fetch = "SELECT * FROM GROUP45.PRODUCT WHERE pTYPE='" + type + "'";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<product> temp = new ArrayList();
        
        while(rs.next()) {
            String name = rs.getString(1);
            String brand = rs.getString(2);
            String description = rs.getString(4);
            Double price = rs.getDouble(5);
            int stock = rs.getInt(6);
            String state = rs.getString(7);
            if(state.equals("selling"))
            temp.add(new product(name, brand, type, description, price, stock,state));
        }
        return temp;
    }
    
}
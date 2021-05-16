/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import iot.models.Order;
import iot.models.OrderItem;
import iot.models.product;
import iot.models.dao.*;
/**
 *
 * @author Jiale Xue
 */
public class TestOrderDB {
    
    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private productDBManager pdb;
    private OrderManager db;
    
    public static void main(String[] args) throws SQLException {
        (new TestOrderDB()).runQueries();
    }
    
    public TestOrderDB() {
        try{
            connector = new DBConnector();
            conn = connector.openConnection();
            pdb = new productDBManager(conn);
            db = new OrderManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestOrderDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private char readChoice() {
         System.out.print("Please enter your test:  :");
        return in.nextLine().toUpperCase().charAt(0);
    }
    
    private void runQueries() throws SQLException{
         char c = readChoice();
        switch(c){
            case 'A': testAddItem();runQueries();break;
            case 'D': testDelete();runQueries();break;
            case 'X':
                exit();break;
            default: System.out.println("You type unknow choice: ");
        }
    }
    private void testDelete(){
        System.out.print("please enter Item Id You want to delete: ");
        int id = in.nextInt();
        in.nextLine();
         try { 
             OrderItem ot = db.findItem(id);
             if(ot==null){ System.out.println(id+ " is not exists in database");
             }else{
                 db.deleteItem(id);
                 System.out.println("test pass");
             }
         } catch( SQLException ex){
            Logger.getLogger(TestOrderDB.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
    private void testAddItem(){
        System.out.print("Please enter Order ID: ");
        int id = in.nextInt();
        in.nextLine();
         try {
          Order o = db.findOrder(id);
         if(o==null){
            System.out.println("This order ID is not exists");
         } else{
                System.out.print("Please enter quantity: ");
                int quantity = in.nextInt();
                in.nextLine();
                System.out.print("Please enter product Name: ");
                String name = in.nextLine();
                product p = pdb.findProductByName(name);
                if (p == null){System.out.println(name+ " is not in database");
                } else if (p.getProductStock() > quantity){
                    db.addItem(id,quantity,name);
                    pdb.updateProduct(name, p.getProductBrand(), p.getProductType(), p.getProductDescription(),p.getProductPrice(), p.getProductStock()-quantity);
                    System.out.println("test pass");
                } else{System.out.println("The product did not have enough Product");}   
         }      
         }catch( SQLException ex){
            Logger.getLogger(TestOrderDB.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    private void exit()
    {System.out.println("Finish Testing");}
    
  
    
}

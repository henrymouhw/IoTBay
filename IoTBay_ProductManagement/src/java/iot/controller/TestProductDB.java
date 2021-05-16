/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import iot.models.product;
import iot.models.dao.*;
/**
 *
 * @author Jiale Xue
 */
public class TestProductDB {
    
    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private productDBManager pdb;
    
    public static void main(String[] args) throws SQLException {
        (new TestProductDB()).runQueries();
    }
    
    public TestProductDB() {
        try{
            connector = new DBConnector();
            conn = connector.openConnection();
            pdb = new productDBManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestProductDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private char readChoice() {
        System.out.print("Operation C, N, T, U, D, S OR * to exit: ");
        //return in.nextLine().charAt(0);
        String line = in.nextLine();
          while(line.length() <1){
             line = in.nextLine();
          }
          return Character.toUpperCase(line.charAt(0));
    }
    
    private void runQueries() throws SQLException{
        char c;

        while((c = readChoice()) != '*'){
           switch (c) {
              case 'C':
                 testAdd();
                 break;
              case 'N':            
                 testReadByName();
                 break;
              case 'T':            
                 testReadByType();
                 break;
              case 'U':
                 testUpdate();
                 break;
              case 'D':
                 testDelete();
                 break;
              case 'S':
                 showAll();
                 break;
              default:
                 System.out.println("Unknown Command");
                 break;
           }
        }
    }
    
    private void testAdd() throws SQLException {
        System.out.print("Name: ");
        String name = in.nextLine();
        System.out.print("Brand: ");
        String brand = in.nextLine();
        System.out.print("Type: ");
        String type = in.nextLine();
        System.out.print("Description: ");
        String description = in.nextLine();
        
        System.out.print("Price: ");
        Double price = in.nextDouble();
        System.out.print("Stock: ");
        int stock = in.nextInt();
        
        try{
            pdb.addProduct(name, brand, type, description, price, stock);
        } catch (SQLException ex){
            Logger.getLogger(TestProductDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Product is added to the database.");
        
    }
    
    private void testReadByName() throws SQLException {
        System.out.print("Name: ");
        String name = in.nextLine();
        product products = pdb.findProductByName(name);
        if(products != null){
            System.out.println("Staff exists in the database.");
        } else {
            System.out.println("Staff does not exist.");
        }
    }
    
    private void testReadByType() throws SQLException {
        System.out.println("Type: ");
        String type = in.nextLine();
        product product = pdb.findProductByType(type);
        if(product != null) {
            System.out.println("Type exists in the database.");
        } else {
            System.out.println("Type does not exist.");
        }
    }
    
    private void testUpdate() {
        System.out.print("Name: ");
        String name = in.nextLine();
        
        try{
            if(pdb.checkProduct(name)) {
                System.out.print("Brand: ");
                String brand = in.nextLine();
                System.out.print("Type: ");
                String type = in.nextLine();
                System.out.print("Description: ");
                String description = in.nextLine();
                
                System.out.print("Price: ");
                Double price = in.nextDouble();
                System.out.print("Stock: ");
                int stock = in.nextInt();
                
                pdb.updateProduct(name, brand, type, description, price, stock);
            } else {
                System.out.println("Product does not exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestProductDB.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }
    
    private void testDelete() {
        System.out.println("Name: ");
        String name = in.nextLine();
        
        try{
            if(pdb.checkProduct(name)) {
                pdb.deleteProduct(name);
            } else {
                System.out.println("Product does not exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestProductDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showAll() {
        try{
            ArrayList<product> products = pdb.fetchProduct();
            System.out.println("Product table: ");
            products.stream().forEach((product) -> {
                System.out.printf("%-30s %-30s %-30s %-30s %-30s %-30s\n", product.getProductName(), product.getProductBrand(), product.getProductType(), product.getProductDescription(), product.getProductPrice(), product.getProductStock());
            });
            System.out.println();
        } catch (SQLException ex){
            Logger.getLogger(TestProductDB.class.getName()).log(Level.SEVERE, null, ex);    
        }
    }
    
}

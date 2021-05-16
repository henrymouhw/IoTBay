package iot.controller;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import iot.models.StaffRecord;
import iot.models.dao.*;

public class TestStaffDB{
    
    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private StaffDBManager sdb;

    public static void main(String[] args) throws SQLException{
        (new TestStaffDB()).runQueries();
    }
 
    public TestStaffDB(){
        try{
            connector = new DBConnector();
            conn = connector.openConnection();
            sdb = new StaffDBManager(conn);
            
         } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestStaffDB.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    private char readChoice(){
        System.out.print("Operation C, N, P, U, D, S OR * to exit: ");
        return in.nextLine().charAt(0);
    }

    private void runQueries() throws SQLException{
        char c;

        while((c = readChoice()) != '*'){
           switch (c) {
              case 'C':
                 testAdd();
                 break;
              case 'N':            
                 testReadbyName();
                 break;
              case 'P':            
                 testReadbyPosition();
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

    private void testAdd(){
        System.out.print("Name: ");
        String name = in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.print("Position: ");
        String position = in.nextLine();
        System.out.print("address: ");
        String address = in.nextLine();
        System.out.print("Status: ");
        String status = in.nextLine();
         
        try{
            sdb.addStaff(name, email, position, address, status);
        } catch (SQLException ex){
            Logger.getLogger(TestStaffDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Staff is added to the database.");
    }

    private void testReadbyName() throws SQLException {
        System.out.print("Name: ");
        String name = in.nextLine();
        StaffRecord staff = sdb.findStaffbyName(name);
        if(staff != null){
            System.out.println("Staff exists in the database.");
        } else {
            System.out.println("Staff does not exist.");
        }
    }

    private void testReadbyPosition() throws SQLException {
        System.out.print("Position: ");
        String position = in.nextLine();
        StaffRecord staff = sdb.findStaffbyPosition(position);
        if(staff != null){
            System.out.println("Staff exists in the database.");
        } else {
            System.out.println("Staff does not exist.");
        }
    }

    private void testUpdate(){
        System.out.print("Email: ");
        String email = in.nextLine();
        
        try {
            if(sdb.checkStaff(email)){
                System.out.print("Name: ");
                String name = in.nextLine();
                System.out.print("Position: ");
                String position = in.nextLine();
                System.out.print("Address: ");
                String address = in.nextLine();
                System.out.print("Status: ");
                String status = in.nextLine();
                sdb.updateStaff(name, email, position, address, status);
            } else {
                System.out.println("Staff does not exist.");
            }        
        } catch (SQLException ex) {
                Logger.getLogger(TestStaffDB.class.getName()).log(Level.SEVERE, null, ex);    
        }
    }

    private void testDelete() {
        System.out.print("Email: ");
        String email = in.nextLine();
     
        try{
            if(sdb.checkStaff(email)) {
                 sdb.deleteStaff(email);
            } else {
                 System.out.println("Staff does not exist.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestStaffDB.class.getName()).log(Level.SEVERE, null, ex);    
        }
    }
    
    private void showAll(){
        try {
            ArrayList<StaffRecord> staffs = sdb.fetchStaff();
            System.out.println("Staff Table: ");
            staffs.stream().forEach((staff) -> {
                System.out.printf("%-30s %-30s %-30s %-30s %-30s\n", staff.getName(), staff.getEmail(), staff.getPosition(), staff.getAddress(), staff.getStatus());
            });
            System.out.println();
        } catch (SQLException ex){
            Logger.getLogger(TestStaffDB.class.getName()).log(Level.SEVERE, null, ex);    
        }
    }
}
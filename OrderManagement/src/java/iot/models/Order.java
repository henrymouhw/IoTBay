/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.models;
import java.util.*;
import java.text.DecimalFormat;
/**
 *
 * @author lin
 */
public class Order {
   private String date;
   private LinkedList<OrderItem> items;
   private String status;
   private String email;
   private int id;

    public Order(String date, LinkedList<OrderItem> items, String status, String email) {
        this.date = date;
        this.items = items;
        this.status = status;
        this.email = email;
    }
    public Order(String date,String status,String email){
        this.date = date;
        this.status = status;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LinkedList<OrderItem> getItems() {
        return items;
    }

    public void setItems(LinkedList<OrderItem> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String total(){
        double totalPrice = 0;
        for(OrderItem item : items){
            totalPrice = totalPrice + item.getTotalInDouble();
        }
        DecimalFormat df = new DecimalFormat("#.00");
       return df.format(totalPrice);
    }
    
    public double getTotal(){
        double totalPrice = 0;
        for(OrderItem item : items){
            totalPrice = totalPrice + item.getTotalInDouble();
        }
       return totalPrice;
    }
}

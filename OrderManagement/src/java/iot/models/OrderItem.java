/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.models;
import java.text.DecimalFormat;
/**
 *
 * @author lin
 */
public class OrderItem {
    private int id;
    private String itemName;
    private double itemPrice;
    private int quantity;

    public OrderItem(int id,String itemName, double itemPrice, int quantity) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getTotalInDouble(){
        return itemPrice * quantity;
    }
            
    public String total(){
       DecimalFormat df = new DecimalFormat("#0.00");
       return df.format(itemPrice * quantity);
    }
}

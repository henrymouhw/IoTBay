package iot.models;

/**
 *
 * @author 74152
 */

public class Payment {
    private String paymethod;
    private String cardnumber;
    private String expirydate;
    private String cvv;
    private String fullname;
    private String date;
    private double amount;
    private String status;
    private int payment_id;

  
   public Payment(int payment_id, String paymethod, String cardnumber, String expirydate, String cvv, String fullname, String date,String status,double amount) {
        
        this.paymethod = paymethod;
        this.cardnumber = cardnumber;
        this.expirydate = expirydate;
        this.cvv = cvv;
        this.fullname = fullname;
        this.date = date;
        this.payment_id = payment_id;
        this.amount = amount;
        this.status = status;
   }

 
   
    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpiry_date(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    

 

    
}
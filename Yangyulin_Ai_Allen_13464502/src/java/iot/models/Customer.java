package iot.models;

/**
 *
 * @author Think
 * 02-DD
 */
public class Customer extends User
{
    //private String email; // PK

    protected String userAddress;

    public Customer(String email, String password, String userType, String userName, String gender, String dob, String status, String userPhoneNum) {
        super(email, password, userType, userName, gender, dob, status, userPhoneNum);
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }   
      
}

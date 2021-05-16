package iot.models;
/**
 *
 * @author Yangyulin AI
 * This class is for login to IoTBay system.(01-DD)
 * userID - pk
 * 
 */

public class User
{
    protected String email;
    protected String password;
    protected String userType;
    protected String userName;
    protected String gender;
    protected String dob;
    protected String status;
    protected String userPhoneNum;

    public User(String email, String password, String userType, String userName, String gender, String dob, String status, String userPhoneNum) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.userName = userName;
        this.gender = gender;
        this.dob = dob;
        this.status = status;
        this.userPhoneNum = userPhoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }
    

}

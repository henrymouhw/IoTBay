package iot.models;

/**
 *
 * @author Think
 * 12-DD
 */

public class Staff extends User
{
    //private String email;// pk
    private String occupation;
    private String code;

    public Staff(String email, String password, String userType, String userName, String gender, String dob, String status, String userPhoneNum) {
        super(email, password, userType, userName, gender, dob, status, userPhoneNum);
    }
    
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    
    
    
    
    
}

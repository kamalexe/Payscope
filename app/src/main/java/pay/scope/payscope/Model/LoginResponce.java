package pay.scope.payscope.Model;

public class LoginResponce {
    private boolean status;
    //    private String mobile_no;
    private String message;
    private String role;
    private String otp_verifiaction;
    private String token;


    public boolean isStatus() {
        return status;
    }


//    public String getMobile_no() {
//        return mobile_no;
//    }
//
//    public void setMobile_no(String mobile_no) {
//        this.mobile_no = mobile_no;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOtp_verifiaction() {
        return otp_verifiaction;
    }

    public void setOtp_verifiaction(String otp_verifiaction) {
        this.otp_verifiaction = otp_verifiaction;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

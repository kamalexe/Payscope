package pay.scope.payscope.Model;

public class ResendOTPModel {
    String email;

    public ResendOTPModel(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

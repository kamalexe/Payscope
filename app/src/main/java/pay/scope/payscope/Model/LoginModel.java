package pay.scope.payscope.Model;

public class LoginModel {
    private String username;
    private String password;
    private double latitude;
    private double logitude;
    private String ip_address;

    public LoginModel(String username, String password, double latitude, double logitude, String ip_address) {
        this.username = username;
        this.password = password;
        this.latitude = latitude;
        this.logitude = logitude;
        this.ip_address = ip_address;
    }

    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLogitude() {
        return logitude;
    }

    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
// Getters and setters
}


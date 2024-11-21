package pay.scope.payscope.Model;

public class CashBackModel {

    String name;
    String time;
    String panding;
    double price;

    public CashBackModel() {
    }

    public CashBackModel(String name, String time, String panding, double price) {
        this.name = name;
        this.time = time;
        this.panding = panding;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPanding() {
        return panding;
    }

    public void setPanding(String panding) {
        this.panding = panding;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

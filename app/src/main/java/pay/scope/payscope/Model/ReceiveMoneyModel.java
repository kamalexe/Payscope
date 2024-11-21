package pay.scope.payscope.Model;

public class ReceiveMoneyModel {

    String name;
    String time;
    double price;
    int img_uri;

    public ReceiveMoneyModel() {
    }

    public ReceiveMoneyModel(String name, String time, double price, int img_uri) {
        this.name = name;
        this.time = time;
        this.price = price;
        this.img_uri = img_uri;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImg_uri() {
        return img_uri;
    }

    public void setImg_uri(int img_uri) {
        this.img_uri = img_uri;
    }
}

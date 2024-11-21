package pay.scope.payscope.Model;

public class MyCartModel {

    String name;
    String send;
    String date;
    double price;
    int img_uri;


    public MyCartModel(String name, String send, String date, double price, int img_uri) {
        this.name = name;
        this.send = send;
        this.date = date;
        this.price = price;
        this.img_uri = img_uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

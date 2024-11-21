package pay.scope.payscope.Model;

public class StatementModel {
    int img_uri;
    double amount;
    String name;
    String bankName;
    String date;
    String expend;

    public StatementModel() {
    }

    public StatementModel(int img_uri, double amount, String name, String bankName, String date, String expend) {
        this.img_uri = img_uri;
        this.amount = amount;
        this.name = name;
        this.bankName = bankName;
        this.date = date;
        this.expend = expend;
    }

    public String getExpend() {
        return expend;
    }

    public void setExpend(String expend) {
        this.expend = expend;
    }

    public int getImg_uri() {
        return img_uri;
    }

    public void setImg_uri(int img_uri) {
        this.img_uri = img_uri;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

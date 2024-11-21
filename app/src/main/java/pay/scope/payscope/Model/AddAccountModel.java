package pay.scope.payscope.Model;

public class AddAccountModel {

    int img_uri;
    String account_name ;
    String account_number;


    public AddAccountModel() {
    }

    public AddAccountModel(int img_uri, String account_name, String account_number) {
        this.img_uri = img_uri;
        this.account_name = account_name;
        this.account_number = account_number;
    }

    public int getImg_uri() {
        return img_uri;
    }

    public void setImg_uri(int img_uri) {
        this.img_uri = img_uri;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }
}

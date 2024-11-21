package pay.scope.payscope.Model;

public class PopularBankModel {

    String popularbank_Name;
    int popularbank_img;

    public PopularBankModel(String popularbank_Name, int popularbank_img) {
        this.popularbank_Name = popularbank_Name;
        this.popularbank_img = popularbank_img;
    }

    public String getPopularbank_Name() {
        return popularbank_Name;
    }

    public void setPopularbank_Name(String popularbank_Name) {
        this.popularbank_Name = popularbank_Name;
    }

    public int getPopularbank_img() {
        return popularbank_img;
    }

    public void setPopularbank_img(int popularbank_img) {
        this.popularbank_img = popularbank_img;
    }
}

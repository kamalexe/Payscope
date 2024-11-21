package pay.scope.payscope.Model;

public class DTHRechargeModel {
    int recharge_img;
    String recharge_Name;

    public DTHRechargeModel(int recharge_img, String recharge_Name) {
        this.recharge_img = recharge_img;
        this.recharge_Name = recharge_Name;
    }

    public int getRecharge_img() {
        return recharge_img;
    }

    public void setRecharge_img(int recharge_img) {
        this.recharge_img = recharge_img;
    }

    public String getRecharge_Name() {
        return recharge_Name;
    }

    public void setRecharge_Name(String recharge_Name) {
        this.recharge_Name = recharge_Name;
    }
}

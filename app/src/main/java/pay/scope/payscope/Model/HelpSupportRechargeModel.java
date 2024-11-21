package pay.scope.payscope.Model;

public class HelpSupportRechargeModel {
    private int helpSuppRecharge_Img;
    private String helpSuppRecharge_Name;

    public HelpSupportRechargeModel(int helpSuppRecharge_Img, String helpSuppRecharge_Name) {
        this.helpSuppRecharge_Img = helpSuppRecharge_Img;
        this.helpSuppRecharge_Name = helpSuppRecharge_Name;
    }

    public int getHelpSuppRecharge_Img() {
        return helpSuppRecharge_Img;
    }

    public void setHelpSuppRecharge_Img(int helpSuppRecharge_Img) {
        this.helpSuppRecharge_Img = helpSuppRecharge_Img;
    }

    public String getHelpSuppRecharge_Name() {
        return helpSuppRecharge_Name;
    }

    public void setHelpSuppRecharge_Name(String helpSuppRecharge_Name) {
        this.helpSuppRecharge_Name = helpSuppRecharge_Name;
    }
}

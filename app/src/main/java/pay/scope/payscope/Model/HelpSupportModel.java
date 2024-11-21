package pay.scope.payscope.Model;

public class HelpSupportModel {
    private int helpSupp_Img;
   private String helpSupp_Name;

    public HelpSupportModel(int helpSupp_Img, String helpSupp_Name) {
        this.helpSupp_Img = helpSupp_Img;
        this.helpSupp_Name = helpSupp_Name;
    }

    public int getHelpSupp_Img() {
        return helpSupp_Img;
    }

    public void setHelpSupp_Img(int helpSupp_Img) {
        this.helpSupp_Img = helpSupp_Img;
    }

    public String getHelpSupp_Name() {
        return helpSupp_Name;
    }

    public void setHelpSupp_Name(String helpSupp_Name) {
        this.helpSupp_Name = helpSupp_Name;
    }
}

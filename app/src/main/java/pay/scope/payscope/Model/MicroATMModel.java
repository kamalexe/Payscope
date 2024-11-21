package pay.scope.payscope.Model;

public class MicroATMModel {
   private String ATM_Name;
   private double ATM_Amount;

    public MicroATMModel(String ATM_Name, double ATM_Amount) {
        this.ATM_Name = ATM_Name;
        this.ATM_Amount = ATM_Amount;
    }

    public String getATM_Name() {
        return ATM_Name;
    }

    public void setATM_Name(String ATM_Name) {
        this.ATM_Name = ATM_Name;
    }

    public double getATM_Amount() {
        return ATM_Amount;
    }

    public void setATM_Amount(double ATM_Amount) {
        this.ATM_Amount = ATM_Amount;
    }
}

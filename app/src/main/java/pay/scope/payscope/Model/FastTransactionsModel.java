package pay.scope.payscope.Model;

public class FastTransactionsModel {
    String FastTrans_Name,FastTrans_Date;
    double FastTrans_Amount;

    public FastTransactionsModel(String fastTrans_Name, String fastTrans_Date, double fastTrans_Amount) {
        FastTrans_Name = fastTrans_Name;
        FastTrans_Date = fastTrans_Date;
        FastTrans_Amount = fastTrans_Amount;
    }

    public String getFastTrans_Name() {
        return FastTrans_Name;
    }

    public void setFastTrans_Name(String fastTrans_Name) {
        FastTrans_Name = fastTrans_Name;
    }

    public String getFastTrans_Date() {
        return FastTrans_Date;
    }

    public void setFastTrans_Date(String fastTrans_Date) {
        FastTrans_Date = fastTrans_Date;
    }

    public double getFastTrans_Amount() {
        return FastTrans_Amount;
    }

    public void setFastTrans_Amount(double fastTrans_Amount) {
        FastTrans_Amount = fastTrans_Amount;
    }
}

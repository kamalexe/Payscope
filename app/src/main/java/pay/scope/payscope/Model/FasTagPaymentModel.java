package pay.scope.payscope.Model;

public class FasTagPaymentModel {

    String FasTagPayment_Id,FasTagPayment_Name,FasTagPayment_Number;
    double FasTagPayment_Amount;
    int FasTagPayment_Img;

    public FasTagPaymentModel(String fasTagPayment_Id, String fasTagPayment_Name, String fasTagPayment_Number, double fasTagPayment_Amount, int fasTagPayment_Img) {
        FasTagPayment_Id = fasTagPayment_Id;
        FasTagPayment_Name = fasTagPayment_Name;
        FasTagPayment_Number = fasTagPayment_Number;
        FasTagPayment_Amount = fasTagPayment_Amount;
        FasTagPayment_Img = fasTagPayment_Img;
    }

    public String getFasTagPayment_Id() {
        return FasTagPayment_Id;
    }

    public void setFasTagPayment_Id(String fasTagPayment_Id) {
        FasTagPayment_Id = fasTagPayment_Id;
    }

    public String getFasTagPayment_Name() {
        return FasTagPayment_Name;
    }

    public void setFasTagPayment_Name(String fasTagPayment_Name) {
        FasTagPayment_Name = fasTagPayment_Name;
    }

    public String getFasTagPayment_Number() {
        return FasTagPayment_Number;
    }

    public void setFasTagPayment_Number(String fasTagPayment_Number) {
        FasTagPayment_Number = fasTagPayment_Number;
    }

    public double getFasTagPayment_Amount() {
        return FasTagPayment_Amount;
    }

    public void setFasTagPayment_Amount(double fasTagPayment_Amount) {
        FasTagPayment_Amount = fasTagPayment_Amount;
    }

    public int getFasTagPayment_Img() {
        return FasTagPayment_Img;
    }

    public void setFasTagPayment_Img(int fasTagPayment_Img) {
        FasTagPayment_Img = fasTagPayment_Img;
    }
}

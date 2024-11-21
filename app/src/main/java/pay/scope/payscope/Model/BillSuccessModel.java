package pay.scope.payscope.Model;

public class BillSuccessModel {

    String BillSuccess_Name;
    int BillSuccess_Img;
    double BillSuccess_Amount;
    String BillSuccess_Id;

    public BillSuccessModel(String billSuccess_Name, int billSuccess_Img, double billSuccess_Amount, String billSuccess_Id) {
        BillSuccess_Name = billSuccess_Name;
        BillSuccess_Img = billSuccess_Img;
        BillSuccess_Amount = billSuccess_Amount;
        BillSuccess_Id = billSuccess_Id;
    }

    public String getBillSuccess_Name() {
        return BillSuccess_Name;
    }

    public void setBillSuccess_Name(String billSuccess_Name) {
        BillSuccess_Name = billSuccess_Name;
    }

    public int getBillSuccess_Img() {
        return BillSuccess_Img;
    }

    public void setBillSuccess_Img(int billSuccess_Img) {
        BillSuccess_Img = billSuccess_Img;
    }

    public double getBillSuccess_Amount() {
        return BillSuccess_Amount;
    }

    public void setBillSuccess_Amount(double billSuccess_Amount) {
        BillSuccess_Amount = billSuccess_Amount;
    }

    public String getBillSuccess_Id() {
        return BillSuccess_Id;
    }

    public void setBillSuccess_Id(String billSuccess_Id) {
        BillSuccess_Id = billSuccess_Id;
    }
}

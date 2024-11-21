package pay.scope.payscope.Model;

public class BillPaymentModel {

    String BillPayment_Name;
    int BillPayment_Img;
    double BillPayment_Amount;
    String BillPayment_Date;
    String BillPayment_Id;
    Boolean checkBox;

    public BillPaymentModel(String billPayment_Name, int billPayment_Img, double billPayment_Amount, String billPayment_Date, String billPayment_Id, Boolean checkBox) {
        BillPayment_Name = billPayment_Name;
        BillPayment_Img = billPayment_Img;
        BillPayment_Amount = billPayment_Amount;
        BillPayment_Date = billPayment_Date;
        BillPayment_Id = billPayment_Id;
        this.checkBox = checkBox;
    }

    public String getBillPayment_Name() {
        return BillPayment_Name;
    }

    public void setBillPayment_Name(String billPayment_Name) {
        BillPayment_Name = billPayment_Name;
    }

    public int getBillPayment_Img() {
        return BillPayment_Img;
    }

    public void setBillPayment_Img(int billPayment_Img) {
        BillPayment_Img = billPayment_Img;
    }

    public double getBillPayment_Amount() {
        return BillPayment_Amount;
    }

    public void setBillPayment_Amount(double billPayment_Amount) {
        BillPayment_Amount = billPayment_Amount;
    }

    public String getBillPayment_Date() {
        return BillPayment_Date;
    }

    public void setBillPayment_Date(String billPayment_Date) {
        BillPayment_Date = billPayment_Date;
    }

    public String getBillPayment_Id() {
        return BillPayment_Id;
    }

    public void setBillPayment_Id(String billPayment_Id) {
        BillPayment_Id = billPayment_Id;
    }

    public Boolean getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(Boolean checkBox) {
        this.checkBox = checkBox;
    }
}
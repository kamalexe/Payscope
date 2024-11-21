package pay.scope.payscope.Model;

public class AllBankModel {
    String bank_Name;
    int bank_img;

    public AllBankModel() {
    }

    public AllBankModel(String bank_Name, int bank_img) {
        this.bank_Name = bank_Name;
        this.bank_img = bank_img;
    }

    public String getBank_Name() {
        return bank_Name;
    }

    public void setBank_Name(String bank_Name) {
        this.bank_Name = bank_Name;
    }

    public int getBank_img() {
        return bank_img;
    }

    public void setBank_img(int bank_img) {
        this.bank_img = bank_img;
    }
}

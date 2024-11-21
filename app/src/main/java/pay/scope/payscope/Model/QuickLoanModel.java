package pay.scope.payscope.Model;

public class QuickLoanModel {

    private int loanImg_uri;
    private double loanAmount;
    private String loanName;

    private String loanResult;
    private String loanDate;

    public QuickLoanModel(int loanImg_uri, double loanAmount, String loanName, String loanResult, String loanDate) {
        this.loanImg_uri = loanImg_uri;
        this.loanAmount = loanAmount;
        this.loanName = loanName;
        this.loanResult = loanResult;
        this.loanDate = loanDate;
    }

    public int getLoanImg_uri() {
        return loanImg_uri;
    }

    public void setLoanImg_uri(int loanImg_uri) {
        this.loanImg_uri = loanImg_uri;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }


    public String getLoanResult() {
        return loanResult;
    }

    public void setLoanResult(String loanResult) {
        this.loanResult = loanResult;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }
}

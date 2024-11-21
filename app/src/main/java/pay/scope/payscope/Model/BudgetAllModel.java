package pay.scope.payscope.Model;

public class BudgetAllModel {
    private String AllNote;
    private String AllDate;
    private String AllTime;
    private double AllAmount;


    public BudgetAllModel(String allNote, String allDate, String allTime, double allAmount) {
        AllNote = allNote;
        AllDate = allDate;
        AllTime = allTime;
        AllAmount = allAmount;
    }

    public String getAllNote() {
        return AllNote;
    }

    public void setAllNote(String allNote) {
        AllNote = allNote;
    }

    public String getAllDate() {
        return AllDate;
    }

    public void setAllDate(String allDate) {
        AllDate = allDate;
    }

    public String getAllTime() {
        return AllTime;
    }

    public void setAllTime(String allTime) {
        AllTime = allTime;
    }

    public double getAllAmount() {
        return AllAmount;
    }

    public void setAllAmount(double allAmount) {
        AllAmount = allAmount;
    }
}
package pay.scope.payscope.Model;

public class BudgetAddTextModel {

    private String AddName;
    private String AddTransactions;
    private String AddDate;
    private double AddAmount;

    public BudgetAddTextModel(String addName, String addTransactions, String addDate, double addAmount) {
        AddName = addName;
        AddTransactions = addTransactions;
        AddDate = addDate;
        AddAmount = addAmount;
    }

    public String getAddName() {
        return AddName;
    }

    public void setAddName(String addName) {
        AddName = addName;
    }

    public String getAddTransactions() {
        return AddTransactions;
    }

    public void setAddTransactions(String addTransactions) {
        AddTransactions = addTransactions;
    }

    public String getAddDate() {
        return AddDate;
    }

    public void setAddDate(String addDate) {
        AddDate = addDate;
    }

    public double getAddAmount() {
        return AddAmount;
    }

    public void setAddAmount(double addAmount) {
        AddAmount = addAmount;
    }
}

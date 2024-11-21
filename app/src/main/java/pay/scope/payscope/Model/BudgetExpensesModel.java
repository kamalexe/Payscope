package pay.scope.payscope.Model;

public class BudgetExpensesModel {
    private String ExpensesName, ExpensesDate, ExpensesTransactions;
    private double ExpensesAmount;

    public BudgetExpensesModel(String expensesName, String expensesDate, String expensesTransactions, double expensesAmount) {
        ExpensesName = expensesName;
        ExpensesDate = expensesDate;
        ExpensesTransactions = expensesTransactions;
        ExpensesAmount = expensesAmount;
    }

    public String getExpensesName() {
        return ExpensesName;
    }

    public void setExpensesName(String expensesName) {
        ExpensesName = expensesName;
    }

    public String getExpensesDate() {
        return ExpensesDate;
    }

    public void setExpensesDate(String expensesDate) {
        ExpensesDate = expensesDate;
    }

    public String getExpensesTransactions() {
        return ExpensesTransactions;
    }

    public void setExpensesTransactions(String expensesTransactions) {
        ExpensesTransactions = expensesTransactions;
    }

    public double getExpensesAmount() {
        return ExpensesAmount;
    }

    public void setExpensesAmount(double expensesAmount) {
        ExpensesAmount = expensesAmount;
    }
}
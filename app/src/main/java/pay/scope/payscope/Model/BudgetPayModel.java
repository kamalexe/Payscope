package pay.scope.payscope.Model;

public class BudgetPayModel {
   private String BudgetPayName;
   private int BudgetPayImg;
  private String BudgetPayBtn;


    public BudgetPayModel(String budgetPayName, int budgetPayImg,  String budgetPayBtn) {
        BudgetPayName = budgetPayName;
        BudgetPayImg = budgetPayImg;
        BudgetPayBtn = budgetPayBtn;
    }

    public String getBudgetPayBtn() {
        return BudgetPayBtn;
    }

    public void setBudgetPayBtn(String budgetPayBtn) {
        BudgetPayBtn = budgetPayBtn;
    }


    public String getBudgetPayName() {
        return BudgetPayName;
    }

    public void setBudgetPayName(String budgetPayName) {
        BudgetPayName = budgetPayName;
    }

    public int getBudgetPayImg() {
        return BudgetPayImg;
    }

    public void setBudgetPayImg(int budgetPayImg) {
        BudgetPayImg = budgetPayImg;
    }
}

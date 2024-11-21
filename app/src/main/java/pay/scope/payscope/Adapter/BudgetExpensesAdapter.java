package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.BudgetExpensesModel;
import pay.scope.payscope.R;

public class BudgetExpensesAdapter extends RecyclerView.Adapter<BudgetExpensesAdapter.ViewHolder> {
    private final Context context;
    List<BudgetExpensesModel> budgetExpensesModelList;
    public BudgetExpensesAdapter(Context context, List<BudgetExpensesModel> budgetExpensesModelList) {
        this.context = context;
        this.budgetExpensesModelList = budgetExpensesModelList;
    }

    @NonNull
    @Override
    public BudgetExpensesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.budgetexpenses_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetExpensesAdapter.ViewHolder holder, int position) {
        BudgetExpensesModel budgetExpensesModel = budgetExpensesModelList.get(position);
        holder.bind(budgetExpensesModel);
    }

    @Override
    public int getItemCount() {
        return budgetExpensesModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ExpensesName, ExpensesDate, ExpensesTransactions, ExpensesAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ExpensesName = itemView.findViewById(R.id.ExpensesName);
            ExpensesDate = itemView.findViewById(R.id.ExpensesDate);
            ExpensesTransactions = itemView.findViewById(R.id.ExpensesTransactions);
            ExpensesAmount = itemView.findViewById(R.id.ExpensesAmount);
        }

        public void bind(BudgetExpensesModel budgetExpensesModel) {
            ExpensesName.setText(budgetExpensesModel.getExpensesName());
            ExpensesDate.setText(budgetExpensesModel.getExpensesDate());
            ExpensesTransactions.setText(budgetExpensesModel.getExpensesTransactions());
            ExpensesAmount.setText(String.valueOf(budgetExpensesModel.getExpensesAmount()));
        }
    }
}

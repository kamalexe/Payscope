package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.BudgetAddTextModel;
import pay.scope.payscope.R;

public class BudgetAddTextAdapter extends RecyclerView.Adapter<BudgetAddTextAdapter.ViewHolder> {
    private final Context context;
    private final List<BudgetAddTextModel> budgetAddTextModelList;

    public BudgetAddTextAdapter(Context context, List<BudgetAddTextModel> budgetAddTextModelList) {
        this.context = context;
        this.budgetAddTextModelList = budgetAddTextModelList;
    }

    @NonNull
    @Override
    public BudgetAddTextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.addtext_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetAddTextAdapter.ViewHolder holder, int position) {
        BudgetAddTextModel budgetAddTextModel = budgetAddTextModelList.get(position);
        holder.BudgetAddName.setText(budgetAddTextModel.getAddName());
        holder.BudgetAddDate.setText(budgetAddTextModel.getAddDate());
        holder.BudgetAddTransactions.setText(budgetAddTextModel.getAddTransactions());
        holder.BudgetAddAmount.setText(String.valueOf(budgetAddTextModel.getAddAmount()));
    }

    @Override
    public int getItemCount() {
        return budgetAddTextModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView BudgetAddName, BudgetAddAmount,BudgetAddDate,BudgetAddTransactions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BudgetAddName = itemView.findViewById(R.id.BudgetAddName);
            BudgetAddAmount = itemView.findViewById(R.id.BudgetAddAmount);
            BudgetAddTransactions = itemView.findViewById(R.id.BudgetAddTransactions);
            BudgetAddDate = itemView.findViewById(R.id.BudgetAddDate);
        }
    }
}

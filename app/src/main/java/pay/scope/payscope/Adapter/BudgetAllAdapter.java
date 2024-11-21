package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.BudgetAllModel;
import pay.scope.payscope.R;

public class BudgetAllAdapter extends RecyclerView.Adapter<BudgetAllAdapter.ViewHolder> {
    private final Context context;
    private final List<BudgetAllModel> budgetAllModelList;
    public BudgetAllAdapter(Context context, List<BudgetAllModel> budgetAllModelList) {
        this.context = context;
        this.budgetAllModelList = budgetAllModelList;
    }

    @NonNull
    @Override
    public BudgetAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.budgetall_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetAllAdapter.ViewHolder holder, int position) {
        BudgetAllModel budgetAllModel = budgetAllModelList.get(position);
        holder.AllNote.setText(budgetAllModel.getAllNote());
        holder.AllDate.setText(budgetAllModel.getAllDate());
        holder.AllTime.setText(budgetAllModel.getAllTime());
        holder.AllAmount.setText(String.valueOf(budgetAllModel.getAllAmount()));

    }

    @Override
    public int getItemCount() {
        return budgetAllModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView AllNote, AllDate, AllTime, AllAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            AllNote = itemView.findViewById(R.id.BudgetAllNote);
            AllDate = itemView.findViewById(R.id.BudgetAllDate);
            AllTime = itemView.findViewById(R.id.BudgetAllTime);
            AllAmount = itemView.findViewById(R.id.BudgetAllAmount);
        }
    }
}

package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.BudgetPayModel;
import pay.scope.payscope.R;

public class BudgetPayAdapter extends RecyclerView.Adapter<BudgetPayAdapter.ViewHolder> {
    private final Context context;
    private final List<BudgetPayModel> budgetPayModelList;

    public BudgetPayAdapter(Context context, List<BudgetPayModel> budgetPayModelList) {
        this.context = context;
        this.budgetPayModelList = budgetPayModelList;
    }

    @NonNull
    @Override
    public BudgetPayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.budgetpay_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BudgetPayAdapter.ViewHolder holder, int position) {
        BudgetPayModel budgetPayModel = budgetPayModelList.get(position);
        holder.bind(budgetPayModel);
    }

    @Override
    public int getItemCount() {
        return budgetPayModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView BudgetPayName;
        ImageView BudgetPayImg;
        Button BudgetPayBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BudgetPayName = itemView.findViewById(R.id.BudgetPayName);
            BudgetPayImg = itemView.findViewById(R.id.BudgetPayImg);
            BudgetPayBtn = itemView.findViewById(R.id.BudgetPayBtn);
        }

        public void bind(BudgetPayModel budgetPayModel) {
            BudgetPayName.setText(budgetPayModel.getBudgetPayName());
            BudgetPayBtn.setText(budgetPayModel.getBudgetPayBtn());
            BudgetPayImg.setImageResource(budgetPayModel.getBudgetPayImg());

        }
    }
}

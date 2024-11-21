package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.FastTransactionsModel;
import pay.scope.payscope.R;

public class FastTransactionsAdapter extends RecyclerView.Adapter<FastTransactionsAdapter.ViewHolder> {
    Context context;
    List<FastTransactionsModel> fastTransactionsModelList;

    public FastTransactionsAdapter(Context context, List<FastTransactionsModel> transactionsModels) {
        this.context = context;
        this.fastTransactionsModelList = transactionsModels;
    }

    @NonNull
    @Override
    public FastTransactionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.fasttrans_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FastTransactionsAdapter.ViewHolder holder, int position) {
        FastTransactionsModel fastTransactionsModel = fastTransactionsModelList.get(position);
        holder.binds(fastTransactionsModel);
    }

    @Override
    public int getItemCount() {
        return fastTransactionsModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView FastTrans_Name, FastTrans_Date, FastTrans_Amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FastTrans_Name = itemView.findViewById(R.id.fastTrans_Name);
            FastTrans_Date = itemView.findViewById(R.id.fastTrans_Date);
            FastTrans_Amount = itemView.findViewById(R.id.fastTrans_Amount);
        }

        public void binds(FastTransactionsModel fastTransactionsModel) {
            FastTrans_Name.setText(fastTransactionsModel.getFastTrans_Name());
            FastTrans_Date.setText(fastTransactionsModel.getFastTrans_Date());
            FastTrans_Amount.setText(String.valueOf(fastTransactionsModel.getFastTrans_Amount()));
        }
    }
}

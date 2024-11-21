package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.StatementModel;
import pay.scope.payscope.R;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.ViewHolder> {
    Context context;
    List<StatementModel> statementModelList;

    public StatementAdapter(Context context, List<StatementModel> statementModelList) {
        this.context = context;
        this.statementModelList = statementModelList;
    }

    @NonNull
    @Override
    public StatementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.statement_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StatementAdapter.ViewHolder holder, int position) {
        StatementModel statementModel = statementModelList.get(position);
        holder.bind(statementModel);
    }

    @Override
    public int getItemCount() {
        return statementModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView statementDate, statementName, statementBankName, statementAmount,statementexpend;
        ImageView statementImg_Uri;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            statementDate = itemView.findViewById(R.id.StatementDate);
            statementName = itemView.findViewById(R.id.statementName);
            statementBankName = itemView.findViewById(R.id.statementBankName);
            statementAmount = itemView.findViewById(R.id.statementAmount);
            statementImg_Uri = itemView.findViewById(R.id.statementImg_Uri);
            statementexpend = itemView.findViewById(R.id.statementexpend);
        }

        public void bind(StatementModel statementModel) {
            statementAmount.setText(String.valueOf(statementModel.getAmount()));
            statementName.setText(statementModel.getName());
            statementBankName.setText(statementModel.getBankName());
            statementDate.setText(statementModel.getDate());
            statementexpend.setText(statementModel.getExpend());
            statementImg_Uri.setImageResource(statementModel.getImg_uri());

        }
    }
}

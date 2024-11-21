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
import pay.scope.payscope.Model.QuickLoanModel;
import pay.scope.payscope.R;

public class QuickLoanAdapter extends RecyclerView.Adapter<QuickLoanAdapter.ViewHolder> {
    private final Context context;
    private final List<QuickLoanModel> quickLoanModelList;

    public QuickLoanAdapter(Context context, List<QuickLoanModel> quickLoanModelList) {
        this.context = context;
        this.quickLoanModelList = quickLoanModelList;
    }

    @NonNull
    @Override
    public QuickLoanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.quickloan_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuickLoanAdapter.ViewHolder holder, int position) {
        QuickLoanModel quickLoanModel = quickLoanModelList.get(position);
        holder.bind(quickLoanModel);
        holder.itemView.setOnClickListener(v -> {
        });
    }

    @Override
    public int getItemCount() {
        return quickLoanModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView loanAmount, loanName, loanResult, loanDate;
        ImageView loanImg_uri;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            loanAmount = itemView.findViewById(R.id.loanAmount);
            loanName = itemView.findViewById(R.id.loanName);
            loanResult = itemView.findViewById(R.id.loanResult);
            loanDate = itemView.findViewById(R.id.loanDate);
            loanImg_uri = itemView.findViewById(R.id.loanIMg);

        }

        public void bind(QuickLoanModel quickLoanModel) {
            loanAmount.setText(String.valueOf(quickLoanModel.getLoanAmount()));
            loanName.setText(quickLoanModel.getLoanName());
            loanResult.setText(quickLoanModel.getLoanResult());
            loanDate.setText(quickLoanModel.getLoanDate());
            loanImg_uri.setImageResource(quickLoanModel.getLoanImg_uri());
        }
    }
}

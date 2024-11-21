package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pay.scope.payscope.Model.QuickHistoryModel;

import pay.scope.payscope.R;

public class QuickHistoryAdapter extends RecyclerView.Adapter<QuickHistoryAdapter.ViewHolder> {
   private final Context context;
   private List<QuickHistoryModel.Data> quickHistoryModelList;

    public QuickHistoryAdapter(Context context, List<QuickHistoryModel.Data> quickHistoryModelList) {
        this.context = context;
        this.quickHistoryModelList = new ArrayList<>(quickHistoryModelList);
        Collections.reverse(this.quickHistoryModelList);
    }

    @NonNull
    @Override
    public QuickHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.quickhistory_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuickHistoryAdapter.ViewHolder holder, int position) {
        QuickHistoryModel.Data quickHistoryModel = quickHistoryModelList.get(position);
        holder.userName.setText(quickHistoryModel.getUser_name());
        holder.ifse_code.setText(quickHistoryModel.getIfsc_code());
        holder.accountNo.setText(String.valueOf(quickHistoryModel.getAccount_number()));
        holder.amount.setText(String.valueOf(quickHistoryModel.getAmount()));
    }

    @Override
    public int getItemCount() {
        return quickHistoryModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, accountNo, ifse_code, amount, rupeesRecycler;
        ImageView imageView;
        CardView cardRecycler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.quickNameRecycler);
            accountNo = itemView.findViewById(R.id.quickAccountRecycler);
            amount = itemView.findViewById(R.id.quickAmountRecycler);
            ifse_code = itemView.findViewById(R.id.quickIFSCRecycler);
            imageView = itemView.findViewById(R.id.quickImgRecycler);
            rupeesRecycler = itemView.findViewById(R.id.quickRupeesRecycler);
            cardRecycler = itemView.findViewById(R.id.quickCardRecycler);

        }
    }
}

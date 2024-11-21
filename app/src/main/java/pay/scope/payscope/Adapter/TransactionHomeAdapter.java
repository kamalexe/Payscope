package pay.scope.payscope.Adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.TransactionModel;
import pay.scope.payscope.R;

public class TransactionHomeAdapter extends RecyclerView.Adapter<TransactionHomeAdapter.ViewHolder> {
    private static final int MAX_ITEMS = 5;
    Context context;
    List<TransactionModel.Data> transactionModelList;
    public TransactionHomeAdapter(Context context, List<TransactionModel.Data> transactionModelList) {
        this.context = context;
        this.transactionModelList = transactionModelList;
    }

    @NonNull
    @Override
    public TransactionHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_home_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Calculate the actual position of the item in the original list
        int actualPosition = transactionModelList.size() - MAX_ITEMS + position;
        if (actualPosition >= 0 && actualPosition < transactionModelList.size()) {
            TransactionModel.Data transactionModel = transactionModelList.get(actualPosition);
            holder.userName.setText(transactionModel.getUser_name());
            holder.bankName.setText(transactionModel.getBank_name());
            holder.transactionstatus.setText(transactionModel.getStatus());
            holder.accountNo.setText(String.valueOf(transactionModel.getAccount_number()));
            holder.amount.setText(String.valueOf(transactionModel.getAmount()));

            Log.d("Adapter", "Position: " + position);

            String status = transactionModel.getStatus();
            if (status != null) {
                int color;
                int cardColor;
                switch (status) {
                    case "Success":
                        color = ContextCompat.getColor(context, R.color.green);
                        cardColor = ContextCompat.getColor(context, R.color.lightgreen);
                        break;
                    case "Failed":
                        color = ContextCompat.getColor(context, R.color.red);
                        cardColor = ContextCompat.getColor(context, R.color.lightred);
                        break;
                    case "Pending":
                        color = ContextCompat.getColor(context, R.color.yellow);
                        cardColor = ContextCompat.getColor(context, R.color.lightyellow);
                        break;
                    default:
                        color = ContextCompat.getColor(context, R.color.black);
                        cardColor = ContextCompat.getColor(context, R.color.lightblack);
                        break;
                }
                holder.amount.setTextColor(color);
                holder.transactionstatus.setTextColor(color);
                holder.rupeesrecycler.setTextColor(color);
                holder.img.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                holder.cardrecycler.setCardBackgroundColor(cardColor);
            }
        }
    }

    @Override
    public int getItemCount() {
        /*
        if (transactionModelList.size() > MAX_ITEMS) {
            return MAX_ITEMS;
        } else {
            return transactionModelList.size();
        }
         */
        return Math.min(transactionModelList.size(), MAX_ITEMS);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, accountNo, bankName, amount, rupeesrecycler,transactionstatus;
        ImageView img;
        CardView cardrecycler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.transaction_recyclerName);
            accountNo = itemView.findViewById(R.id.transaction_recyclerAccount);
            amount = itemView.findViewById(R.id.transaction_recyclerAmount);
            bankName = itemView.findViewById(R.id.transaction_recyclerBank);
            transactionstatus = itemView.findViewById(R.id.transaction_status);

            img = itemView.findViewById(R.id.transaction_recyclerImg);
            rupeesrecycler = itemView.findViewById(R.id.transaction_recyclerRupees);
            cardrecycler = itemView.findViewById(R.id.transaction_CardRecycler);

        }

    }
}





package pay.scope.payscope.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Model.TransactionModel;
import pay.scope.payscope.R;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private final List<TransactionModel.Data> transactionModelList;
    private final List<TransactionModel.Data> transactionListFull;
    private final Context context;

    public TransactionAdapter(Context context, List<TransactionModel.Data> transactionList) {
        this.context = context;
        this.transactionModelList = transactionList;
        this.transactionListFull = new ArrayList<>(transactionList);
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_trans, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        TransactionModel.Data transactionModel = transactionModelList.get(position);
        holder.userName.setText(transactionModel.getUser_name());
        holder.bankName.setText(transactionModel.getBank_name());
        holder.accountNo.setText(String.valueOf(transactionModel.getAccount_number()));
        holder.amount.setText(String.valueOf(transactionModel.getAmount()));
//        Toast.makeText(context, "Status : " + transactionModel.getStatus(), Toast.LENGTH_SHORT).show();

        String status = transactionModel.getStatus();
        if (status != null) {
            switch (status) {
                case "Success":
                    holder.amount.setTextColor(ContextCompat.getColor(context, R.color.green));
                    holder.rupeesrecycler.setTextColor(ContextCompat.getColor(context, R.color.green));
                    holder.img.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.green), PorterDuff.Mode.SRC_IN);
                    holder.cardrecycler.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.lightgreen));

                    break;
                case "Failed":
                    holder.amount.setTextColor(ContextCompat.getColor(context, R.color.red));
                    holder.rupeesrecycler.setTextColor(ContextCompat.getColor(context, R.color.red));
                    holder.img.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.red), PorterDuff.Mode.SRC_IN);
                    holder.cardrecycler.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.lightred));

                    break;
                case "Pending":
                    holder.amount.setTextColor(ContextCompat.getColor(context, R.color.yellow));
                    holder.rupeesrecycler.setTextColor(ContextCompat.getColor(context, R.color.yellow));
                    holder.img.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.yellow), PorterDuff.Mode.SRC_IN);
                    holder.cardrecycler.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.lightyellow));

                    break;
                default:
                    holder.amount.setTextColor(ContextCompat.getColor(context, R.color.black));
                    holder.rupeesrecycler.setTextColor(ContextCompat.getColor(context, R.color.black));
                    holder.img.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.black), PorterDuff.Mode.SRC_IN);
                    holder.cardrecycler.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.lightblack));
                    break;
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filter(String text) {
        transactionModelList.clear();
        if (text.isEmpty()) {
            transactionModelList.addAll(transactionListFull);
        } else {
            String filterPattern = text.toLowerCase().trim();
            for (TransactionModel.Data item : transactionListFull) {
                if (item.getBank_name().toLowerCase().contains(filterPattern)) {
                    // Check if any field in your model matches the query
                    transactionModelList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return transactionModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, accountNo, bankName, amount, rupeesrecycler;
        ImageView img;
        CardView cardrecycler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.namerecycler);
            accountNo = itemView.findViewById(R.id.accountNorecycler);
            bankName = itemView.findViewById(R.id.bankrecycler);
            amount = itemView.findViewById(R.id.amountrecycler);
            rupeesrecycler = itemView.findViewById(R.id.rupeesrecycler);
            img = itemView.findViewById(R.id.imgrecycler);
            cardrecycler = itemView.findViewById(R.id.cardrecycler);
        }
    }
}

/*
// Create a new reversed list

        this.transactionModelList=new ArrayList<>(transactionModelList);
        Collections.reverse(this.transactionModelList);

        Drawable image=holder.img.getContext().getResources().getDrawable(R.drawable.baseline_auto_awesome_24);
        holder.img.setBackgroundColor(Color.BLACK);
        holder.img.setImageDrawable(image);
 */
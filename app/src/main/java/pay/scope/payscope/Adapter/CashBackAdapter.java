package pay.scope.payscope.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Activity.CashBackDetailsActivity;
import pay.scope.payscope.Model.CashBackModel;
import pay.scope.payscope.R;

public class CashBackAdapter extends RecyclerView.Adapter<CashBackAdapter.ViewHolder> {

    Context context;
    List<CashBackModel> cashBacjModelList;

    public CashBackAdapter(Context context, List<CashBackModel> cashBacjModelList) {
        this.context = context;
        this.cashBacjModelList = cashBacjModelList;
    }

    @NonNull
    @Override
    public CashBackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cashback_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CashBackAdapter.ViewHolder holder, int position) {
        CashBackModel cashBackModel = cashBacjModelList.get(position);
        holder.bind(cashBackModel);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CashBackDetailsActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cashBacjModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, time, price, panding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.cashbackname);
            time = itemView.findViewById(R.id.cashbacktime);
            price = itemView.findViewById(R.id.cashbackprice);
            panding = itemView.findViewById(R.id.cashbackpanding);

        }


        public void bind(CashBackModel cashBackModel) {

            name.setText(cashBackModel.getName());
            panding.setText(cashBackModel.getName());
            price.setText(String.valueOf(cashBackModel.getPrice()));
            time.setText(String.valueOf(cashBackModel.getTime()));

        }
    }
}

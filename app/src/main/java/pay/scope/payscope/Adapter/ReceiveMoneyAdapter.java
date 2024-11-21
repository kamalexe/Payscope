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

import pay.scope.payscope.Model.ReceiveMoneyModel;

import pay.scope.payscope.R;

public class ReceiveMoneyAdapter extends RecyclerView.Adapter<ReceiveMoneyAdapter.ViewHolder> {
    Context context;
    List<ReceiveMoneyModel> receiveMoneyModelList;
    public ReceiveMoneyAdapter(Context context, List<ReceiveMoneyModel> receiveMoneyModelList) {
        this.context = context;
        this.receiveMoneyModelList = receiveMoneyModelList;
    }

    @NonNull
    @Override
    public ReceiveMoneyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reveivemoney_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiveMoneyAdapter.ViewHolder holder, int position) {
        ReceiveMoneyModel receiveMoneyModel = receiveMoneyModelList.get(position);
        holder.bind(receiveMoneyModel);
    }

    @Override
    public int getItemCount() {
        return receiveMoneyModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, time, price;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.namereceive_recycler);
            time = itemView.findViewById(R.id.timereceive_recycler);
            price = itemView.findViewById(R.id.pricereceive_recycler);
            img = itemView.findViewById(R.id.imgreceive_recycler);
        }

        public void bind(ReceiveMoneyModel receiveMoneyModel) {
            img.setImageResource(receiveMoneyModel.getImg_uri());
            name.setText(receiveMoneyModel.getName());
            price.setText(String.valueOf(receiveMoneyModel.getPrice()));
            time.setText(String.valueOf(receiveMoneyModel.getTime()));
        }

    }
}

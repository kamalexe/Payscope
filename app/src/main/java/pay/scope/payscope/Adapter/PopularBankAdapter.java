package pay.scope.payscope.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Activity.SelectAccountActivity;
import pay.scope.payscope.Model.PopularBankModel;
import pay.scope.payscope.R;

public class PopularBankAdapter extends RecyclerView.Adapter<PopularBankAdapter.ViewHolder> {

    Context context;
    List<PopularBankModel> popularBankModelList;

    public PopularBankAdapter(Context context, List<PopularBankModel> popularBankModelList) {
        this.context = context;
        this.popularBankModelList = popularBankModelList;
    }

    @NonNull
    @Override
    public PopularBankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.popularbank_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularBankAdapter.ViewHolder holder, int position) {
        PopularBankModel popularBankModel = popularBankModelList.get(position);
        holder.bind(popularBankModel);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SelectAccountActivity.class);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return popularBankModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView popularbank_name;
        ImageView popularbank_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popularbank_img = itemView.findViewById(R.id.popularbank_img);
            popularbank_name = itemView.findViewById(R.id.popularbank_name);
        }

        public void bind(PopularBankModel popularBankModel) {
            popularbank_img.setImageResource(popularBankModel.getPopularbank_img());
            popularbank_name.setText(popularBankModel.getPopularbank_Name());
        }
    }
}

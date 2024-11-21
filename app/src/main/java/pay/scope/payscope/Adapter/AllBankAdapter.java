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

import pay.scope.payscope.Activity.FoundBankActivity;
import pay.scope.payscope.Model.AllBankModel;
import pay.scope.payscope.R;

public class AllBankAdapter extends RecyclerView.Adapter<AllBankAdapter.ViewHolder> {
    Context context;
    List<AllBankModel> allBankModelList;

    public AllBankAdapter(Context context, List<AllBankModel> allBankModelList) {
        this.context = context;
        this.allBankModelList = allBankModelList;
    }

    @NonNull
    @Override
    public AllBankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.allbank_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllBankAdapter.ViewHolder holder, int position) {
        AllBankModel allBankModel = allBankModelList.get(position);
        holder.bind(allBankModel);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FoundBankActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return allBankModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView addBank_Name;
        ImageView addBank_Img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            addBank_Img = itemView.findViewById(R.id.addbank_img);
            addBank_Name = itemView.findViewById(R.id.addbank_name);
        }

        public void bind(AllBankModel allBankModel) {
            addBank_Img.setImageResource(allBankModel.getBank_img());
            addBank_Name.setText(allBankModel.getBank_Name());

        }
    }
}

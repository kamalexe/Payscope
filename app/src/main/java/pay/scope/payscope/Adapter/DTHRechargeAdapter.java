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
import pay.scope.payscope.Activity.DTHRechargeDetailsActivity;
import pay.scope.payscope.Model.DTHRechargeModel;
import pay.scope.payscope.R;

public class DTHRechargeAdapter extends RecyclerView.Adapter<DTHRechargeAdapter.ViewHolder> {
    Context context;
    List<DTHRechargeModel> dthRechargeModelList;

    public DTHRechargeAdapter(Context context, List<DTHRechargeModel> dthRechargeModelList) {
        this.context = context;
        this.dthRechargeModelList = dthRechargeModelList;
    }

    @NonNull
    @Override
    public DTHRechargeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dthrecharge_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DTHRechargeAdapter.ViewHolder holder, int position) {
        DTHRechargeModel dthRechargeModel = dthRechargeModelList.get(position);
        holder.bind(dthRechargeModel);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DTHRechargeDetailsActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dthRechargeModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dthRecharge_name;
        ImageView dthRecharge_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dthRecharge_name = itemView.findViewById(R.id.dthRecharge_name);
            dthRecharge_img = itemView.findViewById(R.id.dthRecharge_img);
        }

        public void bind(DTHRechargeModel dthRechargeModel) {
            dthRecharge_name.setText(dthRechargeModel.getRecharge_Name());
            dthRecharge_img.setImageResource(dthRechargeModel.getRecharge_img());
        }
    }
}

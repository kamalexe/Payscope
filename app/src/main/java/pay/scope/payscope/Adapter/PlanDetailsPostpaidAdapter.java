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

import pay.scope.payscope.Activity.PlanShowPostpaidActivity;
import pay.scope.payscope.Model.PlanDetailsPostpaidModel;
import pay.scope.payscope.R;
public class PlanDetailsPostpaidAdapter extends RecyclerView.Adapter<PlanDetailsPostpaidAdapter.ViewHolder> {
    private final Context context;
    private final List<PlanDetailsPostpaidModel> detailsPostpaidModelList;

    public PlanDetailsPostpaidAdapter(Context context, List<PlanDetailsPostpaidModel> detailsPostpaidModelList) {
        this.context = context;
        this.detailsPostpaidModelList = detailsPostpaidModelList;
    }

    @NonNull
    @Override
    public PlanDetailsPostpaidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.plandetails_postpaidrecycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlanDetailsPostpaidAdapter.ViewHolder holder, int position) {
        PlanDetailsPostpaidModel detailsPostpaidModel = detailsPostpaidModelList.get(position);
        holder.planPrisePostpaid.setText(String.valueOf(detailsPostpaidModel.getPrisePostpaid()));
        holder.plandataPostpaid.setText(detailsPostpaidModel.getDataPostpaid());
        holder.planNetpackPostpaid.setText(String.valueOf(detailsPostpaidModel.getNetpackPostpaid()));
        holder.plandetailsPostpaid.setText(detailsPostpaidModel.getDetailsPostpaid());
        holder.planAddonDataPostpaid.setText(detailsPostpaidModel.getAddonDataPostpaid());
        holder.showDetailsPostpaid.setImageResource(detailsPostpaidModel.getImgShowDetailsPostpaid());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlanShowPostpaidActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return detailsPostpaidModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView planPrisePostpaid, plandataPostpaid, planNetpackPostpaid, plandetailsPostpaid, planAddonDataPostpaid;
        ImageView showDetailsPostpaid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            planPrisePostpaid = itemView.findViewById(R.id.planPrisePostpaid);
            plandataPostpaid = itemView.findViewById(R.id.plandataPostpaid);
            planNetpackPostpaid = itemView.findViewById(R.id.planNetpackPostpaid);
            plandetailsPostpaid = itemView.findViewById(R.id.plandetailsPostpaid);
            planAddonDataPostpaid = itemView.findViewById(R.id.planAddonDataPostpaid);
            showDetailsPostpaid = itemView.findViewById(R.id.showDetailsPostpaid);
        }
    }
}

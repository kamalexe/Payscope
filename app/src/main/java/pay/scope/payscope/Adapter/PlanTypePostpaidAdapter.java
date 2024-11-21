package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.PlanTypePostpaidModel;
import pay.scope.payscope.R;

public class PlanTypePostpaidAdapter extends RecyclerView.Adapter<PlanTypePostpaidAdapter.ViewHolder> {
   private final Context context;
   private final List<PlanTypePostpaidModel> typePostpaidModelList;

    public PlanTypePostpaidAdapter(Context context, List<PlanTypePostpaidModel> typePostpaidModelList) {
        this.context = context;
        this.typePostpaidModelList = typePostpaidModelList;
    }

    @NonNull
    @Override
    public PlanTypePostpaidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.plantype_postpaidrecycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlanTypePostpaidAdapter.ViewHolder holder, int position) {
        PlanTypePostpaidModel typePostpaidModel = typePostpaidModelList.get(position);
        holder.planTypePostpaid.setText(typePostpaidModel.getPlanTypePostpaid());
    }

    @Override
    public int getItemCount() {
        return typePostpaidModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView planTypePostpaid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            planTypePostpaid = itemView.findViewById(R.id.planTypePostpaid);
        }
    }
}

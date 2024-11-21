package pay.scope.payscope.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.PlanTypeModel;
import pay.scope.payscope.R;

public class PlanTypeAdapter extends RecyclerView.Adapter<PlanTypeAdapter.ViewHolder> {

    Context context;
    List<PlanTypeModel> planTypeModelList;

    public PlanTypeAdapter(Context context, List<PlanTypeModel> planTypeModelList) {
        this.context = context;
        this.planTypeModelList = planTypeModelList;
    }

    @NonNull
    @Override
    public PlanTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_type_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlanTypeAdapter.ViewHolder holder, int position) {
        PlanTypeModel planTypeModel = planTypeModelList.get(position);
        holder.bind(planTypeModel);
    }

    @Override
    public int getItemCount() {
        return planTypeModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView planType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            planType = itemView.findViewById(R.id.planType);
        }

        public void bind(PlanTypeModel planTypeModel) {
            planType.setText(planTypeModel.getPlanType());
        }
    }
}

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

import de.hdodenhof.circleimageview.CircleImageView;
import pay.scope.payscope.Activity.RechargePlanShowActivity;
import pay.scope.payscope.Model.PlanDetailsModel;
import pay.scope.payscope.R;

public class PlanDetailsAdapter extends RecyclerView.Adapter<PlanDetailsAdapter.ViewHolder> {

    Context context;
    List<PlanDetailsModel> planDetailsModelList;

    public PlanDetailsAdapter(Context context, List<PlanDetailsModel> planDetailsModelList) {
        this.context = context;
        this.planDetailsModelList = planDetailsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.plandetails_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlanDetailsModel planDetailsModel = planDetailsModelList.get(position);
        holder.bind(planDetailsModel);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RechargePlanShowActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return planDetailsModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView planPrises, plandatas, planNetpacks, plandetailss, planAddonDatas;
        CircleImageView showDetailss;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            planPrises = itemView.findViewById(R.id.planPrise);
            plandatas = itemView.findViewById(R.id.plandata);
            planNetpacks = itemView.findViewById(R.id.planNetpack);
            plandetailss = itemView.findViewById(R.id.plandetails);
            planAddonDatas = itemView.findViewById(R.id.planAddonData);
            showDetailss = itemView.findViewById(R.id.showDetails);
        }

        public void bind(PlanDetailsModel planDetailsModel) {

            planPrises.setText(String.valueOf(planDetailsModel.getPrise()));
            plandatas.setText(planDetailsModel.getData());
            planNetpacks.setText(String.valueOf(planDetailsModel.getNetpack()));
            plandetailss.setText(planDetailsModel.getDetails());
            planAddonDatas.setText(planDetailsModel.getAddonData());
            showDetailss.setImageResource(planDetailsModel.getImgshowDetails());
        }
    }
}


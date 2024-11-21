package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.ChooseRechargeModel;
import pay.scope.payscope.R;

public class ChooseRechargeAdapter extends RecyclerView.Adapter<ChooseRechargeAdapter.ViewHolder> {

    Context context;
    List<ChooseRechargeModel> chooseRechargeModelList;

    public ChooseRechargeAdapter(Context context, List<ChooseRechargeModel> chooseRechargeModelList) {
        this.context = context;
        this.chooseRechargeModelList = chooseRechargeModelList;
    }

    @NonNull
    @Override
    public ChooseRechargeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_recharge_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseRechargeAdapter.ViewHolder holder, int position) {
        ChooseRechargeModel chooseRechargeModel = chooseRechargeModelList.get(position);
        holder.bind(chooseRechargeModel);
    }

    @Override
    public int getItemCount() {
        return chooseRechargeModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView chooseRecharge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chooseRecharge = itemView.findViewById(R.id.chooseRecharge);
        }

        public void bind(ChooseRechargeModel chooseRechargeModel) {
            chooseRecharge.setText(chooseRechargeModel.getChoosePlan());
        }
    }
}

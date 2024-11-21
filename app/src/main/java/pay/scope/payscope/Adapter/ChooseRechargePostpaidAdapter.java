package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.ChooseRechargePostpaidModel;
import pay.scope.payscope.R;

public class ChooseRechargePostpaidAdapter extends RecyclerView.Adapter<ChooseRechargePostpaidAdapter.ViewHolder> {
    private final Context context;
    private final List<ChooseRechargePostpaidModel> rechargePostpaidModelList;


    public ChooseRechargePostpaidAdapter(Context context, List<ChooseRechargePostpaidModel> rechargePostpaidModelList) {
        this.context = context;
        this.rechargePostpaidModelList = rechargePostpaidModelList;
    }

    @NonNull
    @Override

    public ChooseRechargePostpaidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.chooserecharge_postpaidrecycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseRechargePostpaidAdapter.ViewHolder holder, int position) {
        ChooseRechargePostpaidModel rechargePostpaidModel = rechargePostpaidModelList.get(position);
        holder.ChooseRechargePostpaid.setText(rechargePostpaidModel.getChooseRechargePostpaid());
    }

    @Override
    public int getItemCount() {
        return rechargePostpaidModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ChooseRechargePostpaid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ChooseRechargePostpaid = itemView.findViewById(R.id.ChooseRechargePostpaid);
        }
    }
}

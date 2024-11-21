package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.VirtualCardModel;
import pay.scope.payscope.R;

public class VirtualCardAdapter extends RecyclerView.Adapter<VirtualCardAdapter.ViewHolder> {
    private final Context context;
    private final List<VirtualCardModel.Data> virtualCardModelList;

    public VirtualCardAdapter(Context context, List<VirtualCardModel.Data> virtualCardModelList) {
        this.context = context;
        this.virtualCardModelList = virtualCardModelList;
    }

    @NonNull
    @Override
    public VirtualCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.virtualtranscation_recyclar, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VirtualCardAdapter.ViewHolder holder, int position) {
        VirtualCardModel.Data virtualCardModel = virtualCardModelList.get(position);

        holder.virtualName.setText(virtualCardModel.getRemitter_name());
        holder.virtualAccountNo.setText(String.valueOf(virtualCardModel.getRemitter_account_number()));
        holder.virtualAmount.setText(String.valueOf(virtualCardModel.getCredit_amount()));
        holder.virtualTime.setText(virtualCardModel.getCredit_time());
    }

    @Override
    public int getItemCount() {
        return virtualCardModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView virtualName, virtualAccountNo, virtualAmount, virtualTime;
//        ImageView Virtual_imgUri;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            virtualName = itemView.findViewById(R.id.virtualName);
            virtualAccountNo = itemView.findViewById(R.id.virtualAccountNo);
            virtualAmount = itemView.findViewById(R.id.virtualAmount);
            virtualTime = itemView.findViewById(R.id.virtualTime);
//            Virtual_day = itemView.findViewById(R.id.virtualDay);
//            Virtual_imgUri = itemView.findViewById(R.id.virtualImg_Uri);
        }

    }


}

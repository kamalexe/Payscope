package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.DownStateCalenderModel;
import pay.scope.payscope.R;

public class DownStateCalenderAdapter extends RecyclerView.Adapter<DownStateCalenderAdapter.ViewHolder> {
    private final Context context;
    private final List<DownStateCalenderModel> stateCalenderModelList;
    private OnDateClickListener onDateClickListener;

    public DownStateCalenderAdapter(Context context, List<DownStateCalenderModel> stateCalenderModelList) {
        this.context = context;
        this.stateCalenderModelList = stateCalenderModelList;
    }

    public void setOnDateClickListener(OnDateClickListener onDateClickListener) {
        this.onDateClickListener = onDateClickListener;
    }

    @NonNull
    @Override
    public DownStateCalenderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.calenderbottomsheet_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DownStateCalenderAdapter.ViewHolder holder, int position) {
        DownStateCalenderModel stateCalenderModel = stateCalenderModelList.get(position);
        String dateText = stateCalenderModel.getMonth() + " " + stateCalenderModel.getYear();
        holder.DSCalenderRecycler_Month.setText(dateText);

        holder.itemView.setOnClickListener(v -> {
            if (onDateClickListener != null) {
                onDateClickListener.onDateClick(dateText);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stateCalenderModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView DSCalenderRecycler_Month;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DSCalenderRecycler_Month = itemView.findViewById(R.id.DSCalenderRecycler_Month);
        }
    }

    public interface OnDateClickListener {
        void onDateClick(String date);
    }
}

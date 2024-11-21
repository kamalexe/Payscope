package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.MonthLayoutModel;
import pay.scope.payscope.R;

public class MonthLayoutAdapter extends RecyclerView.Adapter<MonthLayoutAdapter.ViewHolder> {
    private final Context context;
    private final List<MonthLayoutModel> dateList;
    private int selectedPosition = 0;

    public MonthLayoutAdapter(Context context, List<MonthLayoutModel> dateList) {
        this.context = context;
        this.dateList = dateList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.monthlayout_recycler, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MonthLayoutModel dateModel = dateList.get(position);
        String dateText = dateModel.getMonth() + ", " + dateModel.getYear();
        holder.dateTextView.setText(dateText);
//        holder.checkBox.setChecked(dateModel.isSelected());
        holder.checkBox.setChecked(position == selectedPosition);

        // Change text color if the item is selected
        if (position == selectedPosition) {
            holder.dateTextView.setTextColor(ContextCompat.getColor(context, R.color.blue));
        } else {
            holder.dateTextView.setTextColor(ContextCompat.getColor(context, R.color.hint));
        }

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> dateModel.setSelected(isChecked));
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.MonthLayout_Date);
            checkBox = itemView.findViewById(R.id.MonthLayout_CheckBox);

            checkBox.setOnClickListener(v -> {
                int previousSelectedPosition = selectedPosition;
                selectedPosition = getAdapterPosition();
                notifyItemChanged(previousSelectedPosition);
                notifyItemChanged(selectedPosition);

            });
        }
    }
}



//public class MonthLayoutAdapter extends RecyclerView.Adapter<MonthLayoutAdapter.ViewHolder> {
//    private final List<MonthLayoutModel> dateList;
//    private final Context context;
//    private int selectedPosition = -1; // Initially, no item is selected
//
//    public MonthLayoutAdapter(List<MonthLayoutModel> dateList, Context context) {
//        this.dateList = dateList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.monthlayout_recycler, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        MonthLayoutModel dateModel = dateList.get(position);
//        String dateText = dateModel.getMonth() + ", " + dateModel.getYear();
//        holder.dateTextView.setText(dateText);
//        holder.checkBox.setChecked(position == selectedPosition);
//
//        // Change text color if the item is selected
//        if (position == selectedPosition) {
//            holder.dateTextView.setTextColor(ContextCompat.getColor(context, R.color.blue));
//        } else {
//            holder.dateTextView.setTextColor(ContextCompat.getColor(context, R.color.hint));
//        }
//
//        holder.itemView.setOnClickListener(v -> {
//            int previousSelectedPosition = selectedPosition;
//            selectedPosition = holder.getAdapterPosition();
//            notifyItemChanged(previousSelectedPosition);
//            notifyItemChanged(selectedPosition);
//        });
//
//        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            dateModel.setSelected(isChecked);
//            if (isChecked) {
//                selectedPosition = holder.getAdapterPosition();
//                notifyDataSetChanged(); // Refresh the whole list to update the UI
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return dateList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView dateTextView;
//        CheckBox checkBox;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            dateTextView = itemView.findViewById(R.id.MonthLayout_Date);
//            checkBox = itemView.findViewById(R.id.MonthLayout_CheckBox);
//
//        }
//    }
//}

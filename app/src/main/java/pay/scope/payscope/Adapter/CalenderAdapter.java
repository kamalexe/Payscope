package pay.scope.payscope.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import pay.scope.payscope.Model.CalenderModel;
import pay.scope.payscope.R;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.DataViewHolder> {
    Context context;
    List<CalenderModel> calenderModelList;
    private int selectedPosition = -1; // Initially no item is selected
     int todayPosition;

    public CalenderAdapter(Context context, List<CalenderModel> calenderModelList) {
        this.context = context;
        this.calenderModelList = calenderModelList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged(); // Notify adapter that data set has changed
    }

    @NonNull
    @Override
    public CalenderAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataViewHolder(LayoutInflater.from(context).inflate(R.layout.calender_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderAdapter.DataViewHolder holder, int position) {
        CalenderModel calenderModel = calenderModelList.get(position);
        holder.showCalender.setText(calenderModel.getDisplayDate());

        CardView cardView = holder.showCalenderCard;

//        if (position == todayPosition) {
////            holder.showCalender.setTextColor(ContextCompat.getColor(context, R.color.white));
//            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.blue));
//        } else {
////            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card));
//        }

//         Set background color and text color based on selected position
        if (position == selectedPosition) {
            holder.showCalenderCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.lightgreen));
//            holder.showCalender.setTextColor(ContextCompat.getColor(context, R.color.white));
        } else {
            holder.showCalenderCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
//            holder.showCalender.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return calenderModelList.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView showCalender;
        MaterialCardView showCalenderCard;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            showCalender = itemView.findViewById(R.id.showCalender);
            showCalenderCard = itemView.findViewById(R.id.showCalenderCard);
        }
    }
}

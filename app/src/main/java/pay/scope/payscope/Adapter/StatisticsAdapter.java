package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.StatisticsModel;
import pay.scope.payscope.R;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> {
    Context context;
    List<StatisticsModel> statisticsModelList;

    public StatisticsAdapter(Context context, List<StatisticsModel> statisticsModelList) {
        this.context = context;
        this.statisticsModelList = statisticsModelList;
    }

    @NonNull
    @Override
    public StatisticsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.statistics_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsAdapter.ViewHolder holder, int position) {
        StatisticsModel statisticsModel = statisticsModelList.get(position);
        holder.bind(statisticsModel);
    }

    @Override
    public int getItemCount() {
        return statisticsModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView StatisticsName, StatisticsPaid, StatisticsAmount, StatisticsTotalAmt, StatisticsROI, StatisticsEMI;
        ImageView StatisticsIMG;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            StatisticsName = itemView.findViewById(R.id.StatisticsName);
            StatisticsPaid = itemView.findViewById(R.id.StatisticsPaid);
            StatisticsAmount = itemView.findViewById(R.id.StatisticsAmount);
            StatisticsTotalAmt = itemView.findViewById(R.id.StatisticsTotalAmt);
            StatisticsROI = itemView.findViewById(R.id.StatisticsROI);
            StatisticsEMI = itemView.findViewById(R.id.StatisticsEMI);
            StatisticsIMG = itemView.findViewById(R.id.StatisticsIMg);
        }

        public void bind(StatisticsModel statisticsModel) {
            StatisticsName.setText(statisticsModel.getStatisticsName());
            StatisticsPaid.setText(statisticsModel.getStatisticsPaid());
            StatisticsAmount.setText(String.valueOf(statisticsModel.getStatisticsAmount()));
            StatisticsTotalAmt.setText(String.valueOf(statisticsModel.getStatisticsTotalAmt()));
            StatisticsROI.setText(String.valueOf(statisticsModel.getStatisticsROI()));
            StatisticsEMI.setText(String.valueOf(statisticsModel.getStatisticsEMI()));
            StatisticsIMG.setImageResource(statisticsModel.getStatisticsIMG());

        }
    }
}

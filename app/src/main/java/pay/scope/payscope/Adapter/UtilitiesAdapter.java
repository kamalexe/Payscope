package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.UtilitiesModel;
import pay.scope.payscope.R;

public class UtilitiesAdapter extends RecyclerView.Adapter<UtilitiesAdapter.ViewHolder> {
    Context context;
    List<UtilitiesModel> utilitiesModelList;

    public UtilitiesAdapter(Context context, List<UtilitiesModel> utilitiesModelList) {
        this.context = context;
        this.utilitiesModelList = utilitiesModelList;
    }

    @NonNull
    @Override
    public UtilitiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.utilities_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UtilitiesAdapter.ViewHolder holder, int position) {
        UtilitiesModel utilitiesModel = utilitiesModelList.get(position);
        holder.bind(utilitiesModel);
    }

    @Override
    public int getItemCount() {
        return utilitiesModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView utName, utTime, utMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            utName = itemView.findViewById(R.id.Utilities_name);
            utTime = itemView.findViewById(R.id.Utilities_time);
            utMessage = itemView.findViewById(R.id.Utilities_message);
        }

        public void bind(UtilitiesModel utilitiesModel) {
            utName.setText(utilitiesModel.getUtName());
            utTime.setText(utilitiesModel.getUtTime());
            utMessage.setText(utilitiesModel.getUtMessage());
        }
    }
}

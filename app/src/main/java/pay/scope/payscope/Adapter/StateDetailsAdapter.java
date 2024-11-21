package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.StateDetailsModel;
import pay.scope.payscope.R;

public class StateDetailsAdapter extends RecyclerView.Adapter<StateDetailsAdapter.ViewHolder> {
    Context context;
    List<StateDetailsModel> stateDetailsModelList;

    public StateDetailsAdapter(Context context, List<StateDetailsModel> stateDetailsModelList) {
        this.context = context;
        this.stateDetailsModelList = stateDetailsModelList;
    }

    @NonNull
    @Override
    public StateDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.statedetails_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StateDetailsAdapter.ViewHolder holder, int position) {
        StateDetailsModel stateDetailsModel = stateDetailsModelList.get(position);
        holder.bind(stateDetailsModel);
    }

    @Override
    public int getItemCount() {
        return stateDetailsModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stateDetailsName, stateDetailsFullName, stateDetailsShortName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stateDetailsName = itemView.findViewById(R.id.stateDetailsName);
            stateDetailsFullName = itemView.findViewById(R.id.stateDetailsFullName);
            stateDetailsShortName = itemView.findViewById(R.id.stateDetailsShortName);
        }

        public void bind(StateDetailsModel stateDetailsModel) {
            stateDetailsName.setText(stateDetailsModel.getStateDetailsName());
            stateDetailsFullName.setText(stateDetailsModel.getStateDetailsFullName());
            stateDetailsShortName.setText(stateDetailsModel.getStateDetailsShortName());
        }
    }
}

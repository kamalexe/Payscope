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

import de.hdodenhof.circleimageview.CircleImageView;
import pay.scope.payscope.Model.StateNameModel;
import pay.scope.payscope.R;


public class StateNameAdapter extends RecyclerView.Adapter<StateNameAdapter.ViewHolder> {
    Context context;
    List<StateNameModel> stateNameModelList;

    public StateNameAdapter(Context context, List<StateNameModel> stateNameModelList) {
        this.context = context;
        this.stateNameModelList = stateNameModelList;
    }

    @NonNull
    @Override
    public StateNameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_recycler, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StateNameAdapter.ViewHolder holder, int position) {
        StateNameModel stateNameModel = stateNameModelList.get(position);
        holder.bind(stateNameModel);
    }

    @Override
    public int getItemCount() {
        return stateNameModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView state_Name;
//        CircleImageView state_Img;
        ImageView state_Img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            state_Name = itemView.findViewById(R.id.stateName);
            state_Img = itemView.findViewById(R.id.stateImg);
        }

        public void bind(StateNameModel stateNameModel) {
            state_Img.setImageResource(stateNameModel.getStateImg());
            state_Name.setText(stateNameModel.getStateName());
        }
    }
}


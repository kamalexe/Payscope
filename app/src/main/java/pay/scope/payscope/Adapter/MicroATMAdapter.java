package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.MicroATMModel;
import pay.scope.payscope.R;

public class MicroATMAdapter extends RecyclerView.Adapter<MicroATMAdapter.ViewHolder> {
    Context context;
    List<MicroATMModel> microATMModelList;

    public MicroATMAdapter(Context context, List<MicroATMModel> microATMModelList) {
        this.context = context;
        this.microATMModelList = microATMModelList;
    }

    @NonNull
    @Override
    public MicroATMAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.microatm_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MicroATMAdapter.ViewHolder holder, int position) {
        MicroATMModel microATMModel = microATMModelList.get(position);
        holder.bind(microATMModel);
    }

    @Override
    public int getItemCount() {
        return microATMModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mATM_Name, mATM_Amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mATM_Name = itemView.findViewById(R.id.microATM_Name);
            mATM_Amount = itemView.findViewById(R.id.microATM_Amount);
        }

        public void bind(MicroATMModel microATMModel) {
            mATM_Amount.setText(String.valueOf(microATMModel.getATM_Amount()));
            mATM_Name.setText(microATMModel.getATM_Name());
        }
    }
}

package pay.scope.payscope.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Activity.SupportRechargeActivity;
import pay.scope.payscope.Model.HelpSupportRechargeModel;
import pay.scope.payscope.R;

public class HelpSupportRechargeAdapter extends RecyclerView.Adapter<HelpSupportRechargeAdapter.ViewHolder> {
    private final Context context;
    private final List<HelpSupportRechargeModel> helpSupportRechargeModelList;

    public HelpSupportRechargeAdapter(Context context, List<HelpSupportRechargeModel> helpSupportRechargeModelList) {
        this.context = context;
        this.helpSupportRechargeModelList = helpSupportRechargeModelList;
    }

    @NonNull
    @Override
    public HelpSupportRechargeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.helpsupprecharge_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HelpSupportRechargeAdapter.ViewHolder holder, int position) {
        HelpSupportRechargeModel supportRechargeModel = helpSupportRechargeModelList.get(position);
        holder.bind(supportRechargeModel);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SupportRechargeActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return helpSupportRechargeModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView helpSuppRecharge_Img;
        TextView helpSuppRecharge_Name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            helpSuppRecharge_Img = itemView.findViewById(R.id.helpSuppRecharge_Img);
            helpSuppRecharge_Name = itemView.findViewById(R.id.helpSuppRecharge_Name);
        }

        public void bind(HelpSupportRechargeModel supportRechargeModel) {
            helpSuppRecharge_Img.setImageResource(supportRechargeModel.getHelpSuppRecharge_Img());
            helpSuppRecharge_Name.setText(supportRechargeModel.getHelpSuppRecharge_Name());
        }
    }
}

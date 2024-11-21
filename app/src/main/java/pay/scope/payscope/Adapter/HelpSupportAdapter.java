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

import pay.scope.payscope.Activity.SupportActivity;
import pay.scope.payscope.Model.HelpSupportModel;
import pay.scope.payscope.R;

public class HelpSupportAdapter extends RecyclerView.Adapter<HelpSupportAdapter.ViewHolder> {
    private final Context context;
    private final List<HelpSupportModel> helpSupportModelList;

    public HelpSupportAdapter(Context context, List<HelpSupportModel> helpSupportModelList) {
        this.context = context;
        this.helpSupportModelList = helpSupportModelList;
    }

    @NonNull
    @Override
    public HelpSupportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.helpsupport_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HelpSupportAdapter.ViewHolder holder, int position) {
        HelpSupportModel supportModel = helpSupportModelList.get(position);
        holder.bind(supportModel);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SupportActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return helpSupportModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView helpSupp_Img;
        TextView helpSupp_Name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            helpSupp_Img = itemView.findViewById(R.id.helpSupp_Img);
            helpSupp_Name = itemView.findViewById(R.id.helpSupp_Name);
        }

        public void bind(HelpSupportModel supportModel) {
            helpSupp_Img.setImageResource(supportModel.getHelpSupp_Img());
            helpSupp_Name.setText(supportModel.getHelpSupp_Name());
        }
    }
}

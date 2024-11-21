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

import de.hdodenhof.circleimageview.CircleImageView;
import pay.scope.payscope.Activity.InvestmentDetailsActivity;
import pay.scope.payscope.Model.InvestmentModel;
import pay.scope.payscope.R;

public class InvestmentAdapter extends RecyclerView.Adapter<InvestmentAdapter.ViewHolder> {
    Context context;
    List<InvestmentModel> investmentModelList;

    public InvestmentAdapter(Context context, List<InvestmentModel> investmentModelList) {
        this.context = context;
        this.investmentModelList = investmentModelList;
    }

    @NonNull
    @Override
    public InvestmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.insvestment_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InvestmentAdapter.ViewHolder holder, int position) {
        InvestmentModel investmentModel = investmentModelList.get(position);
        holder.investment_Img.setImageResource(investmentModel.getInvestmentImg());
        holder.investmentOfferImg.setImageResource(investmentModel.getInvestmentOfferImg());
        holder.investment_Title.setText(investmentModel.getInvestmentTitle());
        holder.investment_Description.setText(investmentModel.getInvestmentDes());

        holder.investment_Button.setOnClickListener(v -> {
            Intent intent = new Intent(context, InvestmentDetailsActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return investmentModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView investment_Img;
        TextView investment_Title;
        TextView investment_Description;
        TextView investment_Button;
        CircleImageView investmentOfferImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            investment_Img = itemView.findViewById(R.id.investment_Img);
            investment_Title = itemView.findViewById(R.id.investment_Title);
            investment_Description = itemView.findViewById(R.id.investment_Description);
            investment_Button = itemView.findViewById(R.id.investment_Button);
            investmentOfferImg = itemView.findViewById(R.id.investmentOfferImg);

        }
    }
}

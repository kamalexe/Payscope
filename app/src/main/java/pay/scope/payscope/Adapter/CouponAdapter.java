package pay.scope.payscope.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Activity.RedamptionDetailsActivity;
import pay.scope.payscope.Model.CouponModel;
import pay.scope.payscope.R;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {
    Context context;
    List<CouponModel> couponModelList;

    public CouponAdapter(Context context, List<CouponModel> couponModelList) {
        this.context = context;
        this.couponModelList = couponModelList;
    }

    @NonNull
    @Override
    public CouponAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.coupons_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CouponAdapter.ViewHolder holder, int position) {
        CouponModel couponModel = couponModelList.get(position);
        holder.bind(couponModel);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RedamptionDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return couponModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView CouponMode;

        TextView CouponDate;
        TextView CouponCondition;
        TextView CouponBtn;
        ImageView CouponImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CouponMode = itemView.findViewById(R.id.CouponMode);
            CouponImg = itemView.findViewById(R.id.CouponImg);
            CouponDate = itemView.findViewById(R.id.CouponDate);
            CouponCondition = itemView.findViewById(R.id.CouponCondition);
            CouponBtn = itemView.findViewById(R.id.CouponBtn);
        }

        public void bind(CouponModel couponModel) {

            CouponMode.setText(couponModel.getCouponMode());
            CouponDate.setText(couponModel.getCouponDate());
            CouponCondition.setText(couponModel.getCouponCondition());
            CouponBtn.setText(couponModel.getCouponBtn());
            CouponImg.setImageResource(couponModel.getCouponImg());
        }
    }
}

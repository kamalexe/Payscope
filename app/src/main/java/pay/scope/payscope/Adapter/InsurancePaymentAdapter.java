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

import pay.scope.payscope.Activity.CarInsuranceActivity;
import pay.scope.payscope.Activity.CriticalillnessInsuranceActivity;
import pay.scope.payscope.Activity.HealthInsuranceActivity;
import pay.scope.payscope.Activity.BikeInsuranceActivity;
import pay.scope.payscope.Activity.PaymentProtectActivity;
import pay.scope.payscope.Activity.PersonalAccidentActivity;
import pay.scope.payscope.Model.InsurancePaymentModel;
import pay.scope.payscope.R;

public class InsurancePaymentAdapter extends RecyclerView.Adapter<InsurancePaymentAdapter.ViewHolder> {
    Context context;
    List<InsurancePaymentModel> insurancePaymentModelList;

    public InsurancePaymentAdapter(Context context, List<InsurancePaymentModel> insurancePaymentModelList) {
        this.context = context;
        this.insurancePaymentModelList = insurancePaymentModelList;
    }

    @NonNull
    @Override
    public InsurancePaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.insurance_recycler, parent, false));
    }

    @Override


    public void onBindViewHolder(@NonNull InsurancePaymentAdapter.ViewHolder holder, int position) {

        final InsurancePaymentModel item = insurancePaymentModelList.get(position);
        holder.insurance_Name.setText(item.getInsuranceName());
        holder.insurance_Img.setImageResource(item.getInsuranceImg());

        holder.itemView.setOnClickListener(v -> {
            Class<?> activityClass;
            switch (item.getInsuranceName()) {
                case "Car Insurance":
                    activityClass = CarInsuranceActivity.class;
                    break;
                case "Health Insurance":
                    activityClass = HealthInsuranceActivity.class;
                    break;
                case "Critical illness":
                    activityClass = CriticalillnessInsuranceActivity.class;
                    break;
                case "Personal Accident":
                    activityClass = PersonalAccidentActivity.class;
                    break;
                case "Payment Protect":
                    activityClass = PaymentProtectActivity.class;
                    break;
                default:
                    // Default activity to open if no specific activity is defined for this item
                    activityClass = BikeInsuranceActivity.class;
                    break;
            }

            Intent intent = new Intent(context, activityClass);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return insurancePaymentModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView insurance_Name;
        ImageView insurance_Img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            insurance_Name = itemView.findViewById(R.id.insuranceName);
            insurance_Img = itemView.findViewById(R.id.insuranceImg);
        }
    }
}


package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.AadharPaymentModel;
import pay.scope.payscope.R;

public class AadharPaymentAdapter extends RecyclerView.Adapter<AadharPaymentAdapter.ViewHolder> {
    Context context;
    List<AadharPaymentModel> aadharPaymentModelList;

    public AadharPaymentAdapter(Context context, List<AadharPaymentModel> aadharPaymentModelList) {
        this.context = context;
        this.aadharPaymentModelList = aadharPaymentModelList;
    }

    @NonNull
    @Override
    public AadharPaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.aadharpayment_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AadharPaymentAdapter.ViewHolder holder, int position) {
        AadharPaymentModel aadharPaymentModel = aadharPaymentModelList.get(position);
        holder.bind(aadharPaymentModel);
    }

    @Override
    public int getItemCount() {
        return aadharPaymentModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView AadharPaymentName, AadharPaymentNumber, AadharPaymentDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            AadharPaymentName = itemView.findViewById(R.id.AadharPayment_Name);
            AadharPaymentNumber = itemView.findViewById(R.id.AadharPayment_Number);
            AadharPaymentDate = itemView.findViewById(R.id.AadharPayment_Date);
        }

        public void bind(AadharPaymentModel paymentModel) {
            AadharPaymentName.setText(paymentModel.getAadharPaymentName());
            AadharPaymentNumber.setText(paymentModel.getAadharPaymentNumber());
            AadharPaymentDate.setText(paymentModel.getAadharPaymentDate());
        }
    }
}

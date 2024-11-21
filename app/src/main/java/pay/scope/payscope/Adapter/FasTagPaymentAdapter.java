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
import pay.scope.payscope.Model.FasTagPaymentModel;
import pay.scope.payscope.R;

public class FasTagPaymentAdapter extends RecyclerView.Adapter<FasTagPaymentAdapter.viewHolder> {

    Context context;
    List<FasTagPaymentModel> fasTagPaymentModelList;

    public FasTagPaymentAdapter(Context context, List<FasTagPaymentModel> fasTagPaymentModelList) {
        this.context = context;
        this.fasTagPaymentModelList = fasTagPaymentModelList;
    }

    @NonNull
    @Override
    public FasTagPaymentAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.fastagpayment_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FasTagPaymentAdapter.viewHolder holder, int position) {
        FasTagPaymentModel fasTagPaymentModel = fasTagPaymentModelList.get(position);
        holder.bind(fasTagPaymentModel);


    }

    @Override
    public int getItemCount() {
        return fasTagPaymentModelList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView FasTagPayment_Id, FasTagPayment_Name, FasTagPayment_Number;
        TextView FasTagPayment_Amount;
        ImageView FasTagPayment_Img;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            FasTagPayment_Id = itemView.findViewById(R.id.FasTagPayment_Id);
            FasTagPayment_Name = itemView.findViewById(R.id.FasTagPayment_Name);
            FasTagPayment_Number = itemView.findViewById(R.id.FasTagPayment_number);
            FasTagPayment_Amount = itemView.findViewById(R.id.FasTagPayment_Amount);
            FasTagPayment_Img = itemView.findViewById(R.id.FasTagPayment_Img);
        }

        public void bind(FasTagPaymentModel fasTagPaymentModel) {
            FasTagPayment_Id.setText(fasTagPaymentModel.getFasTagPayment_Id());
            FasTagPayment_Name.setText(fasTagPaymentModel.getFasTagPayment_Name());
            FasTagPayment_Number.setText(fasTagPaymentModel.getFasTagPayment_Number());
            FasTagPayment_Amount.setText(String.valueOf(fasTagPaymentModel.getFasTagPayment_Amount()));
            FasTagPayment_Img.setImageResource(fasTagPaymentModel.getFasTagPayment_Img());
        }

    }
}

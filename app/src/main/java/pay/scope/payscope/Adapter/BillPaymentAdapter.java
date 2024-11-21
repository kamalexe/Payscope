package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.BillPaymentModel;
import pay.scope.payscope.R;

public class BillPaymentAdapter extends RecyclerView.Adapter<BillPaymentAdapter.ViewHolder> {
    Context context;
    List<BillPaymentModel> billPaymentModelList;
    private int selectedPosition = 0;

    public BillPaymentAdapter(Context context, List<BillPaymentModel> billPaymentModelList) {
        this.context = context;
        this.billPaymentModelList = billPaymentModelList;
    }

    @NonNull
    @Override
    public BillPaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.billpayment_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BillPaymentAdapter.ViewHolder holder, int position) {
        BillPaymentModel billPaymentModel = billPaymentModelList.get(position);
        holder.bind(billPaymentModel, position, selectedPosition);
    }

    @Override
    public int getItemCount() {
        return billPaymentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView BillPayment_Name, BillPayment_Amount, BillPayment_Date, BillPayment_Id;
        ImageView BillPayment_Img;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            BillPayment_Name = itemView.findViewById(R.id.Bill_Payment_Name);
            BillPayment_Amount = itemView.findViewById(R.id.Bill_Payment_Amount);
            BillPayment_Date = itemView.findViewById(R.id.Bill_Payment_Date);
            BillPayment_Id = itemView.findViewById(R.id.Bill_Payment_Id);
            BillPayment_Img = itemView.findViewById(R.id.Bill_Payment_Img);
            checkBox = itemView.findViewById(R.id.Bill_Payment_CheckBox);

            checkBox.setOnClickListener(v -> {
                int previousSelectedPosition = selectedPosition;
                selectedPosition = getAdapterPosition();
                notifyItemChanged(previousSelectedPosition);
                notifyItemChanged(selectedPosition);
            });
        }

        public void bind(BillPaymentModel billPaymentModel, int position, int selectedPosition) {
            BillPayment_Name.setText(billPaymentModel.getBillPayment_Name());
            BillPayment_Amount.setText(String.valueOf(billPaymentModel.getBillPayment_Amount()));
            BillPayment_Date.setText(billPaymentModel.getBillPayment_Date());
            BillPayment_Id.setText(billPaymentModel.getBillPayment_Id());
            BillPayment_Img.setImageResource(billPaymentModel.getBillPayment_Img());

            checkBox.setChecked(position == selectedPosition);
        }
    }
}


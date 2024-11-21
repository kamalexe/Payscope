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

import pay.scope.payscope.Model.BillSuccessModel;
import pay.scope.payscope.R;

public class BillSuccessAdapter extends RecyclerView.Adapter<BillSuccessAdapter.ViewHolder> {
    Context context;
    List<BillSuccessModel> billSuccessModelList;

    public BillSuccessAdapter(Context context, List<BillSuccessModel> billSuccessModelList) {
        this.context = context;
        this.billSuccessModelList = billSuccessModelList;
    }

    @NonNull
    @Override
    public BillSuccessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.billsuccess_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BillSuccessAdapter.ViewHolder holder, int position) {
        BillSuccessModel billSuccessModel = billSuccessModelList.get(position);
        holder.bind(billSuccessModel);
    }

    @Override
    public int getItemCount() {
        return billSuccessModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView BillSuccess_Name;
        ImageView BillSuccess_Img;
        TextView BillSuccess_Amount;
        TextView BillSuccess_Id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BillSuccess_Id = itemView.findViewById(R.id.Bill_Success_Id);
            BillSuccess_Amount = itemView.findViewById(R.id.Bill_Success_Amount);
            BillSuccess_Img = itemView.findViewById(R.id.Bill_Success_Img);
            BillSuccess_Name = itemView.findViewById(R.id.Bill_Success_Name);

        }

        public void bind(BillSuccessModel billSuccessModel) {
            BillSuccess_Id.setText(billSuccessModel.getBillSuccess_Id());
            BillSuccess_Name.setText(billSuccessModel.getBillSuccess_Name());
            BillSuccess_Amount.setText(String.valueOf(billSuccessModel.getBillSuccess_Amount()));
            BillSuccess_Img.setImageResource(billSuccessModel.getBillSuccess_Img());

        }
    }
}

package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.InvoiceModel;
import pay.scope.payscope.R;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {
    Context context;
    List<InvoiceModel> invoiceModelList;

    public InvoiceAdapter(Context context, List<InvoiceModel> invoiceModelList) {
        this.context = context;
        this.invoiceModelList = invoiceModelList;
    }

    @NonNull
    @Override
    public InvoiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.invoice_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.ViewHolder holder, int position) {
        InvoiceModel invoiceModel = invoiceModelList.get(position);
        holder.bind(invoiceModel);
    }

    @Override
    public int getItemCount() {
        return invoiceModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView InvoiceSNo, InvoiceDescription, InvoiceQty, InvoiceRate, InvoiceAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            InvoiceSNo = itemView.findViewById(R.id.InvoiceS_No);
            InvoiceDescription = itemView.findViewById(R.id.Invoice_Description);
            InvoiceQty = itemView.findViewById(R.id.Invoice_Qty);
            InvoiceRate = itemView.findViewById(R.id.Invoice_Rate);
            InvoiceAmount = itemView.findViewById(R.id.Invoice_Amount);
        }

        public void bind(InvoiceModel invoiceModel) {
            InvoiceSNo.setText(String.valueOf(invoiceModel.getInvoiceSNo()));
            InvoiceDescription.setText(invoiceModel.getInvoiceDescription());
            InvoiceQty.setText(invoiceModel.getInvoiceQty());
            InvoiceRate.setText(invoiceModel.getInvoiceRate());
            InvoiceAmount.setText(String.valueOf(invoiceModel.getInvoiceAmount()));
        }
    }
}

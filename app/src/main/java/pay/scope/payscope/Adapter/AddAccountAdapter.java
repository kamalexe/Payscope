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

import pay.scope.payscope.Activity.AccountDetailsActivity;
import pay.scope.payscope.Model.AddAccountModel;
import pay.scope.payscope.R;

public class AddAccountAdapter extends RecyclerView.Adapter<AddAccountAdapter.ViewHolder> {
    Context context;
    List<AddAccountModel> addAccountModelList;

    public AddAccountAdapter(Context context, List<AddAccountModel> addAccountModelList) {
        this.context = context;
        this.addAccountModelList = addAccountModelList;
    }

    @NonNull
    @Override
    public AddAccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.addaccount_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddAccountAdapter.ViewHolder holder, int position) {
        AddAccountModel addAccountModel = addAccountModelList.get(position);
        holder.bind(addAccountModel);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AccountDetailsActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return addAccountModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView accountName, accountNumber;
        ImageView accountImg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            accountName = itemView.findViewById(R.id.bankName);
            accountNumber = itemView.findViewById(R.id.bankNumber);
            accountImg = itemView.findViewById(R.id.bankImg);
        }

        public void bind(AddAccountModel addAccountModel) {
            accountImg.setImageResource(addAccountModel.getImg_uri());
            accountName.setText(addAccountModel.getAccount_name());
            accountNumber.setText(addAccountModel.getAccount_number());

        }
    }
}

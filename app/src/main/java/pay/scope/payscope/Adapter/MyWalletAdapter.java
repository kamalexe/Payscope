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

import pay.scope.payscope.Model.MyWalletModel;
import pay.scope.payscope.R;

public class MyWalletAdapter extends RecyclerView.Adapter<MyWalletAdapter.ViewHolder> {
    private final Context context;
    private final List<MyWalletModel> myWalletModelList;

    public MyWalletAdapter(Context context, List<MyWalletModel> myWalletModelList) {
        this.context = context;
        this.myWalletModelList = myWalletModelList;
    }

    @NonNull
    @Override
    public MyWalletAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.my_wallet_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyWalletAdapter.ViewHolder holder, int position) {
        MyWalletModel myWalletModel = myWalletModelList.get(position);
        holder.bind(myWalletModel);
    }

    @Override
    public int getItemCount() {
        return myWalletModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView MyWalletName, MyWalletTime, MyWalletPrice;
        ImageView MyWalletImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MyWalletName = itemView.findViewById(R.id.MyWallet_Name);
            MyWalletTime = itemView.findViewById(R.id.MyWallet_Time);
            MyWalletPrice = itemView.findViewById(R.id.MyWallet_Price);
            MyWalletImg = itemView.findViewById(R.id.MyWallet_Img);

        }

        public void bind(MyWalletModel myWalletModel) {
            MyWalletName.setText(myWalletModel.getMyWalletName());
            MyWalletTime.setText(myWalletModel.getMyWalletTime());
            MyWalletPrice.setText(String.valueOf(myWalletModel.getMyWalletPrice()));
            MyWalletImg.setImageResource(myWalletModel.getMyWalletImg());
        }
    }
}

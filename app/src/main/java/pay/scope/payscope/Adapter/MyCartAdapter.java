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

import pay.scope.payscope.Model.MyCartModel;
import pay.scope.payscope.R;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    Context context;
    List<MyCartModel> myCartModelList;

    public MyCartAdapter(Context context, List<MyCartModel> myCartModelList) {
        this.context = context;
        this.myCartModelList = myCartModelList;
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {
        MyCartModel myCartModel = myCartModelList.get(position);
        holder.bind(myCartModel);
    }

    @Override
    public int getItemCount() {
        return myCartModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView myname, mysend, myprice,myCartDate;
        ImageView myimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myname = itemView.findViewById(R.id.myCartName);
            mysend = itemView.findViewById(R.id.myCartSend);
            myprice = itemView.findViewById(R.id.myCartPrice);
            myimg = itemView.findViewById(R.id.mycartimg);
            myCartDate = itemView.findViewById(R.id.myCartDate);

        }


        public void bind(MyCartModel myCartModel) {
            myimg.setImageResource(myCartModel.getImg_uri());
            myname.setText(myCartModel.getName());
            myCartDate.setText(myCartModel.getDate());
            myprice.setText(String.valueOf(myCartModel.getPrice()));
            mysend.setText(String.valueOf(myCartModel.getSend()));
        }


    }
}

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

import pay.scope.payscope.Activity.ChatsActivity;
import pay.scope.payscope.Model.ChatsContactModel;
import pay.scope.payscope.R;

public class ChatsContactAdapter extends RecyclerView.Adapter<ChatsContactAdapter.ViewHolder> {
    private final Context context;
    private final List<ChatsContactModel> chatsContactModelList;

    public ChatsContactAdapter(Context context, List<ChatsContactModel> chatsContactModelList) {
        this.context = context;
        this.chatsContactModelList = chatsContactModelList;
    }

    @NonNull
    @Override
    public ChatsContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.chatscontact_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsContactAdapter.ViewHolder holder, int position) {
        ChatsContactModel chatsContactModel = chatsContactModelList.get(position);
        holder.bind(chatsContactModel);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatsActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return chatsContactModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ChatsContactName, ChatsContactMessage, ChatsContactDate, ChatsContactAmount;
        ImageView ChatsContactImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ChatsContactName = itemView.findViewById(R.id.ChatsContact_Name);
            ChatsContactMessage = itemView.findViewById(R.id.ChatsContact_Message);
            ChatsContactDate = itemView.findViewById(R.id.ChatsContact_Date);
            ChatsContactAmount = itemView.findViewById(R.id.ChatsContact_Amount);
            ChatsContactImg = itemView.findViewById(R.id.ChatsContact_Img);
        }

        public void bind(ChatsContactModel chatsContactModel) {
            ChatsContactName.setText(chatsContactModel.getChatsContactName());
            ChatsContactMessage.setText(chatsContactModel.getChatsContactMessage());
            ChatsContactDate.setText(chatsContactModel.getChatsContactDate());
            ChatsContactAmount.setText(String.valueOf(chatsContactModel.getChatsContactAmount()));
            ChatsContactImg.setImageResource(chatsContactModel.getChatsContactImg());
        }
    }
}

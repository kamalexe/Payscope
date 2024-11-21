package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.ChatsModel;
import pay.scope.payscope.R;

public class ChatsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;
    private final Context context;
    private final List<ChatsModel> chatsModelList;

    public ChatsAdapter(Context context, List<ChatsModel> chatsModelList) {
        this.context = context;
        this.chatsModelList = chatsModelList;
    }

    @Override
    public int getItemViewType(int position) {
        ChatsModel chatsModel = chatsModelList.get(position);
        return chatsModel.isSentByCurrentUser() ? VIEW_TYPE_SENT : VIEW_TYPE_RECEIVED;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT) {
            View view = LayoutInflater.from(context).inflate(R.layout.chatsend_recycler, parent, false);
            return new SentMessageViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.chatsreceive_recycler, parent, false);
            return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatsModel chatsModel = chatsModelList.get(position);
        if (holder instanceof SentMessageViewHolder) {
            ((SentMessageViewHolder) holder).bind(chatsModel);
        } else {
            ((ReceivedMessageViewHolder) holder).bind(chatsModel);
        }
    }

    @Override
    public int getItemCount() {
        return chatsModelList.size();
    }

    public static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        TextView sendChats;
        public SentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            sendChats = itemView.findViewById(R.id.sendChats);
        }

        public void bind(ChatsModel chatsModel) {
            sendChats.setText(chatsModel.getChats());
        }
    }

    public static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        TextView receiveChats;

        public ReceivedMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            receiveChats = itemView.findViewById(R.id.receiveChats);
        }

        public void bind(ChatsModel chatsModel) {
            receiveChats.setText(chatsModel.getChats());
        }
    }
}

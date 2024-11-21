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
import pay.scope.payscope.Model.NotificationsModel;
import pay.scope.payscope.R;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    Context context;
    List<NotificationsModel> notificationsModelList;

    public NotificationsAdapter(Context context, List<NotificationsModel> notificationsModelList) {
        this.context = context;
        this.notificationsModelList = notificationsModelList;
    }

    @NonNull
    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.ViewHolder holder, int position) {
        NotificationsModel notificationsModel = notificationsModelList.get(position);
        holder.bind(notificationsModel);
    }

    @Override
    public int getItemCount() {
        return notificationsModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView notificationsName, notificationsDescription,notificationsDate;
        ImageView notificationsImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationsName = itemView.findViewById(R.id.name_Notification);
            notificationsDescription = itemView.findViewById(R.id.dec_Notification);
            notificationsImg = itemView.findViewById(R.id.img_Notification);
            notificationsDate = itemView.findViewById(R.id.date_Notification);
        }

        public void bind(NotificationsModel notificationsModel) {
            notificationsName.setText(notificationsModel.getNotificationsName());
            notificationsDescription.setText(notificationsModel.getNotificationsDescription());
            notificationsDate.setText(notificationsModel.getNotificationsDate());
            notificationsImg.setImageResource(notificationsModel.getNotificationsImg());

        }

    }
}

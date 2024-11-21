package pay.scope.payscope.Adapter;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Activity.BudgetAllActivity;
import pay.scope.payscope.Activity.CashBackActivity;
import pay.scope.payscope.Activity.ChatsContactActivity;
import pay.scope.payscope.Activity.ContactUsActivity;
import pay.scope.payscope.Activity.CouponsActivity;
import pay.scope.payscope.Activity.InviteEarnActivity;
import pay.scope.payscope.Activity.LanguageChangeActivity;
import pay.scope.payscope.Activity.MapsActivity;
import pay.scope.payscope.Activity.MyWalletActivity;
import pay.scope.payscope.Activity.ReportBugActivity;
import pay.scope.payscope.Activity.SecurityCentreActivity;
import pay.scope.payscope.Activity.SelfTopUpActivity;
import pay.scope.payscope.Activity.SendFeedbackActivity;
import pay.scope.payscope.Model.DrawerModel;
import pay.scope.payscope.R;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    Context context;
    List<DrawerModel> drawerModelList;

    public DrawerAdapter(Context context, List<DrawerModel> drawerModelList) {
        this.context = context;
        this.drawerModelList = drawerModelList;
    }

    @NonNull
    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.drawer_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerAdapter.ViewHolder holder, int position) {
        DrawerModel drawerModel = drawerModelList.get(position);
        holder.drawer_title.setText(drawerModel.getDrawerTitle());
        holder.drawer_dec.setText(drawerModel.getDrawerDec());
        holder.drawer_img.setImageResource(drawerModel.getDrawerImg());


        holder.itemView.setOnClickListener(v -> {
            Class<?> activityClass; // Initialize to null
            switch (drawerModel.getDrawerTitle()) {
                case "Cashback":
                    activityClass = CashBackActivity.class;
                    break;
                case "Language":
                    activityClass = LanguageChangeActivity.class;
                    break;
                case "Report a bug":
                    activityClass = ReportBugActivity.class;
                    break;
                case "Coupons":
                    activityClass = CouponsActivity.class;
                    break;
                case "Send feedback":
                    activityClass = SendFeedbackActivity.class;
                    break;
                case "My Wallet":
                    activityClass = MyWalletActivity.class;
                    break;
                case "Security Centre":
                    activityClass = SecurityCentreActivity.class;
                    break;
                case "Invite & Earn":
                    activityClass = InviteEarnActivity.class;
                    break;
                case "Self Top-Up":
                    activityClass = SelfTopUpActivity.class;
                    break;
                case "Chats":
                    activityClass = ChatsContactActivity.class;
                    break;
                case "Budget":
                    activityClass = BudgetAllActivity.class;
                    break;
                case "Map":
                    activityClass = MapsActivity.class;
                    break;
                case "Contact Us":
                    activityClass = ContactUsActivity.class;
                    break;
                case "Share":
                    // Directly create and start the share intent here
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Yahan aap apna text daalein");
                    shareIntent.setType("text/plain");
                    context.startActivity(Intent.createChooser(shareIntent, "Share via"));
                    return; // Return here to avoid starting a new activity after sharing
                default:
                    Toast.makeText(context, "Activity not attached", Toast.LENGTH_SHORT).show();
                    return; // Return here if no activity matched
            }

            // Start the activity for the other cases
            Intent intent = new Intent(context, activityClass);
            context.startActivity(intent);
        });


    }


    @Override
    public int getItemCount() {
        return drawerModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView drawer_title, drawer_dec;
        ImageView drawer_img, drawer_view_more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drawer_title = itemView.findViewById(R.id.drawer_title);
            drawer_dec = itemView.findViewById(R.id.drawer_dec);
            drawer_img = itemView.findViewById(R.id.drawer_img);
            drawer_view_more = itemView.findViewById(R.id.drawer_view_more);
        }
    }
}

package pay.scope.payscope.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pay.scope.payscope.Activity.BillPaymentActivity;
import pay.scope.payscope.Activity.DTHRechargeActivity;
import pay.scope.payscope.Activity.FasTagPaymentActivity;
import pay.scope.payscope.Activity.FlightBookingActivity;
import pay.scope.payscope.Activity.IRCTCActivity;
import pay.scope.payscope.Activity.InsurancePaymentActivity;
import pay.scope.payscope.Activity.MobileRechargeActivity;


import pay.scope.payscope.Activity.PenCardServiceActivity;
import pay.scope.payscope.Model.HomeModel;
import pay.scope.payscope.R;

public class HomeRechargeAdapter extends RecyclerView.Adapter<HomeRechargeAdapter.ViewHolder> {

    private final Context context;
    private final List<HomeModel> homeModelList;

    private static final Map<String, Class<?>> ACTIVITY_MAP = new HashMap<>();

    static {
        ACTIVITY_MAP.put("Bill\nPayment", BillPaymentActivity.class);
        ACTIVITY_MAP.put("Mobile\nRecharge", MobileRechargeActivity.class);
        ACTIVITY_MAP.put("DTH\nRecharge", DTHRechargeActivity.class);
        ACTIVITY_MAP.put("Insurance\nPayment", InsurancePaymentActivity.class);
        ACTIVITY_MAP.put("Pan Card\nService", PenCardServiceActivity.class);
        ACTIVITY_MAP.put("Fastag\nPayment", FasTagPaymentActivity.class);
        ACTIVITY_MAP.put("Flight\nBooking", FlightBookingActivity.class);
        ACTIVITY_MAP.put("IRCTC\nBooking", IRCTCActivity.class);
    }

    public HomeRechargeAdapter(Context context, List<HomeModel> homeModelList) {
        this.context = context;
        this.homeModelList = homeModelList;
    }

    @NonNull
    @Override
    public HomeRechargeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.home_recycler, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull HomeRechargeAdapter.ViewHolder holder, int position) {
        HomeModel homeModel = homeModelList.get(position);
        holder.homeText.setText(homeModel.getHomeText());
        holder.homeImage.setImageResource(homeModel.getHomeIcon());

        holder.homeLayout.setOnClickListener(v -> {
            String homeText = homeModel.getHomeText().trim();  // Trimming extra spaces
            Class<?> activityClass = ACTIVITY_MAP.get(homeText);

            if (activityClass != null) {
                Intent intent = new Intent(context, activityClass);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "No activity found for this option", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView homeText;
        ImageView homeImage;
        LinearLayout homeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeText = itemView.findViewById(R.id.home_recycler_text);
            homeImage = itemView.findViewById(R.id.home_recycler_img);
            homeLayout = itemView.findViewById(R.id.home_recycler_layout);
        }
    }
}

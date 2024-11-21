package pay.scope.payscope.Activity;

import static pay.scope.payscope.Activity.LoginActivity3.KEY_USER_TOKEN;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.DrawerAdapter;
import pay.scope.payscope.ApiService.LoginService;
import pay.scope.payscope.Model.DrawerModel;
import pay.scope.payscope.Model.LogoutResponse;
import pay.scope.payscope.R;
import pay.scope.payscope.RetrofitClient.RetrofitLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawerActivity extends AppCompatActivity {
    RecyclerView drawerRecycler;
    MaterialToolbar drawer_toolbar;
    public static SharedPreferences preferences;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    private static String ACCESS_TOKEN;
    CardView logout_yes, Logout_canclebtn;
    private AlertDialog alertDialog;
    TextView drawer_logout, app_version;
    LinearLayout drawer_profile;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        drawerRecycler = findViewById(R.id.drawer_recycler);
        drawer_toolbar = findViewById(R.id.drawer_toolbar);
        drawer_logout = findViewById(R.id.drawer_logout);
        drawer_profile = findViewById(R.id.drawer_profile);
        app_version = findViewById(R.id.app_version);

        preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        ACCESS_TOKEN = preferences.getString(KEY_USER_TOKEN, "");

        drawer_toolbar.setNavigationOnClickListener(v -> finish());
        drawer_logout.setOnClickListener(v -> showAlertDialogLogOut());

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

            String versionName = pInfo.versionName;
            app_version.setText("Version: " + versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        List<DrawerModel> drawerModelList = new ArrayList<>();
        drawerModelList.add(new DrawerModel(R.drawable.baseline_shopping_bag_24, "Cashback", "Earn cashback and get exciting brand vouchers on every recharge!"));
        drawerModelList.add(new DrawerModel(R.drawable.baseline_language_24, "Language", "Switch language easily and enjoy a personalized app experience!"));
        drawerModelList.add(new DrawerModel(R.drawable.baseline_bug_report_24, "Report a bug", "Report a bug and help us improve your experience!"));
        drawerModelList.add(new DrawerModel(R.drawable.baseline_card_giftcard_24, "Coupons", "Exclusive coupons for extra savings on every recharge! "));
        drawerModelList.add(new DrawerModel(R.drawable.baseline_send_24, "Send feedback", "Send feedback and share your thoughts to help us improve! "));
        drawerModelList.add(new DrawerModel(R.drawable.baseline_wallet_24, "My Wallet", "Securely manage your balance and transactions in one place!"));
        drawerModelList.add(new DrawerModel(R.drawable.baseline_security_24, "Security Centre", "Safeguard your account with advanced protection features! "));
        drawerModelList.add(new DrawerModel(R.drawable.baseline_inventory_24, "Invite & Earn", "Refer friends and earn rewards on every successful signup!"));
        drawerModelList.add(new DrawerModel(R.drawable.baseline_photo_camera_front_24, "Self Top-Up", "Easily recharge your account with just a few taps! "));
        drawerModelList.add(new DrawerModel(R.drawable.baseline_chat_24, "Chats", "Connect with support and fellow users for instant assistance!"));
        drawerModelList.add(new DrawerModel(R.drawable.baseline_badge_24, "Budget", "Track your spending and manage your finances effortlessly!"));
        drawerModelList.add(new DrawerModel(R.drawable.location, "Map", "Explore nearby locations and discover exciting offers around you! "));
        drawerModelList.add(new DrawerModel(R.drawable.baseline_contactless_24, "Contact Us", "Reach out for support or inquiries; we're here to help! "));
        drawerModelList.add(new DrawerModel(R.drawable.baseline_share_24, "Share", "Spread the word and share your favorite offers with friends"));


        DrawerAdapter drawerAdapter = new DrawerAdapter(this, drawerModelList);
        drawerRecycler.setAdapter(drawerAdapter);
        drawerRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        drawer_profile.setOnClickListener(v -> startActivity(new Intent(this, ProfileUpdateActivity.class)));


    }


    private void showAlertDialogLogOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View logoutView = inflater.inflate(R.layout.logout_layout, null);
        builder.setView(logoutView);
        builder.setCancelable(false);
        alertDialog = builder.create();
        if (!this.isFinishing()) {
            alertDialog.show();
        }

        logout_yes = logoutView.findViewById(R.id.logoutbtn);
        Logout_canclebtn = logoutView.findViewById(R.id.Logout_canclebtn);

        Logout_canclebtn.setOnClickListener(v -> alertDialog.dismiss());

        logout_yes.setOnClickListener(v -> LogoutLogin());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    private void LogoutLogin() {
        String BASE_URL = "https://stage.payscope.in";
        LoginService apiService = RetrofitLogin.getClient(BASE_URL).create(LoginService.class);
        Call<LogoutResponse> call = apiService.logout("Bearer " + ACCESS_TOKEN);
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogoutResponse> call, @NonNull Response<LogoutResponse> response) {
                if (response.isSuccessful()) {
                    LogoutResponse logoutResponse = response.body();
                    if (logoutResponse != null) {
                        Log.d("logout", logoutResponse.getMessage());
                        Toast.makeText(DrawerActivity.this, logoutResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove(LoginActivity3.KEY_USER_NAME);
                        editor.remove(LoginActivity3.KEY_USER_TOKEN);
                        editor.clear();
                        editor.apply();

                        Intent intent = new Intent(DrawerActivity.this, LoginActivity3.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        DrawerActivity.this.finish();


                    } else {
                        Log.d("logout", "Logout response body is null");
                    }
                } else {
                    int errorCode = response.code();
                    Log.d("logout", "Logout failed with error code: " + errorCode);
                    try {
                        Log.d("logout", "Error body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LogoutResponse> call, @NonNull Throwable t) {
                if (t instanceof IOException) {
                    // Network error
                    Log.d("logout", "Network error: " + t.getMessage());
                } else {
                    // Unexpected error
                    Log.d("logout", "Unexpected error: " + t.getMessage());
                }
            }
        });
    }
}
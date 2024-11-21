package pay.scope.payscope.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

import java.io.IOException;


import pay.scope.payscope.Activity.CashBackActivity;
import pay.scope.payscope.Activity.CouponsActivity;
import pay.scope.payscope.Activity.LanguageChangeActivity;
import pay.scope.payscope.Activity.LoginActivity3;
import pay.scope.payscope.Activity.NotificationsActivity;
import pay.scope.payscope.Activity.ReceiveMoneyActivity;
import pay.scope.payscope.Activity.ReportBugActivity;
import pay.scope.payscope.Activity.SendFeedbackActivity;
import pay.scope.payscope.ApiService.LoginService;
import pay.scope.payscope.Model.LogoutResponse;
import pay.scope.payscope.R;
import pay.scope.payscope.RetrofitClient.RetrofitLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static pay.scope.payscope.Activity.LoginActivity3.KEY_USER_TOKEN;

public class SettingFragment extends Fragment {
    /*MaterialCardView cashback, language, logout,  report_bug, coupons, transactionSetting, send_feedback;
    CardView logout_yes, Logout_canclebtn;
    public static SharedPreferences preferences;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    private AlertDialog alertDialog;
    private static String ACCESS_TOKEN;

     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);



/*
        preferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        ACCESS_TOKEN = preferences.getString(KEY_USER_TOKEN, "");
//        Toast.makeText(requireActivity(), "ACCESS_TOKEN" + ACCESS_TOKEN, Toast.LENGTH_SHORT).show();

        cashback = view.findViewById(R.id.cashback);
        language = view.findViewById(R.id.language);
        logout = view.findViewById(R.id.logout);

        report_bug = view.findViewById(R.id.report_bug);
        coupons = view.findViewById(R.id.coupons);
        send_feedback = view.findViewById(R.id.send_feedback);
        transactionSetting = view.findViewById(R.id.transactionSetting);

        cashback.setOnClickListener(v -> startActivity(new Intent(requireActivity(), CashBackActivity.class)));

        language.setOnClickListener(v -> startActivity(new Intent(requireActivity(), LanguageChangeActivity.class)));

        send_feedback.setOnClickListener(v -> startActivity(new Intent(requireActivity(), SendFeedbackActivity.class)));

        transactionSetting.setOnClickListener(v -> startActivity(new Intent(requireActivity(), ReceiveMoneyActivity.class)));

        report_bug.setOnClickListener(v -> startActivity(new Intent(requireActivity(), ReportBugActivity.class)));

        coupons.setOnClickListener(v -> startActivity(new Intent(requireActivity(), CouponsActivity.class)));

        logout.setOnClickListener(v -> showAlertDialogLogOut());

        return view;
    }
    private void showAlertDialogLogOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View logoutView = inflater.inflate(R.layout.logout_layout, null);
        builder.setView(logoutView);
        builder.setCancelable(false);
        alertDialog = builder.create();
        if (!requireActivity().isFinishing()) {
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
                        Toast.makeText(requireActivity(), logoutResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove(LoginActivity3.KEY_USER_NAME);
                        editor.remove(LoginActivity3.KEY_USER_TOKEN);
                        editor.clear();
                        editor.apply();

                        Intent intent = new Intent(requireActivity(), LoginActivity3.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        requireActivity().finish();


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


 */

        return view;
    }
}
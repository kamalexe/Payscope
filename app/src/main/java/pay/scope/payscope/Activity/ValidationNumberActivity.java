package pay.scope.payscope.Activity;

import static pay.scope.payscope.Activity.LoginActivity3.KEY_USER_NAME;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import pay.scope.payscope.ApiService.LoginService;
import pay.scope.payscope.Model.OTPModel;
import pay.scope.payscope.Model.OTPResponse;
import pay.scope.payscope.Model.ResendOTPModel;
import pay.scope.payscope.Model.ResendOTPResponce;
import pay.scope.payscope.R;
import pay.scope.payscope.RetrofitClient.RetrofitLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ValidationNumberActivity extends AppCompatActivity {
    TextView resendotp, TextEmail, timer;
    Button otpVerify;
    EditText editText1, editText2, editText3, editText4;
    private LoginService apiService;
    Retrofit retrofit;
    public static SharedPreferences preferences;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    public static String globalUserName;
    private CountDownTimer countDownTimer;
    private static final long OTP_TIMEOUT = 60000;

    private static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_number);

        resendotp = findViewById(R.id.resendotp);
        otpVerify = findViewById(R.id.otpVerify);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        TextEmail = findViewById(R.id.TextEmail);
//        loadingOverlay = findViewById(R.id.loadingOverlay);
        timer = findViewById(R.id.timer);

        preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registerName = preferences.getString(KEY_USER_NAME, "");
        Log.d("UserNames", registerName);
        globalUserName = registerName;

        TextEmail.setText(registerName);
        retrofit = RetrofitLogin.getClient("https://stage.payscope.in");
        apiService = retrofit.create(LoginService.class);
        startOTPTimer();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.dismiss();



        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    editText2.requestFocus();
                }
            }
        });

        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (count == 1 && after == 0) {
                    editText1.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    editText3.requestFocus();
                }
            }
        });

        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (count == 1 && after == 0) {
                    editText2.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    editText4.requestFocus();
                }
            }
        });

        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (count == 1 && after == 0) {
                    editText3.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        otpVerify.setOnClickListener(v -> {
            progressDialog.show();
            String OTPString = editText1.getText().toString() + editText2.getText().toString() + editText3.getText().toString() + editText4.getText().toString();
            if (OTPString.isEmpty()) {
                Toast.makeText(ValidationNumberActivity.this, "Enter Otp number", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            } else {
                verifyOTP(OTPString);
            }
        });

        resendotp.setOnClickListener(v -> {
            progressDialog.show();
            resendOTP();
        });
    }

    private void verifyOTP(String OTP) {
        OTPModel otpModel = new OTPModel(OTP);
        apiService.createOTP(otpModel).enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(@NonNull Call<OTPResponse> call, @NonNull Response<OTPResponse> response) {
                progressDialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        OTPResponse otpResponse = response.body();
                        Toast.makeText(ValidationNumberActivity.this, otpResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("OTP", "isSuccessful : " + response);
                        startActivity(new Intent(ValidationNumberActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Log.d("OTP", "isNotSuccessful : " + response);
                    }
                } catch (Exception e) {
                    Log.d("OTP", "Exception : " + e);
                }
            }

            @Override
            public void onFailure(@NonNull Call<OTPResponse> call, @NonNull Throwable t) {
                Log.d("OTP", "onFailure : " + t);
            }
        });
    }

    private void resendOTP() {
        ResendOTPModel resendOTPModel = new ResendOTPModel(globalUserName);
        apiService.createResendOTP(resendOTPModel).enqueue(new Callback<ResendOTPResponce>() {
            @Override
            public void onResponse(@NonNull Call<ResendOTPResponce> call, @NonNull Response<ResendOTPResponce> response) {
                try {
                    if (response.isSuccessful()) {
                        ResendOTPResponce resendOTPResponce = response.body();
                        Toast.makeText(ValidationNumberActivity.this, resendOTPResponce.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        startOTPTimer();
                        Log.d("ResendOTP", "isSuccessful : " + response);
                    } else {
                        Log.d("ResendOTP", "isNotSuccessful : " + response);
                    }
                } catch (Exception e) {
                    Log.d("ResendOTP", "Exception : " + e);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResendOTPResponce> call, @NonNull Throwable t) {
                Log.d("ResendOTP", "onFailure : " + t);
            }
        });
    }

    private void startOTPTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(OTP_TIMEOUT, 1000) {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                timer.setText(String.format("%02d:%02d", minutes, seconds));
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                timer.setText("Resend OTP");
                resendotp.setEnabled(true);
                resendotp.setTextColor(ContextCompat.getColor(ValidationNumberActivity.this, R.color.blue));

            }
        };
        countDownTimer.start();
        resendotp.setEnabled(false);
        resendotp.setTextColor(ContextCompat.getColor(ValidationNumberActivity.this, R.color.hint));
    }


}




package pay.scope.payscope.Activity;

import static pay.scope.payscope.Activity.LoginActivity3.KEY_USER_TOKEN;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import pay.scope.payscope.ApiService.LoginService;
import pay.scope.payscope.Helper.CustomSpinnerAdapter;
import pay.scope.payscope.Model.PaymentModesModel;
import pay.scope.payscope.Model.QuickResponceModel;
import pay.scope.payscope.Model.QuickTransferModel;
import pay.scope.payscope.R;
import pay.scope.payscope.RetrofitClient.RetrofitLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuickTransferActivity extends AppCompatActivity {
    MaterialToolbar QuickTransfer_toolbar;
    private LoginService apiService;
    Retrofit retrofit;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    private static String ACCESS_TOKEN;
    Button quickTransferBtn;
    TextInputEditText quickAccountNo, quickIFSECode, quickHolderName, quickAmount, quickRemark;
    Spinner quickPaymentMode;
    private static int paymentModes;
    ImageView quickTransferHistory;
    TextView blankAccount, blankIfseCode, blankName, blankAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_transfer);

        QuickTransfer_toolbar = findViewById(R.id.QuickTransfer_toolbar);
        quickTransferBtn = findViewById(R.id.quickTransferBtn);

        blankAccount = findViewById(R.id.blank_account);
        blankIfseCode = findViewById(R.id.blank_ifseCode);
        blankName = findViewById(R.id.blank_name);
        blankAmount = findViewById(R.id.blank_amount);

        quickAccountNo = findViewById(R.id.quickTransferAccountNo);
        quickIFSECode = findViewById(R.id.quickTransferIFSECode);
        quickHolderName = findViewById(R.id.quickTransferHolderName);
        quickAmount = findViewById(R.id.quickTransferAmount);
        quickPaymentMode = findViewById(R.id.quickTransferPaymentMode);
        quickRemark = findViewById(R.id.quickTransferRemark);
        quickTransferHistory = findViewById(R.id.quickTransferHistory);

        retrofit = RetrofitLogin.getClient("https://stage.payscope.in");
        apiService = retrofit.create(LoginService.class);
        SharedPreferences preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        ACCESS_TOKEN = preferences.getString(KEY_USER_TOKEN, "");
        Log.d("quickTransfer", "token: " + ACCESS_TOKEN);
        quickPaymentModes();

        QuickTransfer_toolbar.setNavigationOnClickListener(v -> finish());
        QuickTransfer_toolbar.setOnClickListener(v -> startActivity(new Intent(QuickTransferActivity.this, QuickTransferHistory.class)));


        quickTransferBtn.setOnClickListener(v -> {
            String accountNoStr = quickAccountNo.getText().toString().trim();
            String ifseCode = quickIFSECode.getText().toString().trim();
            String holderName = quickHolderName.getText().toString().trim();
            String amountStr = quickAmount.getText().toString().trim();
            String remark = quickRemark.getText().toString().trim();


            if (accountNoStr.isEmpty() || ifseCode.isEmpty() || holderName.isEmpty() || amountStr.isEmpty() || remark.isEmpty()) {
                Toast.makeText(QuickTransferActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                int accountNo = Integer.parseInt(accountNoStr);
                float amount = Float.parseFloat(amountStr);
                if (amount <= 0) {
                    Toast.makeText(QuickTransferActivity.this, "Amount should be greater than zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                createQuickTransfer(accountNo, ifseCode, holderName, amount, paymentModes, remark);
            } catch (NumberFormatException e) {
                Toast.makeText(QuickTransferActivity.this, "Invalid number format", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void createQuickTransfer(int account_number, String ifsc_code, String account_holder_name, float amount, int payment_mode, String remark) {
        QuickResponceModel quickResponceModel = new QuickResponceModel(account_number, ifsc_code, account_holder_name, amount, payment_mode, remark);
        Call<QuickTransferModel> quickTransferCall = apiService.createQuickTransferRequest(quickResponceModel, "Bearer " + ACCESS_TOKEN);
        quickTransferCall.enqueue(new Callback<QuickTransferModel>() {
            @Override
            public void onResponse(@NonNull Call<QuickTransferModel> call, @NonNull Response<QuickTransferModel> response) {
                if (response.isSuccessful()) {
                    QuickTransferModel quickTransferModel = response.body();
                    Toast.makeText(QuickTransferActivity.this, quickTransferModel.getMessage(), Toast.LENGTH_SHORT).show();


                    Log.d("quickTransfer", " account_number : " + account_number + " ifsc_code: " + ifsc_code + " account_holder_name: " + account_holder_name + " amount: " + amount + " payment_mode: " + payment_mode + " remark: " + remark);


                    if (account_number > 0) {
                        blankAccount.setText(String.valueOf(account_number));  // Convert to string for setting text
                    }
                    if (!ifsc_code.isEmpty()) {
                        blankIfseCode.setText(ifsc_code);
                    }
                    if (!account_holder_name.isEmpty()) {
                        blankName.setText(account_holder_name);
                    }
                    if (amount > 0) {
                        blankAmount.setText(String.valueOf(amount));
                    }


                    quickIFSECode.setText("");
                    quickHolderName.setText("");
                    quickAmount.setText("");
                    quickRemark.setText("");

                } else {
                    // Extract and log the error body content
                    ResponseBody responseBody = response.errorBody();
                    String errorBodyString;
                    if (responseBody != null) {
                        try {
                            errorBodyString = responseBody.string(); // Convert the response body to a String
                            Log.d("quickTransfer", "Error Body: " + errorBodyString); // Log the error body
                        } catch (IOException e) {
                            Log.e("quickTransfer", "Error reading response body", e);
                        }
                    } else {
                        Log.d("quickTransfer", "Error Body is null");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<QuickTransferModel> call, @NonNull Throwable t) {
                Log.d("quickTransfer", "onFailure: " + t.getMessage());
            }
        });
    }

    public void quickPaymentModes() {
        Call<List<PaymentModesModel>> call = apiService.paymentModesModel("Bearer " + ACCESS_TOKEN);
        call.enqueue(new Callback<List<PaymentModesModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PaymentModesModel>> call, @NonNull Response<List<PaymentModesModel>> response) {
                List<PaymentModesModel> paymentModesModelList = response.body();
                if (paymentModesModelList != null && paymentModesModelList.size() > 0) {
                    List<String> payModes = new ArrayList<>();
                    for (PaymentModesModel modesModel : paymentModesModelList) {
                        payModes.add(modesModel.getName());
                    }
                    CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(QuickTransferActivity.this, payModes);
                    quickPaymentMode.setAdapter(spinnerAdapter);

                    quickPaymentMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            paymentModes = paymentModesModelList.get(position).getId();
                            Log.d("payMode", "selectedMode: " + paymentModes);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Do nothing
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PaymentModesModel>> call, @NonNull Throwable t) {
                Log.d("payMode", "onFailure: " + t.getMessage());
            }
        });
    }
}


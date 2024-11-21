package pay.scope.payscope.Activity;

import static pay.scope.payscope.Activity.LoginActivity3.KEY_USER_TOKEN;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

import pay.scope.payscope.Adapter.QuickHistoryAdapter;

import pay.scope.payscope.ApiService.LoginService;
import pay.scope.payscope.Model.QuickHistoryModel;
import pay.scope.payscope.R;
import pay.scope.payscope.RetrofitClient.RetrofitLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuickTransferHistory extends AppCompatActivity {
    MaterialToolbar QuickHistory_toolbar;
    private LoginService apiService;
    Retrofit retrofit;
    RecyclerView QuickHistory_recycler;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    private static String ACCESS_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_transfer_history);

        QuickHistory_toolbar = findViewById(R.id.QuickHistory_toolbar);
        QuickHistory_recycler = findViewById(R.id.QuickHistory_recycler);

        QuickHistory_toolbar.setNavigationOnClickListener(v -> finish());

        retrofit = RetrofitLogin.getClient("https://stage.payscope.in");
        apiService = retrofit.create(LoginService.class);
        SharedPreferences preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        ACCESS_TOKEN = preferences.getString(KEY_USER_TOKEN, "");
        Log.d("quickResponce", "token: " + ACCESS_TOKEN);

        quickResponce();

    }
    public void quickResponce() {
        Call<QuickHistoryModel> quickHistoryModelCall = apiService.quickHistoryResponce("Bearer " + ACCESS_TOKEN);
        quickHistoryModelCall.enqueue(new Callback<QuickHistoryModel>() {
            @Override
            public void onResponse(@NonNull Call<QuickHistoryModel> call, @NonNull Response<QuickHistoryModel> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<QuickHistoryModel.Data> quickHistoryModel = response.body().getData();
                    Log.d("quickResponce", "isSuccessful: " + response.isSuccessful());
                    Log.d("quickResponce", "body: " + response.body());
                    for (QuickHistoryModel.Data data : quickHistoryModel) {
                        Log.d("quickResponce", "id: " + data.getId());
                    }
                    QuickHistory_recycler.setLayoutManager(new LinearLayoutManager(QuickTransferHistory.this, LinearLayoutManager.VERTICAL, false));
                    QuickHistoryAdapter adapter = new QuickHistoryAdapter(QuickTransferHistory.this, quickHistoryModel);
                    QuickHistory_recycler.setAdapter(adapter);
                } else {
                    Log.d("quickResponce", "Error Body is null");
                }
            }
            @Override
            public void onFailure(@NonNull Call<QuickHistoryModel> call, @NonNull Throwable t) {
                Log.d("quickResponce", "Response not successful");
            }
        });
    }
}
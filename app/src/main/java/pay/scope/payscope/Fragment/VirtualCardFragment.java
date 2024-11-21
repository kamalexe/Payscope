package pay.scope.payscope.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static pay.scope.payscope.Activity.LoginActivity3.KEY_USER_TOKEN;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.VirtualCardAdapter;
import pay.scope.payscope.ApiService.LoginService;
import pay.scope.payscope.Model.VirtualCardModel;
import pay.scope.payscope.R;
import pay.scope.payscope.RetrofitClient.RetrofitLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VirtualCardFragment extends Fragment {
    RecyclerView virtualCard_recycler;
    public static SharedPreferences preferences;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    private static String ACCESS_TOKEN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_virtual_card, container, false);

        virtualCard_recycler = view.findViewById(R.id.virtualCard_recycler);

        preferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        ACCESS_TOKEN = preferences.getString(KEY_USER_TOKEN, "");
        Log.d("virtualRequest", "token: " + ACCESS_TOKEN);
        VirtualTransaction();

        return view;
    }


    private void VirtualTransaction() {
        String BASE_URL = "https://stage.payscope.in";
        LoginService apiService = RetrofitLogin.getClient(BASE_URL).create(LoginService.class);
        Call<VirtualCardModel.Data> call = apiService.virtualCardModel("Bearer " + ACCESS_TOKEN);
        call.enqueue(new Callback<VirtualCardModel.Data>() {
            @Override
            public void onResponse(@NonNull Call<VirtualCardModel.Data> call, @NonNull Response<VirtualCardModel.Data> response) {
                if (response.isSuccessful() && response.body() != null) {
                    VirtualCardModel.Data virtualCardDataModel = response.body();

                    VirtualCardModel virtualCardModel = new VirtualCardModel();

                    Log.d("virtualRequest", "response: " + virtualCardModel.getMessage());
                    Log.d("virtualRequest", "response: " + virtualCardModel.getStatus());
                    Log.d("virtualRequest", "response: " + virtualCardModel.getData());

                    Log.d("virtualRequest", "response: " + response.body());
                    Log.d("virtualRequest", "message: " + response.message());
                    Log.d("virtualRequest", "isSuccessful: " + response.isSuccessful());
                    Log.d("virtualRequest", "getRemitter_name: " + virtualCardDataModel.getRemitter_name());
                    Log.d("virtualRequest", "getRemitter_account_number: " + virtualCardDataModel.getRemitter_account_number());
                    Log.d("virtualRequest", "getCredit_amount: " + virtualCardDataModel.getCredit_amount());


                    List<VirtualCardModel.Data> virtualCardModelList = new ArrayList<>();
                    virtualCardModelList.add(virtualCardDataModel);
                    VirtualCardAdapter cardAdapter = new VirtualCardAdapter(requireContext(), virtualCardModelList);
                    virtualCard_recycler.setAdapter(cardAdapter);
                    virtualCard_recycler.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));

                } else {
                    Log.d("virtualRequest", "Response was not successful");
                }
            }
            @Override
            public void onFailure(@NonNull Call<VirtualCardModel.Data> call, @NonNull Throwable t) {
                if (isAdded()) {
                    Log.e("virtualRequest", "API call failed: " + t.getMessage());
                }
            }
        });

    }


}
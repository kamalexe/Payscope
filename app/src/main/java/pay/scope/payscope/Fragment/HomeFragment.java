package pay.scope.payscope.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static pay.scope.payscope.Activity.LoginActivity3.KEY_USER_TOKEN;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Activity.QuickViewAllActivity;

import pay.scope.payscope.Adapter.HomeAdapter;
import pay.scope.payscope.Adapter.HomeRechargeAdapter;
import pay.scope.payscope.Adapter.TransactionHomeAdapter;
import pay.scope.payscope.ApiService.LoginService;
import pay.scope.payscope.Model.HomeModel;
import pay.scope.payscope.Model.TransactionModel;
import pay.scope.payscope.R;
import pay.scope.payscope.RetrofitClient.RetrofitLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView transactionRecycler, homeRecycler, homeRechargeRecycler;
    TextView transactionsViewAll, quickViewAll, balance_show1, balance_show2;
    public static SharedPreferences preferences;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    private static String ACCESS_TOKEN;
    int spanCounts = 4;
    int spanOptions = 4;
    ImageView balance_hide1, balance_hide2;
    private boolean isPasswordVisible = false;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        transactionsViewAll = view.findViewById(R.id.transactionsViewAll);
        quickViewAll = view.findViewById(R.id.quickViewAll);
        balance_hide1 = view.findViewById(R.id.balance_hide1);
        balance_hide2 = view.findViewById(R.id.balance_hide2);
        balance_show1 = view.findViewById(R.id.balance_show1);
        balance_show2 = view.findViewById(R.id.balance_show2);


        transactionRecycler = view.findViewById(R.id.transactionRecycler);
        homeRecycler = view.findViewById(R.id.home_recycler);
        homeRechargeRecycler = view.findViewById(R.id.home_recharge_recycler);


        preferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        ACCESS_TOKEN = preferences.getString(KEY_USER_TOKEN, "");
        Log.d("HomeFG", "token: " + ACCESS_TOKEN);
        HomeTransaction();


        quickViewAll.setOnClickListener(v -> startActivity(new Intent(getActivity(), QuickViewAllActivity.class)));

        transactionsViewAll.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, new TransactionFragment()).addToBackStack(null).commit());


        List<HomeModel> homeModelList = new ArrayList<>();
        homeModelList.add(new HomeModel(R.drawable.finger, "Aeps"));
        homeModelList.add(new HomeModel(R.drawable.qick_transfer, "Quick Transfer"));
        homeModelList.add(new HomeModel(R.drawable.money_tranfer, "Money Transfer"));
        homeModelList.add(new HomeModel(R.drawable.scanner, "Scan Pay"));

        homeModelList.add(new HomeModel(R.drawable.send, "Send"));
        homeModelList.add(new HomeModel(R.drawable.quick_loan, "Quick Loan"));
        homeModelList.add(new HomeModel(R.drawable.move_bank, "Move To Bank"));
        homeModelList.add(new HomeModel(R.drawable.add_bank, "Add Bank"));


        HomeAdapter homeAdapter = new HomeAdapter(requireActivity(), homeModelList);
        homeRecycler.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(requireActivity(), spanCounts);
        layoutManager.setSpanCount(spanCounts);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        homeRecycler.setLayoutManager(layoutManager);


        List<HomeModel> homeRechargeModelList = new ArrayList<>();
        homeRechargeModelList.add(new HomeModel(R.drawable.bill_payment, "Bill\nPayment"));
        homeRechargeModelList.add(new HomeModel(R.drawable.mobile_recharge, "Mobile\nRecharge"));
        homeRechargeModelList.add(new HomeModel(R.drawable.dth_recharge, "DTH\nRecharge"));
        homeRechargeModelList.add(new HomeModel(R.drawable.insurance, "Insurance\nPayment"));
        homeRechargeModelList.add(new HomeModel(R.drawable.pen_card, "Pan Card\nService"));
        homeRechargeModelList.add(new HomeModel(R.drawable.fastag, "Fastag\nPayment"));
        homeRechargeModelList.add(new HomeModel(R.drawable.flight, "Flight\nBooking"));
        homeRechargeModelList.add(new HomeModel(R.drawable.train, "IRCTC\nBooking"));

        HomeRechargeAdapter rechargeAdapter = new HomeRechargeAdapter(requireActivity(), homeRechargeModelList);
        homeRechargeRecycler.setAdapter(rechargeAdapter);
        GridLayoutManager layoutManager1 = new GridLayoutManager(requireActivity(), spanOptions);
        layoutManager1.setSpanCount(spanOptions);
        layoutManager1.setOrientation(GridLayoutManager.VERTICAL);
        homeRechargeRecycler.setLayoutManager(layoutManager1);


        balance_hide1.setOnClickListener(v -> {
            if (isPasswordVisible) {
                // Hide the text
                balance_show1.setText("Total Balance");
                balance_hide1.setImageResource(R.drawable.baseline_visibility_off);
            } else {
                // show the text
                balance_hide1.setImageResource(R.drawable.baseline_remove_red_eye);
                balance_show1.setText("₹ 1234");
            }
            isPasswordVisible = !isPasswordVisible;
        });


        balance_hide2.setOnClickListener(v -> {
            if (isPasswordVisible) {
                balance_show2.setText("Total Balance");
                balance_hide2.setImageResource(R.drawable.baseline_visibility_off);
            } else {
                balance_hide2.setImageResource(R.drawable.baseline_remove_red_eye);
                balance_show2.setText("₹ 1234");
            }
            isPasswordVisible = !isPasswordVisible;
        });


        return view;
    }

    private void HomeTransaction() {
        String BASE_URL = "https://stage.payscope.in";
        LoginService apiService = RetrofitLogin.getClient(BASE_URL).create(LoginService.class);
        Call<TransactionModel> call = apiService.historyModel("Bearer " + ACCESS_TOKEN);
        call.enqueue(new Callback<TransactionModel>() {
            @Override
            public void onResponse(@NonNull Call<TransactionModel> call, @NonNull Response<TransactionModel> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<TransactionModel.Data> transactionModel = response.body().getData();

                    Log.d("HomeFG", "isSuccessful: " + response.isSuccessful());
                    Log.d("HomeFG", "body: " + response.body());
                    for (TransactionModel.Data data : transactionModel) {
                        Log.d("HomeFG", "id: " + data.getId());

                    }

                    transactionRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
                    TransactionHomeAdapter homeAdapter = new TransactionHomeAdapter(requireActivity(), transactionModel);
                    transactionRecycler.setAdapter(homeAdapter);

                    Log.d("HomeFG", "Response  successful");
                } else {
                    Log.d("HomeFG", "Response not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<TransactionModel> call, @NonNull Throwable t) {
                Log.e("HomeFG", "Error: " + t.getMessage());
            }
        });
    }
}
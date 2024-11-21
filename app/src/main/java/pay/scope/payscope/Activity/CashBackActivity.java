package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.CashBackAdapter;

import pay.scope.payscope.Model.CashBackModel;

import pay.scope.payscope.R;

public class CashBackActivity extends AppCompatActivity {

    RecyclerView cashback_recycler;
    Button withdrawbtn;
    MaterialToolbar CashBack_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_back);

        cashback_recycler = findViewById(R.id.cashback_recycler);
        withdrawbtn = findViewById(R.id.withdrawbtn);
        CashBack_toolbar = findViewById(R.id.CashBack_toolbar);


        CashBack_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(CashBackActivity.this, MainActivity.class));
            finish();

        });

        List<CashBackModel> cashBackModelList = new ArrayList<>();

        cashBackModelList.add(new CashBackModel("CashBack", "4.00 PM", "Panding", 1245.00));
        cashBackModelList.add(new CashBackModel("CashBack", "4.00 PM", "Panding", 1245.00));
        cashBackModelList.add(new CashBackModel("CashBack", "4.00 PM", "Panding", 1245.00));
        cashBackModelList.add(new CashBackModel("CashBack", "4.00 PM", "Panding", 1245.00));
        cashBackModelList.add(new CashBackModel("CashBack", "4.00 PM", "Panding", 1245.00));
        cashBackModelList.add(new CashBackModel("CashBack", "4.00 PM", "Panding", 1245.00));
        cashBackModelList.add(new CashBackModel("CashBack", "4.00 PM", "Panding", 1245.00));
        cashBackModelList.add(new CashBackModel("CashBack", "4.00 PM", "Panding", 1245.00));
        cashBackModelList.add(new CashBackModel("CashBack", "4.00 PM", "Padding", 1245.00));


        // Set up RecyclerView
        cashback_recycler.setLayoutManager(new LinearLayoutManager(CashBackActivity.this, LinearLayoutManager.VERTICAL, false));
        CashBackAdapter adapter = new CashBackAdapter(CashBackActivity.this, cashBackModelList);
        cashback_recycler.setAdapter(adapter);


        withdrawbtn.setOnClickListener(v -> startActivity(new Intent(CashBackActivity.this, UserBankDetailsActivity.class)));


    }
}
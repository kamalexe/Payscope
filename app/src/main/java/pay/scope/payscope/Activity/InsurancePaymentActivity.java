package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.InsurancePaymentAdapter;
import pay.scope.payscope.Model.InsurancePaymentModel;
import pay.scope.payscope.R;

public class InsurancePaymentActivity extends AppCompatActivity {
    RecyclerView insurancePayment_recycler;
    int spanCounts = 3;

    MaterialToolbar InsurancePayment_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_payment);

        insurancePayment_recycler = findViewById(R.id.insurancePayment_recycler);

        InsurancePayment_toolbar = findViewById(R.id.InsurancePayment_toolbar);
        InsurancePayment_toolbar.setNavigationOnClickListener(v -> { /* startActivity(new Intent(InsurancePaymentActivity.this, MainActivity.class));*/
            finish();
        });


        List<InsurancePaymentModel> insurancePaymentModelList = new ArrayList<>();
        insurancePaymentModelList.add(new InsurancePaymentModel("Bike Insurance", R.drawable.baseline_auto_awesome_24));
        insurancePaymentModelList.add(new InsurancePaymentModel("Car Insurance", R.drawable.car_insurence));
        insurancePaymentModelList.add(new InsurancePaymentModel("Health Insurance", R.drawable.baseline_contactless_24));
        insurancePaymentModelList.add(new InsurancePaymentModel("Personal Accident", R.drawable.baseline_food_bank_24));
        insurancePaymentModelList.add(new InsurancePaymentModel("Payment Protect", R.drawable.baseline_auto_awesome_24));
        insurancePaymentModelList.add(new InsurancePaymentModel("Critical illness", R.drawable.baseline_food_bank_24));

        InsurancePaymentAdapter paymentAdapter = new InsurancePaymentAdapter(InsurancePaymentActivity.this, insurancePaymentModelList);
        insurancePayment_recycler.setAdapter(paymentAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(InsurancePaymentActivity.this, spanCounts);
        layoutManager.setSpanCount(spanCounts);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        insurancePayment_recycler.setLayoutManager(layoutManager);

    }
}
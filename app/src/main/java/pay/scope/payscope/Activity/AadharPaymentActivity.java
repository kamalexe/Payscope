package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.AadharPaymentAdapter;
import pay.scope.payscope.Model.AadharPaymentModel;
import pay.scope.payscope.R;

public class AadharPaymentActivity extends AppCompatActivity {
    MaterialToolbar AadharPayment_toolbar;
    RecyclerView AadharPayment_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar_payment);

        AadharPayment_recycler = findViewById(R.id.AadharPayment_recycler);
        AadharPayment_toolbar = findViewById(R.id.AadharPayment_toolbar);

        AadharPayment_toolbar.setNavigationOnClickListener(v -> {
            /*startActivity(new Intent(AadharPaymentActivity.this, AadharFormActivity.class));*/
            finish(); });


        List<AadharPaymentModel> aadharPaymentModelList = new ArrayList<>();
        aadharPaymentModelList.add(new AadharPaymentModel("BIL", "0930992", "24 Oct 2024"));
        aadharPaymentModelList.add(new AadharPaymentModel("BIL", "0930992", "24 Oct 2024"));
        aadharPaymentModelList.add(new AadharPaymentModel("BIL", "0930992", "24 Oct 2024"));
        aadharPaymentModelList.add(new AadharPaymentModel("BIL", "0930992", "24 Oct 2024"));
        aadharPaymentModelList.add(new AadharPaymentModel("BIL", "0930992", "24 Oct 2024"));
        aadharPaymentModelList.add(new AadharPaymentModel("BIL", "0930992", "24 Oct 2024"));
        aadharPaymentModelList.add(new AadharPaymentModel("BIL", "0930992", "24 Oct 2024"));

        AadharPaymentAdapter paymentAdapter = new AadharPaymentAdapter(AadharPaymentActivity.this, aadharPaymentModelList);
        AadharPayment_recycler.setAdapter(paymentAdapter);
        AadharPayment_recycler.setLayoutManager(new LinearLayoutManager(AadharPaymentActivity.this, LinearLayoutManager.VERTICAL, false));

    }
}
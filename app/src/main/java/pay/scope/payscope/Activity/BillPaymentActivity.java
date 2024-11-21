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

import pay.scope.payscope.Adapter.BillPaymentAdapter;
import pay.scope.payscope.Model.BillPaymentModel;
import pay.scope.payscope.R;

public class BillPaymentActivity extends AppCompatActivity {

    Button payBill_Btn;
    RecyclerView bill_Payment_recycler;
    MaterialToolbar BillPayment_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_payment);

        bill_Payment_recycler = findViewById(R.id.Bill_Payment_recycler);
        payBill_Btn = findViewById(R.id.payBill_Btn);
        BillPayment_toolbar = findViewById(R.id.BillPayment_toolbar);

        BillPayment_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(BillPaymentActivity.this, QuickViewAllActivity.class));
            finish();
        });

        List<BillPaymentModel> billPaymentModelList = new ArrayList<>();
        billPaymentModelList.add(new BillPaymentModel("DD Light Bills", R.drawable.baseline_account_balance_24, 120.00, "Auto to pay on 22 Feb,2020", "E2048344", true));
        billPaymentModelList.add(new BillPaymentModel("DD Light Bills", R.drawable.baseline_assignment_ind_24, 120.00, "Auto to pay on 22 Feb,2020", "E2048344", true));
        billPaymentModelList.add(new BillPaymentModel("DD Light Bills", R.drawable.baseline_account_balance_24, 120.00, "Auto to pay on 22 Feb,2020", "E2048344", true));
        billPaymentModelList.add(new BillPaymentModel("DD Light Bills", R.drawable.baseline_assignment_ind_24, 120.00, "Auto to pay on 22 Feb,2020", "E2048344", true));
        billPaymentModelList.add(new BillPaymentModel("DD Light Bills", R.drawable.baseline_assignment_ind_24, 120.00, "Auto to pay on 22 Feb,2020", "E2048344", true));
        billPaymentModelList.add(new BillPaymentModel("DD Light Bills", R.drawable.baseline_assignment_ind_24, 120.00, "Auto to pay on 22 Feb,2020", "E2048344", true));

        BillPaymentAdapter paymentAdapter = new BillPaymentAdapter(BillPaymentActivity.this, billPaymentModelList);
        bill_Payment_recycler.setAdapter(paymentAdapter);

        bill_Payment_recycler.setLayoutManager(new LinearLayoutManager(BillPaymentActivity.this, LinearLayoutManager.VERTICAL, false));

        payBill_Btn.setOnClickListener(v -> {
            Intent intent = new Intent(BillPaymentActivity.this, BillSuccessfullActivity.class);
            startActivity(intent);
        });


    }
}
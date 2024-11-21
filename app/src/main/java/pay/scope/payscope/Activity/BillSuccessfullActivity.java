package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.BillSuccessAdapter;
import pay.scope.payscope.Model.BillSuccessModel;
import pay.scope.payscope.R;

public class BillSuccessfullActivity extends AppCompatActivity {
    RecyclerView Bill_Success_recycler;
    MaterialToolbar BillSuccessfull_toolbar;
    MaterialCardView BillSuccess_Share, BillSuccess_Printer;
    Button Bill_Success_Done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_successfull);

        Bill_Success_recycler = findViewById(R.id.Bill_Success_recycler);
        BillSuccess_Share = findViewById(R.id.BillSuccess_Share);
        BillSuccess_Printer = findViewById(R.id.BillSuccess_Printer);
        Bill_Success_Done = findViewById(R.id.Bill_Success_Done);
        BillSuccessfull_toolbar = findViewById(R.id.BillSuccessfull_toolbar);

        BillSuccessfull_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(BillSuccessfullActivity.this, BillPaymentActivity.class));
            finish();
        });

        List<BillSuccessModel> billSuccessModelList = new ArrayList<>();
        billSuccessModelList.add(new BillSuccessModel("DD Light Bills", R.drawable.baseline_check_24, 123.00, "E2048333"));
        billSuccessModelList.add(new BillSuccessModel("DD Light Bills", R.drawable.baseline_check_24, 123.00, "E2048333"));
        billSuccessModelList.add(new BillSuccessModel("DD Light Bills", R.drawable.baseline_check_24, 123.00, "E2048333"));
        billSuccessModelList.add(new BillSuccessModel("DD Light Bills", R.drawable.baseline_check_24, 123.00, "E2048333"));

        BillSuccessAdapter successAdapter = new BillSuccessAdapter(BillSuccessfullActivity.this, billSuccessModelList);
        Bill_Success_recycler.setAdapter(successAdapter);
        Bill_Success_recycler.setLayoutManager(new LinearLayoutManager(BillSuccessfullActivity.this, LinearLayoutManager.VERTICAL, false));


        BillSuccess_Share.setOnClickListener(v -> {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Yahan aap apna text daalein");
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        BillSuccess_Printer.setOnClickListener(v -> startActivity(new Intent(BillSuccessfullActivity.this, InvoiceActivity.class)));

        Bill_Success_Done.setOnClickListener(v -> {
            startActivity(new Intent(BillSuccessfullActivity.this, MainActivity.class));
            finish();
        });

    }
}
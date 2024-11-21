package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.FasTagPaymentAdapter;
import pay.scope.payscope.Model.FasTagPaymentModel;
import pay.scope.payscope.R;

public class FasTagPaymentActivity extends AppCompatActivity {
    RecyclerView FasTag_recycler;
    MaterialToolbar FasTagPayment_toolbar;
    MaterialCardView FasTagLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fas_tag_payment);

        FasTag_recycler = findViewById(R.id.FasTag_recycler);
        FasTagPayment_toolbar = findViewById(R.id.FasTagPayment_toolbar);
        FasTagLink = findViewById(R.id.FasTagLink);

        FasTagPayment_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(FasTagPaymentActivity.this, MainActivity.class));
            finish();
        });

        List<FasTagPaymentModel> fasTagPaymentModelList = new ArrayList<>();
        fasTagPaymentModelList.add(new FasTagPaymentModel("12345", "Payscope", "MH 1234SFG", 123.00, R.drawable.mqdefault));
        fasTagPaymentModelList.add(new FasTagPaymentModel("12345", "Payscope", "MH 1234SFG", 123.00, R.drawable.mqdefault));
        fasTagPaymentModelList.add(new FasTagPaymentModel("12345", "Payscope", "MH 1234SFG", 123.00, R.drawable.mqdefault));
        fasTagPaymentModelList.add(new FasTagPaymentModel("12345", "Payscope", "MH 1234SFG", 123.00, R.drawable.mqdefault));
        fasTagPaymentModelList.add(new FasTagPaymentModel("12345", "Payscope", "MH 1234SFG", 123.00, R.drawable.mqdefault));
        fasTagPaymentModelList.add(new FasTagPaymentModel("12345", "Payscope", "MH 1234SFG", 123.00, R.drawable.mqdefault));
        fasTagPaymentModelList.add(new FasTagPaymentModel("12345", "Payscope", "MH 1234SFG", 123.00, R.drawable.mqdefault));


        FasTagPaymentAdapter fasTagPaymentAdapter = new FasTagPaymentAdapter(FasTagPaymentActivity.this, fasTagPaymentModelList);
        FasTag_recycler.setAdapter(fasTagPaymentAdapter);

        FasTag_recycler.setLayoutManager(new LinearLayoutManager(FasTagPaymentActivity.this, LinearLayoutManager.VERTICAL, false));

        FasTagLink.setOnClickListener(v -> startActivity(new Intent(FasTagPaymentActivity.this, FastTransactionsActivity.class)));
    }
}
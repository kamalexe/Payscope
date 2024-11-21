package pay.scope.payscope.Activity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class ICICITransactionActivity extends AppCompatActivity {
    MaterialToolbar TransactionReceipt_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icicitransaction);

        TransactionReceipt_toolbar = findViewById(R.id.TransactionReceipt_toolbar);

        TransactionReceipt_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(ICICITransactionActivity.this, ICICIAEPSActivity.class));
            finish();
        });


    }
}
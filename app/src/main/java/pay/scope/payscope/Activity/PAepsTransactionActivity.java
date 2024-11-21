package pay.scope.payscope.Activity;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class PAepsTransactionActivity extends AppCompatActivity {
    MaterialToolbar PAepsTransactionReceipt_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paeps_transaction);

        PAepsTransactionReceipt_toolbar = findViewById(R.id.PAepsTransactionReceipt_toolbar);

        PAepsTransactionReceipt_toolbar.setNavigationOnClickListener(v -> { /*startActivity(new Intent(PAepsTransactionActivity.this, PAepsActivity.class));*/
            finish();
        });
    }
}

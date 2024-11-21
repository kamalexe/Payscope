package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class CashBackDetailsActivity extends AppCompatActivity {
    MaterialToolbar CashBackDetails_toolbar;
    Button CashBackDetailsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_back_details);

        CashBackDetailsBtn = findViewById(R.id.CashBackDetailsBtn);
        CashBackDetails_toolbar = findViewById(R.id.CashBackDetails_toolbar);

        CashBackDetails_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(CashBackDetailsActivity.this,CashBackActivity.class));
            finish();
        });

        CashBackDetailsBtn.setOnClickListener(v -> startActivity(new Intent(CashBackDetailsActivity.this,MainActivity.class)));

    }
}
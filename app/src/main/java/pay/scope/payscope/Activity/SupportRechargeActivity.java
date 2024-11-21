package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class SupportRechargeActivity extends AppCompatActivity {
    MaterialToolbar SupportRec_toolbar;
    CardView querySupportRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_recharge);

        SupportRec_toolbar = findViewById(R.id.SupportRec_toolbar);
        querySupportRec = findViewById(R.id.querySupportRec);


        SupportRec_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(SupportRechargeActivity.this, HelpActivity.class));
            finish();
        });

        querySupportRec.setOnClickListener(v -> {
//                startActivity(new Intent(SupportRechargeActivity.this, SupportActivity.class));
                finish();
        });

    }
}
package pay.scope.payscope.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class DTHRechargeDetailsActivity extends AppCompatActivity {
    MaterialToolbar Details_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dthrecharge_details);

        Details_toolbar = findViewById(R.id.DTHRechargeDetails_toolbar);
        Details_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(DTHRechargeDetailsActivity.this, DTHRechargeActivity.class));
            finish();
        });



    }
}
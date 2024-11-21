package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class RedamptionDetailsActivity extends AppCompatActivity {
    MaterialToolbar Redamption_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redamption_details);

        Redamption_toolbar = findViewById(R.id.Redamption_toolbar);

        Redamption_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(RedamptionDetailsActivity.this, CouponsActivity.class));
            finish();
        });


    }
}
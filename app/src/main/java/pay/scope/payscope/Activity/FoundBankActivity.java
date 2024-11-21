package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class FoundBankActivity extends AppCompatActivity {
    MaterialToolbar FoundBank_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_bank);
        FoundBank_toolbar = findViewById(R.id.FoundBank_toolbar);

        FoundBank_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(FoundBankActivity.this,));
            finish();
        });

    }
}
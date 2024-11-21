package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class SupportActivity extends AppCompatActivity {
MaterialToolbar Support_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        Support_toolbar = findViewById(R.id.Support_toolbar);


        Support_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(SupportActivity.this, HelpActivity.class));
            finish();
        });
    }
}
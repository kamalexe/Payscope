package pay.scope.payscope.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class LegalPrivacyActivity extends AppCompatActivity {
    MaterialToolbar LegalPrivacy_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_privacy);

        LegalPrivacy_toolbar = findViewById(R.id.LegalPrivacy_toolbar);

        LegalPrivacy_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(LegalPrivacyActivity.this, MainActivity.class));
            finish();
        });

    }
}
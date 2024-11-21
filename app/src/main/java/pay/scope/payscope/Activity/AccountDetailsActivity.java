package pay.scope.payscope.Activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class AccountDetailsActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    TextView ResetChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        toolbar = findViewById(R.id.AccountDetails_toolbar);
        ResetChange = findViewById(R.id.ResetChange);

        toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(AccountDetailsActivity.this, AccountActivity.class));
            finish();
        });

        ResetChange.setOnClickListener(v -> startActivity(new Intent(AccountDetailsActivity.this, PINChangeActivity.class)));


        Drawable overflowIcon = toolbar.getOverflowIcon();
        if (overflowIcon != null) {
            overflowIcon.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
            toolbar.setOverflowIcon(overflowIcon);
        }
    }
}
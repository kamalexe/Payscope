package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class PasswordChangeActivity extends AppCompatActivity {
    Button ChangePassword_submit;
    MaterialToolbar ChangePassword_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        ChangePassword_toolbar = findViewById(R.id.ChangePassword_toolbar);
        ChangePassword_submit = findViewById(R.id.ChangePassword_submit);

        ChangePassword_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(PasswordChangeActivity.this, ProfileUpdateActivity.class));
            finish();
        });

        ChangePassword_submit.setOnClickListener(v -> startActivity(new Intent(PasswordChangeActivity.this, ProfileUpdateActivity.class)));

    }
}

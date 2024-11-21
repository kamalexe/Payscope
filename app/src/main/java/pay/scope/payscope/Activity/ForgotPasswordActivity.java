package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    Button forgotPassword_submit;
    MaterialToolbar ForgotPassword_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotPassword_submit = findViewById(R.id.forgotPassword_submit);
        ForgotPassword_toolbar = findViewById(R.id.ForgotPassword_toolbar);

        ForgotPassword_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity3.class));
            finish();
        });

        forgotPassword_submit.setOnClickListener(v -> startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity3.class)));

    }
}
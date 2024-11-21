package pay.scope.payscope.Activity;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.concurrent.Executor;

import pay.scope.payscope.R;

public class SelfTopUpActivity extends AppCompatActivity {
    MaterialToolbar SelfTopUp_toolbar;
    ImageView SelfTopBiometric;
    Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_top_up);

        SelfTopBiometric = findViewById(R.id.SelfTopBiometric);
        SelfTopUp_toolbar = findViewById(R.id.SelfTopUp_toolbar);

        SelfTopUp_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(SelfTopUpActivity.this, MainActivity.class));
            finish();

        });


        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(SelfTopUpActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Fingerprint Login")
                .setSubtitle("Scan your fingerprint to login")
                .setNegativeButtonText("Cancel")
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
                .build();

        SelfTopBiometric.setOnClickListener(v -> biometricPrompt.authenticate(promptInfo));


    }
}
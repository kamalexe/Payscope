package pay.scope.payscope.Activity;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.Spinner;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.cardview.widget.CardView;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Arrays;
import java.util.concurrent.Executor;

import pay.scope.payscope.Helper.SpinnerUtils;
import pay.scope.payscope.R;

public class AadharFormActivity extends AppCompatActivity {
    MaterialToolbar AadharForm_toolbar;
    Button aadharGoTo_btn;
    CardView biometric;
    Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    Spinner AadharForm_Spinner;
    private final String[] AadharForm_string = {"Select", "SBI", "Union", "Visa"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar_form);

        AadharForm_toolbar = findViewById(R.id.AadharForm_toolbar);
        aadharGoTo_btn = findViewById(R.id.aadharGoTo_btn);
        biometric = findViewById(R.id.biometric);
        AadharForm_Spinner = findViewById(R.id.AadharForm_Spinner);

        AadharForm_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(AadharFormActivity.this, QuickViewAllActivity.class));
            finish();
        });

        aadharGoTo_btn.setOnClickListener(v -> showAlertDialogTransferSuccessfull());

        executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(AadharFormActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(AadharFormActivity.this, AadharPaymentActivity.class));
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

        biometric.setOnClickListener(v -> biometricPrompt.authenticate(promptInfo));


        SpinnerUtils.setUpSpinner(this, AadharForm_Spinner, Arrays.asList(AadharForm_string));

    }


    private void showAlertDialogTransferSuccessfull() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View payment_successful = inflater.inflate(R.layout.transfersuccessful_layout, null);
        builder.setView(payment_successful);
        builder.setCancelable(false);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button Continue_btn = payment_successful.findViewById(R.id.conitnuebtn);


        final boolean condition = true;

        Continue_btn.setOnClickListener(v -> {
            if (condition) {
                // Perform one action if condition is true
                startActivity(new Intent(AadharFormActivity.this, AadharPaymentActivity.class));
                alertDialog.dismiss();

            } else {
                // Perform another action if condition is false
                alertDialog.dismiss();

            }
        });


    }

}
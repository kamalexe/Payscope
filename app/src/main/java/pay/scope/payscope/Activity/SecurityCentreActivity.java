package pay.scope.payscope.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import pay.scope.payscope.R;

public class SecurityCentreActivity extends AppCompatActivity {
    MaterialToolbar SecurityCentre_toolbar;
    LinearLayout SecurityCentre_Apple, SecurityCentre_Google, SecurityCentre_Facebook;
    TextView SecurityCentre_ForgotPassword;
    private final String facebookProfileUrl = "https://www.facebook.com/in/example";
    private final String appleProfileUrl = "https://www.apple.com/in/store?afid=p238%7Cs8Vs8GkTq-dc_mtid_187079nc38483_pcrid_694334137323_pgrid_112258962467_pntwk_g_pchan__pexid__ptid_kwd-10778630_&cid=aos-IN-kwgo-brand--slid---product-";
    private final String googleProfileUrl = "https://www.google.co.in/";

    TextInputEditText passwordEditText;
    TextInputLayout passwordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_centre);

        passwordEditText = findViewById(R.id.passwordEditText);
        passwordLayout = findViewById(R.id.passwordLayout);

        SecurityCentre_Apple = findViewById(R.id.SecurityCentre_Apple);
        SecurityCentre_Google = findViewById(R.id.SecurityCentre_Google);
        SecurityCentre_Facebook = findViewById(R.id.SecurityCentre_Facebook);
        SecurityCentre_ForgotPassword = findViewById(R.id.SecurityCentre_ForgotPassword);
        SecurityCentre_toolbar = findViewById(R.id.SecurityCentre_toolbar);

        SecurityCentre_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(SecurityCentreActivity.this, MainActivity.class));
                finish();
            }
        });

        SecurityCentre_Apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(appleProfileUrl));
                startActivity(intent);
            }
        });

        SecurityCentre_Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleProfileUrl));
                startActivity(intent);
            }
        });

        SecurityCentre_Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUrl));
                startActivity(intent);
            }
        });

        SecurityCentre_ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecurityCentreActivity.this, ForgotPasswordActivity.class));
            }
        });


        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do something if needed when text changes
            }
        });

        passwordLayout.setEndIconOnClickListener(v -> {
            int inputType = passwordEditText.getInputType();
            if (inputType == (InputType.TYPE_CLASS_TEXT | InputType. TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                // Toggle to hide password
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType. TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                // Toggle to show password
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType. TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
            // Move the cursor to the end of the text
            passwordEditText.setSelection(Objects.requireNonNull(passwordEditText.getText()).length());
        });


    }
}

//    TYPE_TEXT_VARIATION_PASSWORD
//            TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
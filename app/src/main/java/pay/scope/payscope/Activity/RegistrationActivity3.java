package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pay.scope.payscope.R;

public class RegistrationActivity3 extends AppCompatActivity {
    Button registration;
    TextView signIn;
    ImageView regPasswordHide, regConPasswordHide;
    EditText regUsername, regNumber, regPassword, regConPassword;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration3);

        registration = findViewById(R.id.registration);
        signIn = findViewById(R.id.signIn);
        regUsername = findViewById(R.id.reg_username);
        regNumber = findViewById(R.id.reg_number);
        regPassword = findViewById(R.id.reg_password);
        regConPassword = findViewById(R.id.reg_com_password);

        regPasswordHide = findViewById(R.id.reg_password_hide);
        regConPasswordHide = findViewById(R.id.reg_com_password_hide);

        registration.setOnClickListener(v -> startActivity(new Intent(RegistrationActivity3.this, LoginActivity3.class)));

        signIn.setOnClickListener(v -> startActivity(new Intent(RegistrationActivity3.this, LoginActivity3.class)));

        regPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        regConPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());


        regPasswordHide.setOnClickListener(v -> {
            if (isPasswordVisible) {
                regPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                regPasswordHide.setImageResource(R.drawable.baseline_visibility_off_24);
            } else {
                regPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                regPasswordHide.setImageResource(R.drawable.baseline_remove_red_eye_24);
            }
            isPasswordVisible = !isPasswordVisible;
            regPassword.setSelection(regPassword.getText().length());
        });

        regConPasswordHide.setOnClickListener(v -> {
            if (isPasswordVisible) {
                regConPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                regConPasswordHide.setImageResource(R.drawable.baseline_visibility_off_24);
            } else {
                regConPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                regConPasswordHide.setImageResource(R.drawable.baseline_remove_red_eye_24);
            }
            isPasswordVisible = !isPasswordVisible;
            regConPassword.setSelection(regConPassword.getText().length());
        });

    }
}


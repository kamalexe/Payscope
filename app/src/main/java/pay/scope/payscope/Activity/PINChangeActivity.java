package pay.scope.payscope.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class PINChangeActivity extends AppCompatActivity {
    MaterialToolbar PINChange_toolbar;
    EditText PINChangeEditText1, PINChangeEditText2, PINChangeEditText3, PINChangeEditText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinchange);

        PINChange_toolbar = findViewById(R.id.PINChange_toolbar);

        PINChangeEditText1 = findViewById(R.id.PINChangeEditText1);
        PINChangeEditText2 = findViewById(R.id.PINChangeEditText2);
        PINChangeEditText3 = findViewById(R.id.PINChangeEditText3);
        PINChangeEditText4 = findViewById(R.id.PINChangeEditText4);

        PINChange_toolbar.setNavigationOnClickListener(v -> finish());

        PINChangeEditText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    PINChangeEditText2.requestFocus();
                }
            }
        });

        PINChangeEditText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    PINChangeEditText3.requestFocus();
                }
            }
        });

        PINChangeEditText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    PINChangeEditText4.requestFocus();
                }
            }
        });


    }
}
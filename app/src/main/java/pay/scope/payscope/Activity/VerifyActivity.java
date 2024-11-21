package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import pay.scope.payscope.R;

public class VerifyActivity extends AppCompatActivity {
    Button btnokey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        btnokey = findViewById(R.id.btnokey);

        btnokey.setOnClickListener(v -> {
            startActivity(new Intent(VerifyActivity.this, MainActivity.class));
            finish();
        });


    }
}
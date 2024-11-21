package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class SendMoneyActivity extends AppCompatActivity {
    MaterialToolbar Send_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        Send_toolbar = findViewById(R.id.Send_toolbar);

        Send_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(SendMoneyActivity.this, MainActivity.class));
            finish();
        });

    }
}
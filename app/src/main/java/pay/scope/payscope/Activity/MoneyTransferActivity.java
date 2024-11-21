package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class MoneyTransferActivity extends AppCompatActivity {
    Button moneyTransferBtn;
    MaterialToolbar moneyTransferToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer);

        moneyTransferBtn = findViewById(R.id.money_transfer_btn);
        moneyTransferToolbar = findViewById(R.id.money_transfer_toolbar);

        moneyTransferToolbar.setNavigationOnClickListener(v -> {   /* startActivity(new Intent(MoneyTransferActivity.this, MainActivity.class)); */
            finish();
        });


        moneyTransferBtn.setOnClickListener(v -> startActivity(new Intent(MoneyTransferActivity.this, UserBankDetailsActivity.class)));


    }
}
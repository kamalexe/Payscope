package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class UserBankDetailsActivity extends AppCompatActivity {
    MaterialToolbar UserBankDetails_toolbar;
    Button UserBankDetails_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bank_details);

        UserBankDetails_toolbar = findViewById(R.id.UserBankDetails_toolbar);
        UserBankDetails_Btn = findViewById(R.id.UserBankDetails_Btn);

        UserBankDetails_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(UserBankDetailsActivity.this, MoneyTransferActivity.class));
            finish();
        });


        UserBankDetails_Btn.setOnClickListener(v -> startActivity(new Intent(UserBankDetailsActivity.this, UPIPINActivity.class)));


    }


}

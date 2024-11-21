package pay.scope.payscope.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class InviteEarnActivity extends AppCompatActivity {
    MaterialToolbar InviteEarn_toolbar;
    TextView InviteEarn_ReferralCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_earn);

        InviteEarn_ReferralCode = findViewById(R.id.InviteEarn_ReferralCode);
        InviteEarn_toolbar = findViewById(R.id.InviteEarn_toolbar);

        InviteEarn_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(InviteEarnActivity.this, MainActivity.class));
            finish();
        });


        InviteEarn_ReferralCode.setOnClickListener(v -> {
            String textToCopy = InviteEarn_ReferralCode.getText().toString();

            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

            ClipData clipData = ClipData.newPlainText("text", textToCopy);
            clipboardManager.setPrimaryClip(clipData);

            Toast.makeText(getApplicationContext(), "Copy", Toast.LENGTH_SHORT).show();
        });
    }
}
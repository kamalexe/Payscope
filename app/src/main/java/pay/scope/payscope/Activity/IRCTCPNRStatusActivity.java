package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class IRCTCPNRStatusActivity extends AppCompatActivity {
    MaterialToolbar PNRStatus_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irctcpnrstatus);

        PNRStatus_toolbar = findViewById(R.id.PNRStatus_toolbar);

        PNRStatus_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(IRCTCPNRStatusActivity.this, IRCTCActivity.class));
                finish();
            }
        });

    }
}
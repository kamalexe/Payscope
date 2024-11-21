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

public class IRCTCStatusActivity extends AppCompatActivity {
    MaterialToolbar IRCTCStatus_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irctcstatus);

        IRCTCStatus_toolbar = findViewById(R.id.IRCTCStatus_toolbar);

        IRCTCStatus_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(IRCTCStatusActivity.this, IRCTCActivity.class));
                finish();
            }
        });


    }
}
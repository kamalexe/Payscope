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

public class IRCTCBookingActivity extends AppCompatActivity {
    MaterialToolbar IRCTCBooking_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irctcbooking);

        IRCTCBooking_toolbar = findViewById(R.id.IRCTCBooking_toolbar);

        IRCTCBooking_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(IRCTCBookingActivity.this, IRCTCActivity.class));
                finish();
            }
        });

    }
}
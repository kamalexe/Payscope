package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class PenCardServiceActivity extends AppCompatActivity {
    CardView e_KYC;
    MaterialToolbar PenCardService_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pen_card_service);

        e_KYC = findViewById(R.id.e_KYC);
        PenCardService_toolbar = findViewById(R.id.PenCardService_toolbar);

        PenCardService_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(PenCardServiceActivity.this, MainActivity.class));
                finish();
            }
        });

        e_KYC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PenCardServiceActivity.this, PanCardDocumentActivity.class));
            }
        });

    }
}
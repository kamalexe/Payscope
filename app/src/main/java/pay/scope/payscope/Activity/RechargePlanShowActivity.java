package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class RechargePlanShowActivity extends AppCompatActivity {
    MaterialToolbar PlanShow_toolbar;
    TextView PlanShowChangePlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_plan_show);

        PlanShow_toolbar = findViewById(R.id.PlanShow_toolbar);
        PlanShowChangePlan = findViewById(R.id.PlanShowChangePlan);

        PlanShow_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RechargePlanShowActivity.this, ChoosePlanActivity.class));

                finish();
            }
        });

        PlanShowChangePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RechargePlanShowActivity.this, ChoosePlanActivity.class));
            }
        });
    }
}
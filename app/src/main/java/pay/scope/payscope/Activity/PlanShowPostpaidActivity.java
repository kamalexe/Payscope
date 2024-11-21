package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class PlanShowPostpaidActivity extends AppCompatActivity {
    MaterialToolbar PlanShowPostPaid_toolbar;
    Button PlanShowPostPaidBtn;
    TextView  PlanShowPostPaidChangePlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_show_postpaid);

        PlanShowPostPaid_toolbar = findViewById(R.id.PlanShowPostPaid_toolbar);
        PlanShowPostPaidBtn = findViewById(R.id.PlanShowPostPaidBtn);
        PlanShowPostPaidChangePlan = findViewById(R.id.PlanShowPostPaidChangePlan);

        PlanShowPostPaid_toolbar.setNavigationOnClickListener(v -> finish());

        PlanShowPostPaidChangePlan.setOnClickListener(v -> startActivity(new Intent(PlanShowPostpaidActivity.this, ChoosePlanPostpaidActivity.class)));

    }
}
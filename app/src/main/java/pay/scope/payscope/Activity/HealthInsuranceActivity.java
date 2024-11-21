package pay.scope.payscope.Activity;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Arrays;

import pay.scope.payscope.Helper.SpinnerUtils;
import pay.scope.payscope.R;


public class HealthInsuranceActivity extends AppCompatActivity {
    MaterialToolbar HealthInsurance_toolbar;
    Spinner Health_spinner;
    String[] courses = {"Select", "Self", "Spouse", "Daughter", "Son", "Mother", "Father"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_insurance);

        Health_spinner = findViewById(R.id.Health_spinner);
        HealthInsurance_toolbar = findViewById(R.id.HealthInsurance_toolbar);

        HealthInsurance_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(HealthInsuranceActivity.this, InsurancePaymentActivity.class));
            finish();
        });

        SpinnerUtils.setUpSpinner(this, Health_spinner, Arrays.asList(courses));


    }
}



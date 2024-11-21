package pay.scope.payscope.Activity;


import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import pay.scope.payscope.R;

public class CriticalillnessInsuranceActivity extends AppCompatActivity {
    TextInputEditText critical_dob;
    MaterialToolbar CriticalInsurance_toolbar;
    TextView add_Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criticalillness_insurance);

        critical_dob = findViewById(R.id.critical_dob);
        add_Address = findViewById(R.id.add_Address);
        CriticalInsurance_toolbar = findViewById(R.id.CriticalInsurance_toolbar);

        CriticalInsurance_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(CriticalillnessInsuranceActivity.this, InsurancePaymentActivity.class));
            finish();
        });

        add_Address.setOnClickListener(v -> startActivity(new Intent(CriticalillnessInsuranceActivity.this, AddAddressActivity.class)));


    }


}
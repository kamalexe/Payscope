package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import pay.scope.payscope.R;

public class AddAddressActivity extends AppCompatActivity {
    CardView add_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        add_clear = findViewById(R.id.add_clear);

        add_clear.setOnClickListener(v -> {
//                startActivity(new Intent(AddAddressActivity.this, CriticalillnessInsuranceActivity.class));
            finish();
        });

    }
}
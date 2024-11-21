package pay.scope.payscope.Activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Arrays;

import pay.scope.payscope.Helper.SpinnerUtils;
import pay.scope.payscope.R;

public class ICICIEKycVerificationActivity extends AppCompatActivity {

    Spinner ICICI_spinner;
    LinearLayout ICICI_scanner;
    private final String[] icici_string = {"Select", "Morpho", "Mantra", "Mantra L1", "Aratek", "Evolute"};
    MaterialToolbar ICICIVerification_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iciciekyc_verification);

        ICICI_spinner = findViewById(R.id.ICICI_spinner);
        ICICI_scanner = findViewById(R.id.ICICI_scanner);
        ICICIVerification_toolbar = findViewById(R.id.ICICIVerification_toolbar);

        ICICIVerification_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(ICICIEKycVerificationActivity.this, QuickViewAllActivity.class));
            finish();
        });

        SpinnerUtils.setUpSpinner(this, ICICI_spinner, Arrays.asList(icici_string));

//        ICICI_scanner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ICICIEKycVerificationActivity.this, ICICIAEPSActivity.class));
//            }
//        });


    }
}
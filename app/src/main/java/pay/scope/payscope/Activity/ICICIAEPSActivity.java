package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Arrays;

import pay.scope.payscope.Helper.SpinnerUtils;
import pay.scope.payscope.R;

public class ICICIAEPSActivity extends AppCompatActivity {
    Spinner transaction_Spinner, device_Spinner, paymentBank_spinner;
    Button ICICI_SuccessfulBtn;
    private final String[] transaction_string = {"Select", "transaction_string", "transaction_string", "transaction_string L1", "Aratek", "Evolute"};
    private final String[] device_string = {"Select", "Morpho", "Mantra", "Aratek", "Evolute"};
    private final String[] paymentBank_string = {"Select", "Airtel Payment Bank", "Airtel Payment Bank", "Airtel Payment Bank", "Airtel Payment Bank", "Airtel Payment Bank"};
    MaterialToolbar ICICIAEPS_toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iciciaeps);

        transaction_Spinner = findViewById(R.id.ICICI_transaction_spinner);
        device_Spinner = findViewById(R.id.ICICI_device_spinner);
        paymentBank_spinner = findViewById(R.id.paymentBank_spinner);
        ICICI_SuccessfulBtn = findViewById(R.id.ICICI_SuccessfulBtn);
        ICICIAEPS_toolbar = findViewById(R.id.ICICIAEPS_toolbar);


        ICICIAEPS_toolbar.setNavigationOnClickListener(v -> finish());

        SpinnerUtils.setUpSpinner(this, transaction_Spinner, Arrays.asList(transaction_string));
        SpinnerUtils.setUpSpinner(this, device_Spinner, Arrays.asList(device_string));
        SpinnerUtils.setUpSpinner(this, paymentBank_spinner, Arrays.asList(paymentBank_string));

        ICICI_SuccessfulBtn.setOnClickListener(v -> startActivity(new Intent(ICICIAEPSActivity.this, ICICITransactionActivity.class)));

    }
}
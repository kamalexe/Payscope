package pay.scope.payscope.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

import pay.scope.payscope.R;

public class ScannerDetailsActivity extends AppCompatActivity {
    private TextView tvMerchantName,tvUpiId;
    MaterialToolbar ScannerDetails_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_details);

        tvMerchantName = findViewById(R.id.tv_merchant_name);
        tvUpiId = findViewById(R.id.tv_upi_id);

        ScannerDetails_toolbar = findViewById(R.id.ScannerDetails_toolbar);

        ScannerDetails_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String scannedData = getIntent().getStringExtra("SCANNED_DATA");
        if (scannedData != null) {
            HashMap<String, String> parsedData = parseUpiData(scannedData);
            displayParsedData(parsedData);
        }

    }


    private HashMap<String, String> parseUpiData(String data) {
        HashMap<String, String> parsedData = new HashMap<>();
        String[] params = data.split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                parsedData.put(keyValue[0], keyValue[1]);
            }
        }
        return parsedData;
    }

    private void displayParsedData(HashMap<String, String> parsedData) {
        tvMerchantName.setText(sanitizeString(Objects.requireNonNull(parsedData.getOrDefault("pn", "N/A"))));
        tvUpiId.setText(parsedData.getOrDefault("upi://pay?pa", "N/A"));
//        tvAmount.setText(parsedData.getOrDefault("aid", "N/A"));
//        tvTransactionNote.setText(parsedData.getOrDefault("cust", "N/A"));
    }


    private String sanitizeString(String input) {
        // Remove special characters and numbers
        return input.replaceAll("[^a-zA-Z\\s]", " ").trim();
    }


}


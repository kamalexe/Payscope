package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class InvestmentDetailsActivity extends AppCompatActivity {
    MaterialToolbar InvestmentDetails_toolbar;
    Button investmentDetailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_details);

        InvestmentDetails_toolbar = findViewById(R.id.InvestmentDetails_toolbar);
        investmentDetailsButton = findViewById(R.id.investmentDetailsButton);

        InvestmentDetails_toolbar.setNavigationOnClickListener(v -> finish());

        investmentDetailsButton.setOnClickListener(v -> startActivity(new Intent(InvestmentDetailsActivity.this, InvestmentMoreDetailsActivity.class)));


    }
}
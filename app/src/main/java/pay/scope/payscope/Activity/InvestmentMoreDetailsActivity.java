package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class InvestmentMoreDetailsActivity extends AppCompatActivity {
    MaterialToolbar InvestmentMoreDetails_toolbar;
    TextView InvestmentMoreButton;
    CardView InvestmentDetails_Deny, InvestmentDetails_Allow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_more_details);

        InvestmentMoreDetails_toolbar = findViewById(R.id.InvestmentMoreDetails_toolbar);
        InvestmentMoreButton = findViewById(R.id.InvestmentMoreButton);

        InvestmentMoreDetails_toolbar.setNavigationOnClickListener(v -> finish());

        InvestmentMoreButton.setOnClickListener(v -> showAlertDialog());

    }


    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(InvestmentMoreDetailsActivity.this);
        LayoutInflater inflater = InvestmentMoreDetailsActivity.this.getLayoutInflater();
        View logoutView = inflater.inflate(R.layout.usercontect_dialog, null);
        builder.setView(logoutView);
        builder.setCancelable(false);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();


        InvestmentDetails_Allow = logoutView.findViewById(R.id.InvestmentDetails_Allow);
        InvestmentDetails_Deny = logoutView.findViewById(R.id.InvestmentDetails_Deny);

        InvestmentDetails_Deny.setOnClickListener(v -> alertDialog.dismiss());

        InvestmentDetails_Allow.setOnClickListener(v -> {
            startActivity(new Intent(InvestmentMoreDetailsActivity.this, InvestmentDetailsActivity.class));
            finish();
        });
    }
}
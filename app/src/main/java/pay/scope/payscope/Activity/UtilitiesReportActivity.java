package pay.scope.payscope.Activity;

import android.content.res.ColorStateList;
import android.os.Bundle;

import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class UtilitiesReportActivity extends AppCompatActivity {
    MaterialToolbar UtilitiesReport_toolbar;
    RadioButton UtilitiesReport_RadioButton1, UtilitiesReport_RadioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilities_report);

        UtilitiesReport_RadioButton1 = findViewById(R.id.UtilitiesReport_RadioButton1);
        UtilitiesReport_RadioButton2 = findViewById(R.id.UtilitiesReport_RadioButton2);
        UtilitiesReport_toolbar = findViewById(R.id.UtilitiesReport_toolbar);

        UtilitiesReport_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(UtilitiesReportActivity.this, UtilitiesActivity.class));
            finish();
        });

        UtilitiesReport_RadioButton1.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(UtilitiesReportActivity.this, R.color.black)));
        UtilitiesReport_RadioButton2.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(UtilitiesReportActivity.this, R.color.black)));
        UtilitiesReport_RadioButton1.setOnCheckedChangeListener(radioButtonListener);
        UtilitiesReport_RadioButton2.setOnCheckedChangeListener(radioButtonListener);
        UtilitiesReport_RadioButton1.setChecked(true);
    }

    private final CompoundButton.OnCheckedChangeListener radioButtonListener = (buttonView, isChecked) -> {
        if (isChecked) {

            clearAllSelections();

            buttonView.setChecked(true);
            buttonView.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(UtilitiesReportActivity.this, R.color.blue)));
        }
    };

    private void clearAllSelections() {
        UtilitiesReport_RadioButton1.setChecked(false);
        UtilitiesReport_RadioButton2.setChecked(false);

        UtilitiesReport_RadioButton1.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(UtilitiesReportActivity.this, R.color.black)));
        UtilitiesReport_RadioButton2.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(UtilitiesReportActivity.this, R.color.black)));

    }

}
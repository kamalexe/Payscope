package pay.scope.payscope.Activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class AddUPIActivity extends AppCompatActivity {

    RadioButton upi_RadioButton1, upi_RadioButton2, upi_RadioButton3;
    MaterialToolbar UPIWallet_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upiactivity);

        upi_RadioButton1 = findViewById(R.id.upi_RadioButton1);
        upi_RadioButton2 = findViewById(R.id.upi_RadioButton2);
        upi_RadioButton3 = findViewById(R.id.upi_RadioButton3);
        UPIWallet_toolbar = findViewById(R.id.UPIWallet_toolbar);

        UPIWallet_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(AddUPIActivity.this, MainActivity.class));
            finish();
        });


//        upi_RadioButton3.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(AddUPIActivity.this, R.color.black)));

        upi_RadioButton1.setOnCheckedChangeListener(radioButtonListener);
        upi_RadioButton2.setOnCheckedChangeListener(radioButtonListener);
        upi_RadioButton3.setOnCheckedChangeListener(radioButtonListener);
        upi_RadioButton1.setChecked(true);

    }

    private final CompoundButton.OnCheckedChangeListener radioButtonListener = (buttonView, isChecked) -> {
        if (isChecked) {
            clearAllSelections();
            buttonView.setChecked(true);
            buttonView.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(AddUPIActivity.this, R.color.blue)));
        }
    };

    private void clearAllSelections() {
        upi_RadioButton1.setChecked(false);
        upi_RadioButton2.setChecked(false);
        upi_RadioButton3.setChecked(false);

        upi_RadioButton1.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(AddUPIActivity.this, R.color.black)));
        upi_RadioButton2.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(AddUPIActivity.this, R.color.black)));
        upi_RadioButton3.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(AddUPIActivity.this, R.color.black)));

    }
}

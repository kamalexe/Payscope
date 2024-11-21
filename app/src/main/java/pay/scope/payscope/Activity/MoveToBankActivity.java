package pay.scope.payscope.Activity;

import android.content.res.ColorStateList;
import android.os.Bundle;

import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class MoveToBankActivity extends AppCompatActivity {
    MaterialToolbar MoveBank_toolbar;
    RadioGroup MoveBank_RadioGroup;

    RadioButton MoveBank_RadioButton1, MoveBank_RadioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_to_bank);

        MoveBank_toolbar = findViewById(R.id.MoveBank_toolbar);
        MoveBank_RadioGroup = findViewById(R.id.MoveBank_RadioGroup);
        MoveBank_RadioButton1 = findViewById(R.id.MoveBank_RadioButton1);
        MoveBank_RadioButton2 = findViewById(R.id.MoveBank_RadioButton2);

        MoveBank_toolbar.setNavigationOnClickListener(v -> /* startActivity(new Intent(MoveToBankActivity.this, MainActivity.class));*/  finish());

        MoveBank_RadioButton1.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(MoveToBankActivity.this, R.color.blue)));
        MoveBank_RadioButton2.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(MoveToBankActivity.this, R.color.black)));

        MoveBank_RadioButton1.setOnCheckedChangeListener(radioButtonListener);
        MoveBank_RadioButton2.setOnCheckedChangeListener(radioButtonListener);


        MoveBank_RadioButton1.setChecked(true);
    }

    private final CompoundButton.OnCheckedChangeListener radioButtonListener = (buttonView, isChecked) -> {
        if (isChecked) {
            clearAllSelections();
            buttonView.setChecked(true);
            buttonView.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(MoveToBankActivity.this, R.color.blue)));
        }
    };

    private void clearAllSelections() {
        MoveBank_RadioButton1.setChecked(false);
        MoveBank_RadioButton2.setChecked(false);

        MoveBank_RadioButton1.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(MoveToBankActivity.this, R.color.black)));
        MoveBank_RadioButton2.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(MoveToBankActivity.this, R.color.black)));
    }
}

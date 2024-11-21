package pay.scope.payscope.Activity;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class AdultEconomyActivity extends AppCompatActivity {
    private ImageView AdultsMinus, AdultsPlus, ChildrenMinus, ChildrenPlus, InfantsMinus, InfantsPlus;
    private TextView AdultsCounts, ChildrenCounts, InfantsCounts;
    private int adultCount = 1;
    private int childrenCount = 0;
    private int infantsCount = 0;
    MaterialToolbar Travellers_toolbar;
    RadioButton Travellers_radioButton1, Travellers_radioButton2, Travellers_radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult_economy);

        AdultsMinus = findViewById(R.id.AdultsMinus);
        AdultsPlus = findViewById(R.id.AdultsPlus);
        ChildrenMinus = findViewById(R.id.ChildrenMinus);
        ChildrenPlus = findViewById(R.id.ChildrenPlus);
        InfantsMinus = findViewById(R.id.InfantsMinus);
        InfantsPlus = findViewById(R.id.InfantsPlus);
        AdultsCounts = findViewById(R.id.AdultsCounts);
        ChildrenCounts = findViewById(R.id.ChildrenCounts);
        InfantsCounts = findViewById(R.id.InfantsCounts);
        Travellers_toolbar = findViewById(R.id.Travellers_toolbar);

        Travellers_radioButton1 = findViewById(R.id.Travellers_radioButton1);
        Travellers_radioButton2 = findViewById(R.id.Travellers_radioButton2);
        Travellers_radioButton3 = findViewById(R.id.Travellers_radioButton3);

        updateCounts();

        Travellers_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(AdultEconomyActivity.this, FlightBookingActivity.class));
            finish();
        });


        AdultsMinus.setOnClickListener(v -> AdultsDecrease());
        AdultsPlus.setOnClickListener(v -> AdultsIncrease());

        ChildrenMinus.setOnClickListener(v -> ChildrenDecrease());
        ChildrenPlus.setOnClickListener(v -> ChildrenIncrease());

        InfantsMinus.setOnClickListener(v -> InfantsDecrease());
        InfantsPlus.setOnClickListener(v -> InfantsIncrease());


        Travellers_radioButton1.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(AdultEconomyActivity.this, R.color.black)));
        Travellers_radioButton2.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(AdultEconomyActivity.this, R.color.black)));
        Travellers_radioButton3.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(AdultEconomyActivity.this, R.color.black)));

        Travellers_radioButton1.setOnCheckedChangeListener(radioButtonListener);
        Travellers_radioButton2.setOnCheckedChangeListener(radioButtonListener);
        Travellers_radioButton3.setOnCheckedChangeListener(radioButtonListener);
        Travellers_radioButton1.setChecked(true);


    }


    private void updateCounts() {
        AdultsCounts.setText(String.valueOf(adultCount));
        ChildrenCounts.setText(String.valueOf(childrenCount));
        InfantsCounts.setText(String.valueOf(infantsCount));

        // Check if total count equals 15
        if (adultCount + childrenCount + infantsCount == 15) {
            // Change the color of the decrease button images
            AdultsMinus.setColorFilter(ContextCompat.getColor(this, R.color.lightblack), PorterDuff.Mode.SRC_IN);
            ChildrenMinus.setColorFilter(ContextCompat.getColor(this, R.color.lightblack), PorterDuff.Mode.SRC_IN);
            InfantsMinus.setColorFilter(ContextCompat.getColor(this, R.color.lightblack), PorterDuff.Mode.SRC_IN);

            // Change the color of the increase button images for Children and Infants
            ChildrenPlus.setColorFilter(ContextCompat.getColor(this, R.color.lightblack), PorterDuff.Mode.SRC_IN);
            InfantsPlus.setColorFilter(ContextCompat.getColor(this, R.color.lightblack), PorterDuff.Mode.SRC_IN);

        } else {
            // Reset the color of the decrease button images to default
            AdultsMinus.setColorFilter(ContextCompat.getColor(this, R.color.textblue), PorterDuff.Mode.SRC_IN);
            ChildrenMinus.setColorFilter(ContextCompat.getColor(this, R.color.textblue), PorterDuff.Mode.SRC_IN);
            InfantsMinus.setColorFilter(ContextCompat.getColor(this, R.color.textblue), PorterDuff.Mode.SRC_IN);

            // Reset the color of the increase button images for Children and Infants to default
            ChildrenPlus.setColorFilter(ContextCompat.getColor(this, R.color.textblue), PorterDuff.Mode.SRC_IN);
            InfantsPlus.setColorFilter(ContextCompat.getColor(this, R.color.textblue), PorterDuff.Mode.SRC_IN);
            AdultsPlus.setColorFilter(ContextCompat.getColor(this, R.color.textblue), PorterDuff.Mode.SRC_IN);
        }
    }

    public void AdultsIncrease() {
        if (adultCount + childrenCount + infantsCount < 15) {
            adultCount++;
            updateCounts();
        }
    }

    public void AdultsDecrease() {
        if (adultCount > 1) {
            adultCount--;
            updateCounts();
        }
    }

    public void ChildrenIncrease() {
        if (adultCount + childrenCount + infantsCount < 15) {
            childrenCount++;
            updateCounts();

        }

    }

    public void ChildrenDecrease() {
        if (childrenCount > 0) {
            childrenCount--;
            updateCounts();
        }
    }

    public void InfantsIncrease() {
        if (adultCount + childrenCount + infantsCount < 15) {
            infantsCount++;
            updateCounts();
        }
    }

    public void InfantsDecrease() {
        if (infantsCount > 0) {
            infantsCount--;
            updateCounts();
        }
    }

    private final CompoundButton.OnCheckedChangeListener radioButtonListener = (buttonView, isChecked) -> {
        if (isChecked) {

            clearAllSelections();

            buttonView.setChecked(true);
            buttonView.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(AdultEconomyActivity.this, R.color.blue)));
        }
    };

    private void clearAllSelections() {
        Travellers_radioButton1.setChecked(false);
        Travellers_radioButton2.setChecked(false);
        Travellers_radioButton3.setChecked(false);

        Travellers_radioButton1.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(AdultEconomyActivity.this, R.color.black)));
        Travellers_radioButton2.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(AdultEconomyActivity.this, R.color.black)));
        Travellers_radioButton3.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(AdultEconomyActivity.this, R.color.black)));


    }
}




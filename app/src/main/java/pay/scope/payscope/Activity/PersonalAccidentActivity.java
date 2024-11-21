package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.R;

public class PersonalAccidentActivity extends AppCompatActivity {

    ImageSlider personal_Accident_imageSlider;
    int selectedCardIndex = -1;
    MaterialCardView selectCover_card1, selectCover_card2, selectCover_card3;
    MaterialToolbar PersonalAccident_toolbar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_accident);


        personal_Accident_imageSlider = findViewById(R.id.personal_Accident_imageSlider);
        selectCover_card1 = findViewById(R.id.selectCover_card1);
        selectCover_card2 = findViewById(R.id.selectCover_card2);
        selectCover_card3 = findViewById(R.id.selectCover_card3);

        PersonalAccident_toolbar = findViewById(R.id.PersonalAccident_toolbar);
        PersonalAccident_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(PersonalAccidentActivity.this, InsurancePaymentActivity.class));
                finish();
            }
        });

        selectedCardIndex = 0;
        updateCardBackground();

        List<SlideModel> slideModelsList = new ArrayList<>();

        slideModelsList.add(new SlideModel(R.drawable.personal_acciden, "", ScaleTypes.FIT));
        slideModelsList.add(new SlideModel(R.drawable.personal_accident1, "", ScaleTypes.FIT));
        slideModelsList.add(new SlideModel(R.drawable.personal_acciden2, "", ScaleTypes.FIT));
        personal_Accident_imageSlider.setImageList(slideModelsList);

        selectCover_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update selected card index and change background color
                selectedCardIndex = 0;
                updateCardBackground();
            }
        });

        selectCover_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCardIndex = 1;
                updateCardBackground();
            }
        });

        selectCover_card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCardIndex = 2;
                updateCardBackground();
            }
        });


    }

    private void updateCardBackground() {
        selectCover_card1.setCardBackgroundColor(getResources().getColor(R.color.cardchange));
        selectCover_card2.setCardBackgroundColor(getResources().getColor(R.color.cardchange));
        selectCover_card3.setCardBackgroundColor(getResources().getColor(R.color.cardchange));


        switch (selectedCardIndex) {
            case 0:
                selectCover_card1.setCardBackgroundColor(ContextCompat.getColor(PersonalAccidentActivity.this, R.color.editbox));
                break;
            case 1:
                selectCover_card2.setCardBackgroundColor(ContextCompat.getColor(PersonalAccidentActivity.this, R.color.editbox));
                break;
            case 2:
                selectCover_card3.setCardBackgroundColor(ContextCompat.getColor(PersonalAccidentActivity.this, R.color.editbox));
                break;
            default:
                // No card selected, do nothing
                break;
        }

    }
}
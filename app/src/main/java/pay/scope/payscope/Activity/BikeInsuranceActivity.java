package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.R;

public class BikeInsuranceActivity extends AppCompatActivity {
    ImageSlider bike_imageSlider;
    MaterialToolbar BikeInsurance_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_insurance);

        bike_imageSlider = findViewById(R.id.bike_imageSlider);
        BikeInsurance_toolbar = findViewById(R.id.BikeInsurance_toolbar);

        BikeInsurance_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(BikeInsuranceActivity.this, InsurancePaymentActivity.class));
            finish();
        });

        List<SlideModel> slideModelsList = new ArrayList<>();

        slideModelsList.add(new SlideModel("https://i.pinimg.com/736x/5d/29/f8/5d29f8e969c5e0cfb205636498f5e4e4.jpg", "", ScaleTypes.FIT));
        slideModelsList.add(new SlideModel("https://thumbs.dreamstime.com/z/motorcycle-insurance-concept-generic-black-custom-sitting-brochure-photo-realistic-d-model-scene-isolated-white-66430500.jpg", "", ScaleTypes.FIT));
        slideModelsList.add(new SlideModel("https://staticimg.insurancedekho.com/uploads/news/image/mceu_20135446111654017485090_1654017485.jpg", "", ScaleTypes.FIT));

        bike_imageSlider.setImageList(slideModelsList);


    }
}
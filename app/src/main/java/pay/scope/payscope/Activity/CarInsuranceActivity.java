package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.R;

public class CarInsuranceActivity extends AppCompatActivity {

    ImageSlider car_imageSlider;
    MaterialToolbar CarInsurance_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_insurance);

        car_imageSlider = findViewById(R.id.car_imageSlider);

        CarInsurance_toolbar = findViewById(R.id.CarInsurance_toolbar);
        CarInsurance_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(CarInsuranceActivity.this, InsurancePaymentActivity.class));
                finish();
            }
        });

        List<SlideModel> slideModelsList = new ArrayList<>();
        slideModelsList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtdcVCEZY6nDRtMYFqVG3GJc9gnH6HeMGLbTCw9Y45tA&s", "", ScaleTypes.FIT));
        slideModelsList.add(new SlideModel("https://img.freepik.com/premium-photo/car-insurance-concept-dark-blue-background_220873-4216.jpg", "", ScaleTypes.FIT));
        slideModelsList.add(new SlideModel("https://www.shutterstock.com/image-vector/concept-auto-insurance-car-palm-260nw-2022528464.jpg", "", ScaleTypes.FIT));
        car_imageSlider.setImageList(slideModelsList);

    }
}
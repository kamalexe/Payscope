package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.R;

public class PaymentProtectActivity extends AppCompatActivity {

    ImageSlider payment_Protect_imageSlider;
    MaterialToolbar PaymentProtect_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_protect);

        payment_Protect_imageSlider = findViewById(R.id.payment_Protect_imageSlider);
        PaymentProtect_toolbar = findViewById(R.id.PaymentProtect_toolbar);

        PaymentProtect_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(PaymentProtectActivity.this, InsurancePaymentActivity.class));
                finish();
            }
        });

        List<SlideModel> slideModelsList = new ArrayList<>();
        slideModelsList.add(new SlideModel("https://m.media-amazon.com/images/I/41LixCWx4SL.jpg", "", ScaleTypes.FIT));
        slideModelsList.add(new SlideModel("https://cdn.open-pr.com/T/b/Tb19519853_g.jpg", "", ScaleTypes.FIT));
        slideModelsList.add(new SlideModel("https://i.ytimg.com/vi/5asAt1Jt9lI/maxresdefault.jpg", "", ScaleTypes.FIT));
        payment_Protect_imageSlider.setImageList(slideModelsList);


    }
}
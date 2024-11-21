package pay.scope.payscope.Activity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.HelpSupportAdapter;
import pay.scope.payscope.Adapter.HelpSupportRechargeAdapter;
import pay.scope.payscope.Model.HelpSupportModel;
import pay.scope.payscope.Model.HelpSupportRechargeModel;
import pay.scope.payscope.R;

public class HelpActivity extends AppCompatActivity {
    MaterialToolbar Help_toolbar;
    RecyclerView HelpSupp_recycler, HelpSuppRecharge_recycler;
    ImageSlider help_imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Help_toolbar = findViewById(R.id.Help_toolbar);
        help_imageSlider = findViewById(R.id.help_imageSlider);
        HelpSupp_recycler = findViewById(R.id.HelpSupp_recycler);
        HelpSuppRecharge_recycler = findViewById(R.id.HelpSuppRecharge_recycler);


        Help_toolbar.setNavigationOnClickListener(v -> { /*startActivity(new Intent(HelpActivity.this, ProfileUpdateActivity.class));*/
            finish();
        });


        List<SlideModel> slideModelsList = new ArrayList<>();
        slideModelsList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSP4O94dW4uyrUrXG91jAWl_RxugeoSq_jTDZCzioY61A&s", "", ScaleTypes.FIT));
        slideModelsList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdWEN2TfYn9xTryD_e8we44t2f78lo_MHdAFww80iSww&s", "", ScaleTypes.FIT));
        slideModelsList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSErnLfddAQMqgfVzrw94zu6CgNNjjWoBzXLVZTAzELbQ&s", "", ScaleTypes.FIT));
        help_imageSlider.setImageList(slideModelsList);


        List<HelpSupportModel> helpSupportModelList = new ArrayList<>();
        helpSupportModelList.add(new HelpSupportModel(R.drawable.upi, "UPI"));
        helpSupportModelList.add(new HelpSupportModel(R.drawable.baseline_person_24, "Profile"));
        helpSupportModelList.add(new HelpSupportModel(R.drawable.baseline_medical_services_24, "Travel"));
        helpSupportModelList.add(new HelpSupportModel(R.drawable.baseline_person_24, "Profile"));
        helpSupportModelList.add(new HelpSupportModel(R.drawable.upi, "UPI"));
        helpSupportModelList.add(new HelpSupportModel(R.drawable.baseline_person_24, "Profile"));
        helpSupportModelList.add(new HelpSupportModel(R.drawable.baseline_medical_services_24, "Travel"));
        helpSupportModelList.add(new HelpSupportModel(R.drawable.baseline_person_24, "Profile"));
        helpSupportModelList.add(new HelpSupportModel(R.drawable.upi, "UPI"));
        HelpSupportAdapter supportAdapter = new HelpSupportAdapter(HelpActivity.this, helpSupportModelList);
        HelpSupp_recycler.setAdapter(supportAdapter);
        HelpSupp_recycler.setLayoutManager(new LinearLayoutManager(HelpActivity.this, LinearLayoutManager.HORIZONTAL, false));


        List<HelpSupportRechargeModel> helpSupportRechargeModelList = new ArrayList<>();
        helpSupportRechargeModelList.add(new HelpSupportRechargeModel(R.drawable.mobile_recharge, "Mobile\nRecharge"));
        helpSupportRechargeModelList.add(new HelpSupportRechargeModel(R.drawable.mobile_recharge, "Mobile\nPostpaid"));
        helpSupportRechargeModelList.add(new HelpSupportRechargeModel(R.drawable.baseline_lightbulb_outline_24, "Electricity\nBill"));
        helpSupportRechargeModelList.add(new HelpSupportRechargeModel(R.drawable.baseline_credit_card, "Credit\nCard Bill"));
        helpSupportRechargeModelList.add(new HelpSupportRechargeModel(R.drawable.mobile_recharge, "Mobile\nRecharge"));
        helpSupportRechargeModelList.add(new HelpSupportRechargeModel(R.drawable.mobile_recharge, "Mobile\nPostpaid"));
        helpSupportRechargeModelList.add(new HelpSupportRechargeModel(R.drawable.baseline_lightbulb_outline_24, "Electricity\nBill"));
        helpSupportRechargeModelList.add(new HelpSupportRechargeModel(R.drawable.baseline_credit_card, "Credit\nCard Bill"));
        HelpSupportRechargeAdapter supportRechargeAdapter = new HelpSupportRechargeAdapter(HelpActivity.this, helpSupportRechargeModelList);
        HelpSuppRecharge_recycler.setAdapter(supportRechargeAdapter);
        HelpSuppRecharge_recycler.setLayoutManager(new LinearLayoutManager(HelpActivity.this, LinearLayoutManager.HORIZONTAL, false));


    }
}
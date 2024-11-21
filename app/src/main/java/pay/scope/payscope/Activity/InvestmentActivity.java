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

import pay.scope.payscope.Adapter.InvestmentAdapter;
import pay.scope.payscope.Model.InvestmentModel;
import pay.scope.payscope.R;

public class InvestmentActivity extends AppCompatActivity {
    MaterialToolbar Investment_toolbar;
    ImageSlider investment_imageSlider;
    RecyclerView investment_recycler;
//    int spanCounts = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);

        Investment_toolbar = findViewById(R.id.Investment_toolbar);
        investment_imageSlider = findViewById(R.id.investment_imageSlider);
        investment_recycler = findViewById(R.id.investment_recycler);

        Investment_toolbar.setNavigationOnClickListener(v -> finish());


        List<SlideModel> slideModelsList = new ArrayList<>();

        slideModelsList.add(new SlideModel("https://slidemodel.com/wp-content/uploads/30061-01-finance-and-investment-powerpoint-template-1.jpg", "", ScaleTypes.FIT));
        slideModelsList.add(new SlideModel("https://www.collidu.com/media/catalog/product/img/f/a/fa8bb51f69a9c01e3deed4a128980dab39ed06d4be12341ea6507e764d29f42f/investment-plans-slide1.png", "", ScaleTypes.FIT));
        slideModelsList.add(new SlideModel("https://cdn.sketchbubble.com/pub/media/catalog/product/optimized1/e/5/e509f85d85403d9002f0c91dd1bb8b3a3ece98c3fe483965fdd702a123a57277/community-investment-mc-slide1.png", "", ScaleTypes.FIT));
//        slideModelsList.add(new SlideModel("", "", ScaleTypes.FIT));
        investment_imageSlider.setImageList(slideModelsList);


        List<InvestmentModel> investmentModelList = new ArrayList<>();
        investmentModelList.add(new InvestmentModel(R.drawable.microatm, R.drawable.applogo, "Investment Title", "Financial investments come in different forms, such as mutual funds, unit linked investment plans, endowment plans, stocks, bonds and more."));
        investmentModelList.add(new InvestmentModel(R.drawable.wallet, R.drawable.applogo, "Investment Title", "Financial investments come in different forms, such as mutual funds, unit linked investment plans, endowment plans, stocks, bonds and more."));
        investmentModelList.add(new InvestmentModel(R.drawable.apes, R.drawable.applogo, "Investment Title", "Financial investments come in different forms, such as mutual funds, unit linked investment plans, endowment plans, stocks, bonds and more."));
        investmentModelList.add(new InvestmentModel(R.drawable.microatm, R.drawable.applogo, "Investment Title", "Financial investments come in different forms, such as mutual funds, unit linked investment plans, endowment plans, stocks, bonds and more."));
        investmentModelList.add(new InvestmentModel(R.drawable.wallet, R.drawable.applogo, "Investment Title", "Financial investments come in different forms, such as mutual funds, unit linked investment plans, endowment plans, stocks, bonds and more."));
        investmentModelList.add(new InvestmentModel(R.drawable.apes, R.drawable.applogo, "Investment Title", "Financial investments come in different forms, such as mutual funds, unit linked investment plans, endowment plans, stocks, bonds and more."));

        InvestmentAdapter investmentAdapter = new InvestmentAdapter(InvestmentActivity.this, investmentModelList);
        investment_recycler.setAdapter(investmentAdapter);

        investment_recycler.setLayoutManager(new LinearLayoutManager(InvestmentActivity.this, LinearLayoutManager.VERTICAL, false));
//        investment_recycler.setLayoutManager(new LinearLayoutManager(InvestmentActivity.this, LinearLayoutManager.HORIZONTAL, false));


//        GridLayoutManager layoutManager = new GridLayoutManager(InvestmentActivity.this, spanCounts);
//        layoutManager.setSpanCount(spanCounts);
//        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
//        investment_recycler.setLayoutManager(layoutManager)
//
    }
}
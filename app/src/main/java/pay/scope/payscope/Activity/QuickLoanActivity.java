package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.QuickLoanAdapter;
import pay.scope.payscope.Model.QuickLoanModel;
import pay.scope.payscope.R;

public class QuickLoanActivity extends AppCompatActivity {

    MaterialToolbar QuickLoan_toolbar;
    Button quickLoan_Apply;
    RecyclerView quickLoan_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_loan);

        quickLoan_Apply = findViewById(R.id.quickLoan_Apply);
        QuickLoan_toolbar = findViewById(R.id.QuickLoan_toolbar);
        quickLoan_recycler = findViewById(R.id.quickLoan_recycler);

        QuickLoan_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(QuickLoanActivity.this, MainActivity.class));
                finish();

            }
        });

        quickLoan_Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuickLoanActivity.this, LoanEMIActivity.class);
                startActivity(intent);
            }
        });

//        List<SlideModel> slideModelsList = new ArrayList<>();
//
//        slideModelsList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmzhWM_2nhdvFYNknXPVVivNTe4AmkezV5uIgbV64udW5zZQgmy50T6Xsh7Nu39orfjqk&usqp=CAU", "", ScaleTypes.FIT));
//        slideModelsList.add(new SlideModel("https://www.investopedia.com/thmb/YqKsi6TCt_fwnx-FZKU_YZuxBGc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Term-Definitions_loan.asp-b51fa1e26728403dbe6bddb3ff14ea71.jpg", "", ScaleTypes.FIT));
//        slideModelsList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0J21U86XfYGMudxuUwjIAKKOJ6DOiMfxVOa1MA4npqzDDa29FGmgWYVGO6i_3a8FCk7g&usqp=CAU", "", ScaleTypes.FIT));
////        slideModelsList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXmXoA1SNsFzuVzytibZuKgr-9llm562W2sg&s", "", ScaleTypes.FIT));
//        quickLoan_imageSlider.setImageList(slideModelsList);


        List<QuickLoanModel> quickLoanModelList = new ArrayList<>();
        quickLoanModelList.add(new QuickLoanModel(R.drawable.baseline_directions_car_filled_24, 3455.00, "Car EMI", "Done", "15 Apr 2023"));
        quickLoanModelList.add(new QuickLoanModel(R.drawable.baseline_directions_car_filled_24, 3455.00, "Car EMI", "Done", "15 Apr 2023"));
        quickLoanModelList.add(new QuickLoanModel(R.drawable.baseline_directions_car_filled_24, 3455.00, "Car EMI", "Done", "15 Apr 2023"));
        quickLoanModelList.add(new QuickLoanModel(R.drawable.baseline_directions_car_filled_24, 3455.00, "Car EMI", "Done", "15 Apr 2023"));
        quickLoanModelList.add(new QuickLoanModel(R.drawable.baseline_directions_car_filled_24, 3455.00, "Car EMI", "Done", "15 Apr 2023"));
        quickLoanModelList.add(new QuickLoanModel(R.drawable.baseline_directions_car_filled_24, 3455.00, "Car EMI", "Done", "15 Apr 2023"));
        quickLoanModelList.add(new QuickLoanModel(R.drawable.baseline_directions_car_filled_24, 3455.00, "Car EMI", "Done", "15 Apr 2023"));
        quickLoanModelList.add(new QuickLoanModel(R.drawable.baseline_directions_car_filled_24, 3455.00, "Car EMI", "Done", "15 Apr 2023"));
        quickLoanModelList.add(new QuickLoanModel(R.drawable.baseline_directions_car_filled_24, 3455.00, "Car EMI", "Done", "15 Apr 2023"));

        QuickLoanAdapter loanAdapter = new QuickLoanAdapter(QuickLoanActivity.this, quickLoanModelList);
        quickLoan_recycler.setAdapter(loanAdapter);
        quickLoan_recycler.setLayoutManager(new LinearLayoutManager(QuickLoanActivity.this, LinearLayoutManager.VERTICAL, false));


    }
}
package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import java.util.List;

import pay.scope.payscope.Adapter.BudgetAddTextAdapter;
import pay.scope.payscope.Adapter.BudgetPayAdapter;

import pay.scope.payscope.Model.BudgetAddTextModel;
import pay.scope.payscope.Model.BudgetPayModel;
import pay.scope.payscope.R;

public class BudgetActivity extends AppCompatActivity {
    MaterialToolbar Budget_toolbar;
    RecyclerView BudgetAddRecycler, BudgetPayRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        BudgetAddRecycler = findViewById(R.id.BudgetAddRecycler);
        BudgetPayRecycler = findViewById(R.id.BudgetPayRecycler);
        Budget_toolbar = findViewById(R.id.Budget_toolbar);

        Budget_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(BudgetActivity.this, BudgetAllActivity.class));
            finish();
        });

        List<BudgetAddTextModel> budgetAddTextModelList = new ArrayList<>();
        budgetAddTextModelList.add(new BudgetAddTextModel("Anshu Singh ", "AxtZ2r6dsc", "01/2/2023", 43332.00));
        budgetAddTextModelList.add(new BudgetAddTextModel("Anshu Singh ", "AxtZ2r6dsc", "01/2/2023", 43332.00));
        budgetAddTextModelList.add(new BudgetAddTextModel("Anshu Singh ", "AxtZ2r6dsc", "01/2/2023", 43332.00));
        budgetAddTextModelList.add(new BudgetAddTextModel("Anshu Singh ", "AxtZ2r6dsc", "01/2/2023", 43332.00));

        BudgetAddTextAdapter addTextAdapter = new BudgetAddTextAdapter(BudgetActivity.this, budgetAddTextModelList);
        BudgetAddRecycler.setAdapter(addTextAdapter);
        BudgetAddRecycler.setLayoutManager(new LinearLayoutManager(BudgetActivity.this, LinearLayoutManager.VERTICAL, true));

        List<BudgetPayModel> budgetPayModelList = new ArrayList<>();
        budgetPayModelList.add(new BudgetPayModel("Personal Loan", R.drawable.baseline_currency_rupee, "Pay"));
        budgetPayModelList.add(new BudgetPayModel("Bill Pay Offer", R.drawable.baseline_electric_meter_24, "Pay"));
        budgetPayModelList.add(new BudgetPayModel("Change Language", R.drawable.baseline_language_24, "Pay"));
        budgetPayModelList.add(new BudgetPayModel("T.V Recharge", R.drawable.baseline_tv_24, "Pay"));
        budgetPayModelList.add(new BudgetPayModel("Mobile Recharge", R.drawable.mobile_recharge, "Pay"));

        BudgetPayAdapter budgetPayAdapter = new BudgetPayAdapter(BudgetActivity.this, budgetPayModelList);
        BudgetPayRecycler.setAdapter(budgetPayAdapter);
        BudgetPayRecycler.setLayoutManager(new LinearLayoutManager(BudgetActivity.this, LinearLayoutManager.HORIZONTAL, false));

    }
}
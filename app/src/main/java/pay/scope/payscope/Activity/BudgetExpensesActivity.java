package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.BudgetExpensesAdapter;
import pay.scope.payscope.Model.BudgetExpensesModel;
import pay.scope.payscope.R;

public class BudgetExpensesActivity extends AppCompatActivity {
    MaterialToolbar BudgetExpenses_toolbar;
    RecyclerView BudgetExpenses_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_expenses);

        BudgetExpenses_toolbar = findViewById(R.id.BudgetExpenses_toolbar);
        BudgetExpenses_recycler = findViewById(R.id.BudgetExpenses_recycler);

        BudgetExpenses_toolbar.setNavigationOnClickListener(v -> {
            startActivity(new Intent(BudgetExpensesActivity.this, BudgetAllActivity.class));
            finish();
        });

        List<BudgetExpensesModel> budgetExpensesModelList = new ArrayList<>();
        budgetExpensesModelList.add(new BudgetExpensesModel("Anshu Singh", "12/6/2024","Xfsc23Tcwe24", 554.00));
        budgetExpensesModelList.add(new BudgetExpensesModel("Anshu Singh", "12/6/2024","Xfsc23Tcwe24", 554.00));
        budgetExpensesModelList.add(new BudgetExpensesModel("Anshu Singh", "12/6/2024","Xfsc23Tcwe24", 554.00));
        budgetExpensesModelList.add(new BudgetExpensesModel("Anshu Singh", "12/6/2024","Xfsc23Tcwe24", 554.00));

        BudgetExpensesAdapter expensesAdapter = new BudgetExpensesAdapter(BudgetExpensesActivity.this, budgetExpensesModelList);
        BudgetExpenses_recycler.setAdapter(expensesAdapter);
        BudgetExpenses_recycler.setLayoutManager(new LinearLayoutManager(BudgetExpensesActivity.this, LinearLayoutManager.VERTICAL, true));
    }
}
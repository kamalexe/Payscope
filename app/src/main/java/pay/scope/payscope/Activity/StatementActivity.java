package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.StatementAdapter;
import pay.scope.payscope.Model.StatementModel;
import pay.scope.payscope.R;

public class StatementActivity extends AppCompatActivity {
    RecyclerView statementRecyclerView;
    MaterialToolbar statement_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        statementRecyclerView = findViewById(R.id.statementRecyclerView);
        statement_toolbar = findViewById(R.id.statement_toolbar);

        statement_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(StatementActivity.this, QuickViewAllActivity.class));
            finish();
        });

        List<StatementModel> statementModelList = new ArrayList<>();

        statementModelList.add(new StatementModel(R.drawable.baseline_food_bank_24, 12.54, "CALVIN KLEIN JEANS", "HDFC.9322", "Today 12 Apr 2024", "expenditure"));
        statementModelList.add(new StatementModel(R.drawable.baseline_food_bank_24, 12.54, "CALVIN KLEIN JEANS", "HDFC.9322", "Today 12 Apr 2024", "expenditure"));
        statementModelList.add(new StatementModel(R.drawable.baseline_food_bank_24, 12.54, "CALVIN KLEIN JEANS", "HDFC.9322", "Today 12 Apr 2024", "expenditure"));
        statementModelList.add(new StatementModel(R.drawable.baseline_food_bank_24, 12.54, "CALVIN KLEIN JEANS", "HDFC.9322", "Today 12 Apr 2024", "expenditure"));
        statementModelList.add(new StatementModel(R.drawable.baseline_food_bank_24, 12.54, "CALVIN KLEIN JEANS", "HDFC.9322", "Today 12 Apr 2024", "expenditure"));
        statementModelList.add(new StatementModel(R.drawable.baseline_food_bank_24, 12.54, "CALVIN KLEIN JEANS", "HDFC.9322", "Today 12 Apr 2024", "expenditure"));
        statementModelList.add(new StatementModel(R.drawable.baseline_food_bank_24, 12.54, "CALVIN KLEIN JEANS", "HDFC.9322", "Today 12 Apr 2024", "expenditure"));


        StatementAdapter adapter = new StatementAdapter(StatementActivity.this, statementModelList);
        statementRecyclerView.setAdapter(adapter);

        statementRecyclerView.setLayoutManager(new LinearLayoutManager(StatementActivity.this, LinearLayoutManager.VERTICAL, false));


    }
}
package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.StatisticsAdapter;
import pay.scope.payscope.Model.StatisticsModel;
import pay.scope.payscope.R;

public class StatisticsActivity extends AppCompatActivity {
    MaterialToolbar Statistics_toolbar;
    RecyclerView Statistics_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Statistics_toolbar = findViewById(R.id.Statistics_toolbar);
        Statistics_recycler = findViewById(R.id.Statistics_recycler);

        Statistics_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(StatisticsActivity.this, LoanEMIActivity.class));
            finish();

        });

        List<StatisticsModel> statisticsModelList = new ArrayList<>();

        statisticsModelList.add(new StatisticsModel("Car loan","55% paid",R.drawable.baseline_directions_car_24,234.243,644.00,43456.00,774.00));
        statisticsModelList.add(new StatisticsModel("Car loan","55% paid",R.drawable.baseline_directions_car_24,234.243,644.00,43456.00,774.00));
        statisticsModelList.add(new StatisticsModel("Car loan","55% paid",R.drawable.baseline_directions_car_24,234.243,644.00,43456.00,774.00));
        statisticsModelList.add(new StatisticsModel("Car loan","55% paid",R.drawable.baseline_directions_car_24,234.243,644.00,43456.00,774.00));
        statisticsModelList.add(new StatisticsModel("Car loan","55% paid",R.drawable.baseline_directions_car_24,234.243,644.00,43456.00,774.00));
        statisticsModelList.add(new StatisticsModel("Car loan","55% paid",R.drawable.baseline_directions_car_24,234.243,644.00,43456.00,774.00));
        statisticsModelList.add(new StatisticsModel("Car loan","55% paid",R.drawable.baseline_directions_car_24,234.243,644.00,43456.00,774.00));

        StatisticsAdapter adapter = new StatisticsAdapter(StatisticsActivity.this, statisticsModelList);
        Statistics_recycler.setAdapter(adapter);
        Statistics_recycler.setLayoutManager(new LinearLayoutManager(StatisticsActivity.this, LinearLayoutManager.VERTICAL, false));

    }
}
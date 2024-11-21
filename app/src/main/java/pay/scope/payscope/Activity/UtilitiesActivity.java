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

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.UtilitiesAdapter;
import pay.scope.payscope.Model.UtilitiesModel;
import pay.scope.payscope.R;

public class UtilitiesActivity extends AppCompatActivity {
    MaterialToolbar Utilities_toolbar;
    Button UtilitiesBtn;
    RecyclerView Utilities_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilities);

        UtilitiesBtn = findViewById(R.id.UtilitiesBtn);
        Utilities_recycler = findViewById(R.id.Utilities_recycler);

        Utilities_toolbar = findViewById(R.id.Utilities_toolbar);
        Utilities_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(UtilitiesActivity.this, QuickViewAllActivity.class));
                finish();
            }
        });
        UtilitiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UtilitiesActivity.this, UtilitiesReportActivity.class));

            }
        });

        List<UtilitiesModel> utilitiesModelList = new ArrayList<>();
        utilitiesModelList.add(new UtilitiesModel("Andrea Cotes", "5 min ago", "Problem with hot water"));
        utilitiesModelList.add(new UtilitiesModel("Andrea Cotes", "5 min ago", "Problem with hot water"));
        utilitiesModelList.add(new UtilitiesModel("Andrea Cotes", "5 min ago", "Problem with hot water"));
        utilitiesModelList.add(new UtilitiesModel("Andrea Cotes", "5 min ago", "Problem with hot water"));
        utilitiesModelList.add(new UtilitiesModel("Andrea Cotes", "5 min ago", "Problem with hot water"));
        utilitiesModelList.add(new UtilitiesModel("Andrea Cotes", "5 min ago", "Problem with hot water"));
        utilitiesModelList.add(new UtilitiesModel("Andrea Cotes", "5 min ago", "Problem with hot water"));
        utilitiesModelList.add(new UtilitiesModel("Andrea Cotes", "5 min ago", "Problem with hot water"));
        utilitiesModelList.add(new UtilitiesModel("Andrea Cotes", "5 min ago", "Problem with hot water"));
        utilitiesModelList.add(new UtilitiesModel("Andrea Cotes", "5 min ago", "Problem with hot water"));

        UtilitiesAdapter utilitiesAdapter = new UtilitiesAdapter(UtilitiesActivity.this, utilitiesModelList);
        Utilities_recycler.setAdapter(utilitiesAdapter);
        Utilities_recycler.setLayoutManager(new LinearLayoutManager(UtilitiesActivity.this, LinearLayoutManager.VERTICAL, false));


    }
}
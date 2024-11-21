package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.MicroATMAdapter;
import pay.scope.payscope.Model.MicroATMModel;
import pay.scope.payscope.R;

public class MicroATMActivity extends AppCompatActivity {
    RecyclerView microATM_recycelr;
    int spanCounts = 2;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_atmactivity);

        microATM_recycelr = findViewById(R.id.microATM_recycelr);
        toolbar = findViewById(R.id.microATM_toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MicroATMActivity.this, QuickViewAllActivity.class));
                finish();
            }
        });


        List<MicroATMModel> microATMModelList = new ArrayList<>();

        microATMModelList.add(new MicroATMModel("Brown-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("Orange-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("White-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("Yellow-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("Brown-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("Orange-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("White-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("Yellow-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("Brown-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("Orange-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("White-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("Yellow-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("Brown-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("Orange-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("White-label ATMs", 123.00));
        microATMModelList.add(new MicroATMModel("Yellow-label ATMs", 123.00));

        MicroATMAdapter atmAdapter = new MicroATMAdapter(MicroATMActivity.this, microATMModelList);
        microATM_recycelr.setAdapter(atmAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(MicroATMActivity.this, spanCounts);
        layoutManager.setSpanCount(spanCounts);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        microATM_recycelr.setLayoutManager(layoutManager);

    }


}
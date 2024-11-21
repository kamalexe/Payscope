package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.ScannerUPiAdapter;
import pay.scope.payscope.Model.ScannerBottomModel;
import pay.scope.payscope.R;

public class ScannerUPIActivity extends AppCompatActivity {
    MaterialToolbar ScannerUPI_toolbar;
    RecyclerView ScannerUPIRecycler;
    int spanCounts = 4;
    ImageView ScannerUPIImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_upiactivity);

        ScannerUPI_toolbar = findViewById(R.id.ScannerUPI_toolbar);
        ScannerUPIImg = findViewById(R.id.ScannerUPIImg);
        ScannerUPIRecycler = findViewById(R.id.ScannerUPIRecycler);

        ScannerUPI_toolbar.setNavigationOnClickListener(v -> finish());

        ScannerUPIImg.setOnClickListener(v -> startActivity(new Intent(ScannerUPIActivity.this, ChatsContactActivity.class)));

        List<ScannerBottomModel> bottomModels = new ArrayList<>();
        bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Anshu Singh", R.color.abc));
        bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Divya Pandey", R.color.abc1));
        bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Supriya Dubey", R.color.abc2));
        bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Ameesha Gupta", R.color.abc3));

        bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Anshu Singh", R.color.abc311));
        bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Divya Pandey", R.color.textblue));
        bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Supriya Dubey", R.color.abc31));

        ScannerUPiAdapter scannerUPiAdapter = new ScannerUPiAdapter(ScannerUPIActivity.this, bottomModels);
        ScannerUPIRecycler.setAdapter(scannerUPiAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(ScannerUPIActivity.this, spanCounts);
        layoutManager.setSpanCount(spanCounts);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        ScannerUPIRecycler.setLayoutManager(layoutManager);

    }
}
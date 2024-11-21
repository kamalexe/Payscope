package pay.scope.payscope.Activity;

import android.os.Bundle;

import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pay.scope.payscope.Adapter.FastTransactionsAdapter;
import pay.scope.payscope.Helper.SpinnerUtils;
import pay.scope.payscope.Model.FastTransactionsModel;
import pay.scope.payscope.R;

public class FastTransactionsActivity extends AppCompatActivity {

    Spinner FasTag_spinner;
    RecyclerView fastTrans_recycler;
    private final String[] string = {"Select", "HDFC Bank", "SBI Bank", "Union Bank",};
    MaterialToolbar FasTagTransaction_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_transactions);

        FasTag_spinner = findViewById(R.id.FasTag_spinner);
        fastTrans_recycler = findViewById(R.id.fastTrans_recycler);
        FasTagTransaction_toolbar = findViewById(R.id.FasTagTransaction_toolbar);

        FasTagTransaction_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(FastTransactionsActivity.this, FasTagPaymentActivity.class));
            finish();
        });

        SpinnerUtils.setUpSpinner(this, FasTag_spinner, Arrays.asList((string)));

        List<FastTransactionsModel> fastTransactionsModelList = new ArrayList<>();
        fastTransactionsModelList.add(new FastTransactionsModel("Recharge", "12 Apr 2024", 123.00));
        fastTransactionsModelList.add(new FastTransactionsModel("Recharge", "12 Apr 2024", 123.00));
        fastTransactionsModelList.add(new FastTransactionsModel("Recharge", "12 Apr 2024", 123.00));
        fastTransactionsModelList.add(new FastTransactionsModel("Recharge", "12 Apr 2024", 123.00));

        FastTransactionsAdapter fastTransactionsAdapter = new FastTransactionsAdapter(FastTransactionsActivity.this, fastTransactionsModelList);
        fastTrans_recycler.setAdapter(fastTransactionsAdapter);

        fastTrans_recycler.setLayoutManager(new LinearLayoutManager(FastTransactionsActivity.this, LinearLayoutManager.VERTICAL, false));


    }
}
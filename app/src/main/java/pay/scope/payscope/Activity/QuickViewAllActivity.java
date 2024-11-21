package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.QuickAllAdapter;
import pay.scope.payscope.Model.HomeModel;
import pay.scope.payscope.R;

public class QuickViewAllActivity extends AppCompatActivity {
    RecyclerView quickViewAll_recycler;
    MaterialToolbar quickViewAll_toolbar;
    int spanCounts = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_view_all);

        quickViewAll_toolbar = findViewById(R.id.quickViewAll_toolbar);
        quickViewAll_recycler = findViewById(R.id.quickViewAll_recycler);

        quickViewAll_toolbar.setNavigationOnClickListener(v -> finish());

        List<HomeModel> quickModelList = new ArrayList<>();
        quickModelList.add(new HomeModel(R.drawable.finger, "Aeps"));
        quickModelList.add(new HomeModel(R.drawable.send, "Send"));
        quickModelList.add(new HomeModel(R.drawable.logo, "Add Bank"));
        quickModelList.add(new HomeModel(R.drawable.scanner, "Scan Pay"));

        quickModelList.add(new HomeModel(R.drawable.logo, "Statement"));
        quickModelList.add(new HomeModel(R.drawable.atm, "Micro ATM"));
        quickModelList.add(new HomeModel(R.drawable.quick_loan, "Quick Loan"));
        quickModelList.add(new HomeModel(R.drawable.move_bank, "Move To Bank"));

        quickModelList.add(new HomeModel(R.drawable.qick_transfer, "Quick\nTransfer"));
        quickModelList.add(new HomeModel(R.drawable.logo, "Add UPI\nWallet"));
        quickModelList.add(new HomeModel(R.drawable.bill_payment, "Bill\nPayment"));
        quickModelList.add(new HomeModel(R.drawable.logo, "Money\nTransfer"));

        quickModelList.add(new HomeModel(R.drawable.logo, "Icici Aeps"));
        quickModelList.add(new HomeModel(R.drawable.aadhar_payment, "Aadhar Payment"));
        quickModelList.add(new HomeModel(R.drawable.logo, "Utilities"));
        quickModelList.add(new HomeModel(R.drawable.logo, "Investment"));


        QuickAllAdapter allAdapter = new QuickAllAdapter(this, quickModelList);
        quickViewAll_recycler.setAdapter(allAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCounts);
        layoutManager.setSpanCount(spanCounts);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        quickViewAll_recycler.setLayoutManager(layoutManager);

    }
}
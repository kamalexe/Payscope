package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.MyWalletAdapter;
import pay.scope.payscope.Model.MyWalletModel;
import pay.scope.payscope.R;

public class MyWalletActivity extends AppCompatActivity {
    MaterialToolbar MyWallet_toolbar;
    RecyclerView MyWallet_Recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);

        MyWallet_Recycler = findViewById(R.id.MyWallet_Recycler);
        MyWallet_toolbar = findViewById(R.id.MyWallet_toolbar);

        MyWallet_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MyWalletActivity.this, MainActivity.class));
                finish();
            }
        });

        List<MyWalletModel> myWalletModelList = new ArrayList<>();
        myWalletModelList.add(new MyWalletModel("Transfer from Devied", "3.00 PM", R.drawable.baseline_arrow_downward_24, 1234.00));
        myWalletModelList.add(new MyWalletModel("Transfer from Devied", "3.00 PM", R.drawable.baseline_arrow_downward_24, 1234.00));
        myWalletModelList.add(new MyWalletModel("Transfer from Devied", "3.00 PM", R.drawable.baseline_arrow_downward_24, 1234.00));
        myWalletModelList.add(new MyWalletModel("Transfer from Devied", "3.00 PM", R.drawable.baseline_arrow_downward_24, 1234.00));
        myWalletModelList.add(new MyWalletModel("Transfer from Devied", "3.00 PM", R.drawable.baseline_arrow_downward_24, 1234.00));
        myWalletModelList.add(new MyWalletModel("Transfer from Devied", "3.00 PM", R.drawable.baseline_arrow_downward_24, 1234.00));
        myWalletModelList.add(new MyWalletModel("Transfer from Devied", "3.00 PM", R.drawable.baseline_arrow_downward_24, 1234.00));

        MyWalletAdapter walletAdapter = new MyWalletAdapter(MyWalletActivity.this, myWalletModelList);
        MyWallet_Recycler.setAdapter(walletAdapter);
        MyWallet_Recycler.setLayoutManager(new LinearLayoutManager(MyWalletActivity.this, LinearLayoutManager.VERTICAL, false));

    }
}
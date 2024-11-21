package pay.scope.payscope.Activity;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.ReceiveMoneyAdapter;
import pay.scope.payscope.Model.ReceiveMoneyModel;
import pay.scope.payscope.R;

public class ReceiveMoneyActivity extends AppCompatActivity {

    RecyclerView receive_recycler;
    MaterialToolbar receiveMoney_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_money);


        receive_recycler = findViewById(R.id.receive_recycler);

        receiveMoney_toolbar = findViewById(R.id.receiveMoney_toolbar);

        receiveMoney_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(ReceiveMoneyActivity.this, MainActivity.class));
            finish();
        });


        List<ReceiveMoneyModel> receiveMoneyModelList = new ArrayList<>();

        receiveMoneyModelList.add(new ReceiveMoneyModel("Transfer from Devid", "4.00 PM", 1234.00, R.drawable.baseline_arrow_downward_24));
        receiveMoneyModelList.add(new ReceiveMoneyModel("Transfer from Devid", "4.00 PM", 1234.00, R.drawable.baseline_arrow_downward_24));
        receiveMoneyModelList.add(new ReceiveMoneyModel("Transfer from Devid", "4.00 PM", 1234.00, R.drawable.baseline_arrow_downward_24));
        receiveMoneyModelList.add(new ReceiveMoneyModel("Transfer from Devid", "4.00 PM", 1234.00, R.drawable.baseline_arrow_downward_24));
        receiveMoneyModelList.add(new ReceiveMoneyModel("Transfer from Devid", "4.00 PM", 1234.00, R.drawable.baseline_arrow_downward_24));
        receiveMoneyModelList.add(new ReceiveMoneyModel("Transfer from Devid", "4.00 PM", 1234.00, R.drawable.baseline_arrow_downward_24));
        receiveMoneyModelList.add(new ReceiveMoneyModel("Transfer from Devid", "4.00 PM", 1234.00, R.drawable.baseline_arrow_downward_24));
        receiveMoneyModelList.add(new ReceiveMoneyModel("Transfer from Devid", "4.00 PM", 1234.00, R.drawable.baseline_arrow_downward_24));
        receiveMoneyModelList.add(new ReceiveMoneyModel("Transfer from Devid", "4.00 PM", 1234.00, R.drawable.baseline_arrow_downward_24));
        receiveMoneyModelList.add(new ReceiveMoneyModel("Transfer from Devid", "4.00 PM", 1234.00, R.drawable.baseline_arrow_downward_24));

        receive_recycler.setLayoutManager(new LinearLayoutManager(ReceiveMoneyActivity.this, LinearLayoutManager.VERTICAL, false));
        ReceiveMoneyAdapter adapter = new ReceiveMoneyAdapter(ReceiveMoneyActivity.this, receiveMoneyModelList);
        receive_recycler.setAdapter(adapter);

    }
}
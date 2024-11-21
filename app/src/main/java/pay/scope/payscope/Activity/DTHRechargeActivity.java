package pay.scope.payscope.Activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.DTHRechargeAdapter;
import pay.scope.payscope.Model.DTHRechargeModel;
import pay.scope.payscope.R;

public class DTHRechargeActivity extends AppCompatActivity {
    RecyclerView dthRecycler;
    MaterialToolbar DTHRecharge_toolbar;
    RadioButton DTHRecharge_TV, DTHRecharge_CableTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dthrecharge);

        dthRecycler = findViewById(R.id.dthRecycler);
        DTHRecharge_toolbar = findViewById(R.id.DTHRecharge_toolbar);
        DTHRecharge_TV = findViewById(R.id.DTHRecharge_TV);
        DTHRecharge_CableTV = findViewById(R.id.DTHRecharge_CableTV);

        DTHRecharge_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(DTHRechargeActivity.this, MainActivity.class));
            finish();
        });

        List<DTHRechargeModel> dthRechargeModelList = new ArrayList<>();
        dthRechargeModelList.add(new DTHRechargeModel(R.drawable.baseline_account_balance_24, "Tata Play"));
        dthRechargeModelList.add(new DTHRechargeModel(R.drawable.baseline_account_balance_24, "Tata Play"));
        dthRechargeModelList.add(new DTHRechargeModel(R.drawable.baseline_account_balance_24, "Tata Play"));
        dthRechargeModelList.add(new DTHRechargeModel(R.drawable.baseline_account_balance_24, "Tata Play"));
        dthRechargeModelList.add(new DTHRechargeModel(R.drawable.baseline_account_balance_24, "Tata Play"));

        DTHRechargeAdapter rechargeAdapter = new DTHRechargeAdapter(DTHRechargeActivity.this, dthRechargeModelList);
        dthRecycler.setAdapter(rechargeAdapter);
        dthRecycler.setLayoutManager(new LinearLayoutManager(DTHRechargeActivity.this, LinearLayoutManager.VERTICAL, false));


        DTHRecharge_TV.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(DTHRechargeActivity.this, R.color.black)));
        DTHRecharge_CableTV.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(DTHRechargeActivity.this, R.color.black)));
        DTHRecharge_TV.setOnCheckedChangeListener(radioButtonListener);
        DTHRecharge_CableTV.setOnCheckedChangeListener(radioButtonListener);
        DTHRecharge_TV.setChecked(true);
    }

    private final CompoundButton.OnCheckedChangeListener radioButtonListener = (buttonView, isChecked) -> {
        if (isChecked) {
            clearAllSelections();
            buttonView.setChecked(true);
            buttonView.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(DTHRechargeActivity.this, R.color.blue)));
        }
    };

    private void clearAllSelections() {
        DTHRecharge_TV.setChecked(false);
        DTHRecharge_CableTV.setChecked(false);

        DTHRecharge_TV.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(DTHRechargeActivity.this, R.color.black)));
        DTHRecharge_CableTV.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(DTHRechargeActivity.this, R.color.black)));

    }


}
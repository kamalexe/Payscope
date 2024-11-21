package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.AddAccountAdapter;
import pay.scope.payscope.Model.AddAccountModel;
import pay.scope.payscope.R;

public class AccountActivity extends AppCompatActivity {

    RecyclerView addBank_recycler;
    Button addAccount;
    MaterialToolbar addAccount_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        addBank_recycler = findViewById(R.id.addBank_recycler);
        addAccount = findViewById(R.id.addAccount);
        addAccount_toolbar = findViewById(R.id.addAccount_toolbar);

        addAccount_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(AccountActivity.this, MainActivity.class));
            finish();
        });

        List<AddAccountModel> addAccountModelList = new ArrayList<>();
        addAccountModelList.add(new AddAccountModel(R.drawable.visa, "SBI", "XXXXXXXXX1234"));
        addAccountModelList.add(new AddAccountModel(R.drawable.baroda, "SBI", "XXXXXXXXX1234"));

        addBank_recycler.setLayoutManager(new LinearLayoutManager(AccountActivity.this, LinearLayoutManager.VERTICAL, false));
        AddAccountAdapter adapter = new AddAccountAdapter(AccountActivity.this, addAccountModelList);
        addBank_recycler.setAdapter(adapter);


        addAccount.setOnClickListener(v -> startActivity(new Intent(AccountActivity.this, AddBankActivity.class)));


    }
}
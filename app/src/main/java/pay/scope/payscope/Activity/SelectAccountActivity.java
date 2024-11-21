package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import pay.scope.payscope.R;

public class SelectAccountActivity extends AppCompatActivity {
    MaterialToolbar selectBank_toolbar;
    MaterialCardView other_cardView;
    Button gotit_Btn;
    RelativeLayout NeedHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_account);

        gotit_Btn = findViewById(R.id.gotit_Btn);
        selectBank_toolbar = findViewById(R.id.selectBank_toolbar);
        other_cardView = findViewById(R.id.other_cardView);
        NeedHelp = findViewById(R.id.NeedHelp);


        selectBank_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(SelectAccountActivity.this, AddBankActivity.class));
                finish();
            }
        });

        gotit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectAccountActivity.this, AddBankActivity.class));
            }
        });

        other_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectAccountActivity.this, AddBankActivity.class));
            }
        });
        NeedHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectAccountActivity.this, HelpActivity.class));
            }
        });

    }
}
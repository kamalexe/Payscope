package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class IRCTCFoodOrderActivity extends AppCompatActivity {
    MaterialToolbar FoodOrder_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irctcfood_order);

        FoodOrder_toolbar = findViewById(R.id.FoodOrder_toolbar);

        FoodOrder_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(IRCTCFoodOrderActivity.this, IRCTCActivity.class));
                finish();
            }
        });
    }
}
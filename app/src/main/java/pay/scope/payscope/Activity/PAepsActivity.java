package pay.scope.payscope.Activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import pay.scope.payscope.Helper.AepsViewpager;
import pay.scope.payscope.R;

public class PAepsActivity extends AppCompatActivity {

    MaterialToolbar PAeps_toolbar;
    TabLayout AepsTabMode;
    ViewPager2 AepsViewpager2;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paeps);

        PAeps_toolbar = findViewById(R.id.PAeps_toolbar);
        AepsTabMode = findViewById(R.id.AepsTabMode);
        AepsViewpager2 = findViewById(R.id.AepsViewpager2);

        PAeps_toolbar.setNavigationOnClickListener(v -> finish());

        AepsViewpager aepsViewpager = new AepsViewpager(this);
        AepsViewpager2.setAdapter(aepsViewpager);

        new TabLayoutMediator(AepsTabMode, AepsViewpager2, (tab, position) -> {

            @SuppressLint("InflateParams")
            View customTab = LayoutInflater.from(this).inflate(R.layout.aeps_custom_tablayout, null);
            TextView tabText = customTab.findViewById(R.id.tabText);


            switch (position) {
                case 0:
                    tabText.setText("Aeps Withdraw");
                    break;
                case 1:
                    tabText.setText("Mini Statement");
                    break;
                case 2:
                    tabText.setText("Balance Enquiry");
                    break;
                case 3:
                    tabText.setText("Aadhar Payment");
                    break;
                case 4:
                    tabText.setText("Aeps Deposit");
                    break;
                default:
                    tabText.setText("Unknown");
                    break;
            }
            tab.setCustomView(customTab);
        }).attach();

        TabLayout.Tab firstTab = AepsTabMode.getTabAt(0);
        if (firstTab != null && firstTab.getCustomView() != null) {
            TextView tabText = firstTab.getCustomView().findViewById(R.id.tabText);
            tabText.setTextColor(ContextCompat.getColor(PAepsActivity.this, R.color.blue));
            tabText.setTypeface(null, Typeface.BOLD);
        }

        AepsTabMode.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    TextView tabText = customView.findViewById(R.id.tabText);
                    tabText.setTextColor(ContextCompat.getColor(PAepsActivity.this, R.color.blue));
                    tabText.setTypeface(null, Typeface.BOLD);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    TextView tabText = customView.findViewById(R.id.tabText);
                    tabText.setTextColor(ContextCompat.getColor(PAepsActivity.this, R.color.lightblack)); // Unselected color
                    tabText.setTypeface(null, Typeface.NORMAL);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // No action needed on reselection
            }
        });

    }
}




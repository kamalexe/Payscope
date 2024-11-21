package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import pay.scope.payscope.Helper.MobileRechargeViewPager;
import pay.scope.payscope.R;

public class MobileRechargeActivity extends AppCompatActivity {
    MaterialToolbar MobileRecharge_toolbar;
    TabLayout MobileRechargeTabMode;
    ViewPager2 MobileRechargeViewpager;
//    FragmentContainerView MobFragment_view;
//    BottomNavigationView MobNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_recharge);

        MobileRechargeTabMode = findViewById(R.id.MobileRechargeTabMode);
        MobileRechargeViewpager = findViewById(R.id.MobileRechargeViewpager);
        MobileRecharge_toolbar = findViewById(R.id.MobileRecharge_toolbar);
//        MobFragment_view = findViewById(R.id.MobFragment_view);
//        MobNavigationView = findViewById(R.id.MobNavigationView);
//
        MobileRecharge_toolbar.setNavigationOnClickListener(v -> finish());

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.MobFragment_view, new PripaidRechargeFragment()).commit();
//
//        MobNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if (id == R.id.Prepaid) {
//                    fragmentManager.beginTransaction().replace(R.id.MobFragment_view, new PripaidRechargeFragment()).commit();
//                }
//                if (id == R.id.Postpaid) {
//                    fragmentManager.beginTransaction().replace(R.id.MobFragment_view, new PostpaidRechargeFragment()).commit();
//                }
//
//                return true;
//            }
//        });
        MobileRechargeViewPager viewPagerAdapter = new MobileRechargeViewPager(MobileRechargeActivity.this);

        MobileRechargeViewpager.setAdapter(viewPagerAdapter);
        MobileRechargeTabMode.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                MobileRechargeViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        MobileRechargeViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Objects.requireNonNull(MobileRechargeTabMode.getTabAt(position)).select();
            }
        });


    }

}

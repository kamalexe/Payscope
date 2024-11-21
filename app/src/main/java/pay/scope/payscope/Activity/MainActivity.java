package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import pay.scope.payscope.Fragment.HomeFragment;
import pay.scope.payscope.Fragment.MyCardsFragment;
import pay.scope.payscope.Fragment.SettingFragment;
import pay.scope.payscope.Fragment.TransactionFragment;
import pay.scope.payscope.R;

public class MainActivity extends AppCompatActivity {
//    NavigationView navigationView;
    MaterialToolbar toolBar;
    TextView layoutTextView;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        navigationView = findViewById(R.id.nav_view);
        toolBar = findViewById(R.id.toolBar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        drawerLayout = findViewById(R.id.drawer);

        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);

//      toolBar.setOnClickListener(v -> drawerLayout.openDrawer(navigationView));
        toolBar.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, DrawerActivity.class)));

        // Find the TextView within the header layout
        /*
        View headerView = navigationView.getHeaderView(0);
        layoutTextView = headerView.findViewById(R.id.name);
        layoutTextView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProfileUpdateActivity.class)));


         */

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_view, new HomeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.homeItme) {
                fragmentManager.beginTransaction().replace(R.id.fragment_view, new HomeFragment()).addToBackStack(null).commit();
            }
            if (id == R.id.transactionItem) {
                fragmentManager.beginTransaction().replace(R.id.fragment_view, new TransactionFragment()).addToBackStack(null).commit();
            }
            if (id == R.id.myCardsItem) {
                fragmentManager.beginTransaction().replace(R.id.fragment_view, new MyCardsFragment()).addToBackStack(null).commit();
            }
            if (id == R.id.settingItem) {
                fragmentManager.beginTransaction().replace(R.id.fragment_view, new SettingFragment()).addToBackStack(null).commit();
            }
            return true;
        });

        toolBar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.search) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
            if (id == R.id.notifications) {
                startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
            }
            return true;
        });

/*
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            if (id == R.id.contact_us) {
                startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
            } else if (id == R.id.drawerScan) {
                startActivity(new Intent(MainActivity.this, ScannerActivity.class));
            } else if (id == R.id.drawerSelf_top_up) {
                startActivity(new Intent(MainActivity.this, SelfTopUpActivity.class));
            } else if (id == R.id.drawerLocator) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            } else if (id == R.id.drawerBudget) {
                startActivity(new Intent(MainActivity.this, BudgetAllActivity.class));
            } else if (id == R.id.share) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Yahan aap apna text daalein");
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            } else if (id == R.id.drawerChatsContact) {
                startActivity(new Intent(MainActivity.this, ChatsContactActivity.class));
            } else if (id == R.id.drawerWallet) {
                startActivity(new Intent(MainActivity.this, MyWalletActivity.class));
            } else if (id == R.id.drawerSecurityCentre) {
                startActivity(new Intent(MainActivity.this, SecurityCentreActivity.class));
            } else if (id == R.id.drawerInvite) {
                startActivity(new Intent(MainActivity.this, InviteEarnActivity.class));
            } else if (id == R.id.drawerPrivacy) {
                startActivity(new Intent(MainActivity.this, LegalPrivacyActivity.class));
            }
            return true;
        });

 */
    }
}


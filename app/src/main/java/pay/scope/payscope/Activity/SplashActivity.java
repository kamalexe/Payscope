package pay.scope.payscope.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import pay.scope.payscope.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    ImageView splashIMg;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashIMg = findViewById(R.id.splashIMg);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

//        new Handler().postDelayed(() -> {
//            if (isLoggedIn) {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            } else {
//                startActivity(new Intent(SplashActivity.this, LoginActivity3.class));
//            }
//            finish();
//        }, 3000);

        new Handler().postDelayed(() -> {

            startActivity(new Intent(SplashActivity.this, MainActivity.class));

            finish();
        }, 3000);


        Animation rotate = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.trans);
        splashIMg.startAnimation(rotate);
    }
}
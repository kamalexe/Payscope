package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class ProfileUpdateActivity extends AppCompatActivity {
    CardView password_change, help_CardView;
    Button UpdateProfile;
    MaterialToolbar ProfileUpdate_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        password_change = findViewById(R.id.password_change);
        UpdateProfile = findViewById(R.id.UpdateProfile);
        help_CardView = findViewById(R.id.help_CardView);

        ProfileUpdate_toolbar = findViewById(R.id.ProfileUpdate_toolbar);
        ProfileUpdate_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ProfileUpdateActivity.this, MainActivity.class));
                finish();

            }
        });

        password_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileUpdateActivity.this, PasswordChangeActivity.class));
            }
        });

        UpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileUpdateActivity.this, ProfileActivity.class));
            }
        });
        help_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileUpdateActivity.this, HelpActivity.class));
            }
        });
    }
}
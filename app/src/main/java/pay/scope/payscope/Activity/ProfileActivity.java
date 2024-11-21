package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class ProfileActivity extends AppCompatActivity {
    MaterialToolbar ProfileEdit_toolbar;
    Button ProfileEditSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ProfileEditSet = findViewById(R.id.ProfileEditSet);
        ProfileEdit_toolbar = findViewById(R.id.ProfileEdit_toolbar);

        ProfileEdit_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ProfileActivity.this, ProfileUpdateActivity.class));
                finish();

            }
        });

        ProfileEditSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ProfileUpdateActivity.class));
                finish();

            }
        });


    }
}
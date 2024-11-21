package pay.scope.payscope.Activity;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class ChatsUserDetailsActivity extends AppCompatActivity {
    MaterialToolbar ChatsUserDetails_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats_user_details);

        ChatsUserDetails_toolbar = findViewById(R.id.ChatsUserDetails_toolbar);

        ChatsUserDetails_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(ChatsUserDetailsActivity.this,ChatsActivity.class));
            finish();
        });

    }
}
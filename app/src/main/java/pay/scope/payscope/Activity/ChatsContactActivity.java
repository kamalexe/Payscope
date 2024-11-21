package pay.scope.payscope.Activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.ChatsContactAdapter;
import pay.scope.payscope.Model.ChatsContactModel;
import pay.scope.payscope.R;

public class ChatsContactActivity extends AppCompatActivity {

    MaterialToolbar ChatsContact_toolbar;
    RecyclerView ChatsContact_Recycler;
    FloatingActionButton ChatsContact_FAB;
    ImageView ChatsContact_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats_contact);

        ChatsContact_Recycler = findViewById(R.id.ChatsContact_Recycler);
        ChatsContact_Search = findViewById(R.id.ChatsContact_Search);
        ChatsContact_toolbar = findViewById(R.id.ChatsContact_toolbar);

        ChatsContact_toolbar.setNavigationOnClickListener(v -> finish());

        ChatsContact_Search.setOnClickListener(v -> startActivity(new Intent(ChatsContactActivity.this, ListActivity.class)));

        ChatsContact_FAB = findViewById(R.id.ChatsContact_FAB);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.baseline_add_comment_24);
        assert drawable != null;
        drawable.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
        ChatsContact_FAB.setImageDrawable(drawable);

//        ChatsContact_FAB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create an Intent to open the contacts app
//                Intent intent = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
//
//                // Check if there's an activity that can handle this intent
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    // If there's an activity, start it
//                    startActivity(intent);
//                } else {
//                    // If there's no activity to handle the intent, you can show a message to the user
//                    Toast.makeText(getApplicationContext(), "No app to handle contacts.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        ChatsContact_FAB.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivity(intent);

        });


        List<ChatsContactModel> chatsContactModelList = new ArrayList<>();
        chatsContactModelList.add(new ChatsContactModel("Anshu Singh", "Hello", "12/3/2024", 1234.00, R.drawable.logo));
        chatsContactModelList.add(new ChatsContactModel("Anshu Singh", "Hello", "12/3/2024", 1234.00, R.drawable.logo));
        chatsContactModelList.add(new ChatsContactModel("Anshu Singh", "Hello", "12/3/2024", 1234.00, R.drawable.logo));
        chatsContactModelList.add(new ChatsContactModel("Anshu Singh", "Hello", "12/3/2024", 1234.00, R.drawable.logo));
        chatsContactModelList.add(new ChatsContactModel("Anshu Singh", "Hello", "12/3/2024", 1234.00, R.drawable.logo));


        ChatsContactAdapter contactAdapter = new ChatsContactAdapter(ChatsContactActivity.this, chatsContactModelList);
        ChatsContact_Recycler.setAdapter(contactAdapter);
        ChatsContact_Recycler.setLayoutManager(new LinearLayoutManager(ChatsContactActivity.this, LinearLayoutManager.VERTICAL, true));


    }
}
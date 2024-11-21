package pay.scope.payscope.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.ChatsAdapter;
import pay.scope.payscope.Model.ChatsModel;
import pay.scope.payscope.R;

public class ChatsActivity extends AppCompatActivity {
    MaterialToolbar Chats_toolbar;
    TextView Chats_PayBtn, Chats_RequestBtn;
    ImageView ChatsAddLayout, ChatsRupee, ChatsCall;
    EditText Chats_MessageBtn;
    RecyclerView Chats_Recycler;
    TextView Chats_UserName;
    private final String phoneNumber = "+91 1234567890";

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        Chats_PayBtn = findViewById(R.id.Chats_PayBtn);
        Chats_RequestBtn = findViewById(R.id.Chats_RequestBtn);
        Chats_MessageBtn = findViewById(R.id.Chats_MessageBtn);
        Chats_Recycler = findViewById(R.id.Chats_Recycler);
        ChatsAddLayout = findViewById(R.id.ChatsAddLayout);
        ChatsRupee = findViewById(R.id.ChatsRupee);
        ChatsCall = findViewById(R.id.ChatsCall);
        Chats_UserName = findViewById(R.id.Chats_UserName);
        Chats_toolbar = findViewById(R.id.Chats_toolbar);

        Chats_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(ChatsActivity.this,ChatsContactActivity.class));
            finish();
        });

        Chats_MessageBtn.setOnClickListener(v -> {
            Chats_PayBtn.setVisibility(View.GONE);
            Chats_RequestBtn.setVisibility(View.GONE);
            Chats_MessageBtn.setVisibility(View.VISIBLE);
            ChatsRupee.setVisibility(View.VISIBLE);
        });

        Chats_UserName.setOnClickListener(v -> startActivity(new Intent(ChatsActivity.this, ChatsUserDetailsActivity.class)));

        List<ChatsModel> chatsModelList = new ArrayList<>();
        chatsModelList.add(new ChatsModel("Hii, My name is Anshu Singh", true));
        chatsModelList.add(new ChatsModel("Hii, My name is Anshu Singh", false));
        chatsModelList.add(new ChatsModel("Hii, My name is Anshu Singh", true));
        chatsModelList.add(new ChatsModel("Hii, My name is Anshu Singh", false));
        chatsModelList.add(new ChatsModel("Hii, My name is Anshu Singh", true));
        ChatsAdapter chatsAdapter = new ChatsAdapter(ChatsActivity.this, chatsModelList);
        chatsAdapter.notifyDataSetChanged();
        Chats_Recycler.setAdapter(chatsAdapter);
        Chats_Recycler.setLayoutManager(new LinearLayoutManager(ChatsActivity.this, LinearLayoutManager.VERTICAL, false));


        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ChatsActivity.this);
        bottomSheetDialog.setContentView(R.layout.bottomsheet);
        ChatsAddLayout.setOnClickListener(v -> bottomSheetDialog.show());

        ChatsRupee.setOnClickListener(v -> startActivity(new Intent(ChatsActivity.this, PayAmountActivity.class)));

        Chats_PayBtn.setOnClickListener(v -> startActivity(new Intent(ChatsActivity.this, PayAmountActivity.class)));

        Chats_RequestBtn.setOnClickListener(v -> {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Send Message");
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        ChatsCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        });


//        Chats_MessageBtn.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // Not used
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // This method is called to notify you that somewhere within s, the text has been changed
//                if (s.length() > 0) {
//                    if (Character.isDigit(s.charAt(0))) {
//                        Toast.makeText(ChatsActivity.this, "Pay", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(ChatsActivity.this, "Name", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                // Not used
//            }
//        });


    }
}
package pay.scope.payscope.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.NotificationsAdapter;
import pay.scope.payscope.Model.NotificationsModel;
import pay.scope.payscope.R;

public class NotificationsActivity extends AppCompatActivity {

    RecyclerView notification_recycler;
    ;
    MaterialToolbar Notifications_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        notification_recycler = findViewById(R.id.notifications_ListView);
        Notifications_toolbar = findViewById(R.id.Notifications_toolbar);

        Notifications_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(NotificationsActivity.this, MainActivity.class));
                finish();
            }
        });


        List<NotificationsModel> notificationsModelList = new ArrayList<>();

        notificationsModelList.add(new NotificationsModel("Denial Alexander", "Your payment of ₹ 1 failed", "23-04-2024", R.drawable.baseline_person_24));
        notificationsModelList.add(new NotificationsModel("Denial Alexander", "Your payment of ₹ 1 failed", "23-04-2024", R.drawable.baseline_person_24));
        notificationsModelList.add(new NotificationsModel("Denial Alexander", "Your payment of ₹ 1 failed", "23-04-2024", R.drawable.baseline_person_24));
        notificationsModelList.add(new NotificationsModel("Denial Alexander", "Your payment of ₹ 1 failed", "23-04-2024", R.drawable.baseline_person_24));

        NotificationsAdapter adapter = new NotificationsAdapter(NotificationsActivity.this, notificationsModelList);
        notification_recycler.setAdapter(adapter);


        notification_recycler.setLayoutManager(new LinearLayoutManager(NotificationsActivity.this, LinearLayoutManager.VERTICAL, false));


    }
}
package pay.scope.payscope.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class SendFeedbackActivity extends AppCompatActivity {
    RatingBar rt;
    Button send_feedbackBtn;
    MaterialToolbar sendFeedback_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);

        rt = findViewById(R.id.ratingBar);
        send_feedbackBtn = findViewById(R.id.send_feedbackBtn);
        sendFeedback_toolbar = findViewById(R.id.sendFeedback_toolbar);

        sendFeedback_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(SendFeedbackActivity.this, MainActivity.class));
            finish();

        });

        LayerDrawable stars = (LayerDrawable) rt.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        rt.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            // Change color based on rating
            int color;
            if (rating > 4) {
                color = Color.BLUE;
            } else if (rating > 2) {
                color = Color.GREEN;
            } else {
                color = Color.RED;
            }
            // Change color of the stars layer
            stars.getDrawable(2).setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        });

        send_feedbackBtn.setOnClickListener(v -> showAlertDialogFeedBack());

    }


    private void showAlertDialogFeedBack() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SendFeedbackActivity.this);
        LayoutInflater inflater = (LayoutInflater) SendFeedbackActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View sendfeedback = inflater.inflate(R.layout.sendfeedback_layout, null);
        builder.setView(sendfeedback);
        builder.setCancelable(false);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView back_Home = sendfeedback.findViewById(R.id.back_Home);
        ImageView clear_img = sendfeedback.findViewById(R.id.clear_img);

        clear_img.setOnClickListener(v -> alertDialog.dismiss());

        back_Home.setOnClickListener(v -> {
            startActivity(new Intent(SendFeedbackActivity.this, MainActivity.class));
            finish();
        });
    }
}

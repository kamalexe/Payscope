package pay.scope.payscope.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class ContactUsActivity extends AppCompatActivity {
    MaterialToolbar contactUs_toolbar;
    ImageView instagram, facebook, twitter, linkedin;
    TextView sendCall, sendEmail, sendAddress;
    private final String phoneNumber = "+91 1234567890";
    private final String emailId = "abcd1234@gmail.com";
    private final String linkedinProfileUrl = "https://www.linkedin.com/in/example"; // Replace with the actual LinkedIn profile URL
    private final String instagramProfileUrl = "https://www.instagram.com/example1.0/";
    private final String facebookProfileUrl = "https://www.facebook.com/in/example";
    private final String twitterProfileUrl = "https://www.twitter.com/in/example";
    private final String AddressUrl = "https://www.google.com/maps/place/DLF+MyPad/@26.8657996,81.0037812,17z/data=!3m1!4b1!4m6!3m5!1s0x399be2bef8e5f9d3:0xa0cd5cc1e323292c!8m2!3d26.8657996!4d81.0063561!16s%2Fg%2F1hm1w1q4g?entry=ttu"; // Replace with the actual LinkedIn profile URL


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        instagram = findViewById(R.id.instagram);
        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);
        linkedin = findViewById(R.id.linkedin);
        sendCall = findViewById(R.id.sendCall);
        sendEmail = findViewById(R.id.sendEmail);
        sendAddress = findViewById(R.id.sendAddress);


        contactUs_toolbar = findViewById(R.id.contactUs_toolbar);
        contactUs_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(ContactUsActivity.this, MainActivity.class));
            finish();

        });


        instagram.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramProfileUrl));
            startActivity(intent);
        });

        facebook.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookProfileUrl));
            startActivity(intent);
        });

        twitter.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterProfileUrl));
            startActivity(intent);
        });

        linkedin.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinProfileUrl));
            startActivity(intent);

        });

        sendCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        });


        sendEmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + emailId));
            startActivity(intent);
        });

        sendAddress.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AddressUrl));
            startActivity(intent);

        });

    }
}
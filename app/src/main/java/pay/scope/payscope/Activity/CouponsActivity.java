package pay.scope.payscope.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.CouponAdapter;

import pay.scope.payscope.Model.CouponModel;
import pay.scope.payscope.R;

public class CouponsActivity extends AppCompatActivity {
    MaterialToolbar Coupons_toolbar;
    RecyclerView coupons_recycler;
    private EditText referralCodeEditText;
    TextView copyText;
    ImageView copyImg;
    Button applyReferralButton;
    private static final String REFERRAL_CODE = "FNPR15RG"; // F N P R 1 5 R G

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);

        referralCodeEditText = findViewById(R.id.referralCodeEditText);
        applyReferralButton = findViewById(R.id.applyReferralButton);
        coupons_recycler = findViewById(R.id.coupons_recycler);
        Coupons_toolbar = findViewById(R.id.Coupons_toolbar);
        copyText = findViewById(R.id.copyText);
        copyImg = findViewById(R.id.copyImg);

        Coupons_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(CouponsActivity.this, MainActivity.class));
            finish();
        });


        applyReferralButton.setOnClickListener(v -> {
            String enteredCode = referralCodeEditText.getText().toString().trim();

            if (enteredCode.equals(REFERRAL_CODE)) {

//                    referralStatusTextView.setText("Referral code applied successfully!");
                Toast.makeText(CouponsActivity.this, "Referral code applied successfully!", Toast.LENGTH_SHORT).show();
                // Implement logic to reward the referrer and referee here
            } else {

//                    referralStatusTextView.setText("Invalid referral code. Please try again.");
                Toast.makeText(CouponsActivity.this, "Invalid referral code. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });


        List<CouponModel> couponModelList = new ArrayList<>();

        couponModelList.add(new CouponModel("Gift Card Value of ₹50 or 10% off at Mcdonolds .", "23 Aug 2003", "Terms & Condition Apply", "100", R.drawable.baroda, "6GHAbs132"));
        couponModelList.add(new CouponModel("Gift Card Value of ₹50 or 10% off at Mcdonolds .", "23 Aug 2003", "Terms & Condition Apply", "100", R.drawable.logo, "6GHAbs132"));
        couponModelList.add(new CouponModel("Gift Card Value of ₹50 or 10% off at Mcdonolds .", "23 Aug 2003", "Terms & Condition Apply", "100", R.drawable.union, "6GHAbs132"));
        couponModelList.add(new CouponModel("Gift Card Value of ₹50 or 10% off at Mcdonolds .", "23 Aug 2003", "Terms & Condition Apply", "100", R.drawable.sbi, "6GHAbs132"));
        couponModelList.add(new CouponModel("Gift Card Value of ₹50 or 10% off at Mcdonolds .", "23 Aug 2003", "Terms & Condition Apply", "100", R.drawable.baroda, "6GHAbs132"));

        CouponAdapter couponAdapter = new CouponAdapter(CouponsActivity.this, couponModelList);
        coupons_recycler.setAdapter(couponAdapter);
        coupons_recycler.setLayoutManager(new LinearLayoutManager(CouponsActivity.this, LinearLayoutManager.VERTICAL, false));


        copyImg.setOnClickListener(v -> {
            String textToCopy = copyText.getText().toString();

            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

            ClipData clipData = ClipData.newPlainText("text", textToCopy);
            clipboardManager.setPrimaryClip(clipData);

            Toast.makeText(getApplicationContext(), "Copy", Toast.LENGTH_SHORT).show();
        });


    }
}
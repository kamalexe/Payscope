package pay.scope.payscope.Activity;

import static pay.scope.payscope.Activity.LoginActivity3.KEY_USER_NAME;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import pay.scope.payscope.R;

public class UsersQRActivity extends AppCompatActivity {
    ImageView userIdCopy, usersQrDownload, usersQrGenerate;
    MaterialToolbar usersQrClear;
    TextView userIdText, qrUserName;
    NestedScrollView userQrNested;
    LinearLayout userQrLinear;
    RelativeLayout usersQrRelative;
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1;
    CardView usersQrOpenScanner, usersQrShare;
    public static String paymentData;

    public static SharedPreferences preferences;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    public static String loginUserId;
    public static String userUpiId;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_qractivity);

        userIdCopy = findViewById(R.id.user_id_copy);
        usersQrClear = findViewById(R.id.user_qr_clear);
        userIdText = findViewById(R.id.user_id_text);
        usersQrOpenScanner = findViewById(R.id.user_qr_open_scanner);
        usersQrShare = findViewById(R.id.user_qr_share);
        usersQrDownload = findViewById(R.id.user_qr_download);
        userQrNested = findViewById(R.id.user_qr_nested);
        userQrLinear = findViewById(R.id.user_qr_linear);
        usersQrRelative = findViewById(R.id.user_qr_relative);
        usersQrGenerate = findViewById(R.id.user_qr_generate);
        qrUserName = findViewById(R.id.qr_user_name);

        preferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        loginUserId = preferences.getString(KEY_USER_NAME, "");
        Log.d("UserNames", loginUserId);

        userUpiId = loginUserId + "@okhdfcbank";
        userIdText.setText(userUpiId);

        qrUserName.setText("AnshuSingh");
        generateQRCode();


        usersQrClear.setNavigationOnClickListener(v -> finish());

        userIdCopy.setOnClickListener(v -> {
            String textToCopy = userIdText.getText().toString();
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("text", textToCopy);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(getApplicationContext(), "Copy", Toast.LENGTH_SHORT).show();
        });

        usersQrOpenScanner.setOnClickListener(v -> startActivity(new Intent(UsersQRActivity.this, ScannerActivity.class)));


        usersQrShare.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(UsersQRActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UsersQRActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
            } else {
                usersQrClear.setVisibility(View.GONE);
                userQrLinear.setVisibility(View.GONE);
                usersQrRelative.setVisibility(View.GONE);
                shareQrView();
            }
        });

        usersQrDownload.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(UsersQRActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UsersQRActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
            } else {
                saveQrToGallery();
            }
        });

        String scannedData = getIntent().getStringExtra("SCANNED_DATA");
        if (scannedData != null) {
            HashMap<String, String> parsedData = parseUpiData(scannedData);
            displayParsedData(parsedData);
        }

    }

    private HashMap<String, String> parseUpiData(String data) {
        HashMap<String, String> parsedData = new HashMap<>();
        String[] params = data.split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2) {
                parsedData.put(keyValue[0], keyValue[1]);
            }
        }
        return parsedData;
    }

    private void displayParsedData(HashMap<String, String> parsedData) {
        userIdText.setText(parsedData.getOrDefault("upi://pay?pa", "N/A"));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveQrToGallery();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveQrToGallery() {
        Bitmap bitmap = getBitmapFromView(userQrNested);
        File imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "user_qr.png");

        try (FileOutputStream fileOutputStream = new FileOutputStream(imageFile)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            Toast.makeText(this, "QR code saved to gallery", Toast.LENGTH_SHORT).show();
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(imageFile);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving QR code", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    private void shareQrView() {
        Bitmap bitmap = getBitmapFromView(usersQrRelative);
        File imageFile = new File(getCacheDir(), "shared_user_qr.png");

        try (FileOutputStream fileOutputStream = new FileOutputStream(imageFile)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

            Uri imageUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileprovider", imageFile);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/png");
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "");
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error sharing QR code", Toast.LENGTH_SHORT).show();
        }
    }

/*
    private void generateQRCode() {
        paymentData = "upi://pay?pa=" + userUpiId + "&pn=" + qrUserName.getText().toString() + "&am=&cu=INR";

        @SuppressLint("UseCompatLoadingForDrawables") Bitmap logo = ((BitmapDrawable) getResources().getDrawable(R.drawable.visa)).getBitmap();

        QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
        try {
            Bitmap qrCode = qrCodeGenerator.generateQRCode(paymentData, logo);
            usersQrGenerate.setImageBitmap(qrCode);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

 */



    private void generateQRCode() {
//        paymentData = "upi://pay?pa=" + userUpiId + "&pn=" + name + "&am=" + amount + "&cu=INR";
        paymentData = "upi://pay?pa=" + userUpiId + "&pn=" + qrUserName.getText().toString() + "&am=&cu=INR";

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(paymentData, BarcodeFormat.QR_CODE, 300, 300);

            usersQrGenerate.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


}

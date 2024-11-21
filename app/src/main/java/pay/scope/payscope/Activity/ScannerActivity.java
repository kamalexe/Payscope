package pay.scope.payscope.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.HybridBinarizer;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import pay.scope.payscope.Adapter.ScannerBottomAdapter;
import pay.scope.payscope.Model.ScannerBottomModel;
import pay.scope.payscope.R;

public class ScannerActivity extends AppCompatActivity {
    private static final int SELECT_PICTURE = 102;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 101;
    TextView ScannerImgUpload;
    private DecoratedBarcodeView barcodeView;
    private boolean isFlashOn = false;
    ImageView ScannerClear, flashlightButton, ScannerQRUsers, ScannerGalleryImg;
    BottomSheetDialog bottomSheetDialogSheet;
    public final int spanCounts = 4;
    private String scannedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        ScannerImgUpload = findViewById(R.id.ScannerImgUpload);
        barcodeView = findViewById(R.id.barcode_scanner);
        flashlightButton = findViewById(R.id.flashlightButton);
        ScannerQRUsers = findViewById(R.id.ScannerQRUsers);
        ScannerClear = findViewById(R.id.ScannerClear);
        ScannerGalleryImg = findViewById(R.id.ScannerGalleryImg);


        bottomSheetDialogSheet = new BottomSheetDialog(ScannerActivity.this);
        bottomSheetDialogSheet.setContentView(R.layout.scanner_botomsheet);
        bottomSheetDialogSheet.show();
        initializeMonthsRecyclerView();

        barcodeView.getStatusView().setVisibility(View.GONE);


        flashlightButton.setOnClickListener(v -> toggleFlashlight());

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startScanner();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);}


        ScannerQRUsers.setOnClickListener(v -> {
            Intent intent = new Intent(ScannerActivity.this, UsersQRActivity.class);
            startActivity(intent);
//                if (scannedData != null && !scannedData.isEmpty()) {
//                    Intent intent = new Intent(ScannerActivity.this, UsersQRActivity.class);
//                    intent.putExtra("SCANNED_DATA", scannedData);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(ScannerActivity.this, "No QR code scanned yet", Toast.LENGTH_SHORT).show();
//                }
        });



        ScannerImgUpload.setOnClickListener(arg0 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
        });

        ScannerClear.setOnClickListener(v -> finish());

    }


    private void startScanner() {
        barcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                Result rawResult = result.getResult();
                if (rawResult != null) {
                    scannedData = rawResult.getText();
//                    handleQRCodeResult(scannedData);
                    Intent intent = new Intent(ScannerActivity.this, ScannerDetailsActivity.class);
                    intent.putExtra("SCANNED_DATA", scannedData);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {
                // Do something with the possible result points
            }
        });
    }

    private void toggleFlashlight() {
        if (isFlashOn) {
            barcodeView.setTorchOff();
            flashlightButton.setImageResource(R.drawable.baseline_flashlight_off_24);
        } else {
            barcodeView.setTorchOn();
            flashlightButton.setImageResource(R.drawable.baseline_flashlight_on_24);
        }
        isFlashOn = !isFlashOn;
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScanner();
            } else {
                Toast.makeText(this, "Camera permission is required to use the scanner", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                String selectedImagePath = getPath(selectedImageUri);
                if (selectedImagePath != null) {
                    Log.d("ScannerActivity", "Selected Image Path: " + selectedImagePath);
//                    ScannerGalleryImg.setImageURI(selectedImageUri);
                    decodeQRCodeFromImage(selectedImageUri);
                }
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String imagePath = cursor.getString(column_index);
            cursor.close();
            return imagePath;
        } else {
            return null;
        }
    }

    private void decodeQRCodeFromImage(Uri imageUri) {
        try {
            InputStream imageStream = getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            int[] intArray = new int[bitmap.getWidth() * bitmap.getHeight()];
            bitmap.getPixels(intArray, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] pixels = new int[width * height];
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

            RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(binaryBitmap);
            handleQRCodeResult(result.getText());
        } catch (Exception e) {
            Log.e("ScannerActivity", "Error decoding QR code from image", e);
            Toast.makeText(this, "Failed to decode QR code from image", Toast.LENGTH_SHORT).show();
        }
    }


    private void handleQRCodeResult(String resultText) {
//        Toast.makeText(this, "Scanned from Image: " + resultText, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ScannerActivity.this, ScannerDetailsActivity.class);
        intent.putExtra("SCANNED_DATA", resultText);
        startActivity(intent);
        finish();
    }

    private void initializeMonthsRecyclerView() {
        RecyclerView recyclerView = bottomSheetDialogSheet.findViewById(R.id.ScannerBottom_recycler);
        TextInputEditText ScannerBottomEditText = bottomSheetDialogSheet.findViewById(R.id.ScannerBottomEditText);

        if (recyclerView != null) {
            List<ScannerBottomModel> bottomModels = new ArrayList<>();

            bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Anshu Singh", R.color.abc));
            bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Divya Pandey", R.color.abc1));
            bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Supriya Dubey", R.color.abc2));
            bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Ameesha Gupta", R.color.abc3));

            bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Anshu Singh", R.color.abc311));
            bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Anshu Singh", R.color.abc31));
            bottomModels.add(new ScannerBottomModel(R.drawable.baseline_format_bold_24, "Anshu Singh", R.color.abc1));

            ScannerBottomAdapter bottomAdapter = new ScannerBottomAdapter(ScannerActivity.this, bottomModels);
            recyclerView.setAdapter(bottomAdapter);

            GridLayoutManager layoutManager = new GridLayoutManager(ScannerActivity.this, spanCounts);
            layoutManager.setSpanCount(spanCounts);
            layoutManager.setOrientation(GridLayoutManager.VERTICAL);

            recyclerView.setLayoutManager(layoutManager);

        }

        assert ScannerBottomEditText != null;
        ScannerBottomEditText.setOnClickListener(v -> startActivity(new Intent(ScannerActivity.this, ScannerUPIActivity.class)));
        bottomSheetDialogSheet.show();
    }

}

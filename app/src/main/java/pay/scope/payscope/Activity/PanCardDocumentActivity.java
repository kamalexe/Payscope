package pay.scope.payscope.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.Arrays;

import pay.scope.payscope.Helper.SpinnerUtils;
import pay.scope.payscope.R;

public class PanCardDocumentActivity extends AppCompatActivity {
    Spinner Identity_spinner, select_document_spinner;
    ImageView addressImg, identityImg;
    String[] Identity_ID = {"Select", "Passport", "Pan Card"};
    String[] Identity_Document = {"Select document type", "Passport", "Pan Card"};
    MaterialToolbar DocumentSubmission_toolbar;
    private static final int SELECT_ADDRESS_PICTURE = 1;
    private static final int SELECT_IDENTITY_PICTURE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pan_card_document);

        Identity_spinner = findViewById(R.id.Identity_spinner);
        select_document_spinner = findViewById(R.id.select_document_spinner);
        addressImg = findViewById(R.id.addressImg);
        identityImg = findViewById(R.id.identityImg);

        DocumentSubmission_toolbar = findViewById(R.id.DocumentSubmission_toolbar);

        DocumentSubmission_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(PanCardDocumentActivity.this, PenCardServiceActivity.class));
            finish();
        });

        SpinnerUtils.setUpSpinner(this, Identity_spinner, Arrays.asList(Identity_ID));
        SpinnerUtils.setUpSpinner(this, select_document_spinner, Arrays.asList(Identity_Document));

        addressImg.setOnClickListener(arg0 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Address Picture"), SELECT_ADDRESS_PICTURE);
        });

        identityImg.setOnClickListener(arg0 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Identity Picture"), SELECT_IDENTITY_PICTURE);
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            String selectedImagePath = getPath(selectedImageUri);
            System.out.println("Image Path: " + selectedImagePath);
            if (requestCode == SELECT_ADDRESS_PICTURE) {
                addressImg.setImageURI(selectedImageUri);
            } else if (requestCode == SELECT_IDENTITY_PICTURE) {
                identityImg.setImageURI(selectedImageUri);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close(); // Close the cursor
            return path;
        }
        return null;
    }
}
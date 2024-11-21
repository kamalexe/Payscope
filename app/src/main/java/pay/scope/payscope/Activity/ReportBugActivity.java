package pay.scope.payscope.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import pay.scope.payscope.R;

public class ReportBugActivity extends AppCompatActivity {
    MaterialToolbar reportBug_toolbar;
    LinearLayout addText_gallery;
    ImageView addImg_gallery, report_qrScanner;
    private static final int SELECT_PICTURE = 1;
    CheckBox reportBug_CheckBox1, reportBug_CheckBox2, reportBug_CheckBox3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_bug);

        reportBug_toolbar = findViewById(R.id.reportBug_toolbar);
        addImg_gallery = findViewById(R.id.addImg_gallery);
        addText_gallery = findViewById(R.id.addText_gallery);
        report_qrScanner = findViewById(R.id.report_qrScanner);
        reportBug_CheckBox1 = findViewById(R.id.reportBug_CheckBox1);
        reportBug_CheckBox2 = findViewById(R.id.reportBug_CheckBox2);
        reportBug_CheckBox3 = findViewById(R.id.reportBug_CheckBox3);


        reportBug_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(ReportBugActivity.this, MainActivity.class));
            finish();
        });

        report_qrScanner.setOnClickListener(v -> startActivity(new Intent(ReportBugActivity.this, ScannerActivity.class)));


        addText_gallery.setOnClickListener(arg0 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                String selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                addImg_gallery.setImageURI(selectedImageUri);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


}
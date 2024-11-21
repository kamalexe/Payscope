package pay.scope.payscope.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.InvoiceAdapter;
import pay.scope.payscope.Model.InvoiceModel;
import pay.scope.payscope.R;

public class InvoiceActivity extends AppCompatActivity {

    MaterialToolbar Invoice_toolbar;
    RecyclerView Invoice_Recycler;
    Button Invoice_DownloadBtn;
    private PdfDocument document;
    LinearLayout invoiceLinearLayout;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == CREATE_FILE && resultCode == Activity.RESULT_OK) {
            Uri uri;
            if (resultData != null) {
                uri = resultData.getData();

                if (document != null) {
                    ParcelFileDescriptor pdf;
                    try {
                        pdf = getContentResolver().openFileDescriptor(uri, "w");
                        FileOutputStream fileOutputStream = new FileOutputStream(pdf.getFileDescriptor());
                        document.writeTo(fileOutputStream);
                        document.close();
                        Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        try {
                            DocumentsContract.deleteDocument(getContentResolver(), uri);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        Invoice_Recycler = findViewById(R.id.Invoice_Recycler);
        Invoice_DownloadBtn = findViewById(R.id.Invoice_DownloadBtn);
        invoiceLinearLayout = findViewById(R.id.InvoiceLinearLayout);
        Invoice_toolbar = findViewById(R.id.Invoice_toolbar);

        Invoice_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(InvoiceActivity.this, BillSuccessfullActivity.class));
            finish();
        });

        List<InvoiceModel> invoiceModelList = new ArrayList<>();
        invoiceModelList.add(new InvoiceModel(1, "BrochureDesign", "1.00", "4,000.00", 654, 000.00));
        invoiceModelList.add(new InvoiceModel(2, "BrochureDesign", "1.00", "4,000.00", 654, 000.00));
        invoiceModelList.add(new InvoiceModel(3, "BrochureDesign", "1.00", "4,000.00", 464, 000.00));
        invoiceModelList.add(new InvoiceModel(4, "BrochureDesign", "1.00", "4,000.00", 754, 000.00));
        invoiceModelList.add(new InvoiceModel(5, "BrochureDesign", "1.00", "4,000.00", 467, 000.00));
        invoiceModelList.add(new InvoiceModel(5, "BrochureDesign", "1.00", "4,000.00", 467, 000.00));


        InvoiceAdapter invoiceAdapter = new InvoiceAdapter(InvoiceActivity.this, invoiceModelList);
        Invoice_Recycler.setAdapter(invoiceAdapter);
        Invoice_Recycler.setLayoutManager(new LinearLayoutManager(InvoiceActivity.this, LinearLayoutManager.VERTICAL, false));


        Invoice_DownloadBtn.setOnClickListener(this::createPdfView);
    }


    public void createPdfView(View view) {
        final Dialog invoicedialog = new Dialog(this);
        invoicedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        invoicedialog.setContentView(R.layout.activity_invoice); // Correct layout inflation
        invoicedialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(invoicedialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        invoicedialog.getWindow().setAttributes(lp);
        invoiceLinearLayout = invoicedialog.findViewById(R.id.InvoiceLinearLayout);
        invoicedialog.show();

        Button Invoice_DownloadBtn = invoicedialog.findViewById(R.id.Invoice_DownloadBtn);
        Invoice_DownloadBtn.setOnClickListener(v -> {
            Invoice_DownloadBtn.setVisibility(View.GONE);
            generatePdfFromView(invoiceLinearLayout);
        });
    }

    private static final int CREATE_FILE = 1;

    private void createFile() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pgf"); // Corrected type
        intent.putExtra(Intent.EXTRA_TITLE, "invoice.pdf");
        startActivityForResult(intent, CREATE_FILE);
    }


    private void generatePdfFromView(View view) {
        Bitmap bitmap = getBitmapFromView(view);
        document = new PdfDocument();
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page myPage = document.startPage(mypageInfo);

        Canvas canvas = myPage.getCanvas();
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(myPage);
        createFile();


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


}


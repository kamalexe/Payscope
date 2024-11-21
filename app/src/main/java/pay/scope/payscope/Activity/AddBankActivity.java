package pay.scope.payscope.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.AllBankAdapter;
import pay.scope.payscope.Adapter.PopularBankAdapter;
import pay.scope.payscope.Model.AllBankModel;
import pay.scope.payscope.Model.PopularBankModel;
import pay.scope.payscope.R;

public class AddBankActivity extends AppCompatActivity {
    RecyclerView allBank_recycler, popularBank_recycler;
    EditText accountDetails;
    MaterialToolbar select_toolbar;
    ImageView accountDetailsMic;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank);

        allBank_recycler = findViewById(R.id.allBank_recycler);
        popularBank_recycler = findViewById(R.id.popularBank_recycler);
        accountDetails = findViewById(R.id.accountDetails);
        accountDetailsMic = findViewById(R.id.accountDetailsMic);
        select_toolbar = findViewById(R.id.select_toolbar);


        select_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent( AddBankActivity.this,AccountActivity.class));
            finish();
        });


        List<AllBankModel> allBankModelList = new ArrayList<>();
        allBankModelList.add(new AllBankModel("Union Bank Of India", R.drawable.union));
        allBankModelList.add(new AllBankModel("State Bank of India", R.drawable.sbi));
        allBankModelList.add(new AllBankModel("Canara Bank", R.drawable.canra));
        allBankModelList.add(new AllBankModel("Punjab National Bank", R.drawable.panjab));
        allBankModelList.add(new AllBankModel("Yes Bank", R.drawable.yse));
        allBankModelList.add(new AllBankModel("Bank Of Baroda", R.drawable.baroda));
        allBankModelList.add(new AllBankModel("ICICI Bank", R.drawable.icici));
        allBankModelList.add(new AllBankModel("HDFC Bank", R.drawable.hdfc));
        allBankModelList.add(new AllBankModel("Axis Bank", R.drawable.axic));
        // Set up RecyclerView
        allBank_recycler.setLayoutManager(new LinearLayoutManager(AddBankActivity.this, LinearLayoutManager.VERTICAL, false));
        AllBankAdapter adapter = new AllBankAdapter(AddBankActivity.this, allBankModelList);
        allBank_recycler.setAdapter(adapter);


        List<PopularBankModel> popularBankModelList = new ArrayList<>();
        popularBankModelList.add(new PopularBankModel("Union Bank Of India", R.drawable.union));
        popularBankModelList.add(new PopularBankModel("State Bank of India", R.drawable.sbi));
        popularBankModelList.add(new PopularBankModel("Canara Bank", R.drawable.canra));
        popularBankModelList.add(new PopularBankModel("Punjab National Bank", R.drawable.panjab));
        popularBankModelList.add(new PopularBankModel("Yes Bank", R.drawable.yse));
        popularBankModelList.add(new PopularBankModel("Bank Of Baroda", R.drawable.baroda));
        popularBankModelList.add(new PopularBankModel("ICICI Bank", R.drawable.icici));
        popularBankModelList.add(new PopularBankModel("HDFC Bank", R.drawable.hdfc));
        popularBankModelList.add(new PopularBankModel("Axis Bank", R.drawable.axic));

        popularBank_recycler.setLayoutManager(new GridLayoutManager(AddBankActivity.this, 3));
        PopularBankAdapter popularBankAdapter = new PopularBankAdapter(AddBankActivity.this, popularBankModelList);
        popularBank_recycler.setAdapter(popularBankAdapter);


        accountDetailsMic.setOnClickListener(v -> {
            if (checkPermission()) {
                displaySpeechRecognizer();
            } else {
                requestPermission();
            }            });


    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displaySpeechRecognizer();
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now");
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SPEECH_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (results != null && !results.isEmpty()) {
                    Log.d("MainActivity", "Recognized Speech: " + results.get(0));
                    accountDetails.setText(results.get(0));
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.e("MainActivity", "Speech recognition was canceled");
                Toast.makeText(this, "Speech recognition was canceled. Please try again.", Toast.LENGTH_SHORT).show();
            } else {
                Log.e("MainActivity", "Speech recognition failed. Code: " + resultCode);
                Toast.makeText(this, "Speech recognition failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
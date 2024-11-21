package pay.scope.payscope.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import pay.scope.payscope.R;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    SearchView searchView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    ImageView searchViewMic,ListBack;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);
        searchViewMic = findViewById(R.id.searchViewMic);
        ListBack = findViewById(R.id.ListBack);

        ListBack.setOnClickListener(v -> finish());

        arrayList = new ArrayList<>();
        arrayList.add("Add Bank");
        arrayList.add("Scan Pay");
        arrayList.add("Money Transfer");
        arrayList.add("Send");
        arrayList.add("Receive");
        arrayList.add("Move To Bank");
        arrayList.add("Quick Transfer");
        arrayList.add("Add UPI Wallet");

        arrayAdapter = new ArrayAdapter<>(ListActivity.this, R.layout.search_list, R.id.SearchListText, arrayList);
        listView.setAdapter(arrayAdapter);

        customizeSearchView(searchView);


        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = arrayAdapter.getItem(position);
            if (selectedItem != null) {
                switch (selectedItem) {
                    case "Add Bank":
                        startActivity(new Intent(ListActivity.this, AddBankActivity.class));
                        break;
                    case "Scan Pay":
                        startActivity(new Intent(ListActivity.this, ScannerActivity.class));
                        break;
                    case "Money Transfer":
                        startActivity(new Intent(ListActivity.this, MoneyTransferActivity.class));
                        break;
                    case "Send":
                        startActivity(new Intent(ListActivity.this, SendMoneyActivity.class));
                        break;
                    case "Receive":
                        startActivity(new Intent(ListActivity.this, ReceiveMoneyActivity.class));
                        break;
                    case "Move To Bank":
                        startActivity(new Intent(ListActivity.this, MoveToBankActivity.class));
                        break;
                    case "Quick Transfer":
                        startActivity(new Intent(ListActivity.this, QuickTransferActivity.class));
                        break;
                    case "Add UPI Wallet":
                        startActivity(new Intent(ListActivity.this, AddUPIActivity.class));
                        break;
                    default:
                        startActivity(new Intent(ListActivity.this, ListActivity.class));
                        break;
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    listView.setVisibility(View.GONE);
                } else {
                    listView.setVisibility(View.VISIBLE);
                    arrayAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });

        searchViewMic.setOnClickListener(v -> {
            if (checkPermission()) {
                displaySpeechRecognizer();
            } else {
                requestPermission();
            }
        });
    }
    private void customizeSearchView(SearchView searchView) {
        try {
            Field queryTextViewField = SearchView.class.getDeclaredField("mSearchSrcTextView");
            queryTextViewField.setAccessible(true);
            TextView queryTextView = (TextView) queryTextViewField.get(searchView);
            assert queryTextView != null;
            queryTextView.setTextSize(14);
            queryTextView.setHintTextColor(ContextCompat.getColor(ListActivity.this, R.color.hint));
            queryTextView.setTextColor(ContextCompat.getColor(ListActivity.this, R.color.black));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displaySpeechRecognizer();
            }
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION_CODE);
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
                    searchView.setQuery(results.get(0), false);
                }
            }
        }
    }
}


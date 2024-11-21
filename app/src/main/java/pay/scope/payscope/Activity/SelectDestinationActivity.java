package pay.scope.payscope.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.StateDetailsAdapter;
import pay.scope.payscope.Adapter.StateNameAdapter;
import pay.scope.payscope.Model.StateDetailsModel;
import pay.scope.payscope.Model.StateNameModel;
import pay.scope.payscope.R;

public class SelectDestinationActivity extends AppCompatActivity {
    RecyclerView stateRecycler, stateDetailsRecycler;
    int spanCount = 2;
    MaterialToolbar SelectDestination_toolbar;
    SearchView DestinationSearchView;
    ImageView DestinationMic;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_destination);

        stateRecycler = findViewById(R.id.stateRecycler);
        stateDetailsRecycler = findViewById(R.id.stateDetailsRecycler);
        DestinationSearchView = findViewById(R.id.DestinationSearchView);
        DestinationMic = findViewById(R.id.DestinationMic);
        SelectDestination_toolbar = findViewById(R.id.SelectDestination_toolbar);

        SelectDestination_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(SelectDestinationActivity.this, FlightBookingActivity.class));
            finish();
        });

        List<StateNameModel> stateNameModelList = new ArrayList<>();
        stateNameModelList.add(new StateNameModel("Lucknow", R.drawable.logo));
        stateNameModelList.add(new StateNameModel("Lucknow", R.drawable.applogo));
        stateNameModelList.add(new StateNameModel("Lucknow", R.drawable.axic));
        stateNameModelList.add(new StateNameModel("Lucknow", R.drawable.baroda));
        stateNameModelList.add(new StateNameModel("Lucknow", R.drawable.baseline_account_balance_24));

        StateNameAdapter nameAdapter = new StateNameAdapter(SelectDestinationActivity.this, stateNameModelList);
        stateRecycler.setAdapter(nameAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(SelectDestinationActivity.this, spanCount);
        layoutManager.setSpanCount(spanCount);
        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        stateRecycler.setLayoutManager(layoutManager);


        List<StateDetailsModel> stateDetailsModelList = new ArrayList<>();
        stateDetailsModelList.add(new StateDetailsModel("Delhi,India", "Indira Gandhi Airport", "DEL"));
        stateDetailsModelList.add(new StateDetailsModel("Delhi,India", "Indira Gandhi Airport", "DEL"));
        stateDetailsModelList.add(new StateDetailsModel("Delhi,India", "Indira Gandhi Airport", "DEL"));
        stateDetailsModelList.add(new StateDetailsModel("Delhi,India", "Indira Gandhi Airport", "DEL"));
        stateDetailsModelList.add(new StateDetailsModel("Delhi,India", "Indira Gandhi Airport", "DEL"));
        stateDetailsModelList.add(new StateDetailsModel("Delhi,India", "Indira Gandhi Airport", "DEL"));
        stateDetailsModelList.add(new StateDetailsModel("Delhi,India", "Indira Gandhi Airport", "DEL"));

        StateDetailsAdapter detailsAdapter = new StateDetailsAdapter(SelectDestinationActivity.this, stateDetailsModelList);
        stateDetailsRecycler.setAdapter(detailsAdapter);
        stateDetailsRecycler.setLayoutManager(new LinearLayoutManager(SelectDestinationActivity.this, LinearLayoutManager.VERTICAL, false));

        customizeSearchView(DestinationSearchView);
        DestinationMic.setOnClickListener(v -> {
            if (checkPermission()) {
                displaySpeechRecognizer();
            } else {
                requestPermission();
            }
        });

    }


    private void customizeSearchView(androidx.appcompat.widget.SearchView searchView) {
        try {
            Field queryTextViewField = androidx.appcompat.widget.SearchView.class.getDeclaredField("mSearchSrcTextView");
            queryTextViewField.setAccessible(true);
            TextView queryTextView = (TextView) queryTextViewField.get(searchView);

            assert queryTextView != null;
            queryTextView.setTextSize(14);
            queryTextView.setHintTextColor(ContextCompat.getColor(SelectDestinationActivity.this, R.color.hint));
            queryTextView.setTextSize(14);

            queryTextView.setTextColor(ContextCompat.getColor(SelectDestinationActivity.this, R.color.black));
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
                    // Or clear the searchView and set the query
                    DestinationSearchView.setQuery(results.get(0), false);
                }
            }
        }
    }

}
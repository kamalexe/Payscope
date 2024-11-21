package pay.scope.payscope.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.ChooseRechargePostpaidAdapter;
import pay.scope.payscope.Adapter.PlanDetailsPostpaidAdapter;
import pay.scope.payscope.Adapter.PlanTypePostpaidAdapter;
import pay.scope.payscope.Model.ChooseRechargePostpaidModel;
import pay.scope.payscope.Model.PlanDetailsPostpaidModel;
import pay.scope.payscope.Model.PlanTypePostpaidModel;
import pay.scope.payscope.R;

public class ChoosePlanPostpaidActivity extends AppCompatActivity {
    ImageView PlanPostpaid_backImg;
    MaterialCardView PlanPostpaidOperator, PlanPostpaidArea;
    TextView PlanPostpaidOperatorText, PlanPostpaidAreaText;
    SearchView PlanPostpaidSearchView;
    ImageView PlanPostpaidMic;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;
    RecyclerView PlanPostpaidRecycler, PlanPostpaidChooseRecycler, PlanPostpaidDetailsRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_plan_postpaid);

        PlanPostpaid_backImg = findViewById(R.id.PlanPostpaid_backImg);
        PlanPostpaidOperator = findViewById(R.id.PlanPostpaidOperator);
        PlanPostpaidArea = findViewById(R.id.PlanPostpaidArea);
        PlanPostpaidOperatorText = findViewById(R.id.PlanPostpaidOperatorText);
        PlanPostpaidAreaText = findViewById(R.id.PlanPostpaidAreaText);
        PlanPostpaidSearchView = findViewById(R.id.PlanPostpaidSearchView);
        PlanPostpaidMic = findViewById(R.id.PlanPostpaidMic);

        PlanPostpaidRecycler = findViewById(R.id.PlanPostpaidRecycler);
        PlanPostpaidChooseRecycler = findViewById(R.id.PlanPostpaidChooseRecycler);
        PlanPostpaidDetailsRecycler = findViewById(R.id.PlanPostpaidDetailsRecycler);

        PlanPostpaid_backImg.setOnClickListener(v -> finish());


        customizeSearchView(PlanPostpaidSearchView);

        BottomSheetDialog OperatorSheetDialog = new BottomSheetDialog(ChoosePlanPostpaidActivity.this);
        OperatorSheetDialog.setContentView(R.layout.chooseplanoperator_bottomsheet);

        View choosePlanOperatorView = OperatorSheetDialog.findViewById(R.id.chooseplanoperator_bottomsheet);
        if (choosePlanOperatorView != null) {

            ImageView OperatorBottomsheetImg = choosePlanOperatorView.findViewById(R.id.OperatorBottomsheetImg);

            OperatorBottomsheetImg.setOnClickListener(v -> OperatorSheetDialog.dismiss());

            int[] bottomsheetIds = {R.id.Operator_Bottomsheet1, R.id.Operator_Bottomsheet2, R.id.Operator_Bottomsheet3, R.id.Operator_Bottomsheet4, R.id.Operator_Bottomsheet5};
            int[] textIds = {R.id.Operator_BottomsheetText1, R.id.Operator_BottomsheetText2, R.id.Operator_BottomsheetText3, R.id.Operator_BottomsheetText4, R.id.Operator_BottomsheetText5};

            for (int i = 0; i < bottomsheetIds.length; i++) {
                LinearLayout bottomsheet = choosePlanOperatorView.findViewById(bottomsheetIds[i]);
                TextView text = choosePlanOperatorView.findViewById(textIds[i]);
                bottomsheet.setOnClickListener(v -> {
                    String selectedText = text.getText().toString();
                    PlanPostpaidOperatorText.setText(selectedText);
                    OperatorSheetDialog.dismiss();
                });
            }
        }





        BottomSheetDialog AreaSheetDialog = new BottomSheetDialog(ChoosePlanPostpaidActivity.this);
        AreaSheetDialog.setContentView(R.layout.chooseplanarea_bottomsheet);

        View choosePlanAreaView = AreaSheetDialog.findViewById(R.id.chooseplanArea_bottomsheet);
        if (choosePlanAreaView != null) {

            ImageView AreaBottomSheetImg = choosePlanAreaView.findViewById(R.id.AreaBottomSheetImg);
            AreaBottomSheetImg.setOnClickListener(v -> AreaSheetDialog.dismiss());


            int[] AreaBottomsheetIds = {R.id.AreaBottomSheetRadio1};
            int[] AreaTextIds = {R.id.AreaBottomSheetText1};

            for (int i = 0; i < AreaBottomsheetIds.length; i++) {
                RadioButton AreaBottomsheet = choosePlanAreaView.findViewById(AreaBottomsheetIds[i]);
                TextView text = choosePlanAreaView.findViewById(AreaTextIds[i]);
                AreaBottomsheet.setOnClickListener(v -> {
                    String selectedText = text.getText().toString();
                    PlanPostpaidAreaText.setText(selectedText);
                    AreaSheetDialog.dismiss();
                });
            }
        }





        PlanPostpaidArea.setOnClickListener(v -> AreaSheetDialog.show());

        PlanPostpaidOperator.setOnClickListener(v -> OperatorSheetDialog.show());

        PlanPostpaidMic.setOnClickListener(v -> {
            if (checkPermission()) {
                displaySpeechRecognizer();
            } else {
                requestPermission();
            }
        });

        List<ChooseRechargePostpaidModel> rechargePostpaidModelList = new ArrayList<>();
        rechargePostpaidModelList.add(new ChooseRechargePostpaidModel("20 Days Validity"));
        rechargePostpaidModelList.add(new ChooseRechargePostpaidModel("120 Days Validity"));
        rechargePostpaidModelList.add(new ChooseRechargePostpaidModel("48 Days Validity"));
        rechargePostpaidModelList.add(new ChooseRechargePostpaidModel("50 Days Validity"));
        rechargePostpaidModelList.add(new ChooseRechargePostpaidModel("7990 Days Validity"));

        ChooseRechargePostpaidAdapter rechargePostpaidAdapter = new ChooseRechargePostpaidAdapter(ChoosePlanPostpaidActivity.this, rechargePostpaidModelList);
        PlanPostpaidRecycler.setAdapter(rechargePostpaidAdapter);
        PlanPostpaidRecycler.setLayoutManager(new LinearLayoutManager(ChoosePlanPostpaidActivity.this, LinearLayoutManager.HORIZONTAL, false));

        List<PlanTypePostpaidModel> typePostpaidModelList = new ArrayList<>();
        typePostpaidModelList.add(new PlanTypePostpaidModel("Popular"));
        typePostpaidModelList.add(new PlanTypePostpaidModel("Cricket Packs"));
        typePostpaidModelList.add(new PlanTypePostpaidModel("Smart Phone"));
        typePostpaidModelList.add(new PlanTypePostpaidModel("Data Add on"));
        typePostpaidModelList.add(new PlanTypePostpaidModel("Popular"));

        PlanTypePostpaidAdapter typePostpaidAdapter = new PlanTypePostpaidAdapter(ChoosePlanPostpaidActivity.this, typePostpaidModelList);
        PlanPostpaidChooseRecycler.setAdapter(typePostpaidAdapter);
        PlanPostpaidChooseRecycler.setLayoutManager(new LinearLayoutManager(ChoosePlanPostpaidActivity.this, LinearLayoutManager.HORIZONTAL, false));

        List<PlanDetailsPostpaidModel> detailsPostpaidModelList = new ArrayList<>();
        detailsPostpaidModelList.add(new PlanDetailsPostpaidModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));
        detailsPostpaidModelList.add(new PlanDetailsPostpaidModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));
        detailsPostpaidModelList.add(new PlanDetailsPostpaidModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));
        detailsPostpaidModelList.add(new PlanDetailsPostpaidModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));
        detailsPostpaidModelList.add(new PlanDetailsPostpaidModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));
        detailsPostpaidModelList.add(new PlanDetailsPostpaidModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));

        PlanDetailsPostpaidAdapter detailsPostpaidAdapter = new PlanDetailsPostpaidAdapter(ChoosePlanPostpaidActivity.this, detailsPostpaidModelList);
        PlanPostpaidDetailsRecycler.setAdapter(detailsPostpaidAdapter);

        PlanPostpaidDetailsRecycler.setLayoutManager(new LinearLayoutManager(ChoosePlanPostpaidActivity.this, LinearLayoutManager.VERTICAL, false));


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

    private void customizeSearchView(SearchView searchView) {
        try {
            Field queryTextViewField = SearchView.class.getDeclaredField("mSearchSrcTextView");
            queryTextViewField.setAccessible(true);
            TextView queryTextView = (TextView) queryTextViewField.get(searchView);

            assert queryTextView != null;
            queryTextView.setTextSize(14);
            queryTextView.setHintTextColor(ContextCompat.getColor(ChoosePlanPostpaidActivity.this, R.color.hint));
            queryTextView.setTextSize(14);
            queryTextView.setTextColor(ContextCompat.getColor(ChoosePlanPostpaidActivity.this, R.color.black));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SPEECH_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (results != null && !results.isEmpty()) {
                    Log.d("MainActivity", "Recognized Speech: " + results.get(0));
                    PlanPostpaidSearchView.setQuery(results.get(0), false);
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.e("MainActivity", "Speech recognition was canceled");
//                Toast.makeText(this, "Speech recognition was canceled. Please try again.", Toast.LENGTH_SHORT).show();
            } else {
                Log.e("MainActivity", "Speech recognition failed. Code: " + resultCode);
//                Toast.makeText(this, "Speech recognition failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
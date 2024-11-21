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

import pay.scope.payscope.Adapter.ChooseRechargeAdapter;
import pay.scope.payscope.Adapter.PlanDetailsAdapter;
import pay.scope.payscope.Adapter.PlanTypeAdapter;
import pay.scope.payscope.Model.ChooseRechargeModel;
import pay.scope.payscope.Model.PlanDetailsModel;
import pay.scope.payscope.Model.PlanTypeModel;
import pay.scope.payscope.R;

public class ChoosePlanActivity extends AppCompatActivity {
    RecyclerView chooseRechargeRecycler, typePlanChooseRecycler, planDetailsRecycler;
    ImageView ChoosePlan_backImg, chooseRechargeMic;
    SearchView chooseRechargeSearchView;
    TextView ChoosePlanOperatorText, ChoosePlanAreaText;
    MaterialCardView ChoosePlanArea, ChoosePlanOperator;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_plan);

        chooseRechargeRecycler = findViewById(R.id.chooseRechargeRecycler);
        typePlanChooseRecycler = findViewById(R.id.typePlanChooseRecycler);
        planDetailsRecycler = findViewById(R.id.planDetailsRecycler);
        chooseRechargeMic = findViewById(R.id.chooseRechargeMic);
        chooseRechargeSearchView = findViewById(R.id.chooseRechargeSearchView);

        ChoosePlanArea = findViewById(R.id.ChoosePlanArea);
        ChoosePlanOperator = findViewById(R.id.ChoosePlanOperator);

        ChoosePlanOperatorText = findViewById(R.id.ChoosePlanOperatorText);
        ChoosePlanAreaText = findViewById(R.id.ChoosePlanAreaText);

        ChoosePlan_backImg = findViewById(R.id.ChoosePlan_backImg);

        ChoosePlan_backImg.setOnClickListener(v -> {
//                startActivity(new Intent(ChoosePlanActivity.this, MobileRechargeActivity.class));
            finish();
        });


        BottomSheetDialog OperatorSheetDialog = new BottomSheetDialog(ChoosePlanActivity.this);
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
                    ChoosePlanOperatorText.setText(selectedText);
                    OperatorSheetDialog.dismiss();
                });
            }
        }

        ChoosePlanOperator.setOnClickListener(v -> OperatorSheetDialog.show());



        BottomSheetDialog AreaSheetDialog = new BottomSheetDialog(ChoosePlanActivity.this);
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
                    ChoosePlanAreaText.setText(selectedText);
                    AreaSheetDialog.dismiss();
                });
            }
        }

        ChoosePlanArea.setOnClickListener(v -> AreaSheetDialog.show());



        customizeSearchView(chooseRechargeSearchView);

        List<ChooseRechargeModel> chooseRechargeModelList = new ArrayList<>();
        chooseRechargeModelList.add(new ChooseRechargeModel("20 Days Validity"));
        chooseRechargeModelList.add(new ChooseRechargeModel("120 Days Validity"));
        chooseRechargeModelList.add(new ChooseRechargeModel("48 Days Validity"));
        chooseRechargeModelList.add(new ChooseRechargeModel("150 Days Validity"));
        chooseRechargeModelList.add(new ChooseRechargeModel("7990 Days Validity"));
        chooseRechargeRecycler.setLayoutManager(new LinearLayoutManager(ChoosePlanActivity.this, LinearLayoutManager.HORIZONTAL, false));
        ChooseRechargeAdapter adapter = new ChooseRechargeAdapter(ChoosePlanActivity.this, chooseRechargeModelList);
        chooseRechargeRecycler.setAdapter(adapter);


        List<PlanTypeModel> planTypeModelList = new ArrayList<>();
        planTypeModelList.add(new PlanTypeModel("Popular"));
        planTypeModelList.add(new PlanTypeModel("Cricket Packs"));
        planTypeModelList.add(new PlanTypeModel("Smart Phone"));
        planTypeModelList.add(new PlanTypeModel("Data Add on"));
        planTypeModelList.add(new PlanTypeModel("Popular"));

        typePlanChooseRecycler.setLayoutManager(new LinearLayoutManager(ChoosePlanActivity.this, LinearLayoutManager.HORIZONTAL, false));
        PlanTypeAdapter typeAdapter = new PlanTypeAdapter(ChoosePlanActivity.this, planTypeModelList);
        typePlanChooseRecycler.setAdapter(typeAdapter);


        List<PlanDetailsModel> planDetailsModelList = new ArrayList<>();

        planDetailsModelList.add(new PlanDetailsModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));
        planDetailsModelList.add(new PlanDetailsModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));
        planDetailsModelList.add(new PlanDetailsModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));
        planDetailsModelList.add(new PlanDetailsModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));
        planDetailsModelList.add(new PlanDetailsModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));
        planDetailsModelList.add(new PlanDetailsModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));
        planDetailsModelList.add(new PlanDetailsModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));
        planDetailsModelList.add(new PlanDetailsModel(45, "Data", 12.12, R.drawable.baseline_chevron_right_24, "Data 1.5 Gb | Validity Existing Plan | For users with an activity validity plan - for all coustmores", "Best Seller (Data Addon)"));

        planDetailsRecycler.setLayoutManager(new LinearLayoutManager(ChoosePlanActivity.this, LinearLayoutManager.VERTICAL, false));
        PlanDetailsAdapter planDetailsAdapter = new PlanDetailsAdapter(ChoosePlanActivity.this, planDetailsModelList);
        planDetailsRecycler.setAdapter(planDetailsAdapter);

        chooseRechargeMic.setOnClickListener(v -> {
            if (checkPermission()) {
                displaySpeechRecognizer();
            } else {
                requestPermission();
            }
        });

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
            queryTextView.setHintTextColor(ContextCompat.getColor(ChoosePlanActivity.this, R.color.hint));
            queryTextView.setTextSize(14);
            queryTextView.setTextColor(ContextCompat.getColor(ChoosePlanActivity.this, R.color.black));
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
                    chooseRechargeSearchView.setQuery(results.get(0), false);
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


//        View choosePlanOperatorView = OperatorSheetDialog.findViewById(R.id.chooseplanoperator_bottomsheet);
//        if (choosePlanOperatorView != null) {
//
//            LinearLayout Operator_Bottomsheet1 = choosePlanOperatorView.findViewById(R.id.Operator_Bottomsheet1);
//            LinearLayout Operator_Bottomsheet2 = choosePlanOperatorView.findViewById(R.id.Operator_Bottomsheet2);
//            LinearLayout Operator_Bottomsheet3 = choosePlanOperatorView.findViewById(R.id.Operator_Bottomsheet3);
//            LinearLayout Operator_Bottomsheet4 = choosePlanOperatorView.findViewById(R.id.Operator_Bottomsheet4);
//            LinearLayout Operator_Bottomsheet5 = choosePlanOperatorView.findViewById(R.id.Operator_Bottomsheet5);
//
//            TextView Operator_BottomsheetText1 = choosePlanOperatorView.findViewById(R.id.Operator_BottomsheetText1);
//            TextView Operator_BottomsheetText2 = choosePlanOperatorView.findViewById(R.id.Operator_BottomsheetText2);
//            TextView Operator_BottomsheetText3 = choosePlanOperatorView.findViewById(R.id.Operator_BottomsheetText3);
//            TextView Operator_BottomsheetText4 = choosePlanOperatorView.findViewById(R.id.Operator_BottomsheetText4);
//            TextView Operator_BottomsheetText5 = choosePlanOperatorView.findViewById(R.id.Operator_BottomsheetText5);
//
//            Operator_Bottomsheet1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String a = Operator_BottomsheetText1.getText().toString();
//                    ChoosePlanOperatorText.setText(a);
//                    OperatorSheetDialog.dismiss();
//                }
//            });
//
//        }
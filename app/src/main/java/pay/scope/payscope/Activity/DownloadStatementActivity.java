package pay.scope.payscope.Activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import pay.scope.payscope.Adapter.DownStateCalenderAdapter;
import pay.scope.payscope.Model.DownStateCalenderModel;
import pay.scope.payscope.R;

public class DownloadStatementActivity extends AppCompatActivity {
    TextView DownloadStatement_StartFrom, DownloadStatement_EndOn;
    MaterialToolbar DownloadStatement_toolbar;
    LinearLayout DownloadStatement_LinearLayout;
    RadioButton DownloadStatement_RadioButton1, DownloadStatement_RadioButton2, DownloadStatement_RadioButton3, DownloadStatement_RadioButton4, DownloadStatement_RadioButton5;
    BottomSheetDialog bottomSheetDialogSheet;
    boolean isStartFromClicked = false;
    Button DownloadStatementBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_statement);

        DownloadStatement_RadioButton1 = findViewById(R.id.DownloadStatement_RadioButton1);
        DownloadStatement_RadioButton2 = findViewById(R.id.DownloadStatement_RadioButton2);
        DownloadStatement_RadioButton3 = findViewById(R.id.DownloadStatement_RadioButton3);
        DownloadStatement_RadioButton4 = findViewById(R.id.DownloadStatement_RadioButton4);
        DownloadStatement_RadioButton5 = findViewById(R.id.DownloadStatement_RadioButton5);
        DownloadStatement_LinearLayout = findViewById(R.id.DownloadStatement_LinearLayout);
        DownloadStatement_StartFrom = findViewById(R.id.DownloadStatement_StartFrom);
        DownloadStatement_EndOn = findViewById(R.id.DownloadStatement_EndOn);
        DownloadStatementBtn = findViewById(R.id.DownloadStatementBtn);
        DownloadStatement_toolbar = findViewById(R.id.DownloadStatement_toolbar);

        DownloadStatement_toolbar.setNavigationOnClickListener(v -> finish());

        DownloadStatement_RadioButton1.setOnCheckedChangeListener(radioButtonListener);
        DownloadStatement_RadioButton2.setOnCheckedChangeListener(radioButtonListener);
        DownloadStatement_RadioButton3.setOnCheckedChangeListener(radioButtonListener);
        DownloadStatement_RadioButton4.setOnCheckedChangeListener(radioButtonListener);
        DownloadStatement_RadioButton5.setOnCheckedChangeListener(radioButtonListener);

        // Initially disable the End On TextView
        DownloadStatement_EndOn.setEnabled(false);

        bottomSheetDialogSheet = new BottomSheetDialog(DownloadStatementActivity.this);
        bottomSheetDialogSheet.setContentView(R.layout.dscalender_bottomsheet);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(DownloadStatementActivity.this);
        bottomSheetDialog.setContentView(R.layout.downloadstatement_bottomsheet);
        setRadioButtonListeners(bottomSheetDialog);
        TextView dsBottomSheetBtn = bottomSheetDialog.findViewById(R.id.dsBottomSheetBtn);

        if (dsBottomSheetBtn != null) {
            dsBottomSheetBtn.setOnClickListener(v -> bottomSheetDialog.dismiss());
        }

        DownloadStatement_StartFrom.setOnClickListener(v -> {
            isStartFromClicked = true;
            initializeMonthsRecyclerView();
        });

        DownloadStatement_EndOn.setOnClickListener(v -> {
            if (DownloadStatement_StartFrom.getText().toString().isEmpty()) {
                Toast.makeText(DownloadStatementActivity.this, "Please Select Start From ", Toast.LENGTH_SHORT).show();
            } else {
                isStartFromClicked = false;
                initializeMonthsRecyclerView();
            }
        });
    }

    private void setRadioButtonListeners(final BottomSheetDialog bottomSheetDialog) {
        View.OnClickListener radioButtonClickListener = v -> {
            DownloadStatement_LinearLayout.setVisibility(View.GONE);
            bottomSheetDialog.show();
        };

        DownloadStatement_RadioButton1.setOnClickListener(radioButtonClickListener);
        DownloadStatement_RadioButton2.setOnClickListener(radioButtonClickListener);
        DownloadStatement_RadioButton3.setOnClickListener(radioButtonClickListener);
        DownloadStatement_RadioButton4.setOnClickListener(radioButtonClickListener);

        DownloadStatement_RadioButton5.setOnClickListener(v -> DownloadStatement_LinearLayout.setVisibility(View.VISIBLE));
    }

    private final CompoundButton.OnCheckedChangeListener radioButtonListener = (buttonView, isChecked) -> {
        if (isChecked) {
            clearAllSelections();
            buttonView.setChecked(true);
            buttonView.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(DownloadStatementActivity.this, R.color.blue)));
        }
    };
    private void clearAllSelections() {
        DownloadStatement_RadioButton1.setChecked(false);
        DownloadStatement_RadioButton2.setChecked(false);
        DownloadStatement_RadioButton3.setChecked(false);
        DownloadStatement_RadioButton4.setChecked(false);
        DownloadStatement_RadioButton5.setChecked(false);

        DownloadStatement_RadioButton1.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(DownloadStatementActivity.this, R.color.black)));
        DownloadStatement_RadioButton2.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(DownloadStatementActivity.this, R.color.black)));
        DownloadStatement_RadioButton3.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(DownloadStatementActivity.this, R.color.black)));
        DownloadStatement_RadioButton4.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(DownloadStatementActivity.this, R.color.black)));
        DownloadStatement_RadioButton5.setButtonTintList(ColorStateList.valueOf(ContextCompat.getColor(DownloadStatementActivity.this, R.color.black)));
    }
    private void initializeMonthsRecyclerView() {
        RecyclerView recyclerView = bottomSheetDialogSheet.findViewById(R.id.DSCalenderRecycler);

        if (recyclerView != null) {
            List<DownStateCalenderModel> downStateCalenderModelList = generateDatesForLastYears();
            DownStateCalenderAdapter adapter = new DownStateCalenderAdapter(DownloadStatementActivity.this, downStateCalenderModelList);

            adapter.setOnDateClickListener(date -> {
                if (isStartFromClicked) {
                    DownloadStatement_StartFrom.setText(date);
                    // Enable the End On TextView after selecting a start date
                    DownloadStatement_EndOn.setEnabled(true);
                } else {
                    DownloadStatement_EndOn.setText(date);
                }
                bottomSheetDialogSheet.dismiss();
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(DownloadStatementActivity.this));
            recyclerView.setAdapter(adapter);
        }
        bottomSheetDialogSheet.show();
    }
    private List<DownStateCalenderModel> generateDatesForLastYears() {
        List<DownStateCalenderModel> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);

        for (int i = 0; i <= currentYear; i++) {
            int year = currentYear - (i / 12);
            int month = (currentMonth - i) % 12;
            if (month < 0) {
                month += 12;
            }
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            dates.add(new DownStateCalenderModel(-1, calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), year));
        }

        return dates;
    }
}



//    private List<DownStateCalenderModel> generateDatesForLastMonths() {
//        List<DownStateCalenderModel> dates = new ArrayList<>();
//        Calendar calendar = Calendar.getInstance();
//
//        for (int i = 0; i < 60; i++) {
//            String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
//            int year = calendar.get(Calendar.YEAR);
////            dates.add(new DownStateCalenderModel(-1, month, String.valueOf(year)));
//            dates.add(new DownStateCalenderModel(-1, calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), year));
//
//            calendar.add(Calendar.MONTH, -1);
//        }
//        return dates;
//    }
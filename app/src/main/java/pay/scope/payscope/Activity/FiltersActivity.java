package pay.scope.payscope.Activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import pay.scope.payscope.Adapter.MonthLayoutAdapter;
import pay.scope.payscope.Helper.FiltersViewPager;
import pay.scope.payscope.Model.MonthLayoutModel;
import pay.scope.payscope.R;

public class FiltersActivity extends AppCompatActivity {
    MaterialToolbar Filters_toolbar;
    ViewPager2 FiltersViewPager2;
    TextView months_option, categories_option, instrument_option, status_option;
    private int selectedCard = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        // Initialize views
        FiltersViewPager2 = findViewById(R.id.FiltersViewPager2);
        Filters_toolbar = findViewById(R.id.Filters_toolbar);
        months_option = findViewById(R.id.months_option);
        categories_option = findViewById(R.id.categories_option);
        instrument_option = findViewById(R.id.instrument_option);
        status_option = findViewById(R.id.status_option);

        // Set up toolbar navigation
        Filters_toolbar.setNavigationOnClickListener(v -> finish());

        // Define layout resources
        List<Integer> layouts = new ArrayList<>();
        layouts.add(R.layout.months_layout);
        layouts.add(R.layout.categories_layouts);
        layouts.add(R.layout.instrument_layouts);
        layouts.add(R.layout.status_layouts);

        // Set up ViewPager adapter
        FiltersViewPager viewPager = new FiltersViewPager(layouts, FiltersActivity.this);
        FiltersViewPager2.setAdapter(viewPager);
        
        selectedCard = 0;
        updateBackgroundColor();

        // Set up click listeners for options
        months_option.setOnClickListener(v -> {
            selectedCard = 0;
            updateBackgroundColor();
            FiltersViewPager2.setCurrentItem(0);
        });

        categories_option.setOnClickListener(v -> {
            selectedCard = 1;
            updateBackgroundColor();
            FiltersViewPager2.setCurrentItem(1);
        });

        instrument_option.setOnClickListener(v -> {
            selectedCard = 2;
            updateBackgroundColor();
            FiltersViewPager2.setCurrentItem(2);
        });

        status_option.setOnClickListener(v -> {
            selectedCard = 3;
            updateBackgroundColor();
            FiltersViewPager2.setCurrentItem(3);
        });

        // Set up ViewPager page change callback
        FiltersViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        initializeMonthsRecyclerView();
                        break;
                    case 1:
                        new LoadCategoriesLayoutTask().execute();
                        break;
                    case 2:
                        new LoadInstrumentLayoutTask().execute();
                        break;
                    case 3:
                        new LoadStatusLayoutTask().execute();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initializeMonthsRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.Month_RecyclerView);
        if (recyclerView != null) {
            List<MonthLayoutModel> monthLayoutModelList = generateDatesForLastTwoYears();
            MonthLayoutAdapter monthLayoutAdapter = new MonthLayoutAdapter(this, monthLayoutModelList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(monthLayoutAdapter);
        }
    }
    private List<MonthLayoutModel> generateDatesForLastTwoYears() {
        List<MonthLayoutModel> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);

        for (int i = 0; i <= 12; i++) {
            int year = currentYear - (i / 12);
            int month = (currentMonth - i) % 12;
            if (month < 0) {
                month += 12;
            }
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            dates.add(new MonthLayoutModel(-1, calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), year, false));
        }

        return dates;
    }

    private void updateBackgroundColor() {
        months_option.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.editbox));
        months_option.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.hint));

        categories_option.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.editbox));
        categories_option.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.hint));

        instrument_option.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.editbox));
        instrument_option.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.hint));

        status_option.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.editbox));
        status_option.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.hint));

        switch (selectedCard) {
            case 0:
                months_option.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                months_option.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                break;
            case 1:
                categories_option.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                categories_option.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                break;
            case 2:
                instrument_option.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                instrument_option.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                break;
            case 3:
                status_option.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                status_option.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                break;
            default:
                break;
        }
    }
    @SuppressLint("StaticFieldLeak")
    private class LoadInstrumentLayoutTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            InstrumentLayout();
        }
    }

    private void InstrumentLayout() {
        CheckBox checkBoxOption1 = findViewById(R.id.checkBoxOption1);
        CheckBox checkBoxOption2 = findViewById(R.id.checkBoxOption2);
        TextView textOption1 = findViewById(R.id.textOption1);
        TextView textOption2 = findViewById(R.id.textOption2);

        if (checkBoxOption1 != null && checkBoxOption2 != null) {
            setupCheckBox(checkBoxOption1, checkBoxOption2, textOption1);
            setupCheckBox(checkBoxOption2, checkBoxOption1, textOption2);
        } else {
            Log.e("FiltersActivity", "CheckBox is null");
        }
    }

    private void setupCheckBox(CheckBox checkBox, CheckBox otherCheckBox, TextView textView) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                otherCheckBox.setChecked(false);
                updateCheckBoxColors(textView, true);
            } else {
                updateCheckBoxColors(textView, false);
            }
        });
    }

    private void updateCheckBoxColors(TextView textView, boolean isSelected) {
        if (isSelected) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.blue));
        } else {
            textView.setTextColor(ContextCompat.getColor(this, R.color.hint));
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadCategoriesLayoutTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            CategoriesLayout();
        }
    }

    private void CategoriesLayout() {
        int[] checkBoxIds = {
                R.id.CategoriesCheckBox1,
                R.id.CategoriesCheckBox2,
                R.id.CategoriesCheckBox3,
                R.id.CategoriesCheckBox4
        };

        int[] textIds = {
                R.id.CategoriesText1,
                R.id.CategoriesText2,
                R.id.CategoriesText3,
                R.id.CategoriesText4
        };

        List<CheckBox> checkBoxes = new ArrayList<>();
        List<TextView> textViews = new ArrayList<>();

        // Initialize checkboxes and text views
        for (int i = 0; i < checkBoxIds.length; i++) {
            CheckBox checkBox = findViewById(checkBoxIds[i]);
            TextView textView = findViewById(textIds[i]);

            if (checkBox != null && textView != null) {
                checkBoxes.add(checkBox);
                textViews.add(textView);
            }
        }

        // Set up listeners for checkboxes
        for (int i = 0; i < checkBoxes.size(); i++) {
            CheckBox checkBox = checkBoxes.get(i);
            TextView textView = textViews.get(i);

            setupCheckBox(checkBox, checkBoxes, textView);
        }
    }

    private void setupCheckBox(CheckBox checkBox, List<CheckBox> checkBoxes, TextView textView) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                uncheckOtherCheckBoxes(checkBoxes, checkBox);
                updateTextViewColor(textView, true);
            } else {
                updateTextViewColor(textView, false);
            }
        });
    }

    private void uncheckOtherCheckBoxes(List<CheckBox> checkBoxes, CheckBox checkedCheckBox) {
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox != checkedCheckBox) {
                checkBox.setChecked(false);
            }
        }
    }

    private void updateTextViewColor(TextView textView, boolean isSelected) {
        if (isSelected) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.blue));
        } else {
            textView.setTextColor(ContextCompat.getColor(this, R.color.hint));
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadStatusLayoutTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            StatusLayouts();
        }
    }

    private void StatusLayouts() {
        // Get references to UI elements
        CheckBox statusCheckBox1 = findViewById(R.id.statusCheckBox1);
        CheckBox statusCheckBox2 = findViewById(R.id.statusCheckBox2);
        TextView statusText1 = findViewById(R.id.statusText1);
        TextView statusText2 = findViewById(R.id.statusText2);

        if (statusCheckBox1 != null && statusCheckBox2 != null) {
            StatusCheckBoxOptions(statusCheckBox1, statusCheckBox2, statusText1);
            StatusCheckBoxOptions(statusCheckBox2, statusCheckBox1, statusText2);
        }
    }

    private void StatusCheckBoxOptions(CheckBox checkBox, CheckBox otherCheckBox, TextView textView) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                otherCheckBox.setChecked(false);
                StatusTextViewColor(textView, true);
            } else {
                StatusTextViewColor(textView, false);
            }
        });
    }

    private void StatusTextViewColor(TextView textView, boolean isSelected) {
        if (isSelected) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.blue));
        } else {
            textView.setTextColor(ContextCompat.getColor(this, R.color.hint));
        }
    }
}

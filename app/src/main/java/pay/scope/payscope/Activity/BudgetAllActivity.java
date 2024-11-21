package pay.scope.payscope.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import pay.scope.payscope.Adapter.BudgetAllAdapter;
import pay.scope.payscope.Model.BudgetAllModel;
import pay.scope.payscope.R;

public class BudgetAllActivity extends AppCompatActivity {
    MaterialToolbar BudgetAll_toolbar;
    CardView BudgetAll_CardView, BudgetAll_AddCardView;
    ImageView BudgetAlarm;
    RecyclerView BudgetAll_recycler;
    List<BudgetAllModel> budgetAllModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_all);

        BudgetAll_toolbar = findViewById(R.id.BudgetAll_toolbar);
        BudgetAll_AddCardView = findViewById(R.id.BudgetAll_AddCardView);
        BudgetAlarm = findViewById(R.id.BudgetAlarm);
        BudgetAll_CardView = findViewById(R.id.BudgetAll_CardView);
        BudgetAll_recycler = findViewById(R.id.BudgetAll_recycler);

        BudgetAll_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(BudgetActivity.this, MainActivity.class));
            finish();
        });

        BudgetAll_CardView.setOnClickListener(v -> startActivity(new Intent(BudgetAllActivity.this, BudgetExpensesActivity.class)));

        BudgetAll_AddCardView.setOnClickListener(v -> startActivity(new Intent(BudgetAllActivity.this, BudgetActivity.class)));

        budgetAllModelList = new ArrayList<>();

        BudgetAllAdapter budgetAllAdapter = new BudgetAllAdapter(BudgetAllActivity.this, budgetAllModelList);
        BudgetAll_recycler.setAdapter(budgetAllAdapter);
        BudgetAll_recycler.setLayoutManager(new LinearLayoutManager(BudgetAllActivity.this, LinearLayoutManager.VERTICAL, true));

        BudgetAlarm.setOnClickListener(v -> {
            LayoutInflater inflater = LayoutInflater.from(BudgetAllActivity.this);
            View dialogView = inflater.inflate(R.layout.budgetall_dialog, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(BudgetAllActivity.this);


            final EditText DialogNote = dialogView.findViewById(R.id.DialogNote);
            final TextView DialogDate = dialogView.findViewById(R.id.DialogDate);
            final TextView DialogTime = dialogView.findViewById(R.id.DialogTime);
            final EditText DialogAmount = dialogView.findViewById(R.id.DialogAmount);

            final TextView DialogCancel = dialogView.findViewById(R.id.DialogCancel);
            final TextView DialogSave = dialogView.findViewById(R.id.DialogSave);

            builder.setView(dialogView);

            // Create the dialog
            final AlertDialog dialog = builder.create();
            final Calendar calendar = Calendar.getInstance();
            final int Year = calendar.get(Calendar.YEAR);
            final int Month = calendar.get(Calendar.MONTH);
            final int Day = calendar.get(Calendar.DAY_OF_MONTH);

            DialogDate.setOnClickListener(v1 -> {
                DatePickerDialog pickerDialog = new DatePickerDialog(BudgetAllActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Month is zero-based, so add 1 to it
                        DialogDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, Year, Month, Day);
                pickerDialog.show();
            });

            DialogTime.setOnClickListener(v12 -> {
                // Get current time
                Calendar calendar1 = Calendar.getInstance();
                int hour = calendar1.get(Calendar.HOUR_OF_DAY);
                int minute = calendar1.get(Calendar.MINUTE);

                // Create a TimePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(BudgetAllActivity.this, (view, hourOfDay, minute1) -> {
                    // Convert hour from 24-hour to 12-hour format and determine AM/PM
                    String amPm = (hourOfDay < 12) ? "AM" : "PM";
                    int hourIn12Format = (hourOfDay == 0 || hourOfDay == 12) ? 12 : hourOfDay % 12;

                    // Format the time string
                    String time = String.format(Locale.getDefault(), "%02d:%02d %s", hourIn12Format, minute1, amPm);

                    // Update DialogTime with the selected time
                    DialogTime.setText(time);
                }, hour, minute, false); // Set false for 12-hour format

                // Show the TimePickerDialog
                timePickerDialog.show();
            });


            DialogSave.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View v) {
                    String userInputNote = DialogNote.getText().toString();
                    String userInputDate = DialogDate.getText().toString();
                    String userInputTime = DialogTime.getText().toString();
                    double userInputAmount = Double.parseDouble(DialogAmount.getText().toString());

                    if (!userInputNote.isEmpty() && !userInputDate.isEmpty() && !userInputTime.isEmpty() && !userInputDate.isEmpty()) {
                        // Add the input to the list
                        budgetAllModelList.add(new BudgetAllModel(userInputNote, userInputDate, userInputTime, userInputAmount));
                        // Notify the adapter of the change
                        budgetAllAdapter.notifyDataSetChanged();
//                            budgetAllAdapter.notifyItemChanged(budgetAllModelList.size()-1);
                    }
                    dialog.dismiss(); // Dismiss the dialog
                }
            });

            DialogCancel.setOnClickListener(v13 -> dialog.dismiss());
            dialog.show();
        });


    }
}
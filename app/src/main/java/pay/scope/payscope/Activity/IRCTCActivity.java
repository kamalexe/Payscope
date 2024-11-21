package pay.scope.payscope.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import pay.scope.payscope.Adapter.CalenderAdapter;
import pay.scope.payscope.Model.CalenderModel;
import pay.scope.payscope.R;

public class IRCTCActivity extends AppCompatActivity {

    int NUM_OF_UPCOMING_DATES = 15;
    TextView date_pickerset;
    private int d, m, y;
    MaterialCardView Ac_NonAc, Ac_Only, NonAc_Only,line1;
    TextView Ac_NonAcText, Ac_OnlyText, NonAc_OnlyText;
    private List<CalenderModel> calenderModelList;
    private CalenderAdapter calenderAdapter;
    int selectedCardIndex = -1;
    MaterialToolbar IRCTCBooking_toolbar;
    RecyclerView calenderRecycler;
    LinearLayout trainBooking, PNR_Status, TrainStatus, TrainFoodOrder;
    EditText IRCTC_From;
    private final String IRCTC_Url = "https://etrain.info/trains/Kanpur-Central-CNB-to-Delhi-Azadpur-DAZ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irctcactivity);


        date_pickerset = findViewById(R.id.date_pickerset);
        line1 = findViewById(R.id.line1);
        calenderRecycler = findViewById(R.id.calenderRecycler);

        Ac_NonAc = findViewById(R.id.Ac_NonAc);
        Ac_Only = findViewById(R.id.Ac_Only);
        NonAc_Only = findViewById(R.id.NonAc_Only);

        Ac_NonAcText = findViewById(R.id.Ac_NonAcText);
        Ac_OnlyText = findViewById(R.id.Ac_OnlyText);
        NonAc_OnlyText = findViewById(R.id.NonAc_OnlyText);

        trainBooking = findViewById(R.id.trainBooking);
        PNR_Status = findViewById(R.id.PNR_Status);
        TrainStatus = findViewById(R.id.TrainStatus);
        TrainFoodOrder = findViewById(R.id.TrainFoodOrder);

        IRCTC_From = findViewById(R.id.IRCTC_From);

        IRCTCBooking_toolbar = findViewById(R.id.IRCTCBooking_toolbar);

        IRCTCBooking_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(IRCTCActivity.this, MainActivity.class));
            finish();
        });

        Calendar calendar = Calendar.getInstance();
        y = calendar.get(Calendar.YEAR);
        m = calendar.get(Calendar.MONTH);
        d = calendar.get(Calendar.DAY_OF_MONTH);

        selectedCardIndex = 0;
        updateCardBackground();

        calenderModelList = new ArrayList<>();
        calenderAdapter = new CalenderAdapter(IRCTCActivity.this, calenderModelList);
        calenderRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        calenderRecycler.setAdapter(calenderAdapter);
        populateDateList();

// Select the first item in the RecyclerView
        if (!calenderModelList.isEmpty()) {
            calenderAdapter.setSelectedPosition(0); // Assuming the first item is at position 0
        }

        calenderRecycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_UP) {
                    View child = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(child);
                    if (position != RecyclerView.NO_POSITION) {
                        // Set selected position when item is clicked
                        calenderAdapter.setSelectedPosition(position);
                    }
                }
                return super.onInterceptTouchEvent(rv, e);
            }
        });


        line1.setOnClickListener(v -> {
            DatePickerDialog pickerDialog = new DatePickerDialog(IRCTCActivity.this, new DatePickerDialog.OnDateSetListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    date_pickerset.setText(dayOfMonth + "/" + month + "/" + year);
                }
            }, y, m, d);
            pickerDialog.show();
        });

        Ac_NonAc.setOnClickListener(v -> {
            // Update selected card index and change background color
            selectedCardIndex = 0;
            updateCardBackground();
        });

        Ac_Only.setOnClickListener(v -> {
            selectedCardIndex = 1;
            updateCardBackground();
        });

        NonAc_Only.setOnClickListener(v -> {
            selectedCardIndex = 2;
            updateCardBackground();
        });

        trainBooking.setOnClickListener(v -> startActivity(new Intent(IRCTCActivity.this, IRCTCBookingActivity.class)));

        PNR_Status.setOnClickListener(v -> startActivity(new Intent(IRCTCActivity.this, IRCTCPNRStatusActivity.class)));

        TrainStatus.setOnClickListener(v -> startActivity(new Intent(IRCTCActivity.this, IRCTCStatusActivity.class)));

        TrainFoodOrder.setOnClickListener(v -> startActivity(new Intent(IRCTCActivity.this, IRCTCFoodOrderActivity.class)));

        IRCTC_From.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(IRCTC_Url));
            startActivity(intent);
        });


    }


    @SuppressLint("NotifyDataSetChanged")
    private void populateDateList() {
        SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat sdfDisplay = new SimpleDateFormat("dd MMM EEE", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();

        // Clear existing data
        calenderModelList.clear();

        // Add today's date
        String fullDateToday = sdfFull.format(calendar.getTime());
        String displayDateToday = sdfDisplay.format(calendar.getTime());
        calenderModelList.add(new CalenderModel(fullDateToday, displayDateToday));

        // Move to the next day
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        // Add upcoming dates
        for (int i = 0; i < NUM_OF_UPCOMING_DATES; i++) {
            String fullDate = sdfFull.format(calendar.getTime());
            String displayDate = sdfDisplay.format(calendar.getTime());

            calenderModelList.add(new CalenderModel(fullDate, displayDate));
            calendar.add(Calendar.DAY_OF_MONTH, 1); // Add one day
        }

        // Notify adapter of data change
        calenderAdapter.notifyDataSetChanged();
    }

    private void updateCardBackground() {
        Ac_NonAc.setCardBackgroundColor(getResources().getColor(R.color.white));
        Ac_Only.setCardBackgroundColor(getResources().getColor(R.color.white));
        NonAc_Only.setCardBackgroundColor(getResources().getColor(R.color.white));

//        Ac_OnlyText.setTextColor(ContextCompat.getColor(IRCTCActivity.this, R.color.black));


        switch (selectedCardIndex) {
            case 0:
                Ac_NonAc.setCardBackgroundColor(ContextCompat.getColor(IRCTCActivity.this, R.color.lightgreen));
                break;
            case 1:
                Ac_Only.setCardBackgroundColor(ContextCompat.getColor(IRCTCActivity.this, R.color.lightgreen));
                break;
            case 2:
                NonAc_Only.setCardBackgroundColor(ContextCompat.getColor(IRCTCActivity.this, R.color.lightgreen));
                break;
            default:
                // No card selected, do nothing
                break;
        }

    }


//    private void populateDatesList() {
//        SimpleDateFormat sdfFull = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        Calendar calendar = Calendar.getInstance();
//
//        // Clear existing data
//        calenderModelList.clear();
//
//        // Add today's date
//        String fullDateToday = sdfFull.format(calendar.getTime());
//        calenderModelList.add(new CalenderModel(fullDateToday, "Today"));
//
//        // Find today's position
//        int todayPosition = -1;
//        for (int i = 0; i < calenderModelList.size(); i++) {
//            if (calenderModelList.get(i).getFullDate().equals(fullDateToday)) {
//                todayPosition = i;
//                break;
//            }
//        }
//
//        // Add upcoming dates
//        for (int i = 1; i < NUM_OF_UPCOMING_DATES; i++) {
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//            String fullDate = sdfFull.format(calendar.getTime());
//            calenderModelList.add(new CalenderModel(fullDate, ""));
//        }
//
//        // Notify adapter of data change
//        calenderAdapter = new CalenderAdapter(this, calenderModelList, todayPosition);
//        calenderRecycler.setAdapter(calenderAdapter);
//    }


}

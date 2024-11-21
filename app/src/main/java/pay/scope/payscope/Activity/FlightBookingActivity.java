package pay.scope.payscope.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import pay.scope.payscope.R;

public class FlightBookingActivity extends AppCompatActivity {
    private int days, month, year;
    TextView choosedate_Flight, returndate_Flight, adultEconomy;
    private MaterialCardView Armedforces_card, Student_card, SeniorCitizen_card;
    private int selectedCardIndex = -1;
    private Calendar calendar;
    private String dayName;
    private String formattedDate;
    private SimpleDateFormat sdf;
     EditText flight_Form, flight_To;
    MaterialToolbar FlightBooking_toolbar;
    LinearLayout FlightBooking_Offer, FlightBooking_MyBooking, FlightBooking_Copilot;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_booking);

        choosedate_Flight = findViewById(R.id.Choosedate_Flight);
        returndate_Flight = findViewById(R.id.returndate_Flight);

        Armedforces_card = findViewById(R.id.Armedforces_card);
        Student_card = findViewById(R.id.Student_card);
        SeniorCitizen_card = findViewById(R.id.SeniorCitizen_card);

        FlightBooking_Offer = findViewById(R.id.FlightBooking_Offer);
        FlightBooking_MyBooking = findViewById(R.id.FlightBooking_MyBooking);
        FlightBooking_Copilot = findViewById(R.id.FlightBooking_Copilot);

        adultEconomy = findViewById(R.id.adultEconomy);
        flight_Form = findViewById(R.id.flight_Form);
        flight_To = findViewById(R.id.flight_To);

        FlightBooking_toolbar = findViewById(R.id.FlightBooking_toolbar);
        FlightBooking_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(FlightBookingActivity.this, MainActivity.class));
            finish();
        });


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        days = calendar.get(Calendar.DAY_OF_MONTH);

        sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        formattedDate = sdf.format(calendar.getTime());

        dayName = getDayName(calendar.get(Calendar.DAY_OF_WEEK));

        choosedate_Flight.setText(dayName + ", " + formattedDate);

        selectedCardIndex = 0;
        updateCardBackground();

        choosedate_Flight.setOnClickListener(v -> Choosedate_Flight());

        returndate_Flight.setOnClickListener(v -> Returndate_Flight());

        Armedforces_card.setOnClickListener(v -> {
            // Update selected card index and change background color
            selectedCardIndex = 0;
            updateCardBackground();
        });

        Student_card.setOnClickListener(v -> {
            selectedCardIndex = 1;
            updateCardBackground();
        });

        SeniorCitizen_card.setOnClickListener(v -> {
            selectedCardIndex = 2;
            updateCardBackground();
        });

        adultEconomy.setOnClickListener(v -> startActivity(new Intent(FlightBookingActivity.this, AdultEconomyActivity.class)));

        flight_To.setOnClickListener(v -> startActivity(new Intent(FlightBookingActivity.this, SelectDestinationActivity.class)));

        flight_Form.setOnClickListener(v -> startActivity(new Intent(FlightBookingActivity.this, SelectDestinationActivity.class)));

        FlightBooking_Offer.setOnClickListener(v -> startActivity(new Intent(FlightBookingActivity.this, FlightBookingOfferActivity.class)));
        FlightBooking_MyBooking.setOnClickListener(v -> startActivity(new Intent(FlightBookingActivity.this, FlightMyBookingActivity.class)));
        FlightBooking_Copilot.setOnClickListener(v -> startActivity(new Intent(FlightBookingActivity.this, FlightBookingCopilotActivity.class)));


    }

    public void Choosedate_Flight() {
        DatePickerDialog pickerDialog = new DatePickerDialog(FlightBookingActivity.this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                dayName = getDayName(calendar.get(Calendar.DAY_OF_WEEK));

                sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                formattedDate = sdf.format(calendar.getTime());

                choosedate_Flight.setText(dayName + ", " + formattedDate);
            }
        }, year, month, days);
        pickerDialog.show();
    }

    public void Returndate_Flight() {
        DatePickerDialog pickerDialog = new DatePickerDialog(FlightBookingActivity.this, (view, year, month, dayOfMonth) -> {


            calendar.set(year, month, dayOfMonth);

            sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            formattedDate = sdf.format(calendar.getTime());

            returndate_Flight.setText(formattedDate);
        }, year, month, days);
        pickerDialog.show();
    }

    private void updateCardBackground() {
        Armedforces_card.setCardBackgroundColor(getResources().getColor(R.color.white));
        Student_card.setCardBackgroundColor(getResources().getColor(R.color.white));
        SeniorCitizen_card.setCardBackgroundColor(getResources().getColor(R.color.white));
//        Ac_OnlyText.setTextColor(ContextCompat.getColor(IRCTCActivity.this, R.color.black));
        switch (selectedCardIndex) {
            case 0:
                Armedforces_card.setCardBackgroundColor(ContextCompat.getColor(FlightBookingActivity.this, R.color.lightgreen));
                break;
            case 1:
                Student_card.setCardBackgroundColor(ContextCompat.getColor(FlightBookingActivity.this, R.color.lightgreen));
                break;
            case 2:
                SeniorCitizen_card.setCardBackgroundColor(ContextCompat.getColor(FlightBookingActivity.this, R.color.lightgreen));
                break;
            default:
                // No card selected, do nothing
                break;
        }

    }

    private String getDayName(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "Sun";
            case Calendar.MONDAY:
                return "Mon";
            case Calendar.TUESDAY:
                return "Tue";
            case Calendar.WEDNESDAY:
                return "Wed";
            case Calendar.THURSDAY:
                return "Thu";
            case Calendar.FRIDAY:
                return "Fri";
            case Calendar.SATURDAY:
                return "Sat";
            default:
                return "";
        }
    }

}
package pay.scope.payscope.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import pay.scope.payscope.Adapter.BookingOfferAdapter;
import pay.scope.payscope.Adapter.BookingOfferAdapter1;
import pay.scope.payscope.Model.BookingOfferModel;
import pay.scope.payscope.Model.BookingOfferModel1;
import pay.scope.payscope.R;

public class FlightBookingOfferActivity extends AppCompatActivity {
    MaterialToolbar BookingOffer_toolbar;
    RecyclerView BookingOffer_Recycler,BookingOffer_Recycler1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_booking_offer);

        BookingOffer_toolbar = findViewById(R.id.BookingOffer_toolbar);
        BookingOffer_Recycler = findViewById(R.id.BookingOffer_Recycler);
        BookingOffer_Recycler1 = findViewById(R.id.BookingOffer_Recycler1);

        BookingOffer_toolbar.setNavigationOnClickListener(v -> {
//                startActivity(new Intent(FlightBookingOfferActivity.this,FlightBookingActivity.class));
            finish();
        });

        List<BookingOfferModel> bookingOfferModelList = new ArrayList<>();
        bookingOfferModelList.add(new BookingOfferModel(R.drawable.shopping,"20%","You Get","Dolor Shop","21 Coupons",R.color.blue,R.color.abc30,R.color.white));
        bookingOfferModelList.add(new BookingOfferModel(R.drawable.rupee,"60%","You Get","Ipsum SHop","19 Coupons",R.color.yellow,R.color.abc31,R.color.white));
        bookingOfferModelList.add(new BookingOfferModel(R.drawable.checkmark,"23%","You Get","Dolor Shop","17 Coupons",R.color.green,R.color.abc3111,R.color.white));
        bookingOfferModelList.add(new BookingOfferModel(R.drawable.groups,"67%","You Get","Dolor Shop","23 Coupons",R.color.selected_color,R.color.green,R.color.white));


        BookingOfferAdapter offerAdapter = new BookingOfferAdapter(FlightBookingOfferActivity.this, bookingOfferModelList);
        BookingOffer_Recycler.setAdapter(offerAdapter);
        BookingOffer_Recycler.setLayoutManager(new LinearLayoutManager(FlightBookingOfferActivity.this, LinearLayoutManager.VERTICAL, false));


        List<BookingOfferModel1> bookingOfferModelList1 = new ArrayList<>();
        bookingOfferModelList1.add(new BookingOfferModel1(R.drawable.spinnerimg,"Daily Checkin","Get Coins",R.color.abc30));
        bookingOfferModelList1.add(new BookingOfferModel1(R.drawable.spinnerimg,"Daily Checkin","Get Coins",R.color.abc30));
        bookingOfferModelList1.add(new BookingOfferModel1(R.drawable.gift,"Spin & Win","Win Points",R.color.abc31));
        bookingOfferModelList1.add(new BookingOfferModel1(R.drawable.spinnerimg,"Buzz Offer","Get Coins",R.color.abc311));
        bookingOfferModelList1.add(new BookingOfferModel1(R.drawable.spinnerimg,"Task Offer","Win Points",R.color.abc3111));



        BookingOfferAdapter1 offerAdapter1 = new BookingOfferAdapter1(FlightBookingOfferActivity.this, bookingOfferModelList1);
        BookingOffer_Recycler1.setAdapter(offerAdapter1);
        BookingOffer_Recycler1.setLayoutManager(new LinearLayoutManager(FlightBookingOfferActivity.this, LinearLayoutManager.HORIZONTAL, false));


    }
}
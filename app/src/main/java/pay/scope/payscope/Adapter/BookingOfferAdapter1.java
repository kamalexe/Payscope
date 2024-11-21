package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import pay.scope.payscope.Model.BookingOfferModel1;
import pay.scope.payscope.R;

public class BookingOfferAdapter1 extends RecyclerView.Adapter<BookingOfferAdapter1.ViewHolder> {
    private final Context context;
    private final List<BookingOfferModel1> bookingOfferModel1List;

    public BookingOfferAdapter1(Context context, List<BookingOfferModel1> bookingOfferModel1List) {
        this.context = context;
        this.bookingOfferModel1List = bookingOfferModel1List;
    }

    @NonNull
    @Override
    public BookingOfferAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.bookingoffer_recycler1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookingOfferAdapter1.ViewHolder holder, int position) {
        BookingOfferModel1 bookingOfferModel1 = bookingOfferModel1List.get(position);
        holder.bind(bookingOfferModel1);
    }

    @Override
    public int getItemCount() {
        return bookingOfferModel1List.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView BookingOffer1BankName, BookingOfferDiscount_text;
        //        MaterialCardView BookingOffer1CardView;
        CardView BookingOffer1CardView;
        ImageView BookingOffer1Img;
//        TextView BookingOffer1Amount;
        Button BookingOffer1Btn;
        RelativeLayout BookingOffer1RelativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BookingOffer1BankName = itemView.findViewById(R.id.BookingOffer1BankName);
//            BookingOffer1Amount = itemView.findViewById(R.id.BookingOffer1Amount);
            BookingOffer1Img = itemView.findViewById(R.id.BookingOffer1Img);
//            BookingOffer1Btn = itemView.findViewById(R.id.BookingOffer1Btn);
            BookingOffer1CardView = itemView.findViewById(R.id.BookingOffer1CardView);
            BookingOfferDiscount_text = itemView.findViewById(R.id.BookingOfferDiscount_text);

//            BookingOffer1RelativeLayout = itemView.findViewById(R.id.BookingOffer1RelativeLayout);
        }

        public void bind(BookingOfferModel1 bookingOfferModel1) {
            BookingOffer1BankName.setText(bookingOfferModel1.getOffer1BankName());
            BookingOfferDiscount_text.setText(bookingOfferModel1.getBookingOfferDiscount_text());
//            BookingOffer1Amount.setText(String.valueOf(bookingOfferModel1.getOffer1Amount()));
            BookingOffer1Img.setImageResource(bookingOfferModel1.getOffer1Img());
//            BookingOffer1Btn.setText(bookingOfferModel1.getOffer1Btn());
            BookingOffer1CardView.setCardBackgroundColor(itemView.getResources().getColor(bookingOfferModel1.getColorResourceBackground()));

//            BookingOffer1Btn.setBackgroundColor(itemView.getResources().getColor(bookingOfferModel1.getColorResourceBackground()));
//            BookingOffer1Btn.setTextColor(itemView.getResources().getColor(bookingOfferModel1.getColorResource()));
//            BookingOffer1RelativeLayout.setBackgroundColor(itemView.getResources().getColor(bookingOfferModel1.getColorResourceBackground()));

        }
    }
}

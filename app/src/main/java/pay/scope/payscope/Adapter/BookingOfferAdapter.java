package pay.scope.payscope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pay.scope.payscope.Model.BookingOfferModel;
import pay.scope.payscope.R;

public class BookingOfferAdapter extends RecyclerView.Adapter<BookingOfferAdapter.ViewHolder> {
    private final Context context;
    List<BookingOfferModel> bookingOfferModelList;

    public BookingOfferAdapter(Context context, List<BookingOfferModel> bookingOfferModelList) {
        this.context = context;
        this.bookingOfferModelList = bookingOfferModelList;
    }

    @NonNull
    @Override
    public BookingOfferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.bookingoffer_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookingOfferAdapter.ViewHolder holder, int position) {
        BookingOfferModel bookingOfferModel = bookingOfferModelList.get(position);
        holder.bind(bookingOfferModel);

    }

    @Override
    public int getItemCount() {
        return bookingOfferModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView BookingOfferText1, BookingOfferText2, BookingOfferText3, BookingOfferText4;
        CardView BookingOfferCardView1, BookingOfferCardView2;
//        ImageView  BookingOfferViewAll;
        ImageView BookingOfferImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BookingOfferText1 = itemView.findViewById(R.id.BookingOfferText1);
            BookingOfferText2 = itemView.findViewById(R.id.BookingOfferText2);
            BookingOfferText3 = itemView.findViewById(R.id.BookingOfferText3);
            BookingOfferText4 = itemView.findViewById(R.id.BookingOfferText4);
            BookingOfferImg = itemView.findViewById(R.id.BookingOfferImg);
            BookingOfferCardView1 = itemView.findViewById(R.id.BookingOfferCardView1);
            BookingOfferCardView2 = itemView.findViewById(R.id.BookingOfferCardView2);
//            BookingOfferViewAll = itemView.findViewById(R.id.BookingOfferViewAll);
        }

        public void bind(BookingOfferModel bookingOfferModel) {
            BookingOfferText1.setText(bookingOfferModel.getOfferText1());
            BookingOfferText2.setText(bookingOfferModel.getOfferText2());
            BookingOfferText3.setText(bookingOfferModel.getOfferText3());
            BookingOfferText4.setText(bookingOfferModel.getOfferText4());
            BookingOfferImg.setImageResource(bookingOfferModel.getOfferImg());



            BookingOfferCardView1.setCardBackgroundColor(itemView.getResources().getColor(bookingOfferModel.getColorBackground1()));
            BookingOfferCardView2.setCardBackgroundColor(itemView.getResources().getColor(bookingOfferModel.getColorBackground2()));

            BookingOfferText1.setTextColor(itemView.getResources().getColor(bookingOfferModel.getTextColor()));
            BookingOfferText2.setTextColor(itemView.getResources().getColor(bookingOfferModel.getTextColor()));
            BookingOfferText3.setTextColor(itemView.getResources().getColor(bookingOfferModel.getTextColor()));
            BookingOfferText4.setTextColor(itemView.getResources().getColor(bookingOfferModel.getTextColor()));


//            BookingOfferViewAll.setImageResource(bookingOfferModel.getOfferViewAll());
        }
    }
}

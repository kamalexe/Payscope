package pay.scope.payscope.Model;

import android.widget.Button;

public class BookingOfferModel1 {
    private int Offer1Img;
    private String Offer1BankName;
    private String BookingOfferDiscount_text;
    private int colorResourceBackground;


    //    private double Offer1Amount;
//    private String Offer1Btn;
//    private int colorResource;


    public BookingOfferModel1(int offer1Img, String offer1BankName, String bookingOfferDiscount_text, int colorResourceBackground) {
        Offer1Img = offer1Img;
        Offer1BankName = offer1BankName;
        BookingOfferDiscount_text = bookingOfferDiscount_text;
        this.colorResourceBackground = colorResourceBackground;
    }

    public int getOffer1Img() {
        return Offer1Img;
    }

    public void setOffer1Img(int offer1Img) {
        Offer1Img = offer1Img;
    }

    public String getOffer1BankName() {
        return Offer1BankName;
    }

    public void setOffer1BankName(String offer1BankName) {
        Offer1BankName = offer1BankName;
    }

    public String getBookingOfferDiscount_text() {
        return BookingOfferDiscount_text;
    }

    public void setBookingOfferDiscount_text(String bookingOfferDiscount_text) {
        BookingOfferDiscount_text = bookingOfferDiscount_text;
    }

    public int getColorResourceBackground() {
        return colorResourceBackground;
    }

    public void setColorResourceBackground(int colorResourceBackground) {
        this.colorResourceBackground = colorResourceBackground;
    }
}
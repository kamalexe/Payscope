package pay.scope.payscope.Model;

public class BookingOfferModel {
    private int OfferImg;
    private String OfferText1, OfferText2, OfferText3, OfferText4;

    private int colorBackground1,colorBackground2 ,textColor;

//    private int OfferViewAll;


    public BookingOfferModel(int offerImg, String offerText1, String offerText2, String offerText3, String offerText4, int colorBackground1, int colorBackground2, int textColor) {
        OfferImg = offerImg;
        OfferText1 = offerText1;
        OfferText2 = offerText2;
        OfferText3 = offerText3;
        OfferText4 = offerText4;
        this.colorBackground1 = colorBackground1;
        this.colorBackground2 = colorBackground2;
        this.textColor = textColor;
    }

//    public BookingOfferModel(int offerImg, String offerText1, String offerText2, String offerText3, String offerText4, int colorBackground1, int colorBackground2) {
//        OfferImg = offerImg;
//        OfferText1 = offerText1;
//        OfferText2 = offerText2;
//        OfferText3 = offerText3;
//        OfferText4 = offerText4;
//        this.colorBackground1 = colorBackground1;
//        this.colorBackground2 = colorBackground2;
//    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getOfferImg() {
        return OfferImg;
    }

    public void setOfferImg(int offerImg) {
        OfferImg = offerImg;
    }

    public String getOfferText1() {
        return OfferText1;
    }

    public void setOfferText1(String offerText1) {
        OfferText1 = offerText1;
    }

    public String getOfferText2() {
        return OfferText2;
    }

    public void setOfferText2(String offerText2) {
        OfferText2 = offerText2;
    }

    public String getOfferText3() {
        return OfferText3;
    }

    public void setOfferText3(String offerText3) {
        OfferText3 = offerText3;
    }

    public String getOfferText4() {
        return OfferText4;
    }

    public void setOfferText4(String offerText4) {
        OfferText4 = offerText4;
    }

    public int getColorBackground1() {
        return colorBackground1;
    }

    public void setColorBackground1(int colorBackground1) {
        this.colorBackground1 = colorBackground1;
    }

    public int getColorBackground2() {
        return colorBackground2;
    }

    public void setColorBackground2(int colorBackground2) {
        this.colorBackground2 = colorBackground2;
    }
}
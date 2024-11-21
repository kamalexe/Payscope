package pay.scope.payscope.Model;

public class MyWalletModel {
   private String MyWalletName,MyWalletTime;
   private int MyWalletImg;
   private double MyWalletPrice;

    public MyWalletModel(String myWalletName, String myWalletTime, int myWalletImg, double myWalletPrice) {
        MyWalletName = myWalletName;
        MyWalletTime = myWalletTime;
        MyWalletImg = myWalletImg;
        MyWalletPrice = myWalletPrice;
    }


    public String getMyWalletName() {
        return MyWalletName;
    }

    public void setMyWalletName(String myWalletName) {
        MyWalletName = myWalletName;
    }

    public String getMyWalletTime() {
        return MyWalletTime;
    }

    public void setMyWalletTime(String myWalletTime) {
        MyWalletTime = myWalletTime;
    }

    public int getMyWalletImg() {
        return MyWalletImg;
    }

    public void setMyWalletImg(int myWalletImg) {
        MyWalletImg = myWalletImg;
    }

    public double getMyWalletPrice() {
        return MyWalletPrice;
    }

    public void setMyWalletPrice(double myWalletPrice) {
        MyWalletPrice = myWalletPrice;
    }
}

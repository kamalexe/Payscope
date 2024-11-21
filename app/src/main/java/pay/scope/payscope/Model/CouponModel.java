package pay.scope.payscope.Model;

public class CouponModel {

    private String CouponMode;
    private String CouponDate;
    private String CouponCondition;
    private String CouponBtn;
    private String CouponCode;
    private int CouponImg;

    public CouponModel(String couponMode, String couponDate, String couponCondition, String couponBtn, int couponImg,String couponCode) {
        CouponMode = couponMode;
        CouponDate = couponDate;
        CouponCondition = couponCondition;
        CouponBtn = couponBtn;
        CouponImg = couponImg;
        CouponCode = couponCode;
    }

    public String getCouponMode() {
        return CouponMode;
    }

    public void setCouponMode(String couponMode) {
        CouponMode = couponMode;
    }

    public String getCouponDate() {
        return CouponDate;
    }

    public void setCouponDate(String couponDate) {
        CouponDate = couponDate;
    }

    public String getCouponCondition() {
        return CouponCondition;
    }

    public void setCouponCondition(String couponCondition) {
        CouponCondition = couponCondition;
    }

    public String getCouponBtn() {
        return CouponBtn;
    }

    public void setCouponBtn(String couponBtn) {
        CouponBtn = couponBtn;
    }

    public int getCouponImg() {
        return CouponImg;
    }

    public void setCouponImg(int couponImg) {
        CouponImg = couponImg;
    }

    public String getCouponCode() {
        return CouponCode;
    }

    public void setCouponCode(String couponCode) {
        CouponCode = couponCode;
    }
}

package pay.scope.payscope.Model;

public class ManualRequest {

    private final int bank;

    private final float amount;

    private final int payment_mode;

    private final String pay_date;

    private final String reference_number;
    private  String remark;
    private  String imageData;

    public ManualRequest(int bank, float amount, int payment_mode, String pay_date, String reference_number, String remark, String imageData) {
        this.bank = bank;
        this.amount = amount;
        this.payment_mode = payment_mode;
        this.pay_date = pay_date;
        this.reference_number = reference_number;
        this.remark = remark;
        this.imageData = imageData;
    }

    public ManualRequest(int bank, float amount, int payment_mode, String pay_date, String reference_number) {
        this.bank = bank;
        this.amount = amount;
        this.payment_mode = payment_mode;
        this.pay_date = pay_date;
        this.reference_number = reference_number;
    }

    public int getBank() {
        return bank;
    }

    public float getAmount() {
        return amount;
    }

    public int getPayment_mode() {
        return payment_mode;
    }


    public String getPay_date() {
        return pay_date;
    }

    public String getReference_number() {
        return reference_number;
    }


}







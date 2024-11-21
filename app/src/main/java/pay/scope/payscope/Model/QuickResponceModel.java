package pay.scope.payscope.Model;

public class QuickResponceModel {
    private int account_number;
    private String ifsc_code;
    private String account_holder_name;
    private float amount;
    private int payment_mode;
    private String remark;

    public QuickResponceModel(int account_number, String ifsc_code, String account_holder_name, float amount, int payment_mode, String remark) {
        this.account_number = account_number;
        this.ifsc_code = ifsc_code;
        this.account_holder_name = account_holder_name;
        this.amount = amount;
        this.payment_mode = payment_mode;
        this.remark = remark;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(int payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

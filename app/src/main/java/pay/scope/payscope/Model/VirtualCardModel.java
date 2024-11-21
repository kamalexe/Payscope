package pay.scope.payscope.Model;
public class VirtualCardModel {
    private Boolean status;

    private String message;

    private Data data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private int id;
        private String credit_time;
        private String remitter_name;
        private double credit_amount;
        private int remitter_account_number;
        private String reference_number;
        private String remitter_ifsc_code;
        private String remitter_utr;

        public Data(int id, String credit_time, String remitter_name, double credit_amount, int remitter_account_number, String reference_number, String remitter_ifsc_code, String remitter_utr) {
            this.id = id;
            this.credit_time = credit_time;
            this.remitter_name = remitter_name;
            this.credit_amount = credit_amount;
            this.remitter_account_number = remitter_account_number;
            this.reference_number = reference_number;
            this.remitter_ifsc_code = remitter_ifsc_code;
            this.remitter_utr = remitter_utr;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCredit_time() {
            return credit_time;
        }

        public void setCredit_time(String credit_time) {
            this.credit_time = credit_time;
        }

        public String getRemitter_name() {
            return remitter_name;
        }

        public void setRemitter_name(String remitter_name) {
            this.remitter_name = remitter_name;
        }

        public double getCredit_amount() {
            return credit_amount;
        }

        public void setCredit_amount(double credit_amount) {
            this.credit_amount = credit_amount;
        }

        public int getRemitter_account_number() {
            return remitter_account_number;
        }

        public void setRemitter_account_number(int remitter_account_number) {
            this.remitter_account_number = remitter_account_number;
        }

        public String getReference_number() {
            return reference_number;
        }

        public void setReference_number(String reference_number) {
            this.reference_number = reference_number;
        }

        public String getRemitter_ifsc_code() {
            return remitter_ifsc_code;
        }

        public void setRemitter_ifsc_code(String remitter_ifsc_code) {
            this.remitter_ifsc_code = remitter_ifsc_code;
        }

        public String getRemitter_utr() {
            return remitter_utr;
        }

        public void setRemitter_utr(String remitter_utr) {
            this.remitter_utr = remitter_utr;
        }
    }
}

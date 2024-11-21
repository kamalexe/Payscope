package pay.scope.payscope.Model;

import java.util.List;

public class QuickHistoryModel {
    private Boolean status;
    private String message;
    private List<QuickHistoryModel.Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private Integer id;
        private String user_name;
        private double amount;
        private Long account_number;
        private String account_holder_name;
        private String ifsc_code;
        private String payment_mode_id;


        public Data(Integer id, String user_name, double amount, Long account_number, String account_holder_name, String ifsc_code, String payment_mode_id) {
            this.id = id;
            this.user_name = user_name;
            this.amount = amount;
            this.account_number = account_number;
            this.account_holder_name = account_holder_name;
            this.ifsc_code = ifsc_code;
            this.payment_mode_id = payment_mode_id;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public Long getAccount_number() {
            return account_number;
        }

        public void setAccount_number(Long account_number) {
            this.account_number = account_number;
        }

        public String getAccount_holder_name() {
            return account_holder_name;
        }

        public void setAccount_holder_name(String account_holder_name) {
            this.account_holder_name = account_holder_name;
        }

        public String getIfsc_code() {
            return ifsc_code;
        }

        public void setIfsc_code(String ifsc_code) {
            this.ifsc_code = ifsc_code;
        }

        public String getPayment_mode_id() {
            return payment_mode_id;
        }

        public void setPayment_mode_id(String payment_mode_id) {
            this.payment_mode_id = payment_mode_id;
        }
    }

}

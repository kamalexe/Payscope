package pay.scope.payscope.Model;

import java.util.List;

public class TransactionModel {
    private Boolean status;
    private String message;
    private List<Data> data;

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

        private String bank_name;

        private double amount;

        private Long account_number;

        private String branch_name;
        private String ifsc_code;
        private String status;

        public Data(Integer id, String user_name, String bank_name, Integer amount, Long account_number, String branch_name, String ifsc_code) {
            this.id = id;
            this.user_name = user_name;
            this.bank_name = bank_name;
            this.amount = amount;
            this.account_number = account_number;
            this.branch_name = branch_name;
            this.ifsc_code = ifsc_code;
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

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public Long getAccount_number() {
            return account_number;
        }

        public void setAccount_number(Long account_number) {
            this.account_number = account_number;
        }

        public String getBranch_name() {
            return branch_name;
        }

        public void setBranch_name(String branch_name) {
            this.branch_name = branch_name;
        }

        public String getIfsc_code() {
            return ifsc_code;
        }

        public void setIfsc_code(String ifsc_code) {
            this.ifsc_code = ifsc_code;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
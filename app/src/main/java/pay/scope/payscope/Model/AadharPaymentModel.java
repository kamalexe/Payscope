package pay.scope.payscope.Model;

public class AadharPaymentModel {
    private String AadharPaymentName, AadharPaymentNumber,AadharPaymentDate;

    public AadharPaymentModel(String aadharPaymentName, String aadharPaymentNumber, String aadharPaymentDate) {
        AadharPaymentName = aadharPaymentName;
        AadharPaymentNumber = aadharPaymentNumber;
        AadharPaymentDate = aadharPaymentDate;
    }

    public String getAadharPaymentName() {
        return AadharPaymentName;
    }

    public void setAadharPaymentName(String aadharPaymentName) {
        AadharPaymentName = aadharPaymentName;
    }

    public String getAadharPaymentNumber() {
        return AadharPaymentNumber;
    }

    public void setAadharPaymentNumber(String aadharPaymentNumber) {
        AadharPaymentNumber = aadharPaymentNumber;
    }

    public String getAadharPaymentDate() {
        return AadharPaymentDate;
    }

    public void setAadharPaymentDate(String aadharPaymentDate) {
        AadharPaymentDate = aadharPaymentDate;
    }
}

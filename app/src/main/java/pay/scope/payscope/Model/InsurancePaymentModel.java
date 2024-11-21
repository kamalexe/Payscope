package pay.scope.payscope.Model;

public class InsurancePaymentModel {
    String insuranceName;
    int insuranceImg;

    public InsurancePaymentModel(String insuranceName, int insuranceImg) {
        this.insuranceName = insuranceName;
        this.insuranceImg = insuranceImg;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public int getInsuranceImg() {
        return insuranceImg;
    }

    public void setInsuranceImg(int insuranceImg) {
        this.insuranceImg = insuranceImg;
    }
}

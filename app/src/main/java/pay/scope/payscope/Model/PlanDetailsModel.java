package pay.scope.payscope.Model;

public class PlanDetailsModel {
    int prise;
    String data;
    double netpack;
    int imgshowDetails;
    String details;
    String addonData;

    public PlanDetailsModel() {
    }

    public PlanDetailsModel(int prise, String data, double netpack, int imgshowDetails, String details, String addonData) {
        this.prise = prise;
        this.data = data;
        this.netpack = netpack;
        this.imgshowDetails = imgshowDetails;
        this.details = details;
        this.addonData = addonData;
    }

    public int getPrise() {
        return prise;
    }

    public void setPrise(int prise) {
        this.prise = prise;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getNetpack() {
        return netpack;
    }

    public void setNetpack(double netpack) {
        this.netpack = netpack;
    }

    public int getImgshowDetails() {
        return imgshowDetails;
    }

    public void setImgshowDetails(int imgshowDetails) {
        this.imgshowDetails = imgshowDetails;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAddonData() {
        return addonData;
    }

    public void setAddonData(String addonData) {
        this.addonData = addonData;
    }
}
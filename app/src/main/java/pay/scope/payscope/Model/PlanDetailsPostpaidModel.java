package pay.scope.payscope.Model;

public class PlanDetailsPostpaidModel {
    int PrisePostpaid;
    String DataPostpaid;
    double NetpackPostpaid;
    int ImgShowDetailsPostpaid;
    String DetailsPostpaid;
    String AddonDataPostpaid;

    public PlanDetailsPostpaidModel(int prisePostpaid, String dataPostpaid, double netpackPostpaid, int imgShowDetailsPostpaid, String detailsPostpaid, String addonDataPostpaid) {
        PrisePostpaid = prisePostpaid;
        DataPostpaid = dataPostpaid;
        NetpackPostpaid = netpackPostpaid;
        ImgShowDetailsPostpaid = imgShowDetailsPostpaid;
        DetailsPostpaid = detailsPostpaid;
        AddonDataPostpaid = addonDataPostpaid;
    }

    public int getPrisePostpaid() {
        return PrisePostpaid;
    }

    public void setPrisePostpaid(int prisePostpaid) {
        PrisePostpaid = prisePostpaid;
    }

    public String getDataPostpaid() {
        return DataPostpaid;
    }

    public void setDataPostpaid(String dataPostpaid) {
        DataPostpaid = dataPostpaid;
    }

    public double getNetpackPostpaid() {
        return NetpackPostpaid;
    }

    public void setNetpackPostpaid(double netpackPostpaid) {
        NetpackPostpaid = netpackPostpaid;
    }

    public int getImgShowDetailsPostpaid() {
        return ImgShowDetailsPostpaid;
    }

    public void setImgShowDetailsPostpaid(int imgShowDetailsPostpaid) {
        ImgShowDetailsPostpaid = imgShowDetailsPostpaid;
    }

    public String getDetailsPostpaid() {
        return DetailsPostpaid;
    }

    public void setDetailsPostpaid(String detailsPostpaid) {
        DetailsPostpaid = detailsPostpaid;
    }

    public String getAddonDataPostpaid() {
        return AddonDataPostpaid;
    }

    public void setAddonDataPostpaid(String addonDataPostpaid) {
        AddonDataPostpaid = addonDataPostpaid;
    }
}

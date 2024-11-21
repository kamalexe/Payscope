package pay.scope.payscope.Model;

public class InvestmentModel {

    private int investmentImg,investmentOfferImg;
    private  String investmentTitle,investmentDes;
//    private  String investmentButton;




    public InvestmentModel(int investmentImg, int investmentOfferImg, String investmentTitle, String investmentDes) {
        this.investmentImg = investmentImg;
        this.investmentOfferImg = investmentOfferImg;
        this.investmentTitle = investmentTitle;
        this.investmentDes = investmentDes;
    }

    public int getInvestmentImg() {
        return investmentImg;
    }

    public void setInvestmentImg(int investmentImg) {
        this.investmentImg = investmentImg;
    }

    public String getInvestmentTitle() {
        return investmentTitle;
    }

    public void setInvestmentTitle(String investmentTitle) {
        this.investmentTitle = investmentTitle;
    }

    public String getInvestmentDes() {
        return investmentDes;
    }

    public void setInvestmentDes(String investmentDes) {
        this.investmentDes = investmentDes;
    }

    public int getInvestmentOfferImg() {
        return investmentOfferImg;
    }

    public void setInvestmentOfferImg(int investmentOfferImg) {
        this.investmentOfferImg = investmentOfferImg;
    }
}

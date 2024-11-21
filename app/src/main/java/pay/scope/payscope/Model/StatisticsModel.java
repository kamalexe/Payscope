package pay.scope.payscope.Model;

public class StatisticsModel {

   private String StatisticsName,StatisticsPaid;
   private int StatisticsIMG;
    private double StatisticsAmount,StatisticsTotalAmt,StatisticsROI,StatisticsEMI;

    public StatisticsModel(String statisticsName, String statisticsPaid, int statisticsIMG, double statisticsAmount, double statisticsTotalAmt, double statisticsROI, double statisticsEMI) {
        StatisticsName = statisticsName;
        StatisticsPaid = statisticsPaid;
        StatisticsIMG = statisticsIMG;
        StatisticsAmount = statisticsAmount;
        StatisticsTotalAmt = statisticsTotalAmt;
        StatisticsROI = statisticsROI;
        StatisticsEMI = statisticsEMI;
    }

    public String getStatisticsName() {
        return StatisticsName;
    }

    public void setStatisticsName(String statisticsName) {
        StatisticsName = statisticsName;
    }

    public String getStatisticsPaid() {
        return StatisticsPaid;
    }

    public void setStatisticsPaid(String statisticsPaid) {
        StatisticsPaid = statisticsPaid;
    }

    public int getStatisticsIMG() {
        return StatisticsIMG;
    }

    public void setStatisticsIMG(int statisticsIMG) {
        StatisticsIMG = statisticsIMG;
    }

    public double getStatisticsAmount() {
        return StatisticsAmount;
    }

    public void setStatisticsAmount(double statisticsAmount) {
        StatisticsAmount = statisticsAmount;
    }

    public double getStatisticsTotalAmt() {
        return StatisticsTotalAmt;
    }

    public void setStatisticsTotalAmt(double statisticsTotalAmt) {
        StatisticsTotalAmt = statisticsTotalAmt;
    }

    public double getStatisticsROI() {
        return StatisticsROI;
    }

    public void setStatisticsROI(double statisticsROI) {
        StatisticsROI = statisticsROI;
    }

    public double getStatisticsEMI() {
        return StatisticsEMI;
    }

    public void setStatisticsEMI(double statisticsEMI) {
        StatisticsEMI = statisticsEMI;
    }
}

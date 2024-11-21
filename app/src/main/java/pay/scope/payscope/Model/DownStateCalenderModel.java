package pay.scope.payscope.Model;

public class DownStateCalenderModel {

//    private final int day;
    private final String month;
    private final int year;

    public DownStateCalenderModel(int day, String month, int year) {
//        this.day = day;
        this.month = month;
        this.year = year;
    }

//    public int getDay() {
//        return day;
//    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}

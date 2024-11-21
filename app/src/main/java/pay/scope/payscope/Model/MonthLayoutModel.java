package pay.scope.payscope.Model;

public class MonthLayoutModel {
//    private final int day;
    private final String month;
    private final int year;
    private boolean isSelected;

    public MonthLayoutModel(int day, String month, int year, boolean isSelected) {
//        this.day = day;
        this.month = month;
        this.year = year;
        this.isSelected = isSelected;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}

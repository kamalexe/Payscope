package pay.scope.payscope.Model;

public class CalenderModel {
    private String fullDate;
    private String displayDate;

    public CalenderModel(String fullDate, String displayDate) {
        this.fullDate = fullDate;
        this.displayDate = displayDate;
    }

    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate(String fullDate) {
        this.fullDate = fullDate;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }
}

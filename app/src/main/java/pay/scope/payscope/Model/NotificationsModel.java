package pay.scope.payscope.Model;

public class NotificationsModel {

    String notificationsName,notificationsDescription,notificationsDate;
    int notificationsImg;

    public NotificationsModel(String notificationsName, String notificationsDescription, String notificationsDate, int notificationsImg) {
        this.notificationsName = notificationsName;
        this.notificationsDescription = notificationsDescription;
        this.notificationsDate = notificationsDate;
        this.notificationsImg = notificationsImg;
    }

    public String getNotificationsName() {
        return notificationsName;
    }

    public void setNotificationsName(String notificationsName) {
        this.notificationsName = notificationsName;
    }

    public String getNotificationsDescription() {
        return notificationsDescription;
    }

    public void setNotificationsDescription(String notificationsDescription) {
        this.notificationsDescription = notificationsDescription;
    }

    public int getNotificationsImg() {
        return notificationsImg;
    }

    public void setNotificationsImg(int notificationsImg) {
        this.notificationsImg = notificationsImg;
    }

    public String getNotificationsDate() {
        return notificationsDate;
    }

    public void setNotificationsDate(String notificationsDate) {
        this.notificationsDate = notificationsDate;
    }
}

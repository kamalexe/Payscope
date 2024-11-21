package pay.scope.payscope.Model;

public class UtilitiesModel {
  private   String utName,utTime,utMessage;

    public UtilitiesModel(String utName, String utTime, String utMessage) {
        this.utName = utName;
        this.utTime = utTime;
        this.utMessage = utMessage;
    }

    public String getUtName() {
        return utName;
    }

    public void setUtName(String utName) {
        this.utName = utName;
    }

    public String getUtTime() {
        return utTime;
    }

    public void setUtTime(String utTime) {
        this.utTime = utTime;
    }

    public String getUtMessage() {
        return utMessage;
    }

    public void setUtMessage(String utMessage) {
        this.utMessage = utMessage;
    }
}

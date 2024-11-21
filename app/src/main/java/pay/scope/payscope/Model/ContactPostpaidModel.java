package pay.scope.payscope.Model;

public class ContactPostpaidModel {
    private String PostpaidName;
    private String PostpaidPhoneNumber;
    private String image;


    public ContactPostpaidModel(String postpaidName, String postpaidPhoneNumber, String image) {
        PostpaidName = postpaidName;
        PostpaidPhoneNumber = postpaidPhoneNumber;
        this.image = image;
    }

    public String getPostpaidName() {
        return PostpaidName;
    }

    public String getPostpaidPhoneNumber() {
        return PostpaidPhoneNumber;
    }

    public void setPostpaidName(String postpaidName) {
        PostpaidName = postpaidName;
    }

    public void setPostpaidPhoneNumber(String postpaidPhoneNumber) {
        PostpaidPhoneNumber = postpaidPhoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

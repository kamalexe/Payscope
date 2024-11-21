package pay.scope.payscope.Model;

public class ContactModel {
    private String name;
    private String phoneNumber;
    private String imageUri;


    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public ContactModel(String name, String phoneNumber, String imageUri) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

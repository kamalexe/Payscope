package pay.scope.payscope.Model;

public class ChatsContactModel {
    private String ChatsContactName, ChatsContactMessage, ChatsContactDate;
    private double ChatsContactAmount;
    private int ChatsContactImg;

    public ChatsContactModel(String chatsContactName, String chatsContactMessage, String chatsContactDate, double chatsContactAmount, int chatsContactImg) {
        ChatsContactName = chatsContactName;
        ChatsContactMessage = chatsContactMessage;
        ChatsContactDate = chatsContactDate;
        ChatsContactAmount = chatsContactAmount;
        ChatsContactImg = chatsContactImg;
    }

    public double getChatsContactAmount() {
        return ChatsContactAmount;
    }

    public void setChatsContactAmount(double chatsContactAmount) {
        ChatsContactAmount = chatsContactAmount;
    }

    public String getChatsContactName() {
        return ChatsContactName;
    }

    public void setChatsContactName(String chatsContactName) {
        ChatsContactName = chatsContactName;
    }

    public String getChatsContactMessage() {
        return ChatsContactMessage;
    }

    public void setChatsContactMessage(String chatsContactMessage) {
        ChatsContactMessage = chatsContactMessage;
    }

    public String getChatsContactDate() {
        return ChatsContactDate;
    }

    public void setChatsContactDate(String chatsContactDate) {
        ChatsContactDate = chatsContactDate;
    }

    public int getChatsContactImg() {
        return ChatsContactImg;
    }

    public void setChatsContactImg(int chatsContactImg) {
        ChatsContactImg = chatsContactImg;
    }
}

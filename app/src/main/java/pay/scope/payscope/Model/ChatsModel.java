//package pay.scope.payscope.Model;
//
//public class ChatsModel {
//    private String chats;
//    private boolean isSentByCurrentUser;
//
//    public ChatsModel(boolean isSentByCurrentUser) {
//        this.isSentByCurrentUser = isSentByCurrentUser;
//    }
//
//    public ChatsModel(String chats) {
//        this.chats = chats;
//    }
//
//    public String getChats() {
//        return chats;
//    }
//
//    public void setChats(String chats) {
//        this.chats = chats;
//    }
//
//    public boolean isSentByCurrentUser() {
//        return isSentByCurrentUser;
//    }
//
//    public void setSentByCurrentUser(boolean sentByCurrentUser) {
//        isSentByCurrentUser = sentByCurrentUser;
//    }
//}

package pay.scope.payscope.Model;

public class ChatsModel {
    private final String chats;
    private final boolean sentByCurrentUser;

    public ChatsModel(String chats, boolean sentByCurrentUser) {
        this.chats = chats;
        this.sentByCurrentUser = sentByCurrentUser;
    }

    public String getChats() {
        return chats;
    }

    public boolean isSentByCurrentUser() {
        return sentByCurrentUser;
    }
}

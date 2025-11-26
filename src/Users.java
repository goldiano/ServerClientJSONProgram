import java.util.ArrayList;
import java.util.List;

class Users {
    private int limitMessages;
    private String nickName;
    private String password;
    private boolean isAdmin;
    private List<String> messagesList;

    Users(String nickName, String password, boolean isAdmin) {
        this.nickName = nickName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.limitMessages = 5;
        this.messagesList = new ArrayList<>();
    }

    String addMessage(String addMessage) {
        if(messagesList.size() < limitMessages) {
            messagesList.add(addMessage);
            return "Send message";
        }
        return "Contact email is full";
    }

    String readMessage(int numberMessage) {
        if(messagesList.isEmpty()) return "Mail is empty";
        else
        {
            String messageTemp = messagesList.get(numberMessage);
            messagesList.remove(numberMessage);
            return messageTemp;
        }
    }

    String numberMessages(int number) {
        if(messagesList.isEmpty()) return "Mail is empty";
        else return "You have " + messagesList.size() + " message\\messages";
    }

    String deleteMessage(int numberMessage) {
        messagesList.remove(numberMessage);
        return "Message deleted";
    }

    public String getName() {
        return nickName;
    }

    public String getPass() {
        return password;
    }
}

import java.util.ArrayList;

class Users {
    private int limitMessages;
    private String nickName;
    private String password;
    private ArrayList<String> messagesList;

    Users(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
        this.limitMessages = 5;
        this.messagesList = new ArrayList<>();
    }

    String addMessage(String addMessage) {
        if(messagesList.size() < limitMessages) {
            messagesList.add(addMessage);
            return "Send message";
        }
        return "Contact is full";
    }

    String readMessage(int numberMessage) {
        if(messagesList.isEmpty()) return "Mail is empty";
        else return messagesList.get(numberMessage);
    }
    String numberMessages(int number) {
        if(messagesList.isEmpty()) return "Mail is empty";
        else return "You have " + messagesList.size() + " message\\messages";
    }

    String deleteMessage(int numberMessage) {
        messagesList.remove(numberMessage);
        return "Message deleted";
    }
}

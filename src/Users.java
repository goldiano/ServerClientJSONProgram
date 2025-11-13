import java.util.ArrayList;

class Users {
    private int limitMessages;
    private static String nickName;
    private static String password;
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

    public static String getNickName() {
        return nickName;
    }

    public static String getPassword() {
        return password;
    }
}

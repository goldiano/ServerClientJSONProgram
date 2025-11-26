public class Authenticator {
    private DefaultUsers defaultUsers;

    Authenticator(DefaultUsers defaultUsers) {
        this.defaultUsers = defaultUsers;
    }

    boolean authenticatorClient(ReadMessage readMessage, SendMessage sendMessage) {
        String login;
        String password;
        int tryAgain = 0;

        while (true) {
            sendMessage.writer("Login please: ");
            login = readMessage.reader();
            sendMessage.writer("Password please: ");
            password = readMessage.reader();


            Users user = defaultUsers.findByName(login);
            if (user == null) {
                sendMessage.writer("Wrong login or password");
                tryAgain++;
                if (tryAgain >= 3) {
                    sendMessage.writer("Close connection");
                }
                return false;
            } else return user.getPass().equals(password);
        }
    }
}

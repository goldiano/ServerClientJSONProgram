public class Authenticator {
    boolean  authenticatorClient(ReadMessage readMessage, SendMessage sendMessage) {
        String login;
        String password;
        int tryAgain = 0;

        while(true) {
            sendMessage.writer("Login please: ");
            login = readMessage.reader();
            sendMessage.writer("Password please: ");
            password = readMessage.reader();

            if(login.equals(Users.getNickName()) && password.equals(Users.getPassword())) {
                return true;
            }
            else sendMessage.writer("Wrong login or password");
            tryAgain++;
            if(tryAgain > 3) {
                break;
            }
        }
        return false;
    }
}

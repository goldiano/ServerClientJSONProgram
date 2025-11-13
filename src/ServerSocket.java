import java.io.*;
import java.net.Socket;

class ServerSocket {
    private static final int port = 5000;

    private void createConnection() {
        try (java.net.ServerSocket serverSocket = new java.net.ServerSocket(port)) {

            startCommunicationServer(serverSocket);

        } catch (IOException e) {
            System.err.println("Can't create connection" + e.getMessage());
        }
    }

    private void startCommunicationServer(java.net.ServerSocket serverSocket) {
        try(Socket clientSocket = serverSocket.accept()) {

            DefaultUsers defaultUsers = new DefaultUsers();
            defaultUsers.createUsers();

            messageServiceLoopServer(clientSocket);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void messageServiceLoopServer(Socket clientSocket) {
        boolean checkAuthenticator = false;
        try {
            ReadMessage readMessageServer = new ReadMessage(clientSocket);
            SendMessage sendMessageServer = new SendMessage(clientSocket);
            Authenticator authenticator = new Authenticator();
            ServerCommand serverCommand = new ServerCommand();

            //sendMessageServer.writer("Hello from server");
            checkAuthenticator = authenticator.authenticatorClient(readMessageServer, sendMessageServer);

            if(checkAuthenticator) {
                while(true) {
                    sendMessageServer.writer(serverCommand.chooseCommand(readMessageServer.reader()));
                }
            }
            else sendMessageServer.writer("Good bye, the door is closed to you");


        } catch (Exception e) {
            System.err.println("Error in ServiceLoopServer " + e.getMessage());
        }

    }

//    boolean  authenticatorClient(ReadMessage readMessage, SendMessage sendMessage) {
//        String login;
//        String password;
//        int tryAgain = 0;
//
//        while(true) {
//            sendMessage.writer("Login please: ");
//            login = readMessage.reader();
//            sendMessage.writer("Password please: ");
//            password = readMessage.reader();
//
//            if(login.equals(Users.getNickName()) && password.equals(Users.getPassword())) {
//                return true;
//            }
//            else sendMessage.writer("Wrong login or password");
//            tryAgain++;
//            if(tryAgain > 3) {
//                break;
//            }
//        }
//        return false;
//    }

    public static void main(String[] args) {
        new ServerSocket().createConnection();
    }
}

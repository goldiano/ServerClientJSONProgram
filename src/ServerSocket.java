import java.io.*;
import java.net.Socket;
import java.util.Scanner;


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

            ReadMessage readMessageServer = new ReadMessage(clientSocket);
            SendMessage sendMessageServer = new SendMessage(clientSocket);

            DefaultUsers defaultUsers = new DefaultUsers();
            defaultUsers.createUsers();
            if(authenticatorClient(readMessageServer, sendMessageServer) == false) clientSocket.close();

            messageServiceLoopServer(readMessageServer,sendMessageServer);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void messageServiceLoopServer(ReadMessage readMessage, SendMessage sendMessage) {
        sendMessage.writer("Hello from server");
        while(true) {
            String temp = readMessage.reader();
            System.out.println(temp);
        }

    }

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

    public static void main(String[] args) {
        new ServerSocket().createConnection();
    }
}

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

            messageServiceLoopServer(clientSocket);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void messageServiceLoopServer(Socket clientSocket) {
        DefaultUsers defaultUsers = new DefaultUsers();

        try {
            ReadMessage readMessageServer = new ReadMessage(clientSocket);
            SendMessage sendMessageServer = new SendMessage(clientSocket);
            Authenticator authenticator = new Authenticator(defaultUsers);
            ServerCommand serverCommand = new ServerCommand();

            if(authenticator.authenticatorClient(readMessageServer, sendMessageServer)) {
                sendMessageServer.writer(serverCommand.chooseCommand("help"));
                while(true) {
                    sendMessageServer.writer(serverCommand.chooseCommand(readMessageServer.reader()));
                }
            }
            else {
                sendMessageServer.writer("Good bye, the door is closed to you");
                clientSocket.close();
            }


        } catch (Exception e) {
            System.err.println("Error in ServiceLoopServer " + e.getMessage());
        }

    }
    public static void main(String[] args) {
        new ServerSocket().createConnection();
    }
}

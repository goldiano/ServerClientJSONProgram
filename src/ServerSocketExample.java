import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ServerSocketExample {
    private static final int port = 5000;

    private void createConnection() {
        try (ServerSocket serverSocket = new ServerSocket(port);) {

            startCommunicationServer(serverSocket);

        } catch (IOException e) {
            System.err.println("Can't create connection" + e.getMessage());
        }
    }

    private void startCommunicationServer(ServerSocket serverSocket) {
        try(Socket clientSocket = serverSocket.accept()) {

            ReadMessage readMessageServer = new ReadMessage(clientSocket);
            SendMessage sendMessageServer = new SendMessage(clientSocket);

            messageServiceLoopServer(readMessageServer,sendMessageServer);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void messageServiceLoopServer(ReadMessage readMessage, SendMessage sendMessage) {
        sendMessage.writer("Server start");
        while(true) {
            System.out.println(readMessage.reader());
            sendMessage.writer("Server accepted command");
        }
    }

    public static void main(String[] args) {
        new ServerSocketExample().createConnection();
    }
}

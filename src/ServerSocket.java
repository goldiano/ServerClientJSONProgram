import java.io.*;
import java.net.Socket;

class ServerSocket {
    private static final int port = 5000;

    private void createConnection() {
        try (java.net.ServerSocket serverSocket = new java.net.ServerSocket(port);) {

            startCommunicationServer(serverSocket);

        } catch (IOException e) {
            System.err.println("Can't create connection" + e.getMessage());
        }
    }

    private void startCommunicationServer(java.net.ServerSocket serverSocket) {
        try(Socket clientSocket = serverSocket.accept()) {

            ReadMessage readMessageServer = new ReadMessage(clientSocket);
            SendMessage sendMessageServer = new SendMessage(clientSocket);

            messageServiceLoopServer(readMessageServer,sendMessageServer);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void messageServiceLoopServer(ReadMessage readMessage, SendMessage sendMessage) {
        String msg;
        GsonCommand gsonCommand = new GsonCommand();

        sendMessage.writer("Server start");
        while(true) {
            msg = readMessage.reader();
            System.out.println(msg);
            sendMessage.writer(gsonCommand.chooseCommand(msg));
        }
    }

    public static void main(String[] args) {
        new ServerSocket().createConnection();
    }
}

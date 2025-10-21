import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket {

    private void getAddress() {
        final int port = 5000;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            startConnections(inetAddress, port);
        } catch (Exception e) {
            System.err.println("Can't get address " + e.getMessage());
        }
    }

    private void startConnections(InetAddress inetAddress, int port) {
        try (Socket clientSocket = new Socket(inetAddress, port)) {
            SendMessage sendMessageClient = new SendMessage(clientSocket);
            CreateMessage createMessageClient = new CreateMessage();
            ReadMessage readMessageClient = new ReadMessage(clientSocket);

            messageServiceLoopClient(createMessageClient, sendMessageClient, readMessageClient);

        } catch (Exception e) {
            System.err.println("Error in create socket " + e.getMessage());

        }
    }

    private void messageServiceLoopClient(CreateMessage createMessage, SendMessage sendMessage, ReadMessage readMessage) {

        while (true) {
            messageServiceRead(readMessage);
            messageServiceSend(sendMessage, createMessage);
        }
    }

    private void messageServiceSend(SendMessage sendMessage, CreateMessage createMessage) {
        sendMessage.writer(createMessage.createM());
    }

    private void messageServiceRead(ReadMessage readMessage) {
        System.out.println(readMessage.reader());
    }

    private void tryToReconnect() {

    }

    public static void main(String[] args) {
        new ClientSocket().getAddress();
    }
}

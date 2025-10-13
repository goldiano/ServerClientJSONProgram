import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketV2 {

    private void getAddress() {
        final int port = 5000;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            startConnections(inetAddress,port);
        } catch (Exception e) {
            System.err.println("Can't get address " + e.getMessage());
        }
    }

    private void startConnections(InetAddress inetAddress, int port) {
        try(Socket clientSocket = new Socket(inetAddress,port)) {
            SendMessage sendMessage = new SendMessage(clientSocket);
            CreateMessage createMessage = new CreateMessage();
            ReadMessage readMessage = new ReadMessage(clientSocket);

            messageService(sendMessage, readMessage);
            closeResources(sendMessage, readMessage);

        } catch (Exception e) {
            System.err.println("Error in create socket " + e.getMessage());
        }
    }

    private void messageService(SendMessage sendMessage, ReadMessage readMessage) {
        sendMessage.writer("BattleCruiser operational");
        System.out.println(readMessage.reader());
    }

    private void closeResources(SendMessage sendMessage, ReadMessage readMessage) throws IOException {
        sendMessage.close();
        readMessage.close();
    }
    public static void main(String[] args) {
        new ClientSocketV2().getAddress();
    }
}

import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ClientSocket {

    ReadMessage readMessageClient;

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
            this.readMessageClient = new ReadMessage(clientSocket);

            messageServiceLoopClient(createMessageClient, sendMessageClient);

        } catch (Exception e) {
            System.err.println("Error in create socket " + e.getMessage());

        }
    }

    private void messageServiceLoopClient(CreateMessage createMessage, SendMessage sendMessage) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new IncomingMessage());

        while (true) {
            messageServiceSend(sendMessage,createMessage);
        }
    }

    private void messageServiceSend(SendMessage sendMessage, CreateMessage createMessage) {
       sendMessage.writer(createMessage.createMessage());
    }

    private void messageServiceRead(ReadMessage readMessage) {
        System.out.println(readMessage.reader());
    }

    class IncomingMessage implements Runnable {

        @Override
        public void run() {
            while(true) {
                messageServiceRead(readMessageClient);
            }

        }
    }
    public static void main(String[] args) {
        new ClientSocket().getAddress();
    }
}

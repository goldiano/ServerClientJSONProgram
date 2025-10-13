import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketExample {
    private static final int port = 5000;

    private void startConnection(int port) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            connectionHandle(inetAddress, port);
        } catch (Exception e) {
            System.err.println("Can't get address" + e.getMessage());
        }
    }

    private void connectionHandle(InetAddress inetAddress, int port) {
        try (Socket clientSocket = new Socket(inetAddress, port)) {
       //     handleCommunication(clientSocket);
        } catch (Exception e) {
            System.err.println("Socket not work" + e.getMessage());
        }
    }

//    private void handleCommunication(Socket clientSocket) {
//        String message;
//        SendMessage sendMessage = new SendMessage(clientSocket);
//        try (PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
//             Scanner scanner = new Scanner(System.in);
//             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
//            printWriter.println("Client started");
//
//            while ((message = bufferedReader.readLine()) != null) {
//                System.out.println("Message from server \n" + message);
//                if (message.equalsIgnoreCase("goodbye")) {
//                    printWriter.println("Bye server");
//                    break;
//                }
//                printWriter.println(createMessage(scanner));
//            }
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//    }

//    private String createMessage(Scanner scanner) {
//        return scanner.nextLine();
//    }

    public static void main(String[] args) {
        new ClientSocketExample().startConnection(port);
    }

}


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ServerSocketExample {
    private static final int port = 5000;
    private void start(int port) {
        createConnection();
    }

    private void createConnection() {
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept()) {

            handleCommunication(clientSocket);

        } catch (IOException e) {
            System.err.println("Can't create connection" + e.getMessage());
        }

    }

    private void handleCommunication(Socket clientSocket) {
        try (PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in);
             BufferedReader bufferedLocalReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            printWriter.println("Server is active, wait for command");
            String message;

            while ((message = bufferedLocalReader.readLine()) != null) {
                System.out.println("Message from client: " + message);
                //printWriter.println("Server is active, wait for command");

                if (message.equalsIgnoreCase("goodbye")) {
                    printWriter.println("Bye Bye");
                    break;
                }
                printWriter.println(createMessage(scanner));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private String createMessage(Scanner scanner) {
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        new ServerSocketExample().start(port);
    }
}

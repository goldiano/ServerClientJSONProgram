import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


class SendMessage implements AutoCloseable {
    private final PrintWriter printWriter;

    SendMessage(Socket clientSocket) throws IOException {
        this.printWriter = new PrintWriter(clientSocket.getOutputStream(),true);
    }

    void writer(String message) {
        printWriter.println(message);
    }

    public void close() {
        if(printWriter != null) printWriter.close();
    }
}


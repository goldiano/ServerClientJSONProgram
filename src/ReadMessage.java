import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ReadMessage {
    private BufferedReader bufferedReader;

    ReadMessage(Socket clientSocket) throws IOException {
        this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    String reader() {
        try {
            return bufferedReader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void close() throws IOException {
        if(bufferedReader != null) {
            bufferedReader.close();
        }
    }

}

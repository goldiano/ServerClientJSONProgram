import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ReadMessage implements AutoCloseable {
    private final BufferedReader bufferedReader;

    ReadMessage(Socket clientSocket) throws IOException {
        this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    String reader() {
        GsonCommand gsonCommand = new GsonCommand();
        try {
            return gsonCommand.convertFromGson(bufferedReader.readLine());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws IOException {
        if(bufferedReader != null) bufferedReader.close();
    }
}

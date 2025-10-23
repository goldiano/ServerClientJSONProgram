import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import com.google.gson.Gson;

class ReadMessage implements AutoCloseable {
    private final BufferedReader bufferedReader;
    private final Gson gson = new Gson();

    ReadMessage(Socket clientSocket) throws IOException {
        this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    String reader() {
        try {
            ServerResponse serverResponse = gson.fromJson(bufferedReader.readLine(),ServerResponse.class);
            return serverResponse.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws IOException {
        if(bufferedReader != null) bufferedReader.close();
    }
}

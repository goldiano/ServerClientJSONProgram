import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


class SendMessage implements AutoCloseable {
    private final PrintWriter printWriter;

    SendMessage(Socket clientSocket) throws IOException {
        this.printWriter = new PrintWriter(clientSocket.getOutputStream(),true);
    }

    void writer(ServerResponse serverResponse) {
        Gson gson = new Gson();
        printWriter.println(gson.toJson(serverResponse));
        System.out.println(gson.toJson(serverResponse));
    }

    public void close() {
        if(printWriter != null) printWriter.close();
    }
}


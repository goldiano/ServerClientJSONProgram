import java.io.BufferedReader;
import java.io.InputStreamReader;


class CreateMessage implements AutoCloseable{

    private final int messageLength;
    private BufferedReader bufferedReader;

    CreateMessage() {
        this.messageLength = 255;
    }

    String createMessage() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while(true) {
                String message = bufferedReader.readLine();
                if(message.length() < messageLength) {
                    return message;
                }
                else {
                    System.out.println("This message is to long, and can't be send, please repeat");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if(bufferedReader != null) bufferedReader.close();
    }
}

import java.io.InputStreamReader;
import java.util.Arrays;

class CreateMessage implements AutoCloseable{

    private final int messageLength;
    private final InputStreamReader reader;

    CreateMessage() {
        this.messageLength = 10;
        this.reader = new InputStreamReader(System.in);
    }

    String createMessage() {
        char[] buffer = new char[messageLength];
        try {
            while(true) {
                int count = 0;
                String message;

                count = reader.read(buffer, 0, messageLength);
                message = new String(buffer, 0, count);

                if(count < messageLength) {
                    Arrays.fill(buffer,'\0');
                    return message;
                }
                else {
                    System.out.println("This message is to long, and can't be send, please repeat");
                    Arrays.fill(buffer,'\0');
                    while(reader.ready()) reader.read(); //clean buffer stream
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if(reader != null) reader.close();
    }
}

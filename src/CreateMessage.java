import java.io.IOException;
import java.util.Scanner;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class CreateMessage implements AutoCloseable{

    private final LineReader lineReader;
    private final Terminal terminal;

    private final int messageLength = 255;
    CreateMessage() {
        try {
            this.terminal = TerminalBuilder.builder()
                    .system(true)
                    .build();

            this.lineReader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    String createMessage() {
        if(lineReader == null) throw new IllegalStateException("brak LineReader");
        String input = lineReader.readLine("Wpisz wiadomosc (max 255 znakow): ");
        if(input.length() > messageLength) {
            input = input.substring(0,255);
        }
        return input;
    }
    public void close() {
        try{
            if(terminal != null) terminal.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

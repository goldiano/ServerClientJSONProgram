import java.util.Scanner;

public class CreateMessage implements AutoCloseable{

    private Scanner scanner;

    CreateMessage() {
        this.scanner = new Scanner(System.in);
    }

    String createM() {
        return scanner.nextLine();
    }

    @Override
    public void close() throws Exception {
        if(scanner != null) scanner.close();
    }
}

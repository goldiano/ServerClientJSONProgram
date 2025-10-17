public class GsonCommand {

    String chooseCommand(String lineCommand) {
        return switch (lineCommand.toLowerCase()) {
            case "help" -> """ 
                    uptime: Return server time,
                    info:   Return avaible commands,
                    stop:   Stop and close server and client.\s
                    """;
            case "info" -> """
                    version 0.1.0.
                    create date 2025.07.14.
                    """;
            default -> "ok";
        };
    }
}

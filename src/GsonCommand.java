public class GsonCommand {

    String chooseCommand(String lineCommand) {
        switch(lineCommand.toLowerCase()) {
            case "help": return """ 
                    uptime: Return server time,
                    info:   Return avaible commands,
                    stop:   Stop and close server and client.\s
                    """;
            case "info": return """
                    version 0.1.0.
                    create date 2025.07.14.
                    """;
            default: return "ok";
        }
    }
}

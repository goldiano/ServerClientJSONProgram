import java.time.Duration;
import java.time.LocalDateTime;

class ServerCommand {
    private LocalDateTime startTime;

    ServerCommand() {
        this.startTime = LocalDateTime.now();
    }

    String chooseCommand(String lineCommand) {
        switch (lineCommand.toLowerCase()) {
            case "mail":
            case "help":
                return ("""
                        uptime: Return the server time,
                        info: Return server version and create time,
                        help: List of available commands,
                        mail: check and send your mail, 
                        """);

            case "info":
                return ("""
                        version 0.2.0
                        create date 2025-07-14,
                        modify date 2025-11-04
                        """);
            case "uptime":
                Duration time = Duration.between(startTime, LocalDateTime.now());
                return String.format("Server uptime: %d hours, %d minutes, %d seconds",
                        time.toHours(), time.toMinutes(), time.toSeconds());

            default:
                return ("Unknown command");
        }
    }

}

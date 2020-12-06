package group0153.conferencesystem.exceptions.eventExceptions;

public class CommandException extends Exception {
    public CommandException(String description) {
        super("The command was unsuccessful. Reasoning: " + description);
    }
}

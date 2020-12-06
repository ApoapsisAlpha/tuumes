package group0153.conferencesystem.exceptions.eventExceptions;

public class UnsuccessfulCommandException extends Exception {
    public UnsuccessfulCommandException(String description) {
        super("The command was unsuccessful. Reasoning: " + description);
    }
}

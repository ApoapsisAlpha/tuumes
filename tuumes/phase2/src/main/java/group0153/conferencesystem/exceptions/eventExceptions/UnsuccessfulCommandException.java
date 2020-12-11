package group0153.conferencesystem.exceptions.eventExceptions;

/**
 * Exception class that is thrown when there is an issue with an event-related action
 */
public class UnsuccessfulCommandException extends Exception {

    /**
     * Instantiates an UnsuccessfulCommandException.
     * @param description the String description of the problem
     */
    public UnsuccessfulCommandException(String description) {
        super("The command was unsuccessful. Reasoning: " + description);
    }
}

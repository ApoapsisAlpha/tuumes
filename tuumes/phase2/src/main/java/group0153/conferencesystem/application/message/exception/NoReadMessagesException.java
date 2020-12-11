package group0153.conferencesystem.application.message.exception;

/**
 * An exception class that is thrown when user has no read messages.
 */

public class NoReadMessagesException extends RuntimeException{
    /**
     * Instantiates a new NoReadMessagesException
     */
    public NoReadMessagesException(){
        super("The user has no read messages.");
    }
}

package group0153.conferencesystem.application.message.exception;

/**
 * An exception class that is thrown when user has received no messages.
 */

public class NoMessagesReceivedException extends RuntimeException{
    /**
     * Instantiates a new NoMessagesReceivedException.
     */
    public NoMessagesReceivedException(){
        super("User has received no messages.");
    }
}

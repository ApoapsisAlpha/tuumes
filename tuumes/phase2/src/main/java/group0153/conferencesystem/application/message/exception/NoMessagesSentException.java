package group0153.conferencesystem.application.message.exception;
/**
 * An exception class that is thrown when no messages have been sent by user.
 */
public class NoMessagesSentException extends RuntimeException{
    /**
     * Instantiates a new NoMessagesSentException
     */
    public NoMessagesSentException(){
     super("No messages have been sent to user.");
    }
}

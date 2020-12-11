package group0153.conferencesystem.application.message.exception;

/**
 * An exception class that is thrown when user has no unread messages.
 */

public class NoUnreadMessagesException extends RuntimeException{
    /**
     * Instantiates a new NoUnreadMessagesException
     */
    public NoUnreadMessagesException(){
        super("The user has no unread messages.");
    }
}

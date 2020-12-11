package group0153.conferencesystem.application.message.exception;

/**
 * An exception class that is thrown when user has no unarchived messages.
 */

public class NoUnarchivedMessagesException extends RuntimeException{
    /**
     * Instantiates a new NoUnarchivedMessagesException
     */
    public NoUnarchivedMessagesException(){
        super("User has no unarchived messages");
    }
}

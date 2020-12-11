package group0153.conferencesystem.application.message.exception;

/**
 * An exception class that is thrown when no archived messages are found.
 */

public class NoArchivedMessagesException extends RuntimeException{
    /**
     * Instantiates a new NoArchivedMessagesException.
     */
    public NoArchivedMessagesException(){
        super("User has no archived messages");
    }
}

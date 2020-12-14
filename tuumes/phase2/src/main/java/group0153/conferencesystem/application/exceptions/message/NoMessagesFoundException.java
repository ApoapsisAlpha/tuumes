package group0153.conferencesystem.application.exceptions.message;

/**
 * An exception class that is thrown when no messages are found.
 */

public class NoMessagesFoundException extends RuntimeException{
    /**
     * Instantiates a new NoMessagesFoundException with specific message
     * @param field The type of message not found
     */
    public NoMessagesFoundException(String field){
        super("User has no " + field + "messages");
    }
}

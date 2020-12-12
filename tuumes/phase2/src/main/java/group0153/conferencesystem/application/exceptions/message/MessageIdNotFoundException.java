package group0153.conferencesystem.application.exceptions.message;

/**
 * An exception class that is thrown when message id is not found.
 */

public class MessageIdNotFoundException extends RuntimeException{
    /**
     * Instantiates a new MessageIdNotFoundException.
     */
    public MessageIdNotFoundException(){
        super("Message id does not exist");
    }
}

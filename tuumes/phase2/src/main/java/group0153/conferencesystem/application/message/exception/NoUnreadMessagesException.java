package group0153.conferencesystem.application.message.exception;

public class NoUnreadMessagesException extends RuntimeException{
    public NoUnreadMessagesException(){
        super("The user has no unread messages.");
    }
}

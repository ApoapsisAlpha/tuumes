package group0153.conferencesystem.application.message.exception;

public class NoReadMessagesException extends RuntimeException{
    public NoReadMessagesException(){
        super("The user has no read messages.");
    }
}

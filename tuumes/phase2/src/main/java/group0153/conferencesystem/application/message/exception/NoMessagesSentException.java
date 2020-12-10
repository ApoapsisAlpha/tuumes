package group0153.conferencesystem.application.message.exception;

public class NoMessagesSentException extends RuntimeException{
    public NoMessagesSentException(){
     super("No messages have been sent to user.");
    }
}

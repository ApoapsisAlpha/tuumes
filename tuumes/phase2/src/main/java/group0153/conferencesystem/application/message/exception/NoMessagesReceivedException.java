package group0153.conferencesystem.application.message.exception;

public class NoMessagesReceivedException extends RuntimeException{
    public NoMessagesReceivedException(){
        super("User has received no messages.");
    }
}

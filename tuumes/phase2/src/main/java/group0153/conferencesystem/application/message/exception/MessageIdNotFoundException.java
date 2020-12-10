package group0153.conferencesystem.application.message.exception;

public class MessageIdNotFoundException extends RuntimeException{
    public MessageIdNotFoundException(){
        super("Message id does not exist");
    }
}

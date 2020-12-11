package group0153.conferencesystem.application.message.exception;

public class NoArchivedMessagesException extends RuntimeException{
    public NoArchivedMessagesException(){
        super("User has no archived messages");
    }
}

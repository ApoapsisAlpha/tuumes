package group0153.conferencesystem.application.message.exception;

public class NoUnarchivedMessagesException extends RuntimeException{
    public NoUnarchivedMessagesException(){
        super("User has no unarchived messages");
    }
}

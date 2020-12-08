package group0153.conferencesystem.exceptions.eventExceptions;

public class UserNotFoundException extends UnsuccessfulCommandException {
    public UserNotFoundException() { super("The user could not be found."); }
}

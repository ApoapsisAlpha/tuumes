package group0153.conferencesystem.exceptions.eventExceptions;

/**
 * An Exception thrown when a user cannot be found when attempting to find their id registered in an event
 */
public class UserNotFoundException extends UnsuccessfulCommandException {
    /**
     * Constructs an instance of UserNotFoundException
     */
    public UserNotFoundException() {
        super("The user could not be found.");
    }
}

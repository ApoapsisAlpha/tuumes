package group0153.conferencesystem.application.exceptions;

/**
 * An exception thrown when a new event is attempted to be created and it overlaps with another event in the same
 * room at the same time.
 */
public class ExistingOverlappingEventException extends RuntimeException {

    /**
     * Construct an instance of ExistingOverlappingEventException.
     */
    public ExistingOverlappingEventException() {
        super("There is an existing event at this time.");
    }

}

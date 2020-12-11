package group0153.conferencesystem.exceptions.eventExceptions;

/**
 * A subclass of UnsuccessfulCommandException,thrown when a event not found.
 */
public class EventNotFoundException extends UnsuccessfulCommandException {

    /**
     * Construct an instance of EventNotFoundException, can be thrown when a event is not found.
     */
    public EventNotFoundException() {super("The event could not be found");}
}


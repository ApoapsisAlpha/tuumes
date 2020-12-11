package group0153.conferencesystem.application.exceptions;

/**
 * An exception thrown when a given event is already full
 */
public class FullEventException extends RuntimeException {

    private final String eventId;

    /**
     * Create an instance of the exception with the given event id.
     * @param eventId event id
     */
    public FullEventException(String eventId) {
        super("Event " + eventId + " is full.");
        this.eventId = eventId;
    }

    /**
     * Get the event id.
     * @return event id
     */
    public String getEventId() {
        return eventId;
    }
}

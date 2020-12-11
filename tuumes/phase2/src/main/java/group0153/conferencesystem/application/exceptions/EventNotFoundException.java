package group0153.conferencesystem.application.exceptions;

/**
 * Thrown when an event is not found.
 */
public class EventNotFoundException extends RuntimeException {
    private String eventId;

    /**
     * Construct an instance of EventNotFound using the specified String parameter.
     *
     * @param eventId the id of the Event unable to be found
     */
    public EventNotFoundException(String eventId) {
        super("Event " + eventId + " not found.");
        this.eventId = eventId;
    }

    /**
     * Gets the event id.
     *
     * @return event id.
     */
    public String getEventId() {
        return eventId;
    }
}

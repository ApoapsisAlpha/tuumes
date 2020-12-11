package group0153.conferencesystem.application.exceptions;

/**
 * An exception thrown when a non vip user tries to register a Vip-only event.
 */
public class VipOnlyEventException extends RuntimeException {

    private final String eventId;

    /**
     * Create an instance of the exception with the given event id.
     * @param eventId event id
     */
    public VipOnlyEventException(String eventId) {
        super("Event " + eventId + " is a VIP-only event.");
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

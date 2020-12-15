package group0153.conferencesystem.adapters.controllers.event.requests;

/**
 * Request class for event removal.
 */
public class EventRemoveRequest {
    private String eventId;

    /**
     * Constructs an instance of the request.
     * @param eventId the event id
     */
    public EventRemoveRequest(String eventId) {
        this.eventId = eventId;
    }

    /**
     * Get the event id.
     * @return the event id
     */
    public String getEventId() {
        return eventId;
    }
}

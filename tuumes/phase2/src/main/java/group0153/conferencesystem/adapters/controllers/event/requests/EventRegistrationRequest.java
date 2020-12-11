package group0153.conferencesystem.adapters.controllers.event.requests;

public class EventRegistrationRequest {
    private String userId;
    private String eventId;

    public EventRegistrationRequest(String userId, String eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    /**
     * Gets eventId.
     *
     * @return Value of eventId.
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Gets userId.
     *
     * @return Value of userId.
     */
    public String getUserId() {
        return userId;
    }
}

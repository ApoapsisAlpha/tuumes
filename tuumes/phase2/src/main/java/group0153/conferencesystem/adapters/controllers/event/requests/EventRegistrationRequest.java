package group0153.conferencesystem.adapters.controllers.event.requests;

/**
 * A class which facilitates the registration of a user to an event by storing the request.
 */
public class EventRegistrationRequest {
    private String userId;
    private String eventId;

    /**
     * Construct an instance of EventRegistrationRequest
     *
     * @param userId  the id of the user attempting to register
     * @param eventId the id of the event attempting to be registered to
     */
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

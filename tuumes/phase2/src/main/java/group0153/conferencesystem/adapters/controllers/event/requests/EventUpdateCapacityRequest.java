package group0153.conferencesystem.adapters.controllers.event.requests;

public class EventUpdateCapacityRequest {
    private String eventId;
    private int userLimit;

    /**
     * Gets eventId.
     *
     * @return Value of eventId.
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Gets userLimit.
     *
     * @return Value of userLimit.
     */
    public int getUserLimit() {
        return userLimit;
    }
}

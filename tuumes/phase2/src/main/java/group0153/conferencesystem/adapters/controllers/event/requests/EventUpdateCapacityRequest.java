package group0153.conferencesystem.adapters.controllers.event.requests;

/**
 * A class facilitating the user's updating of events, specifically pertaining to capacity, by storing the information
 * they input.
 */
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

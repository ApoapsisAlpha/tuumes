package group0153.conferencesystem.adapters.controllers.event.requests;

/**
 * A class facilitating the user's updating of events, specifically pertaining to capacity, by storing the information
 * they input.
 */
public class EventUpdateCapacityRequest {
    private final String eventId;
    private final int userLimit;

    /**
     * Construct an instance of EventUpdateCapacityRequest using the provided information.
     *
     * @param eventId   the String id of the event specified by the user.
     * @param userLimit the int maximum capacity of the event.
     */
    public EventUpdateCapacityRequest(String eventId, int userLimit) {
        this.eventId = eventId;
        this.userLimit = userLimit;
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
     * Gets userLimit.
     *
     * @return Value of userLimit.
     */
    public int getUserLimit() {
        return userLimit;
    }
}

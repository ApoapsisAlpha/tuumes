package group0153.conferencesystem.adapters.controllers.event.resource;

public class EventUpdateCapacityResource {
    private String eventId;
    private int userLimit;

    public EventUpdateCapacityResource(String eventId, int userLimit) {
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

package group0153.conferencesystem.adapters.controllers.event.requests;

/**
 * A class that facilitates the creation of an event as requested.
 */
public class EventCreationRequest {
    private String name;
    private String description;
    private Long startTime;
    private Long endTime;
    private String roomId;
    private int speakerLimit;
    private int userLimit;
    private boolean isVipOnlyEvent;

    /**
     * @param name           Name of event
     * @param description    Description of event
     * @param startTime      Start time of event
     * @param endTime        End time of event
     * @param roomId         Room id for the room where the event takes place
     * @param speakerLimit   Maximum number of speakers
     * @param userLimit      Maximum number of attending users
     * @param isVipOnlyEvent Boolean indicating whetehr the event is for VIP-only users.
     */
    public EventCreationRequest(String name, String description, Long startTime, Long endTime, String roomId,
                                int speakerLimit, int userLimit, boolean isVipOnlyEvent) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.speakerLimit = speakerLimit;
        this.userLimit = userLimit;
        this.isVipOnlyEvent = isVipOnlyEvent;
    }

    /**
     * Get the name of the event.
     *
     * @return the name of the event.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the description of the event.
     *
     * @return a String description of the event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the event start time.
     *
     * @return the start time of this event
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * Get the event end time.
     *
     * @return the end time of this event.
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * Get the id of the room this event takes place in.
     *
     * @return the id of the room the event takes place in.
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Get the limit of number of speakers for this event.
     *
     * @return the limit of the number of speakers for this event.
     */
    public int getSpeakerLimit() {
        return speakerLimit;
    }

    /**
     * Get the maximum number of attendees allowed in this event.
     *
     * @return the integer total number of users allowed to attend this event.
     */
    public int getUserLimit() {
        return userLimit;
    }

    /**
     * Get whether this event is a VIP-only event.
     *
     * @return a boolean indicating whether this event is a VIP-only event.
     */
    public boolean isVipOnlyEvent() {
        return isVipOnlyEvent;
    }
}

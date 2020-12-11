package group0153.conferencesystem.application.event.data;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.entities.event.Event;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A class for storing information of a event.
 */
public class EventData implements Data {
    private String id;
    private final String name;
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String roomId;
    private final int speakerLimit;
    private final int userLimit;
    private final boolean isVipOnlyEvent;
    private List<String> userIds;
    private List<String> speakerIds;

    /**
     * Construct a new instance of EventData using the specified event
     * @param event the event
     */
    public EventData(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.roomId = event.getRoomId();
        this.speakerLimit = event.getSpeakerLimit();
        this.userLimit = event.getUserLimit();
        this.isVipOnlyEvent = event.isVipOnlyEvent();
        this.userIds = event.getUserIds();
        this.speakerIds = event.getSpeakerIds();
    }

    public EventData(String name, String description, LocalDateTime startTime, LocalDateTime endTime,
                     String roomId, int speakerLimit, int userLimit, boolean isVipOnlyEvent) {
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
     * Get the event name.
     * @return name of the event data.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the event description.
     * @return description of the event data.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the start time.
     * @return start time of the event data.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Get the end time.
     * @return end time of the event data.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Get the room id.
     * @return room id
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Get the speaker limit
     * @return speaker limit
     */
    public int getSpeakerLimit() {
        return speakerLimit;
    }

    /**
     * Get the list of speaker ids of the event data.
     * @return An ArrayList of ids of speakers of the event data.
     */
    public List<String> getSpeakerIds() {
        return speakerIds;
    }

    /**
     * Get the maximum number of users allowed to attend the event of the event data.
     * @return The maximum number of users allowed to attend in the event data.
     */
    public int getUserLimit() {
        return userLimit;
    }

    /**
     * Get the list of ids of attendees of the event.
     * @return An ArrayList of ids of users who registered to the event in the event data.
     */
    public List<String> getUserIds() {
        return userIds;
    }

    /**
     * Get whether the event in event data is a VIP-only event.
     * @return true iff the event in event data is a VIP-only event.
     */
    public boolean isVipOnlyEvent() {
        return isVipOnlyEvent;
    }
}

package group0153.conferencesystem.application.event.data;

import group0153.conferencesystem.application.Data;
import group0153.conferencesystem.application.room.data.RoomData;
import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A class for storing information of a event.
 */
public class EventData implements Data {
    final private String eventName;
    final private String description;
    final private LocalDateTime startTime;
    final private LocalDateTime endTime;
    final private ArrayList<String> speakerIds;
    final private int userLimit;
    final private int userCount;
    final private ArrayList<String> userIds;
    final private boolean isVipOnlyEvent;
    final private RoomData roomData;

    /**
     * Construct a new instance of EventData with specified information.
     * @param event The event to construct EventData.
     * @param roomData The RoomData to construct EventData.
     */
    public EventData(Event event, RoomData roomData) {
        ArrayList<String> speakerIds1;
        this.eventName = event.getEventName();
        this.description = event.getDescription();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        speakerIds1 = event.getSpeakerIds();
        this.speakerIds = speakerIds1;
        this.userLimit = event.getUserLimit();
        this.userCount = event.getUserCount();
        this.userIds = event.getUserIds();
        this.isVipOnlyEvent = event.isVipOnlyEvent();
        this.roomData = roomData;
    }

    /**
     * Get the event name.
     * @return name of the event data.
     */
    public String getEventName() {
        return eventName;
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
     * Get the list of speaker ids of the event data.
     * @return An ArrayList of ids of speakers of the event data.
     */
    public ArrayList<String> getSpeakerIds() {
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
     * Get the current number of users registered to the event.
     * @return The current number of users registered to the event in the event data.
     */
    public int getUserCount() {
        return userCount;
    }

    /**
     * Get the list of ids of attendees of the event.
     * @return An ArrayList of ids of users who registered to the event in the event data.
     */
    public ArrayList<String> getUserIds() {
        return userIds;
    }

    /**
     * Get whether the event in event data is a VIP-only event.
     * @return true iff the event in event data is a VIP-only event.
     */
    public boolean isVipOnlyEvent() {
        return isVipOnlyEvent;
    }

    /**
     * Get the room data of the event.
     * @return A RoomData of the event in the event data.
     */
    public RoomData getRoomData() {
        return roomData;
    }
}

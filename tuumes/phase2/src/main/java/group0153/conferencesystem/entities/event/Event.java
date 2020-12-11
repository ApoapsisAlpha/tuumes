package group0153.conferencesystem.entities.event;

import group0153.conferencesystem.application.exceptions.UnsuccessfulCommandException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * An entity class for events.
 * Event class for an object that contains id, name, description, start time, end time, room id, list of speaker ids,
 * user limit, user count, list of user ids and whether is a Vip-only event of the event.
 */
public class Event {
    private final String id;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String roomId;
    private int speakerLimit;
    private int userLimit;
    private boolean isVipOnlyEvent;
    private List<String> userIds;
    private List<String> speakerIds;

    /**
     * Creates an Event instance.
     *
     * @param id id
     * @param name name
     * @param description description
     * @param startTime start time
     * @param endTime end time
     * @param roomId room id for the room where the event takes place
     * @param speakerLimit maximum number of speakers
     * @param userLimit maximum number of attending users
     * @param isVipOnlyEvent
     */
    public Event(String id, String name, String description, LocalDateTime startTime, LocalDateTime endTime,
                 String roomId, int speakerLimit, int userLimit, boolean isVipOnlyEvent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.speakerLimit = speakerLimit;
        this.userLimit = userLimit;
        this.isVipOnlyEvent = isVipOnlyEvent;
        userIds = new ArrayList<>();
        speakerIds = new ArrayList<>();
    }

    /**
     * Set the name of the event.
     * @param name the new name of this Event.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the description of the event.
     * @param description the new String description of this Event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set the start time of the Event.
     * @param startTime the Date start time of the Event.
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Set the end time of the Event.
     * @param endTime the Date end time of the Event.
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Set the id of the room the Event takes place in.
     * @param roomId the String id of the room the Event takes place in.
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * Add a speaker id to the list of speaker ids of the event.
     * @param speakerId The id of the speaker to be added.
     * @throws UnsuccessfulCommandException The speaker could not be successfully added.
     */
    public void addSpeakerId(String speakerId) throws UnsuccessfulCommandException {
        for (String otherSpeakerId : this.getSpeakerIds()) {
            if (otherSpeakerId.equals(speakerId)) throw new UnsuccessfulCommandException("This speaker is already registered for this event.");
        }
        this.speakerIds.add(speakerId);
    }

    /**
     * Set the user limit of the event.
     * @param userLimit the new integer total number of users allowed to attend this event.
     */
    public void setUserLimit(int userLimit) {
        this.userLimit = userLimit;
    }

    /**
     * Unregister the specified user from this Event.
     * @param userId Remove userId from this event. Nothing happens if the user was not
     *               in this event to begin with.
     */
    public void removeUserId(String userId) {
        this.userIds.remove(userId);
    }

    /**
     * Set whether the event is VIP-only.
     * @param isVipOnlyEvent whether this event is to be for VIPs only.
     */
    public void setIsVipOnlyEvent(boolean isVipOnlyEvent) {
        this.isVipOnlyEvent = isVipOnlyEvent;
    }

    // getters

    /**
     * Get the event id.
     * @return the id of this event.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the event name.
     * @return the name of this event.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the event description.
     * @return the description of this event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the event start time.
     * @return the start time of this event.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Get the event end time.
     * @return the end time of this event.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Get the event room id.
     * @return the room id of this event is to take place in.
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Get the speaker ids of the event.
     * @return ArrayList of speaker ids of this event.
     */
    public List<String> getSpeakerIds() {
        return speakerIds;
    }

    /**
     * Get the maximum number of attendees allowed in this event.
     * @return the integer total number of users allowed to attend this event.
     */
    public int getUserLimit() {
        return userLimit;
    }

    /**
     * Get the current number of attendees.
     * @return the current number of users registered to attend this event.
     */
    public int getUserCount() {
        return userIds.size();
    }

    /**
     * Get the maximum number of speakers allowed in the event.
     *
     * @return the speaker limit
     */
    public int getSpeakerLimit() {
        return speakerLimit;
    }

    /**
     * Get a list of the user ids of this event.
     * @return ArrayList containing the string ids of users attending this event.
     */
    public List<String> getUserIds() {
        return userIds;
    }

    /**
     * Get whether this event is a VIP-only event.
     * @return boolean representing whether only VIPs can attend this event.
     */
    public boolean isVipOnlyEvent() {
        return isVipOnlyEvent;
    }

    /**
     * Get the total speaker count.
     * @return speaker count
     */
    public int getSpeakerCount() {
        return speakerIds.size();
    }
}

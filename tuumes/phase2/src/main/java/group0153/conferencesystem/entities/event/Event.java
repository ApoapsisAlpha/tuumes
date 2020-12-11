package group0153.conferencesystem.entities.event;

import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * An entity class for events.
 * Event class for an object that contains id, name, description, start time, end time, room id, list of speaker ids,
 * user limit, user count, list of user ids and whether is a Vip-only event of the event.
 */
public class Event {
    private final String id;                      // id of this event.
    private String eventName;                     // name of the event.
    private String description;                   // description of the event.
    private LocalDateTime startTime;              // start time of event.
    private LocalDateTime endTime;                // end time of event.
    private String roomId;                        // room number where the event take place.
    private int speakerLimit;
    private int userLimit;                        // maximum amount of people allowed at this event.
    private boolean isVipOnlyEvent;               // whether this event is VIP-only.
    private ArrayList<String> userIds;            // list of ids of the users registered to this event.
    private ArrayList<String> speakerIds;         // the list of speaker ids of the event.

    /**
     * Constructor of event instance.
     * @param id             the id of the Event
     * @param eventName      the name of the Event
     * @param description    the description of the event
     * @param startTime      the time the Event starts
     * @param endTime        the time the Event ends
     * @param roomId         the id of the Room where the Event takes place
     * @param speakerIds     the list of speaker ids of the event.
     * @param userLimit      the total number of Users this Event can have registered
     * @param isVipOnlyEvent a boolean whether this Event is for VIPs only
     */
    public Event(String id, String eventName, String description, LocalDateTime startTime, LocalDateTime endTime,
                 String roomId, ArrayList<String> speakerIds, int userLimit, boolean isVipOnlyEvent) {
        this.id = id;
        this.eventName = eventName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.speakerIds = speakerIds;
        this.userLimit = userLimit;
        this.userIds = new ArrayList<>();
        this.isVipOnlyEvent = isVipOnlyEvent;
    }

    // setters

    /**
     * Set the name of the event.
     * @param eventName the new name of this Event.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
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
     * @param speakerId The id of the speaker to be removed.
     *                  If the speaker is not registered for this event, nothing happens.
     */
    public void removeSpeakerId(String speakerId) {
        this.speakerIds.remove(speakerId);
    }


    /**
     * Set the list of speaker ids of the event.
     * @param speakerIds the new list of speaker ids of this event.
     */
    public void setSpeakerIds(ArrayList<String> speakerIds) {
        this.speakerIds = speakerIds;
    }

    /**
     * Set the user limit of the event.
     * @param userLimit the new integer total number of users allowed to attend this event.
     */
    public void setUserLimit(int userLimit) {
        this.userLimit = userLimit;
    }

    /**
     * Increase the capacity of the event by amount.
     * @param amount: the amount to increase.
     */
    public void increaseUserCount(int amount) {
        this.userCount += amount;
    }

    /**
     * Decrease the capacity of the event by amount.
     * @param amount: the amount to decrease.
     */
    public void decreaseUserCount(int amount) {
        this.userCount -= amount;
    }

    /**
     * Set the id list of users registered to the event.
     * @param userIds the new ArrayList containing the String ids of users attending this event.
     */
    public void setUserIds(ArrayList<String> userIds) {
        this.userIds = userIds;
    }

    /**
     * Add userId to this event.
     * @param userId the id of the User registering for this Event.
     * @throws UnsuccessfulCommandException User could not be registered.
     */
    public void addUserId(String userId) throws UnsuccessfulCommandException {
        for (String otherUserId : this.getUserIds()) {
            if (userId.equals(otherUserId))
                throw new UnsuccessfulCommandException("User is already registered for this event.");
        }
        if (!this.hasSpotsLeft())
            throw new UnsuccessfulCommandException("This event's user limit has already been reached.");
        this.userIds.add(userId);
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
     * Return whether there is enough room in this Event for another User to register.
     * @return True if userCount < userLimit.
     */
    private boolean hasSpotsLeft(){
        return this.getUserCount() < this.getUserLimit();
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
    public String getEventName() {
        return eventName;
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
    public ArrayList<String> getSpeakerIds() {
        return speakerIds;
    }

    /**
     * Get the maximum number of attendees allowed of this event.
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
     * Get a list of the user ids of this event.
     * @return ArrayList containing the string ids of users attending this event.
     */
    public ArrayList<String> getUserIds() {
        return userIds;
    }

    /**
     * Get whether this event is a VIP-only event.
     * @return boolean representing whether only VIPs can attend this event.
     */
    public boolean isVipOnlyEvent() {
        return isVipOnlyEvent;
    }
}

package group0153.conferencesystem.entities.event;

import group0153.conferencesystem.exceptions.eventExceptions.UnsuccessfulCommandException;

import java.util.ArrayList;
import java.util.Date;

public abstract class Event implements Comparable<Event> {
    private final String id;                      // id of this event.
    private String eventName;                     // name of the event.
    private String description;                   // description of the event.
    private Date startTime;                       // start time of event.
    private Date endTime;                         // end time of event.
    private String roomId;                            // room number where the event take place.
    private int userLimit;                        // maximum amount of people allowed at this event.
    private int userCount;                        // number of people currently scheduled to go to this event.
    private ArrayList<String> userIds;      // list of ids of the users registered to this event.
    private boolean isVipOnlyEvent;               // whether this event is VIP-only.

    public Event(String id, String eventName, String description, Date startTime, Date endTime,
                 String roomId, int userLimit, boolean isVipOnlyEvent) {
        this.id = id;
        this.eventName = eventName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.userLimit = userLimit;
        this.userCount = 0;
        this.userIds = new ArrayList<>();
        this.isVipOnlyEvent = isVipOnlyEvent;
    }

    /**
     * set whether the event is VIP-only.
     */
    public void setIsVipOnlyEvent(boolean bool) {
        this.isVipOnlyEvent = bool;
    }

    /**
     * get the name of the event
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * set the name of the event.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * get the start time of the event.
     */
    public Date getStartTime() {
        return this.startTime;
    }

    /**
     * set the start time of the event.
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return A string representing the event type.
     * "OneSpeakerEvent"
     * "MultiSpeakerEvent"
     * "NoSpeakerEvent"
     */
    public abstract String getEventType();
    /**
     * get the end time of the event.
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * set the end time of the event.
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * get the event id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * get the maximum number of attendees allowed of this event.
     */
    public int getUserLimit() {
        return this.userLimit;
    }

    /**
     * set the user limit of the event.
     */
    public void setUserLimit(int userLimit) {
        this.userLimit = userLimit;
    }

    /**
     * get the current number of attendees.
     */
    public int getUserCount() {
        return this.userCount;
    }

    /**
     * set the number of users currently registered to the event.
     */
    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    /**
     * get a list of the userIds of this event.
     */
    public ArrayList<String> getUserIds() {
        return userIds;
    }

    /**
     * set the id list of users registered to the event.
     */
    public void setUserIds(ArrayList<String> userIds) {
        this.userIds = userIds;
    }

    /**
     * get the room id of this event.
     */
    public String getRoomId() {
        return this.roomId;
    }

    /**
     * set the room of the event.
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * get the description of this event.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * set the description of the event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get whether this event is a VIP-only event.
     */
    public boolean isVipOnlyEvent() {
        return isVipOnlyEvent;
    }

    /**
     * add userId to this event.
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
     *
     * @return True if userCount < userLimit.
     */
    public boolean hasSpotsLeft() {
        return this.getUserCount() < this.getUserLimit();
    }

    /**
     * @param userId Remove userId from this event. Nothing happens if the user was not
     *               in this event to begin with.
     * @return Returns true if userId was removed. Returns false if the userId was not found.
     */
    public boolean removeUserId(String userId) {
        this.userIds.remove(userId);
    }

    /**
     * increase the capacity of the event by amount.
     *
     * @param amount: the amount to increase.
     */
    public void increaseUserCount(int amount) {
        this.userCount += amount;
    }

    /**
     * decrease the capacity of the event by amount.
     *
     * @param amount: the amount to decrease.
     */
    public void decreaseUserCount(int amount) {
        this.userCount -= amount;
    }

    /**
     * @param speakerId The id of the speaker to be added.
     * @throws UnsuccessfulCommandException The speaker could not be successfully added.
     */
    public abstract void addSpeakerId(String speakerId) throws UnsuccessfulCommandException;

    /**
     * @param speakerId The id of the speaker to be removed.
     * @throws UnsuccessfulCommandException The speaker could not be removed.
     */
    public abstract void removeSpeakerId(String speakerId) throws UnsuccessfulCommandException;

    public abstract ArrayList<String> getSpeakerIds() throws UnsuccessfulCommandException;



    /**
     * @return return a string representation of the event.
     */
    public String toString() {
        return "(" + id + ") " + description + " | " + startTime.toString() + " - " + endTime.toString() + " | Capacity: " + userCount + "/" +
                userLimit + " spots.";
    }

    @Override
    public int compareTo(Event otherEvent) {
        if (this.startTime.before(otherEvent.startTime)) return -1;
        if (this.startTime.equals(otherEvent.startTime)) return 0;
        return 1;
    }
}

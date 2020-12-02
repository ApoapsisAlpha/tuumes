package group0153.conferencesystem.entities;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private final String id;                      // id of this event.
    private String eventName;                     // name of the event
    private String description;                   // description of the event.
    private Date startTime;                       // start time of event.
    private Date endTime;                         // end time of event.
    private Room room;                            // room number where the event take place.
    private ArrayList<String> speakerIds;         // list of ids of the speakers of this event.
    private int userLimit;                        // maximum amount of people allowed at this event.
    private int userCount;                        // number of people currently scheduled to go to this event.
    private ArrayList<String> userIds;      // list of ids of the users registered to this event.
    private boolean isVipOnlyEvent;               // whether this event is VIP-only.

    public Event(String id, String eventName, String description, Date startTime, Date endTime,
                 Room room, ArrayList<String> speakerIds, int userLimit, boolean isVipOnlyEvent) {
        this.id = id;
        this.eventName = eventName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.speakerIds = speakerIds;
        this.userLimit = userLimit;
        this.userCount = 0;
        this.userIds = new ArrayList<>();
        this.isVipOnlyEvent = isVipOnlyEvent;
    }

    /**
     * set the name of the event.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * set the description of the event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * set the start time of the event.
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * set the end time of the event.
     */
    public void setEndTime(Date endTime) { this.endTime = endTime; }

    /**
     * set the user limit of the event.
     */
    public void setUserLimit(int userLimit) {
        this.userLimit = userLimit;
    }

    /**
     * set the number of users currently registered to the event.
     */
    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    /**
     * set the id list of the speakers of the event.
     */
    public void setSpeakerIds(ArrayList<String> speakerIds) {
        this.speakerIds = speakerIds;
    }

    /**
     * set the room of the event.
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * set the id list of users registered to the event.
     */
    public void setUserIds(ArrayList<String> userIds) {
        this.userIds = userIds;
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
     * get the start time of the event.
     */
    public Date getStartTime() {
        return this.startTime;
    }

    /**
     * get the end time of the event.
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * get the event id.
     */
    public String getId() { return this.id; }

    /**
     * get the maximum number of attendees allowed of this event.
     */
    public int getUserLimit() { return this.userLimit; }

    /**
     * get the current number of attendees.
     */
    public int getUserCount() { return this.userCount; }

    /**
     * get a list of the userIds of this event.
     */
    public ArrayList<String> getUserIds() {
        return userIds;
    }

    /**
     * get a id list of the speakers of this event.
     */
    public ArrayList<String> getSpeakerIds() {
        return speakerIds;
    }

    /**
     * get the room id of this event.
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * get the description of this event.
     */
    public String getDescription() {
        return this.description;
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
    public void addUserId(String userId){
        this.userIds.add(userId);
    }

    /**
     * remove userId from this event.
     */
    public void removeUserId(String userId){
        this.userIds.remove(new String(userId));
    }

    /**
     * add speaker to this event.
     */
    public void addSpeakerId(String speakerId){
        this.speakerIds.add(speakerId);
    }

    /**
     * remove speaker from this event.
     */
    public void removeSpeakerId(String speaker){
        this.speakerIds.remove(new String(speaker));
    }

    /**
     * increase the capacity of the event by amount.
     * @param amount: the amount to increase.
     */
    public void increaseUserCount(int amount) {
        this.userCount += amount;
    }

    /**
     * decrease the capacity of the event by amount.
     * @param amount: the amount to decrease.
     */
    public void decreaseUserCount(int amount) {
        this.userCount -= amount;
    }

    /**
     * @return return a string representation of the event.
     */
    public String toString() {
        return "(" + id + ") " + description + " | " + startTime.toString() + " - " + endTime.toString() + " | Capacity: "  + userCount + "/" +
                userLimit + " spots.";
    }
}

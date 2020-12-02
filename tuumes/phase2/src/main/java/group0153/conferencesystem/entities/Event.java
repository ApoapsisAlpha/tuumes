package group0153.conferencesystem.entities;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String description; // description of the event
    private Date startTime; // start time of event
    private Date endTime; // end time of event
    private int userLimit; // maximum amount of people allowed at this event
    private int userCount; // number of people currently scheduled to go to this event
    private final String id;
    private final ArrayList<String> userIds;
    private ArrayList<String> speakerIds;
    private Room room; // room number where the event take place

    public Event(Date startTime, Date endTime, String id, int userLimit, ArrayList<String> speakerIds, Room room) {
        this.description = "No description available yet.";
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
        this.userLimit = userLimit;
        this.userCount = 0;
        this.userIds = new ArrayList<>();
        this.speakerIds = speakerIds;
        this.room = room;
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
     * add userId to this event.
     */
    public void addUserId(String userId){
        this.userIds.add(userId);
    }

    /**
     * @return return a string representation of the event.
     */
    public String toString() {
        return "(" + id + ") " + description + " | " + startTime.toString() + " - " + endTime.toString() + " | Capacity: "  + userCount + "/" +
                userLimit + " spots.";
    }
}

package group0153.conferencesystem.application;

import group0153.conferencesystem.entities.Event;
import group0153.conferencesystem.entities.MultiSpeakerEvent;
import group0153.conferencesystem.entities.NoSpeakerEvent;
import group0153.conferencesystem.entities.OneSpeakerEvent;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class for building and returning an event.
 * Use the setEventAttributes method to quickly set the details of the events.
 * Use the build method to return an instance of the event being built.
 */
public class EventBuilder {
    private String id;                      // id of this event.
    private String eventName;                     // name of the event
    private String description;                   // description of the event.
    private Date startTime;                       // start time of event.
    private Date endTime;                         // end time of event.
    private String roomId;                            // room number where the event take place.
    private int userLimit;                        // maximum amount of people allowed at this event.
    private boolean isVipOnlyEvent;               // whether this event is VIP-only.
    private ArrayList<String> speakerIds;
    private String speakerId;

    private void setId(String id) { this.id = id; }

    private void setEventName(String eventName) { this.eventName = eventName; }

    private void setDescription (String description) { this.description = description; }

    private void setStartTime(Date startTime) { this.startTime = startTime; }

    private void setEndTime(Date endTime) { this.endTime = endTime; }

    private void setRoomId(String roomId) { this.roomId = roomId; }

    private void setUserLimit(int userLimit) { this.userLimit = userLimit; }

    private void setIsVipOnlyEvent(boolean isVipOnlyEvent) { this.isVipOnlyEvent = isVipOnlyEvent; }

    private void setSpeakerIds(ArrayList<String> speakerIds) { this.speakerIds = speakerIds; }

    private void setSpeakerId(String speakerId) { this.speakerId = speakerId; }

    /**
     *
     * @param id The id to be set.
     * @param eventName The eventName to be set.
     * @param description The event description to be set.
     * @param startTime The start time of the event to be set.
     * @param endTime The end time of the event to be set.
     * @param roomId The roomId associated with this event to be set.
     * @param userLimit The user limit of this event to be set.
     * @param isVipOnlyEvent Whether this event is set as vip only or not.
     */
    public void setEventAttributes(String id, String eventName, String description, Date startTime, Date endTime,
                                    String roomId, int userLimit, boolean isVipOnlyEvent) {
        this.setId(id);
        this.setEventName(eventName);
        this.setDescription(description);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setRoomId(roomId);
        this.setUserLimit(userLimit);
        this.setIsVipOnlyEvent(isVipOnlyEvent);
    }

    /**
     *
     * @param id The id to be set.
     * @param eventName The eventName to be set.
     * @param description The event description to be set.
     * @param startTime The start time of the event to be set.
     * @param endTime The end time of the event to be set.
     * @param roomId The roomId associated with this event to be set.
     * @param userLimit The user limit of this event to be set.
     * @param isVipOnlyEvent Whether this event is set as vip only or not.
     * @param speakerIds A list of the speaker ids of this event to be set.
     */
    public void setEventAttributes(String id, String eventName, String description, Date startTime, Date endTime,
                                    String roomId, int userLimit, boolean isVipOnlyEvent, ArrayList<String> speakerIds) {
        setEventAttributes(id, eventName, description, startTime, endTime, roomId, userLimit, isVipOnlyEvent);
        this.setSpeakerIds(speakerIds);
    }
    /**
     *
     * @param id The id to be set.
     * @param eventName The eventName to be set.
     * @param description The event description to be set.
     * @param startTime The start time of the event to be set.
     * @param endTime The end time of the event to be set.
     * @param roomId The roomId associated with this event to be set.
     * @param userLimit The user limit of this event to be set.
     * @param isVipOnlyEvent Whether this event is set as vip only or not.
     * @param speakerId The speaker id of this event to be set.
     */
    public void setEventAttributes(String id, String eventName, String description, Date startTime, Date endTime,
                                    String roomId, int userLimit, boolean isVipOnlyEvent, String speakerId) {
        setEventAttributes(id, eventName, description, startTime, endTime, roomId, userLimit, isVipOnlyEvent);
        this.setSpeakerId(speakerId);
    }

    /**
     *
     * @param eventType The type of the event to be returned. The type that is entered
     *                  will be checked without case sensitivity.
     *                  Valid types currently are:
     *                  MultiSpeakerEvent
     *                  NoSpeakerEvent
     *                  OneSpeakerEvent
     * @return Returns null if the eventType given is not a valid eventType.
     * Returns the event comprising of the attributes that are currently set otherwise
     * (the variables can be set by the set methods in this instance).
     */
    public Event build(String eventType) {
        if (eventType.equalsIgnoreCase("MultiSpeakerEvent"))
            return new MultiSpeakerEvent(id, eventName, description, startTime, endTime,
                    roomId, userLimit, isVipOnlyEvent, speakerIds);
        if (eventType.equalsIgnoreCase("NoSpeakerEvent"))
            return new NoSpeakerEvent(id, eventName, description, startTime, endTime,
                    roomId, userLimit, isVipOnlyEvent);
        if (eventType.equalsIgnoreCase("OneSpeakerEvent"))
            return new OneSpeakerEvent(id, eventName, description, startTime, endTime,
                    roomId, userLimit, isVipOnlyEvent, speakerId);
        return null;
    }
}

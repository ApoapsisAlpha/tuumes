package group0153.conferencesystem.application;

import group0153.conferencesystem.entities.Event;
import group0153.conferencesystem.entities.MultiSpeakerEvent;
import group0153.conferencesystem.entities.NoSpeakerEvent;
import group0153.conferencesystem.entities.OneSpeakerEvent;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class for building and returning an event.
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


    void setId(String id) { this.id = id; }

    void setEventName(String eventName) { this.eventName = eventName; }

    void setDescription (String description) { this.description = description; }

    void setStartTime(Date startTime) { this.startTime = startTime; }

    void setEndTime(Date endTime) { this.endTime = endTime; }

    void setRoomId(String roomId) { this.roomId = roomId; }

    void setUserLimit(int userLimit) { this.userLimit = userLimit; }

    void setIsVipOnlyEvent(boolean isVipOnlyEvent) { this.isVipOnlyEvent = isVipOnlyEvent; }

    void setSpeakerIds(ArrayList<String> speakerIds) { this.speakerIds = speakerIds; }

    void setSpeakerId(String speakerId) { this.speakerId = speakerId; }

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

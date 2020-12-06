package group0153.conferencesystem.application.event;

import group0153.conferencesystem.entities.event.Event;
import group0153.conferencesystem.entities.event.MultiSpeakerEvent;
import group0153.conferencesystem.entities.event.NoSpeakerEvent;
import group0153.conferencesystem.entities.event.OneSpeakerEvent;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class for building and returning an event.
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

    public void setId(String id) {
        this.id = id;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setUserLimit(int userLimit) {
        this.userLimit = userLimit;
    }

    public void setIsVipOnlyEvent(boolean isVipOnlyEvent) {
        this.isVipOnlyEvent = isVipOnlyEvent;
    }

    public void setSpeakerIds(ArrayList<String> speakerIds) {
        this.speakerIds = speakerIds;
    }

    public void setSpeakerId(String speakerId) {
        this.speakerId = speakerId;
    }

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
